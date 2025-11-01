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
        String sql = "SELECT id, title, duration_min, rating, release_date, description, poster_url, genre, director " +
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
        String sql = "SELECT id, title, duration_min, rating, release_date, description, poster_url, genre, director " +
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
        String sql = "SELECT id, title, duration_min, rating, release_date, description, poster_url, genre, director " +
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
     * Create new movie (for admin panel)
     */
    public Movie create(Connection c, Movie movie) throws SQLException {
        String sql = "INSERT INTO api_movie (id, title, duration_min, rating, release_date, description, poster_url, genre, director) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        UUID id = UUID.randomUUID();
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, id);
            st.setString(2, movie.getTitle());
            st.setInt(3, movie.getDurationMin() != null ? movie.getDurationMin() : 0);
            st.setString(4, movie.getRating());
            st.setObject(5, movie.getReleaseDate());
            st.setString(6, movie.getDescription() != null ? movie.getDescription() : "");
            st.setString(7, movie.getPosterUrl() != null ? movie.getPosterUrl() : "");
            st.setString(8, movie.getGenre());
            st.setString(9, movie.getDirector());
            st.executeUpdate();
            movie.setId(id);
        }
        return movie;
    }

    /**
     * Update movie (for admin panel)
     */
    public boolean update(Connection c, Movie movie) throws SQLException {
        String sql = "UPDATE api_movie SET title = ?, duration_min = ?, rating = ?, " +
                "release_date = ?, description = ?, poster_url = ?, genre = ?, director = ? WHERE id = ?";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setString(1, movie.getTitle());
            st.setInt(2, movie.getDurationMin() != null ? movie.getDurationMin() : 0);
            st.setString(3, movie.getRating());
            st.setObject(4, movie.getReleaseDate());
            st.setString(5, movie.getDescription() != null ? movie.getDescription() : "");
            st.setString(6, movie.getPosterUrl() != null ? movie.getPosterUrl() : "");
            st.setString(7, movie.getGenre());
            st.setString(8, movie.getDirector());
            st.setObject(9, movie.getId());
            return st.executeUpdate() > 0;
        }
    }

    /**
     * Delete movie (for admin panel)
     */
    public boolean delete(Connection c, UUID id) throws SQLException {
        // Xóa cascade: showtimes và tickets sẽ tự động xóa nhờ foreign key constraints
        String sql = "DELETE FROM api_movie WHERE id = ?";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, id);
            return st.executeUpdate() > 0;
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
        m.setGenre(rs.getString("genre"));
        m.setDirector(rs.getString("director"));
        return m;
    }
}
