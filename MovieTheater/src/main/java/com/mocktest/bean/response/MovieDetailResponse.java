package com.mocktest.bean.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.TypeMovie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDetailResponse {
    private Long id;
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
}
