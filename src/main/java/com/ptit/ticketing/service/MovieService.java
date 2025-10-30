package com.ptit.ticketing.service;

import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.repo.MovieRepository;

import java.util.List;
import java.util.UUID;

public class MovieService {
    private final MovieRepository movieRepository = new MovieRepository();

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void saveMovie(Movie movie) {
        if (movie.getId() == null) {
            movieRepository.save(movie);
        } else {
            movieRepository.update(movie);
        }
    }

    public void deleteMovie(UUID id) {
        movieRepository.deleteById(id);
    }
}