package com.ptit.ticketing.repo;

import com.ptit.ticketing.domain.Movie;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Repository for Movie entity
 * Maps to Django api_movie table
 */
public class MovieRepo extends BaseRepo {
    public MovieRepo(DataSource ds) {
        super(ds);
    }

    /**
     * Find all movies
     */
    public List<Movie> findAll(Connection c) throws SQLException {
        String sql = "SELECT id, title, duration_min, rating, release_date, description, poster_url " +
                "FROM api_movie ORDER BY title";
        try (PreparedStatement st = c.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            List<Movie> movies = new ArrayList<>();
            while (rs.next()) {
                movies.add(mapRow(rs));
            }
            return movies;
        }
    }

    /**
     * Find movie by ID
     */
    public Optional<Movie> findById(Connection c, UUID id) throws SQLException {
        String sql = "SELECT id, title, duration_min, rating, release_date, description, poster_url " +
                "FROM api_movie WHERE id = ?";
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
     * Search movies by title
     */
    public List<Movie> searchByTitle(Connection c, String keyword) throws SQLException {
        String sql = "SELECT id, title, duration_min, rating, release_date, description, poster_url " +
                "FROM api_movie WHERE title ILIKE ? ORDER BY title";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setString(1, "%" + keyword + "%");
            try (ResultSet rs = st.executeQuery()) {
                List<Movie> movies = new ArrayList<>();
                while (rs.next()) {
                    movies.add(mapRow(rs));
                }
                return movies;
            }
        }
    }

    /**
     * Insert new movie
     */
    public UUID insert(Connection c, Movie movie) throws SQLException {
        String sql = "INSERT INTO api_movie (id, title, duration_min, rating, release_date, description, poster_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        UUID id = UUID.randomUUID();
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, id);
            st.setString(2, movie.getTitle());
            st.setInt(3, movie.getDurationMin());
            st.setString(4, movie.getRating());
            st.setObject(5, movie.getReleaseDate());
            st.setString(6, movie.getDescription());
            st.setString(7, movie.getPosterUrl());
            st.executeUpdate();
        }
        return id;
    }

    /**
     * Update movie
     */
    public void update(Connection c, Movie movie) throws SQLException {
        String sql = "UPDATE api_movie SET title = ?, duration_min = ?, rating = ?, " +
                "release_date = ?, description = ?, poster_url = ? WHERE id = ?";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setString(1, movie.getTitle());
            st.setInt(2, movie.getDurationMin());
            st.setString(3, movie.getRating());
            st.setObject(4, movie.getReleaseDate());
            st.setString(5, movie.getDescription());
            st.setString(6, movie.getPosterUrl());
            st.setObject(7, movie.getId());
            st.executeUpdate();
        }
    }

    /**
     * Delete movie
     */
    public void delete(Connection c, UUID id) throws SQLException {
        String sql = "DELETE FROM api_movie WHERE id = ?";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, id);
            st.executeUpdate();
        }
    }

    private Movie mapRow(ResultSet rs) throws SQLException {
        Movie m = new Movie();
        m.setId((UUID) rs.getObject("id"));
        m.setTitle(rs.getString("title"));
        m.setDurationMin(rs.getInt("duration_min"));
        m.setRating(rs.getString("rating"));
        LocalDate releaseDate = rs.getObject("release_date", LocalDate.class);
        m.setReleaseDate(releaseDate);
        m.setDescription(rs.getString("description"));
        m.setPosterUrl(rs.getString("poster_url"));
        return m;
    }
}
