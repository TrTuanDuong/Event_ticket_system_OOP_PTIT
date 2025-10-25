package com.ptit.ticketing.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Booking entity - Maps to Django api_booking table
 */
public class Booking {
    private UUID id;
    private UUID userId;
    private UUID showtimeId;
    private String status; // 'pending', 'reserved', 'paid', 'canceled'
    private BigDecimal totalAmount;
    private String paymentMethod;
    private OffsetDateTime createdAt;
    private OffsetDateTime expiresAt;

    // Additional fields for joined queries
    private String userName;
    private String movieTitle;
    private OffsetDateTime showtimeStart;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(UUID showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    // Business logic methods
    public boolean isExpired() {
        if (!"pending".equals(status))
            return false;
        return OffsetDateTime.now().isAfter(expiresAt);
    }

    public long getTimeRemainingSeconds() {
        if (!"pending".equals(status))
            return 0;
        Duration duration = Duration.between(OffsetDateTime.now(), expiresAt);
        return Math.max(0, duration.getSeconds());
    }

    @Override
    public String toString() {
        return userName + " - " + movieTitle + " (" + status + ")";
    }

    public void setCreatedAt(LocalDateTime now) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCreatedAt'");
    }

    public void setShowtimeId(int showtimeId2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setShowtimeId'");
    }
}
