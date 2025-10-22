package com.ptit.ticketing.service;

import javax.sql.DataSource;

import com.ptit.ticketing.model.Report;

import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class ReportService extends BaseService {
    public ReportService(DataSource ds) {
        super(ds);
    }

    public Map<String, BigDecimal> getDailyRevenue() {
        String sql = """
            SELECT DATE(booked_at) AS day, SUM(price) AS total
            FROM api_ticket
            WHERE status = 'paid'
            GROUP BY day
            ORDER BY day DESC
        """;
        Map<String, BigDecimal> map = new LinkedHashMap<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("day"), rs.getBigDecimal("total"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public Map<String, BigDecimal> getMonthlyRevenue() {
        String sql = """
            SELECT TO_CHAR(booked_at, 'YYYY-MM') AS month, SUM(price) AS total
            FROM api_ticket
            WHERE status = 'paid'
            GROUP BY month
            ORDER BY month DESC
        """;
        Map<String, BigDecimal> map = new LinkedHashMap<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("month"), rs.getBigDecimal("total"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
    public List<Report> generateReports() {
    List<Report> list = new ArrayList<>();
    String sql = """
        SELECT m.title AS movie_name,
               s.start_time AS showtime,
               COUNT(t.id) AS tickets_sold,
               SUM(t.price) AS revenue
        FROM api_ticket t
        JOIN api_showtime s ON t.showtime_id = s.id
        JOIN api_movie m ON s.movie_id = m.id
        WHERE t.status = 'paid'
        GROUP BY m.title, s.start_time
        ORDER BY s.start_time DESC
    """;

    try (Connection c = ds.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            list.add(new Report(
                rs.getString("movie_name"),
                rs.getString("showtime"),
                rs.getInt("tickets_sold"),
                rs.getDouble("revenue")
            ));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return list;
}

    
}
