package com.ptit.ticketing.repo;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.Movie;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovieRepository {

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                movies.add(mapResultSetToMovie(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public Movie save(Movie movie) {
        String sql = "INSERT INTO movies (id, title, duration_min, rating, release_date, description, poster_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            movie.setId(UUID.randomUUID()); // Tạo ID mới
            
            pstmt.setObject(1, movie.getId());
            pstmt.setString(2, movie.getTitle());
            pstmt.setInt(3, movie.getDurationMin());
            pstmt.setString(4, movie.getRating());
            pstmt.setDate(5, Date.valueOf(movie.getReleaseDate()));
            pstmt.setString(6, movie.getDescription());
            pstmt.setString(7, movie.getPosterUrl());
            pstmt.executeUpdate();
            
            return movie;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Movie update(Movie movie) {
        String sql = "UPDATE movies SET title = ?, duration_min = ?, rating = ?, release_date = ?, description = ?, poster_url = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, movie.getTitle());
            pstmt.setInt(2, movie.getDurationMin());
            pstmt.setString(3, movie.getRating());
            pstmt.setDate(4, Date.valueOf(movie.getReleaseDate()));
            pstmt.setString(5, movie.getDescription());
            pstmt.setString(6, movie.getPosterUrl());
            pstmt.setObject(7, movie.getId());
            pstmt.executeUpdate();
            
            return movie;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteById(UUID id) {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Movie mapResultSetToMovie(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getObject("id", UUID.class));
        movie.setTitle(rs.getString("title"));
        movie.setDurationMin(rs.getInt("duration_min"));
        movie.setRating(rs.getString("rating"));
        movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
        movie.setDescription(rs.getString("description"));
        movie.setPosterUrl(rs.getString("poster_url"));
        return movie;
    }
}