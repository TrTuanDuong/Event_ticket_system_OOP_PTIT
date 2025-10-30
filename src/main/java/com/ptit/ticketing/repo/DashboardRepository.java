package com.ptit.ticketing.repo;

import com.ptit.ticketing.config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardRepository {

    public int countTotalMovies() {
        return count("SELECT COUNT(*) FROM movies");
    }

    public int countUpcomingShowtimes() {
        return count("SELECT COUNT(*) FROM showtimes WHERE start_time > NOW()");
    }

    public double getTotalRevenue() {
        String sql = "SELECT SUM(price) FROM tickets";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int count(String sql) {
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}