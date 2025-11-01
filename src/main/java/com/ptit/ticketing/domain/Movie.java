package com.ptit.ticketing.domain;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Movie entity - Maps to Django api_movie table
 */
public class Movie {
    private UUID id;
    private String title;
    private Integer durationMin;
    private String rating;
    private LocalDate releaseDate;
    private String description;
    private String posterUrl;
    private String genre;
    private String director;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(Integer durationMin) {
        this.durationMin = durationMin;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    // Convenience methods for admin panel compatibility
    public Integer getDuration() {
        return durationMin;
    }

    public void setDuration(Integer duration) {
        this.durationMin = duration;
    }

    @Override
    public String toString() {
        return title + " (" + durationMin + " min)";
    }
}
