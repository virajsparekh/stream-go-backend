package com.streamgo.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;
import java.util.List;

@Document(collection = "movies")
@Data
public class Movie {
    @Id
    private String id;

    private String title;
    private String type;

    @Field("imdb_rating")
    private Double imdbRating;

    @Field("release_year")
    private Integer year;

    private List<String> genres;
    private Integer runtime;

    @Field("small_poster")
    private String smallPoster;

    @Field("large_poster")
    private String largePoster;

    @Field("production_companies")
    private List<String> productionCompanies;

    @Field("countries")
    private List<String> countries;

    @Field("languages")
    private List<String> languages;

    @Field("rent_price")
    private String rentPrice;

    @Field("purchase_price")
    private String purchasePrice;

    @Field("created_at")
    private Date createdAt;

    @Field("synopsis")
    private String synopsis;

    @Field("release_date")
    private String releaseDate;

    @Field("box_office")
    private Integer budget;

    @Field("movie_country")
    private String movieCountry;

    @Field("featured")
    private Boolean featured;

    public String getMovieCountry() {
        return movieCountry != null ? movieCountry : "Unknown";
    }
}