package com.ptit.ticketing.service;

import com.ptit.ticketing.domain.Showtime;
import com.ptit.ticketing.repo.ShowtimeRepo;
import com.ptit.ticketing.util.Tx;

import javax.sql.DataSource;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service layer cho Showtime operations
 */
public class ShowtimeService extends BaseService {

    private ShowtimeRepo showtimeRepo;

    public ShowtimeService(DataSource ds) {
        super(ds);
        this.showtimeRepo = new ShowtimeRepo(ds);
    }

    /**
     * Lấy tất cả showtimes sắp tới
     */
    public List<Showtime> getUpcomingShowtimes() {
        return Tx.withTx(ds, conn -> showtimeRepo.findUpcoming(conn));
    }

    /**
     * Lấy showtimes của một movie cụ thể
     */
    public List<Showtime> getShowtimesByMovie(UUID movieId) {
        return getUpcomingShowtimes().stream()
                .filter(s -> s.getMovieId().equals(movieId))
                .sorted((s1, s2) -> s1.getStartTime().compareTo(s2.getStartTime()))
                .collect(Collectors.toList());
    }

    /**
     * Lấy showtimes theo ngày
     */
    public List<Showtime> getShowtimesByDate(UUID movieId, OffsetDateTime date) {
        return getShowtimesByMovie(movieId).stream()
                .filter(s -> isSameDay(s.getStartTime(), date))
                .collect(Collectors.toList());
    }

    /**
     * Lấy showtime theo ID
     */
    public Showtime getShowtimeById(UUID showtimeId) {
        return getUpcomingShowtimes().stream()
                .filter(s -> s.getId().equals(showtimeId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Lấy các ngày có showtime cho movie
     */
    public List<OffsetDateTime> getAvailableDates(UUID movieId) {
        return getShowtimesByMovie(movieId).stream()
                .map(Showtime::getStartTime)
                .map(this::startOfDay)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Check xem showtime có còn ghế trống không
     */
    public boolean hasAvailableSeats(UUID showtimeId) {
        // TODO: Query từ seat table để check
        // Hiện tại return true để test
        return true;
    }

    /**
     * Đếm số ghế còn trống
     */
    public int getAvailableSeatsCount(UUID showtimeId) {
        // TODO: COUNT seats WHERE showtime_id = ? AND NOT EXISTS (SELECT 1 FROM tickets
        // WHERE ...)
        return 100; // Mock data
    }

    /**
     * Tạo showtime mới
     */
    public UUID createShowtime(Showtime showtime) {
        return Tx.withTx(ds, conn -> {
            return showtimeRepo.insert(conn, showtime);
        });
    }

    /**
     * Cập nhật showtime
     */
    public void updateShowtime(Showtime showtime) {
        Tx.withTx(ds, conn -> {
            showtimeRepo.update(conn, showtime);
            return null;
        });
    }

    /**
     * Xóa showtime
     */
    public void deleteShowtime(UUID showtimeId) {
        Tx.withTx(ds, conn -> {
            showtimeRepo.delete(conn, showtimeId);
            return null;
        });
    }

    /**
     * Lấy tất cả movies để chọn khi tạo showtime
     */
    public List<com.ptit.ticketing.domain.Movie> getAllMovies() {
        return Tx.withTx(ds, conn -> {
            String sql = "SELECT * FROM api_movie ORDER BY title";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();

            List<com.ptit.ticketing.domain.Movie> movies = new ArrayList<>();
            while (rs.next()) {
                com.ptit.ticketing.domain.Movie m = new com.ptit.ticketing.domain.Movie();
                m.setId((UUID) rs.getObject("id"));
                m.setTitle(rs.getString("title"));
                movies.add(m);
            }
            return movies;
        });
    }

    /**
     * Lấy tất cả auditoriums để chọn khi tạo showtime
     */
    public List<com.ptit.ticketing.domain.Auditorium> getAllAuditoriums() {
        return Tx.withTx(ds, conn -> {
            String sql = "SELECT * FROM api_auditorium ORDER BY name";
            var ps = conn.prepareStatement(sql);
            var rs = ps.executeQuery();

            List<com.ptit.ticketing.domain.Auditorium> auditoriums = new ArrayList<>();
            while (rs.next()) {
                com.ptit.ticketing.domain.Auditorium a = new com.ptit.ticketing.domain.Auditorium();
                a.setId((UUID) rs.getObject("id"));
                a.setName(rs.getString("name"));
                auditoriums.add(a);
            }
            return auditoriums;
        });
    }

    private boolean isSameDay(OffsetDateTime dt1, OffsetDateTime dt2) {
        return dt1.getYear() == dt2.getYear() &&
                dt1.getMonthValue() == dt2.getMonthValue() &&
                dt1.getDayOfMonth() == dt2.getDayOfMonth();
    }

    private OffsetDateTime startOfDay(OffsetDateTime dt) {
        return dt.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }
}
