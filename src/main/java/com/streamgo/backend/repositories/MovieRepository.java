package com.streamgo.backend.repositories;

import com.streamgo.backend.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByType(String type);
    List<Movie> findByTitleContainingIgnoreCase(String title);
   List<Movie> findByMovieCountryAndFeatured(String movieCountry, boolean featured);
    List<Movie> findByMovieCountry(String movieCountry);
    List<Movie> findMovieByFeatured(boolean featured);
}