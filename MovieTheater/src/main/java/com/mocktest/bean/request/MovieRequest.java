package com.mocktest.bean.request;

import com.fasterxml.jackson.annotation.*;
import com.mocktest.entities.Movie;
import com.mocktest.entities.TypeMovie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieRequest {
    private Long id;
    private String content;
    private String movieNameEnglish;
    private String movieNameVN;
    private String actor;
    private String director;
    private int duration;
    private String movieProductionCompany;
    private LocalDate startedDate;
    private LocalDate endDate;
    private String imageURL;
    private String version;
    private List<Long> typeMovieId;
    private List<LocalTime> startTime;
    private Long roomId;
}
