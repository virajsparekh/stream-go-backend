package com.streamgo.backend.controllers;

import com.streamgo.backend.models.Movie;
import com.streamgo.backend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/indian")
    public ResponseEntity<List<Movie>> getIndianMovies() {
        List<Movie> movies = movieService.getIndianMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("featuredIndian")
    public ResponseEntity<List<Movie>> getFeaturedIndianMovies() {
        List<Movie> movies = movieService.getFeaturedIndianMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Movie>> getFeaturedMovies() {
        List<Movie> movies = movieService.getFeaturedMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String title) {
        List<Movie> movies = movieService.searchMovies(title);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable String id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable String id, @RequestBody Movie movieDetails) {
        try {
            Movie updatedMovie = movieService.updateMovie(id, movieDetails);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String id) {
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.ok("Movie deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}