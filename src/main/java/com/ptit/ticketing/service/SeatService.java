package com.ptit.ticketing.service;

import com.ptit.ticketing.domain.Seat;
import com.ptit.ticketing.repo.SeatRepo;
import com.ptit.ticketing.util.Tx;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Service layer cho Seat operations
 */
public class SeatService extends BaseService {

    private SeatRepo seatRepo;

    public SeatService(DataSource ds) {
        super(ds);
        this.seatRepo = new SeatRepo(ds);
    }

    /**
     * Lấy tất cả ghế của auditorium
     */
    public List<Seat> getSeatsByAuditorium(UUID auditoriumId) {
        return Tx.withTx(ds, conn -> seatRepo.findByAuditorium(conn, auditoriumId));
    }

    /**
     * Lấy ghế available cho showtime
     */
    public List<Seat> getAvailableSeats(UUID showtimeId, UUID auditoriumId) {
        return Tx.withTx(ds, conn -> seatRepo.findAvailableSeats(conn, showtimeId, auditoriumId));
    }

    /**
     * Check ghế có available không
     */
    public boolean isSeatAvailable(UUID showtimeId, UUID seatId) {
        return Tx.withTx(ds, conn -> !seatRepo.isSeatBooked(conn, showtimeId, seatId));
    }

    /**
     * Count available seats
     */
    public int countAvailableSeats(UUID showtimeId, UUID auditoriumId) {
        return Tx.withTx(ds, conn -> seatRepo.countAvailableSeats(conn, showtimeId, auditoriumId));
    }

    /**
     * Get IDs của tất cả ghế đã được book cho showtime
     * Dùng cho real-time updates
     */
    public Set<UUID> getBookedSeatIds(UUID showtimeId) {
        return Tx.withTx(ds, conn -> seatRepo.getBookedSeatIds(conn, showtimeId));
    }
}
