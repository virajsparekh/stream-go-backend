package com.streamgo.backend.repositories;

import com.streamgo.backend.models.TVShow;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TVShowRepository extends MongoRepository<TVShow, String> {
    List<TVShow> findByTitleContainingIgnoreCase(String title);
    List<TVShow> findByFeatured(boolean featured);
}