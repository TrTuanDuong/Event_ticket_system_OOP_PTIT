package com.ptit.ticketing.domain;

import java.util.UUID;

/**
 * Seat entity - Maps to Django api_seat table
 */
public class Seat {
    private UUID id;
    private UUID auditoriumId;
    private String rowLabel;
    private Integer seatNumber;
    private String seatType; // 'standard', 'vip', 'couple'

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(UUID auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getSeatLabel() {
        return rowLabel + seatNumber;
    }

    public double getPriceMultiplier() {
        return switch (seatType) {
            case "vip" -> 1.5;
            case "couple" -> 3.0;
            default -> 1.0;
        };
    }

    @Override
    public String toString() {
        return getSeatLabel() + " (" + seatType + ")";
    }
}
