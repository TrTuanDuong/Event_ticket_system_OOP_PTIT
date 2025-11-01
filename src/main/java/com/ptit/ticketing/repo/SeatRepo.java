package com.ptit.ticketing.repo;

import com.ptit.ticketing.domain.Seat;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Repository cho Seat entity
 */
public class SeatRepo {

    private final DataSource ds;

    public SeatRepo(DataSource ds) {
        this.ds = ds;
    }

    private Seat mapRow(ResultSet rs) throws SQLException {
        Seat seat = new Seat();
        seat.setId((UUID) rs.getObject("id"));
        seat.setAuditoriumId((UUID) rs.getObject("auditorium_id"));
        seat.setRowLabel(rs.getString("row_label"));
        seat.setSeatNumber(rs.getInt("seat_number"));
        seat.setSeatType(rs.getString("seat_type"));
        return seat;
    }

    /**
     * Lấy tất cả ghế của một auditorium
     */
    public List<Seat> findByAuditorium(Connection conn, UUID auditoriumId) throws SQLException {
        String sql = "SELECT * FROM api_seat WHERE auditorium_id = ? ORDER BY row_label, seat_number";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, auditoriumId);
            ResultSet rs = stmt.executeQuery();
            List<Seat> seats = new ArrayList<>();
            while (rs.next()) {
                seats.add(mapRow(rs));
            }
            return seats;
        }
    }

    /**
     * Check ghế đã được book cho showtime chưa
     */
    public boolean isSeatBooked(Connection conn, UUID showtimeId, UUID seatId) throws SQLException {
        String sql = "SELECT EXISTS(SELECT 1 FROM api_ticket WHERE showtime_id = ? AND seat_id = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, showtimeId);
            stmt.setObject(2, seatId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            return false;
        }
    }

    /**
     * Lấy danh sách ghế available cho showtime
     */
    public List<Seat> findAvailableSeats(Connection conn, UUID showtimeId, UUID auditoriumId) throws SQLException {
        String sql = "SELECT s.* FROM api_seat s " +
                "WHERE s.auditorium_id = ? " +
                "AND NOT EXISTS (" +
                "    SELECT 1 FROM api_ticket t " +
                "    WHERE t.seat_id = s.id AND t.showtime_id = ?" +
                ") " +
                "ORDER BY s.row_label, s.seat_number";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, auditoriumId);
            stmt.setObject(2, showtimeId);
            ResultSet rs = stmt.executeQuery();
            List<Seat> seats = new ArrayList<>();
            while (rs.next()) {
                seats.add(mapRow(rs));
            }
            return seats;
        }
    }

    /**
     * Count available seats cho showtime
     */
    public int countAvailableSeats(Connection conn, UUID showtimeId, UUID auditoriumId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM api_seat s " +
                "WHERE s.auditorium_id = ? " +
                "AND NOT EXISTS (" +
                "    SELECT 1 FROM api_ticket t " +
                "    WHERE t.seat_id = s.id AND t.showtime_id = ?" +
                ")";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, auditoriumId);
            stmt.setObject(2, showtimeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    /**
     * Get IDs của tất cả ghế đã được book cho showtime
     * Dùng cho real-time seat availability updates
     */
    public Set<UUID> getBookedSeatIds(Connection conn, UUID showtimeId) throws SQLException {
        String sql = "SELECT DISTINCT seat_id FROM api_ticket WHERE showtime_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, showtimeId);
            ResultSet rs = stmt.executeQuery();

            Set<UUID> bookedSeats = new HashSet<>();
            while (rs.next()) {
                bookedSeats.add((UUID) rs.getObject("seat_id"));
            }
            return bookedSeats;
        }
    }
}
