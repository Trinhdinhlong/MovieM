package com.mocktest.bean.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Movie;
import com.mocktest.entities.Room;
import com.mocktest.entities.ShowTime;
import com.mocktest.entities.TypeMovie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {
    private Long id;
    private String content;
    private String movieNameEnglish;
    private String movieNameVN;
    private String actor;
    private String director;
    private int duration;
    private String movieProductionCompany;
    private LocalDateTime createdDate;
    private LocalDate startedDate;
    private LocalDate endDate;
    private String version;
    private String imageURL;
    private Set<TypeMovie> typeMovies;
    private List<ShowTime> showTimes;
    private Room room;

    public MovieResponse(Movie movie) {
        this.id = movie.getId();
        this.content = movie.getContent();
        this.movieNameEnglish = movie.getMovieNameEnglish();
        this.movieNameVN = movie.getMovieNameVN();
        this.actor = movie.getActor();
        this.director = movie.getDirector();
        this.duration = movie.getDuration();
        this.movieProductionCompany = movie.getMovieProductionCompany();
        this.startedDate = movie.getStartedDate();
        this.endDate = movie.getEndDate();
        this.imageURL = movie.getImageURL();
        this.typeMovies = movie.getTypeMovies();
        this.showTimes = movie.getShowTimes();
        this.version = movie.getVersion();
    }
}
