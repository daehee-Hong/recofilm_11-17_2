package com.example.recofilm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDto {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private Double vote_average;
    private Long vote_count;
    private String release_date;
    private Double popularity;
}
