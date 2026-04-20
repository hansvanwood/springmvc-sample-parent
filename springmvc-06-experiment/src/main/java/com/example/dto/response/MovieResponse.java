package com.example.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponse {
    private Integer movieId;
    private String movieName;
    private String type;
    private Double doubanScore;
}