package com.ptit.ticketing.domain;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String name;
    private LocalDateTime startTime;
    private String location;

    // Constructor
    public Event(int id, String name, LocalDateTime startTime, String location) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.location = location;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public LocalDateTime getStartTime() { return startTime; }
    public String getLocation() { return location; }

    /**
     * Quan trọng: Ghi đè toString()
     * Để ComboBox (ô chọn sự kiện) hiển thị tên sự kiện đẹp mắt.
     */
    @Override
    public String toString() {
        return this.name + " (" + this.location + ")";
    }
}