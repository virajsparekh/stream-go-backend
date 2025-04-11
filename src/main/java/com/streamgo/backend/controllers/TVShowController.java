package com.streamgo.backend.controllers;

import com.streamgo.backend.models.TVShow;
import com.streamgo.backend.services.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tvshows")
public class TVShowController {
    @Autowired
    private TVShowService tvShowService;

    @GetMapping
    public ResponseEntity<List<TVShow>> getAllTVShows() {
        List<TVShow> tvShows = tvShowService.getAllTVShows();
        return new ResponseEntity<>(tvShows, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TVShow>> searchTVShows(@RequestParam String title) {
        List<TVShow> tvShows = tvShowService.searchTVShows(title);
        return new ResponseEntity<>(tvShows, HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<TVShow>> getFeaturedTVShows() {
        List<TVShow> tvShows = tvShowService.getFeaturedTVShows();
        return new ResponseEntity<>(tvShows, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTVShowById(@PathVariable String id) {
        Optional<TVShow> tvShow = tvShowService.getTVShowById(id);
        if (tvShow.isPresent()) {
            return new ResponseEntity<>(tvShow.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("TV Show not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTVShow(@PathVariable String id, @RequestBody TVShow tvShowDetails) {
        try {
            TVShow updatedTVShow = tvShowService.updateTVShow(id, tvShowDetails);
            return new ResponseEntity<>(updatedTVShow, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTVShow(@PathVariable String id) {
        try {
            tvShowService.deleteTVShow(id);
            return ResponseEntity.ok("TV Show deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}