package com.ptit.ticketing.repo;

import com.ptit.ticketing.config.Database; // File của Thành viên A
import com.ptit.ticketing.domain.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SeatRepository {

    /**
     * Tải toàn bộ cấu trúc ghế của 1 PHÒNG CHIẾU
     */
    public List<Seat> findSeatsByAuditoriumId(UUID auditoriumId) {
        List<Seat> seats = new ArrayList<>();
        String sql = "SELECT * FROM seats WHERE auditorium_id = ? ORDER BY row_label, seat_number";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setObject(1, auditoriumId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seats.add(mapResultSetToSeat(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    /**
     * Tải các ghế ĐÃ ĐƯỢC ĐẶT cho 1 SUẤT CHIẾU cụ thể
     * Giả định có bảng 'tickets' lưu (showtime_id, seat_id)
     */
    public Set<UUID> findBookedSeatIds(UUID showtimeId) {
        Set<UUID> bookedSeatIds = new HashSet<>();
        String sql = "SELECT seat_id FROM tickets WHERE showtime_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setObject(1, showtimeId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookedSeatIds.add(rs.getObject("seat_id", UUID.class));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedSeatIds;
    }

    // Helper để map CSDL sang Java
    private Seat mapResultSetToSeat(ResultSet rs) throws SQLException {
        Seat seat = new Seat();
        seat.setId(rs.getObject("id", UUID.class));
        seat.setAuditoriumId(rs.getObject("auditorium_id", UUID.class));
        seat.setRowLabel(rs.getString("row_label"));
        seat.setSeatNumber(rs.getInt("seat_number"));
        seat.setSeatType(rs.getString("seat_type")); // 'standard', 'vip', ...
        return seat;
    }
}