package com.example.recofilm.controller;

import com.example.recofilm.entity.Movie;
import com.example.recofilm.repository.MovieRepository;
import com.example.recofilm.service.MovieService;
import com.example.recofilm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Random;

@Controller
public class CategoryController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/now_playing")
    public String now_playing(){ return "category/now_playing"; }

    @GetMapping("/upcoming")
    public String upcoming(){ return "category/upcoming"; }

    @GetMapping("/rate")
    public String rate(){ return "category/rate"; }

    @GetMapping("/popular")
    public String popularPage(){
        return "category/popular";
    }
    //recommend
    @GetMapping("/recommend")
    public String recommend(){
        return "recommend/recommend";
    }

    @GetMapping("/recommend_success")
    public String recommendSuccess(Model model){
        Random random = new Random();
        int randomInt = random.nextInt(100);
        Long randomLong = Long.valueOf(randomInt);
        Optional<Movie> movieOptional = movieRepository.findById(randomLong);
        Movie movie = movieOptional.get();
        model.addAttribute("title", movie.getTitle());
        model.addAttribute("poster_path", movie.getPoster_path());
        model.addAttribute("movieID", movie.getMovieID());

        return "recommend/recommend_success";
    }
}
