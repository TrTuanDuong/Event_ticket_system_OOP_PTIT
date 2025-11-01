package com.ptit.ticketing.service;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.repo.MovieRepo;
import com.ptit.ticketing.util.Tx;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service layer cho Movie operations
 */
public class MovieService extends BaseService {

    private MovieRepo movieRepo;

    public MovieService(DataSource ds) {
        super(ds);
        this.movieRepo = new MovieRepo(ds);
    }

    // No-arg constructor for admin panel
    public MovieService() {
        super(Database.get().ds());
        this.movieRepo = new MovieRepo(Database.get().ds());
    }

    /**
     * Lấy tất cả movies
     */
    public List<Movie> getAllMovies() {
        return Tx.withTx(ds, conn -> movieRepo.findAll(conn));
    }

    /**
     * Tìm movie theo ID
     */
    public Optional<Movie> getMovieById(UUID id) {
        return Tx.withTx(ds, conn -> movieRepo.findById(conn, id));
    }

    /**
     * Search movies theo title
     */
    public List<Movie> searchMovies(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllMovies();
        }

        String searchTerm = "%" + keyword.trim().toLowerCase() + "%";
        return Tx.withTx(ds, conn -> movieRepo.searchByTitle(conn, searchTerm));
    }

    /**
     * Filter movies theo rating
     */
    public List<Movie> getMoviesByRating(String rating) {
        return getAllMovies().stream()
                .filter(m -> m.getRating() != null && m.getRating().equalsIgnoreCase(rating))
                .collect(Collectors.toList());
    }

    /**
     * Lấy movies mới nhất (sort by release date desc)
     */
    public List<Movie> getLatestMovies(int limit) {
        return getAllMovies().stream()
                .sorted((m1, m2) -> {
                    if (m1.getReleaseDate() == null)
                        return 1;
                    if (m2.getReleaseDate() == null)
                        return -1;
                    return m2.getReleaseDate().compareTo(m1.getReleaseDate());
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Lấy movies phổ biến (có nhiều showtimes)
     * TODO: Implement với JOIN query
     */
    public List<Movie> getPopularMovies(int limit) {
        // Hiện tại trả về tất cả, sau này có thể JOIN với showtime để count
        return getAllMovies().stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Get movie statistics
     */
    public MovieStats getMovieStats(UUID movieId) {
        // TODO: Implement với queries phức tạp hơn
        Optional<Movie> movieOpt = getMovieById(movieId);
        if (movieOpt.isEmpty()) {
            return null;
        }

        Movie movie = movieOpt.get();
        MovieStats stats = new MovieStats();
        stats.movieTitle = movie.getTitle();
        stats.totalBookings = 0; // TODO: Count from bookings table
        stats.totalRevenue = 0.0; // TODO: Sum from payments table
        stats.avgRating = 0.0; // TODO: Calculate from reviews (if any)

        return stats;
    }

    /**
     * Create new movie
     */
    public boolean createMovie(Movie movie) {
        try {
            return Tx.withTx(ds, conn -> movieRepo.create(conn, movie)) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update existing movie
     */
    public boolean updateMovie(Movie movie) {
        try {
            return Tx.withTx(ds, conn -> movieRepo.update(conn, movie));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete movie by ID
     */
    public boolean deleteMovie(UUID movieId) {
        try {
            return Tx.withTx(ds, conn -> movieRepo.delete(conn, movieId));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inner class cho movie statistics
     */
    public static class MovieStats {
        public String movieTitle;
        public int totalBookings;
        public double totalRevenue;
        public double avgRating;

        @Override
        public String toString() {
            return String.format("Movie: %s | Bookings: %d | Revenue: %.2f | Rating: %.1f",
                    movieTitle, totalBookings, totalRevenue, avgRating);
        }
    }
}
