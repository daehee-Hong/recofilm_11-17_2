package com.example.recofilm.controller;

import com.example.recofilm.dto.MovieDto;
import com.example.recofilm.service.MovieService;
import com.example.recofilm.userdetails.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MovieService movieService;


    @GetMapping("/")
    private String indexPage(@AuthenticationPrincipal UserCustom user, Model model) {
        List<MovieDto> popularList = movieService.sortPopularMovie();
        List<MovieDto> upComingList = movieService.sortUpcomingMovie();
        List<MovieDto> topRatedList = movieService.sortTopRatedMovie();
        List<MovieDto> nowPlayingList = movieService.sortNowPlayingMovie();
        model.addAttribute("nowPlayingList", nowPlayingList);
        model.addAttribute("topRatedList", topRatedList);
        model.addAttribute("upComingList", upComingList);
        model.addAttribute("popularList", popularList);

        if(user != null) {
            model.addAttribute("nickname", user.getNickname());
        }
        return "index";
    }
    //로고 클릭시 메인페이지로
    @GetMapping("/index")
    public String returnIndexPage(@AuthenticationPrincipal UserCustom user, Model model) {
        if(user != null) {
            model.addAttribute("nickname", user.getNickname());
        }
        return "redirect:/";
    }


    @GetMapping("/recofilm_info")
    public String info() {
        return "recofilm_info";
    }

    @GetMapping("/test")
    public String test1Page() {
        return "test";
    }

    @GetMapping("/search_result")
    private String getSearchData(@RequestParam String keyword, Model model) {
        List<MovieDto> searchResult = movieService.findByMovie(keyword);
        model.addAttribute("searchResult", searchResult);
        return "search_result";
    }
}
