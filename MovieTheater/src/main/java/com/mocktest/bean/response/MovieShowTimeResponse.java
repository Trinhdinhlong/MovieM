package com.mocktest.bean.response;

import com.mocktest.entities.Movie;
import com.mocktest.entities.Room;
import com.mocktest.entities.ShowTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieShowTimeResponse {
    private Long id;
    private String movieNameEnglish;
    private String movieNameVN;
    private String imageURL;
    private List<ShowTime> showTimes;
    private LocalDateTime createdDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long roomId;

    public MovieShowTimeResponse(Movie movie, Room room){
        id = movie.getId();
        movieNameEnglish = movie.getMovieNameEnglish();
        movieNameVN = movie.getMovieNameVN();
        imageURL = movie.getImageURL();
        showTimes = movie.getShowTimes();
        startDate = movie.getStartedDate();
        endDate = movie.getEndDate();
        roomId = room.getId();
    }
    public MovieShowTimeResponse(Movie movie){
        id = movie.getId();
        movieNameEnglish = movie.getMovieNameEnglish();
        movieNameVN = movie.getMovieNameVN();
        imageURL = movie.getImageURL();
        showTimes = movie.getShowTimes();
        startDate = movie.getStartedDate();
        endDate = movie.getEndDate();
    }
}
