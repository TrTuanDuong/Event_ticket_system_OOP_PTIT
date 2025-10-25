package com.ptit.ticketing.repo;

import java.sql.*;
import java.util.UUID;

/**
 * SeatRepo - Quản lý dữ liệu ghế ngồi trong cơ sở dữ liệu.
 */
public class SeatRepo extends BaseRepo {

    public SeatRepo(javax.sql.DataSource ds) {
        super(ds);
    }

    // Kiểm tra ghế có trống hay không
    public boolean isAvailable(UUID seatId, UUID showtimeId) throws SQLException {
        String sql = """
            SELECT COUNT(*) 
            FROM api_booking_seats bs 
            JOIN api_booking b ON b.id = bs.booking_id 
            WHERE bs.seat_id = ? 
              AND b.showtime_id = ? 
              AND b.status IN ('PENDING', 'CONFIRMED', 'PAID')
            """;

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, seatId, java.sql.Types.OTHER);
            ps.setObject(2, showtimeId, java.sql.Types.OTHER);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) == 0; // true nếu ghế trống
        }
    }

    // Đánh dấu ghế được giữ tạm thời
    public void lockSeat(int seatId, int bookingId) throws SQLException {
        String sql = "INSERT INTO api_booking_seats (booking_id, seat_id) VALUES (?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            ps.setInt(2, seatId);
            ps.executeUpdate();
        }
    }


    public void lockSeat(UUID id, int bookingId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lockSeat'");
    }
}
