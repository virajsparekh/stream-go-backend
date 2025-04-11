package com.streamgo.backend.services;

import com.streamgo.backend.exceptions.ResourceNotFoundException;
import com.streamgo.backend.models.Movie;
import com.streamgo.backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {
        movie.setType("movie");
        movie.setCreatedAt(new java.util.Date());
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findByType("movie");
    }

    public List<Movie> getIndianMovies() {
        return movieRepository.findByMovieCountry("Indian");
    }

    public List<Movie> getFeaturedIndianMovies() {
        return movieRepository.findByMovieCountryAndFeatured("Indian", true);
    }

    public List<Movie> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> getFeaturedMovies() {
        return movieRepository.findMovieByFeatured(true);
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(String id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        if (movieDetails.getTitle() != null) movie.setTitle(movieDetails.getTitle());
        if (movieDetails.getSynopsis() != null) movie.setSynopsis(movieDetails.getSynopsis());
        if (movieDetails.getRentPrice() != null) movie.setRentPrice(movieDetails.getRentPrice());
        if (movieDetails.getPurchasePrice() != null) movie.setPurchasePrice(movieDetails.getPurchasePrice());
        if (movieDetails.getFeatured() != null) movie.setFeatured(movieDetails.getFeatured());

        return movieRepository.save(movie);
    }
}