package com.ptit.ticketing.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Ticket entity - Maps to Django api_ticket table
 */
public class Ticket {
    private UUID id;
    private UUID bookingId;
    private UUID showtimeId;
    private UUID seatId;
    private BigDecimal price;
    private String qrCode;
    private String status; // 'reserved', 'paid', 'checked_in', 'canceled', 'refunded'
    private OffsetDateTime bookedAt;

    // Additional fields for display
    private String seatLabel; // e.g., "A1"
    private String movieTitle;
    private OffsetDateTime showtimeStart;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(UUID showtimeId) {
        this.showtimeId = showtimeId;
    }

    public UUID getSeatId() {
        return seatId;
    }

    public void setSeatId(UUID seatId) {
        this.seatId = seatId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(OffsetDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public String getSeatLabel() {
        return seatLabel;
    }

    public void setSeatLabel(String seatLabel) {
        this.seatLabel = seatLabel;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public OffsetDateTime getShowtimeStart() {
        return showtimeStart;
    }

    public void setShowtimeStart(OffsetDateTime showtimeStart) {
        this.showtimeStart = showtimeStart;
    }

    @Override
    public String toString() {
        return String.format("Ticket[id=%s, price=%.2f, status=%s, booked_at=%s]", 
            id, price, status, bookedAt);
    }
}

