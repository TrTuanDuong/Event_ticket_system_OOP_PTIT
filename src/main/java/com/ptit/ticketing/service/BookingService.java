package com.ptit.ticketing.service;

import com.ptit.ticketing.domain.Booking;
import com.ptit.ticketing.domain.Seat;
import com.ptit.ticketing.repo.BookingRepo;
import com.ptit.ticketing.util.Tx;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service cho booking operations
 */
public class BookingService extends BaseService {

    private BookingRepo bookingRepo;

    public BookingService(DataSource ds) {
        super(ds);
        this.bookingRepo = new BookingRepo(ds);
    }

    /**
     * Tạo booking mới - Simplified cho MVP
     */
    public Booking createBooking(UUID userId, UUID showtimeId, List<Seat> seats, BigDecimal totalPrice) {
        return createBooking(userId, showtimeId, seats, totalPrice, "cash");
    }

    /**
     * Tạo booking mới với payment method
     */
    public Booking createBooking(UUID userId, UUID showtimeId, List<Seat> seats, BigDecimal totalPrice,
            String paymentMethod) {
        return Tx.withTx(ds, conn -> {
            // Tạo booking
            Booking booking = new Booking();
            booking.setId(UUID.randomUUID());
            booking.setUserId(userId);
            booking.setShowtimeId(showtimeId);
            booking.setTotalAmount(totalPrice);

            // Nếu QR payment thì pending_approval, cash thì paid luôn
            if ("qr_code".equals(paymentMethod)) {
                booking.setStatus("pending_approval");
            } else {
                booking.setStatus("paid");
            }

            booking.setPaymentMethod(paymentMethod);
            booking.setCreatedAt(OffsetDateTime.now());
            booking.setExpiresAt(OffsetDateTime.now().plusMinutes(10));

            // Insert booking
            String sql = "INSERT INTO api_booking (id, user_id, showtime_id, status, total_amount, payment_method, created_at, expires_at) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            var ps = conn.prepareStatement(sql);
            ps.setObject(1, booking.getId());
            ps.setObject(2, booking.getUserId());
            ps.setObject(3, booking.getShowtimeId());
            ps.setString(4, booking.getStatus());
            ps.setBigDecimal(5, booking.getTotalAmount());
            ps.setString(6, booking.getPaymentMethod());
            ps.setObject(7, booking.getCreatedAt());
            ps.setObject(8, booking.getExpiresAt());
            ps.executeUpdate();

            // Tạo tickets cho từng ghế với giá theo seat_type
            String ticketSql = "INSERT INTO api_ticket (id, booking_id, showtime_id, seat_id, price, booked_at, status) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            for (Seat seat : seats) {
                BigDecimal seatPrice = getSeatPrice(seat);

                var ticketPs = conn.prepareStatement(ticketSql);
                ticketPs.setObject(1, UUID.randomUUID());
                ticketPs.setObject(2, booking.getId());
                ticketPs.setObject(3, showtimeId);
                ticketPs.setObject(4, seat.getId());
                ticketPs.setBigDecimal(5, seatPrice);
                ticketPs.setObject(6, OffsetDateTime.now());
                ticketPs.setString(7, "booked");
                ticketPs.executeUpdate();
            }

            return booking;
        });
    }

    /**
     * Tính giá theo loại ghế
     */
    private BigDecimal getSeatPrice(Seat seat) {
        return switch (seat.getSeatType().toLowerCase()) {
            case "vip" -> new BigDecimal("80000");
            case "couple" -> new BigDecimal("150000");
            default -> new BigDecimal("50000"); // standard
        };
    }

    /**
     * Lấy bookings của user với thông tin movie và showtime
     */
    public List<Booking> getUserBookings(UUID userId) {
        return Tx.withTx(ds, conn -> {
            List<Booking> bookings = new ArrayList<>();
            String sql = "SELECT b.*, " +
                    "m.title as movie_title, " +
                    "s.start_time as showtime_start, " +
                    "s.end_time as showtime_end, " +
                    "a.name as auditorium_name " +
                    "FROM api_booking b " +
                    "JOIN api_showtime s ON b.showtime_id = s.id " +
                    "JOIN api_movie m ON s.movie_id = m.id " +
                    "JOIN api_auditorium a ON s.auditorium_id = a.id " +
                    "WHERE b.user_id = ? " +
                    "ORDER BY b.created_at DESC";
            var ps = conn.prepareStatement(sql);
            ps.setObject(1, userId);
            var rs = ps.executeQuery();

            while (rs.next()) {
                Booking b = new Booking();
                b.setId((UUID) rs.getObject("id"));
                b.setUserId((UUID) rs.getObject("user_id"));
                b.setShowtimeId((UUID) rs.getObject("showtime_id"));
                b.setStatus(rs.getString("status"));
                b.setTotalAmount(rs.getBigDecimal("total_amount"));
                b.setPaymentMethod(rs.getString("payment_method"));

                // Fix timezone: Convert Timestamp to OffsetDateTime with Vietnam timezone
                Timestamp createdAtTs = rs.getTimestamp("created_at");
                Timestamp expiresAtTs = rs.getTimestamp("expires_at");
                Timestamp showtimeStartTs = rs.getTimestamp("showtime_start");

                b.setCreatedAt(createdAtTs.toInstant()
                        .atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                        .toOffsetDateTime());
                b.setExpiresAt(expiresAtTs.toInstant()
                        .atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                        .toOffsetDateTime());

                // Additional fields from JOIN
                b.setMovieTitle(rs.getString("movie_title"));
                b.setShowtimeStart(showtimeStartTs.toInstant()
                        .atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                        .toOffsetDateTime());

                bookings.add(b);
            }

            return bookings;
        });
    }

    /**
     * Cập nhật trạng thái booking và payment method
     */
    public void updateBookingStatus(UUID bookingId, String status, String paymentMethod) {
        Tx.withTx(ds, conn -> {
            String sql = "UPDATE api_booking SET status = ?, payment_method = ? WHERE id = ?";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, paymentMethod);
            ps.setObject(3, bookingId);
            ps.executeUpdate();
            return null;
        });
    }

    /**
     * Lấy danh sách ghế của một booking
     */
    public String getBookingSeats(UUID bookingId) {
        return Tx.withTx(ds, conn -> {
            String sql = "SELECT s.row_label, s.seat_number " +
                    "FROM api_ticket t " +
                    "JOIN api_seat s ON t.seat_id = s.id " +
                    "WHERE t.booking_id = ? " +
                    "ORDER BY s.row_label, s.seat_number";
            var ps = conn.prepareStatement(sql);
            ps.setObject(1, bookingId);
            var rs = ps.executeQuery();

            StringBuilder seats = new StringBuilder();
            while (rs.next()) {
                if (seats.length() > 0) {
                    seats.append(", ");
                }
                seats.append(rs.getString("row_label"))
                        .append(rs.getInt("seat_number"));
            }

            return seats.toString();
        });
    }

    /**
     * Lấy tất cả bookings đang chờ phê duyệt (QR payment)
     */
    public List<Booking> getPendingApprovals() {
        return Tx.withTx(ds, conn -> {
            return bookingRepo.findPendingApproval(conn);
        });
    }

    /**
     * Admin phê duyệt booking
     */
    public boolean approveBooking(UUID bookingId) {
        return Tx.withTx(ds, conn -> {
            int updated = bookingRepo.updateStatus(conn, bookingId, "paid");
            return updated > 0;
        });
    }

    /**
     * Admin từ chối booking
     */
    public boolean rejectBooking(UUID bookingId) {
        return Tx.withTx(ds, conn -> {
            int updated = bookingRepo.updateStatus(conn, bookingId, "canceled");
            return updated > 0;
        });
    }
}
