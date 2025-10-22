package com.ptit.ticketing.repo;

import com.ptit.ticketing.domain.Ticket;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class TicketRepo extends BaseRepo {
    public TicketRepo(DataSource ds) { super(ds); }

    public List<Ticket> findAll() throws SQLException {
        String sql = "SELECT id, booking_id, showtime_id, seat_id, price, qr_code, status, booked_at FROM api_ticket";
        List<Ticket> list = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getObject("id", java.util.UUID.class));
                t.setBookingId(rs.getObject("booking_id", java.util.UUID.class));
                t.setShowtimeId(rs.getObject("showtime_id", java.util.UUID.class));
                t.setSeatId(rs.getObject("seat_id", java.util.UUID.class));
                t.setPrice(rs.getBigDecimal("price"));
                t.setQrCode(rs.getString("qr_code"));
                t.setStatus(rs.getString("status"));
                t.setBookedAt(rs.getObject("booked_at", java.time.OffsetDateTime.class));
                list.add(t);
            }
        }
        return list;
    }

    public List<Ticket> findByBooking(UUID bookingId) throws SQLException {
        String sql = "SELECT * FROM api_ticket WHERE booking_id = ?";
        List<Ticket> list = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ticket t = new Ticket();
                    t.setId(rs.getObject("id", java.util.UUID.class));
                    t.setBookingId(rs.getObject("booking_id", java.util.UUID.class));
                    t.setShowtimeId(rs.getObject("showtime_id", java.util.UUID.class));
                    t.setSeatId(rs.getObject("seat_id", java.util.UUID.class));
                    t.setPrice(rs.getBigDecimal("price"));
                    t.setQrCode(rs.getString("qr_code"));
                    t.setStatus(rs.getString("status"));
                    t.setBookedAt(rs.getObject("booked_at", java.time.OffsetDateTime.class));
                    list.add(t);
                }
            }
        }
        return list;
    }

    public void updateStatus(UUID id, String status) throws SQLException {
        String sql = "UPDATE api_ticket SET status = ? WHERE id = ?";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setObject(2, id);
            ps.executeUpdate();
            c.commit();
        }
    }
}
