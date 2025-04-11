package com.streamgo.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;
import java.util.List;

@Document(collection = "tv_shows")
@Data
public class TVShow {
    @Id
    private String id;

    private String title;
    private String type;

    @Field("synopsis")
    private String synopsis;

    @Field("small_poster")
    private String smallPoster;

    @Field("large_poster")
    private String largePoster;

    @Field("start_year")
    private Integer startYear;

    @Field("end_year")
    private Integer endYear;

    private Integer seasons;

    private List<String> genres;
    private List<String> countries;
    private List<String> languages;

    @Field("episode_runtime")
    private Integer episodeRuntime;

    @Field("imdb_rating")
    private Double imdbRating;

    @Field("production_companies")
    private List<String> productionCompanies;

    @Field("created_at")
    private Date createdAt;

    @Field("rent_price")
    private String rentPrice;

    @Field("purchase_price")
    private String purchasePrice;

    private Boolean featured;

    public Integer getSeasons() {
        return seasons != null ? seasons : 0;
    }
}