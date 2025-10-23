package com.ptit.ticketing.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Showtime entity - Maps to Django api_showtime table
 */
public class Showtime {
    private UUID id;
    private UUID movieId;
    private UUID auditoriumId;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private BigDecimal basePrice;
    private String status; // 'scheduled', 'ongoing', 'completed', 'cancelled'

    // Additional fields for joined queries
    private String movieTitle;
    private String auditoriumName;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMovieId() {
        return movieId;
    }

    public void setMovieId(UUID movieId) {
        this.movieId = movieId;
    }

    public UUID getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(UUID auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    @Override
    public String toString() {
        return movieTitle + " - " + auditoriumName + " @ " + startTime;
    }
}
