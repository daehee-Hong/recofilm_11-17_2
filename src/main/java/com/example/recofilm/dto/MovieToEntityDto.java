package com.example.recofilm.dto;

import com.example.recofilm.entity.Movie;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
@Data
public class MovieToEntityDto {
    private Long id;
    private int movieID;
    private String title;
    private String poster_path;

    @Builder
    public Movie toEntity(){
        return Movie.builder()
                .id(id)
                .movieID(movieID)
                .title(title)
                .poster_path(poster_path)
                .build();
    }
}
