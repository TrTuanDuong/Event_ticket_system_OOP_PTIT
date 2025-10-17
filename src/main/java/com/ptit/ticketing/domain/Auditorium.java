package com.ptit.ticketing.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Auditorium entity - Maps to Django api_auditorium table
 */
public class Auditorium {
    private UUID id;
    private String name;
    private Integer standardRowCount;
    private Integer vipRowCount;
    private Integer coupleRowCount;
    private Integer seatsPerRow;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStandardRowCount() {
        return standardRowCount;
    }

    public void setStandardRowCount(Integer standardRowCount) {
        this.standardRowCount = standardRowCount;
    }

    public Integer getVipRowCount() {
        return vipRowCount;
    }

    public void setVipRowCount(Integer vipRowCount) {
        this.vipRowCount = vipRowCount;
    }

    public Integer getCoupleRowCount() {
        return coupleRowCount;
    }

    public void setCoupleRowCount(Integer coupleRowCount) {
        this.coupleRowCount = coupleRowCount;
    }

    public Integer getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(Integer seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getTotalSeats() {
        int standard = standardRowCount != null ? standardRowCount * seatsPerRow : 0;
        int vip = vipRowCount != null ? vipRowCount * seatsPerRow : 0;
        int couple = coupleRowCount != null ? coupleRowCount * (seatsPerRow / 2) : 0;
        return standard + vip + couple;
    }

    @Override
    public String toString() {
        return name + " (" + getTotalSeats() + " seats)";
    }
}
