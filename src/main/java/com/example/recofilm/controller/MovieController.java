package com.example.recofilm.controller;

import com.example.recofilm.dto.DBform;
import com.example.recofilm.dto.MovieDetailDto;
import com.example.recofilm.dto.MovieDto;
import com.example.recofilm.dto.MovieToEntityDto;
import com.example.recofilm.entity.Movie;
import com.example.recofilm.repository.MovieRepository;
import com.example.recofilm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/detail")
    public String movieInfo(@RequestParam int id, Model model) {
        MovieDetailDto dto = movieService.findByMovieDetail(id);
        List<MovieDto> dtoList = movieService.findBySimilarMovie(id);
        model.addAttribute("similarList", dtoList);
        model.addAttribute("movieDetail", dto);
        return "detailPage/movie_info";
    }

    @GetMapping("/dbpage")
    public String dbPage(Model model){
        List<Movie> movielist = movieRepository.findAll();
        model.addAttribute("list", movielist);
        return "db/dbController";
    }

    @PostMapping("/saveDB")
    public String saveDB(DBform dBform){
        int pageValue = dBform.getPage();
        movieService.movieToDB(pageValue);
        return "redirect:/dbpage";
    }

    @PostMapping("/deleteDB")
    public String deleteDB(){
        movieRepository.deleteAll();
        return "redirect:/dbpage";
    }


}
