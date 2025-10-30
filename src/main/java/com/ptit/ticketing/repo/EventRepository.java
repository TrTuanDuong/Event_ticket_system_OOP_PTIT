package com.ptit.ticketing.repo;

import com.ptit.ticketing.config.Database; // Giả định file của A
import com.ptit.ticketing.domain.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    /**
     * Nhiệm vụ: Tải tất cả sự kiện SẮP DIỄN RA
     */
    public List<Event> findAllUpcomingEvents() {
        List<Event> events = new ArrayList<>();
        // Giả định bảng 'events'
        String sql = "SELECT * FROM events WHERE start_time > NOW() ORDER BY start_time ASC";

        // Sử dụng Singleton của Thành viên A
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
        return events;
    }

    private Event mapResultSetToEvent(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
        String location = rs.getString("location");
        
        return new Event(id, name, startTime, location);
    }
}