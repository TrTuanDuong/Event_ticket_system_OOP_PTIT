package com.ptit.ticketing.repo;

import com.ptit.ticketing.domain.Booking;
import javax.sql.DataSource;
// import java.math.BigDecimal;
import java.sql.*;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * Repository for Booking entity
 * Maps to Django api_booking table
 */
public class BookingRepo extends BaseRepo {
    public BookingRepo(DataSource ds) {
        super(ds);
    }

    /**
     * Find all bookings with user and showtime details
     */
    public List<Booking> findAll(Connection c) throws SQLException {
        String sql = """
                SELECT b.id, b.user_id, b.showtime_id, b.status, b.total_amount,
                    b.payment_method, b.created_at, b.expires_at,
                    u.username as user_name,
                    m.title as movie_title,
                    s.start_time as showtime_start
                FROM api_booking b
                JOIN api_user u ON b.user_id = u.id
                JOIN api_showtime s ON b.showtime_id = s.id
                JOIN api_movie m ON s.movie_id = m.id
                ORDER BY b.created_at DESC
                LIMIT 100
                """;
        try (PreparedStatement st = c.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            List<Booking> bookings = new ArrayList<>();
            while (rs.next()) {
                bookings.add(mapRow(rs));
            }
            return bookings;
        }
    }

    /**
     * Find bookings by user ID
     */
    public List<Booking> findByUserId(Connection c, UUID userId) throws SQLException {
        String sql = """
                SELECT b.id, b.user_id, b.showtime_id, b.status, b.total_amount,
                    b.payment_method, b.created_at, b.expires_at,
                    u.username as user_name,
                    m.title as movie_title,
                    s.start_time as showtime_start
                FROM api_booking b
                JOIN api_user u ON b.user_id = u.id
                JOIN api_showtime s ON b.showtime_id = s.id
                JOIN api_movie m ON s.movie_id = m.id
                WHERE b.user_id = ?
                ORDER BY b.created_at DESC
                """;
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, userId);
            try (ResultSet rs = st.executeQuery()) {
                List<Booking> bookings = new ArrayList<>();
                while (rs.next()) {
                    bookings.add(mapRow(rs));
                }
                return bookings;
            }
        }
    }

    /**
     * Find expired pending bookings
     */
    public List<Booking> findExpiredPending(Connection c) throws SQLException {
        String sql = """
                SELECT b.id, b.user_id, b.showtime_id, b.status, b.total_amount,
                    b.payment_method, b.created_at, b.expires_at,
                    u.username as user_name,
                    m.title as movie_title,
                    s.start_time as showtime_start
                FROM api_booking b
                JOIN api_user u ON b.user_id = u.id
                JOIN api_showtime s ON b.showtime_id = s.id
                JOIN api_movie m ON s.movie_id = m.id
                WHERE b.status = 'pending' AND b.expires_at < NOW()
                ORDER BY b.expires_at DESC
                """;
        try (PreparedStatement st = c.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            List<Booking> bookings = new ArrayList<>();
            while (rs.next()) {
                bookings.add(mapRow(rs));
            }
            return bookings;
        }
    }

    /**
     * Cleanup expired bookings (set status to canceled)
     */
    public int cleanupExpired(Connection c) throws SQLException {
        String sql = "UPDATE api_booking SET status = 'canceled' " +
                "WHERE status = 'pending' AND expires_at < NOW()";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            return st.executeUpdate();
        }
    }

    /**
     * Find booking by ID
     */
    public Optional<Booking> findById(Connection c, UUID id) throws SQLException {
        String sql = """
                SELECT b.id, b.user_id, b.showtime_id, b.status, b.total_amount,
                    b.payment_method, b.created_at, b.expires_at,
                    u.username as user_name,
                    m.title as movie_title,
                    s.start_time as showtime_start
                FROM api_booking b
                JOIN api_user u ON b.user_id = u.id
                JOIN api_showtime s ON b.showtime_id = s.id
                JOIN api_movie m ON s.movie_id = m.id
                WHERE b.id = ?
                """;
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    private Booking mapRow(ResultSet rs) throws SQLException {
        Booking b = new Booking();
        b.setId((UUID) rs.getObject("id"));
        b.setUserId((UUID) rs.getObject("user_id"));
        b.setShowtimeId((UUID) rs.getObject("showtime_id"));
        b.setStatus(rs.getString("status"));
        b.setTotalAmount(rs.getBigDecimal("total_amount"));
        b.setPaymentMethod(rs.getString("payment_method"));
        b.setCreatedAt(rs.getObject("created_at", OffsetDateTime.class));
        b.setExpiresAt(rs.getObject("expires_at", OffsetDateTime.class));
        b.setUserName(rs.getString("user_name"));
        b.setMovieTitle(rs.getString("movie_title"));
        b.setShowtimeStart(rs.getObject("showtime_start", OffsetDateTime.class));
        return b;
    }

    public int insert(Booking booking) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    public void scheduleExpire(int bookingId, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scheduleExpire'");
    }

    public boolean updateStatus(int bookingId, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }

    public List<Booking> findByUser(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUser'");
    }
}
