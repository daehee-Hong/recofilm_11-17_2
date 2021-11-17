package com.example.recofilm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int movieID;
    @Column
    private String title;
    @Column
    private String poster_path;
    @Builder
    public Movie(Long id, int movieID, String title, String poster_path) {
        this.id = id;
        this.movieID = movieID;
        this.title = title;
        this.poster_path = poster_path;
    }
}
