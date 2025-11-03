package com.ptit.ticketing.repo;

import com.ptit.ticketing.domain.Showtime;
import javax.sql.DataSource;
// import java.math.BigDecimal;
import java.sql.*;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * Repository for Showtime entity
 * Maps to Django api_showtime table
 */
public class ShowtimeRepo extends BaseRepo {
    public ShowtimeRepo(DataSource ds) {
        super(ds);
    }

    /**
     * Find upcoming showtimes with movie and auditorium details
     */
    public List<Showtime> findUpcoming(Connection c) throws SQLException {
        String sql = """
                SELECT s.id, s.movie_id, s.auditorium_id, s.start_time, s.end_time,
                    s.base_price, s.status,
                    m.title as movie_title, m.duration_min,
                    a.name as auditorium_name
                FROM api_showtime s
                JOIN api_movie m ON s.movie_id = m.id
                JOIN api_auditorium a ON s.auditorium_id = a.id
                WHERE s.start_time >= NOW()
                ORDER BY s.start_time ASC
                LIMIT 50
                """;
        try (PreparedStatement st = c.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            List<Showtime> showtimes = new ArrayList<>();
            while (rs.next()) {
                showtimes.add(mapRow(rs));
            }
            return showtimes;
        }
    }

    /**
     * Find ALL showtimes (for Admin Panel) - không filter theo thời gian
     */
    public List<Showtime> findAll(Connection c) throws SQLException {
        String sql = """
                SELECT s.id, s.movie_id, s.auditorium_id, s.start_time, s.end_time,
                    s.base_price, s.status,
                    m.title as movie_title, m.duration_min,
                    a.name as auditorium_name
                FROM api_showtime s
                JOIN api_movie m ON s.movie_id = m.id
                JOIN api_auditorium a ON s.auditorium_id = a.id
                WHERE s.status != 'canceled'
                ORDER BY s.start_time DESC
                """;
        try (PreparedStatement st = c.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            List<Showtime> showtimes = new ArrayList<>();
            while (rs.next()) {
                showtimes.add(mapRow(rs));
            }
            return showtimes;
        }
    }

    /**
     * Find showtimes by movie ID
     */
    public List<Showtime> findByMovieId(Connection c, UUID movieId) throws SQLException {
        String sql = """
                SELECT s.id, s.movie_id, s.auditorium_id, s.start_time, s.end_time,
                    s.base_price, s.status,
                    m.title as movie_title, m.duration_min,
                    a.name as auditorium_name
                FROM api_showtime s
                JOIN api_movie m ON s.movie_id = m.id
                JOIN api_auditorium a ON s.auditorium_id = a.id
                WHERE s.movie_id = ?
                ORDER BY s.start_time ASC
                """;
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, movieId);
            try (ResultSet rs = st.executeQuery()) {
                List<Showtime> showtimes = new ArrayList<>();
                while (rs.next()) {
                    showtimes.add(mapRow(rs));
                }
                return showtimes;
            }
        }
    }

    /**
     * Find showtime by ID
     */
    public Optional<Showtime> findById(Connection c, UUID id) throws SQLException {
        String sql = """
                SELECT s.id, s.movie_id, s.auditorium_id, s.start_time, s.end_time,
                    s.base_price, s.status,
                    m.title as movie_title, m.duration_min,
                    a.name as auditorium_name
                FROM api_showtime s
                JOIN api_movie m ON s.movie_id = m.id
                JOIN api_auditorium a ON s.auditorium_id = a.id
                WHERE s.id = ?
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

    /**
     * Insert new showtime
     */
    public UUID insert(Connection c, Showtime showtime) throws SQLException {
        String sql = "INSERT INTO api_showtime (id, movie_id, auditorium_id, start_time, end_time, base_price, status) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        UUID id = UUID.randomUUID();
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, id);
            st.setObject(2, showtime.getMovieId());
            st.setObject(3, showtime.getAuditoriumId());
            st.setObject(4, showtime.getStartTime());
            st.setObject(5, showtime.getEndTime());
            st.setBigDecimal(6, showtime.getBasePrice());
            st.setString(7, showtime.getStatus() != null ? showtime.getStatus() : "scheduled");
            st.executeUpdate();
        }
        return id;
    }

    /**
     * Update showtime
     */
    public void update(Connection c, Showtime showtime) throws SQLException {
        String sql = "UPDATE api_showtime SET movie_id = ?, auditorium_id = ?, start_time = ?, " +
                "end_time = ?, base_price = ?, status = ? WHERE id = ?";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, showtime.getMovieId());
            st.setObject(2, showtime.getAuditoriumId());
            st.setObject(3, showtime.getStartTime());
            st.setObject(4, showtime.getEndTime());
            st.setBigDecimal(5, showtime.getBasePrice());
            st.setString(6, showtime.getStatus());
            st.setObject(7, showtime.getId());
            st.executeUpdate();
        }
    }

    /**
     * Delete showtime
     */
    public void delete(Connection c, UUID id) throws SQLException {
        String sql = "DELETE FROM api_showtime WHERE id = ?";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, id);
            st.executeUpdate();
        }
    }

    private Showtime mapRow(ResultSet rs) throws SQLException {
        Showtime s = new Showtime();
        s.setId((UUID) rs.getObject("id"));
        s.setMovieId((UUID) rs.getObject("movie_id"));
        s.setAuditoriumId((UUID) rs.getObject("auditorium_id"));
        
        // FIX TIMEZONE: Convert từ UTC sang Asia/Ho_Chi_Minh (+07:00)
        OffsetDateTime startTime = rs.getTimestamp("start_time")
            .toInstant()
            .atZone(java.time.ZoneId.of("Asia/Ho_Chi_Minh"))
            .toOffsetDateTime();
        s.setStartTime(startTime);
        
        // AUTO-CALCULATE end_time: start_time + duration_min
        int durationMin = rs.getInt("duration_min");
        OffsetDateTime endTime = startTime.plusMinutes(durationMin);
        s.setEndTime(endTime);
            
        s.setBasePrice(rs.getBigDecimal("base_price"));
        s.setStatus(rs.getString("status"));
        s.setMovieTitle(rs.getString("movie_title"));
        s.setAuditoriumName(rs.getString("auditorium_name"));
        return s;
    }
}
