package com.ptit.ticketing.service;
import com.ptit.ticketing.util.Tx;

import com.ptit.ticketing.domain.Booking;
import com.ptit.ticketing.domain.Seat;
import com.ptit.ticketing.repo.BookingRepo;
import com.ptit.ticketing.repo.SeatRepo;
import com.ptit.ticketing.util.Tx;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

public class BookingService extends BaseService {
    private final BookingRepo bookingRepo;
    private final SeatRepo seatRepo;

    // ✅ Constructor nhận DataSource từ BaseService
    public BookingService(DataSource ds) {
        super(ds);
        this.bookingRepo = new BookingRepo(ds);
        this.seatRepo = new SeatRepo(ds);
    }

    public BookingService(BookingRepo bookingRepo2, SeatRepo seatRepo2) {
        super((DataSource) null);
        this.bookingRepo = bookingRepo2;
        this.seatRepo = seatRepo2;
    }

    public boolean createBooking(Booking booking, List<Seat> seats) {
        return Tx.run((java.util.concurrent.Callable<Boolean>) () -> {
            for (Seat seat : seats) {
                if (!seatRepo.isAvailable(seat.getId(), booking.getShowtimeId())) {
                    throw new RuntimeException("Seat " + seat.getId() + " already booked");
                }
            }

            booking.setStatus("PENDING");
            booking.setCreatedAt(LocalDateTime.now());
            int bookingId = bookingRepo.insert(booking);

            for (Seat seat : seats) {
                seatRepo.lockSeat(seat.getId(), bookingId);
            }

            bookingRepo.scheduleExpire(bookingId, 10);
            return true;
        });
    }

    public boolean confirmBooking(int bookingId) {
        return bookingRepo.updateStatus(bookingId, "CONFIRMED");
    }

    public boolean cancelBooking(int bookingId) {
        return bookingRepo.updateStatus(bookingId, "CANCELLED");
    }

    public List<Booking> getUserBookings(int userId) {
        return bookingRepo.findByUser(userId);
    }
}
