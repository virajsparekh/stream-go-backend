package com.streamgo.backend.services;

import com.streamgo.backend.exceptions.ResourceNotFoundException;
import com.streamgo.backend.models.TVShow;
import com.streamgo.backend.repositories.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TVShowService {
    @Autowired
    private TVShowRepository tvShowRepository;

    public List<TVShow> getAllTVShows() {
        return tvShowRepository.findAll();
    }

    public List<TVShow> searchTVShows(String title) {
        return tvShowRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<TVShow> getFeaturedTVShows() {
        return tvShowRepository.findByFeatured(true);
    }

    public Optional<TVShow> getTVShowById(String id) {
        return tvShowRepository.findById(id);
    }

    public TVShow updateTVShow(String id, TVShow tvShowDetails) {
        TVShow tvShow = tvShowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TV Show not found"));

        if (tvShowDetails.getTitle() != null) {
            tvShow.setTitle(tvShowDetails.getTitle());
        }
        if (tvShowDetails.getSynopsis() != null) {
            tvShow.setSynopsis(tvShowDetails.getSynopsis());
        }
        if (tvShowDetails.getRentPrice() != null) {
            tvShow.setRentPrice(tvShowDetails.getRentPrice());
        }
        if (tvShowDetails.getPurchasePrice() != null) {
            tvShow.setPurchasePrice(tvShowDetails.getPurchasePrice());
        }
        if (tvShowDetails.getFeatured() != null) {
            tvShow.setFeatured(tvShowDetails.getFeatured());
        }

        return tvShowRepository.save(tvShow);
    }

    public void deleteTVShow(String id) {
        TVShow tvShow = tvShowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TV Show not found with id: " + id));
        tvShowRepository.delete(tvShow);
    }
}