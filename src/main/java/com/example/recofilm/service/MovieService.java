package com.example.recofilm.service;

import com.example.recofilm.dto.MovieDetailDto;
import com.example.recofilm.dto.MovieDto;
import com.example.recofilm.openApi.*;
import com.example.recofilm.openApi.ApiRank;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final ApiRank apiRank;
    private final ApiSearch apiSearch;
    private final ApiUpComing apiUpComing;
    private final ApiTopRated apiTopRated;
    private final ApiNowPlaying apiNowPlaying;
    private final ApiMovieDetail apiMovieDetail;
    private final ApiSimilarMovie apiSimilarMovie;
    private final ApiRankToDB apiRankToDB;


    public List<MovieDto> sortPopularMovie() {
        return apiRank.movieRank();
    }

    public List<MovieDto> findByMovie(String keyword) {
        return apiSearch.movieSearch(keyword);
    }

    public List<MovieDto> sortUpcomingMovie() {
        return apiUpComing.movieUpcoming();
    }

    public List<MovieDto> sortTopRatedMovie() {
        return apiTopRated.movieTopRated();
    }

    public List<MovieDto> sortNowPlayingMovie() {
        return apiNowPlaying.movieNowPlaying();
    }

    public MovieDetailDto findByMovieDetail(int movieid) {
        return apiMovieDetail.movieDetail(movieid);
    }

    public List<MovieDto> findBySimilarMovie(int movieid) {
        return apiSimilarMovie.similarMovie(movieid);
    }

    public void movieToDB(int pageValue){
        apiRankToDB.movieRankToDB(pageValue);
    }

}
