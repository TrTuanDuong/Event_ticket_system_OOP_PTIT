package com.ptit.ticketing.repo;

import java.sql.*;
import java.time.LocalDateTime;

import javax.sql.DataSource;

public class PaymentRepo extends BaseRepo {
    protected PaymentRepo(DataSource ds) {
        super(ds);
        //TODO Auto-generated constructor stub
    }

    public boolean insert(int bookingId, double amount, String method, LocalDateTime time) throws SQLException {
        String sql = "INSERT INTO api_payment (booking_id, amount, method, paid_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            ps.setDouble(2, amount);
            ps.setString(3, method);
            ps.setTimestamp(4, Timestamp.valueOf(time));
            return ps.executeUpdate() > 0;
        }
    }
}
