package com.mocktest.bean.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieWithCategoryResponse {
    private String categoryName;
    private List<MovieDetailResponse> movies;
}
