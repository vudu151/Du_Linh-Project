package org.example.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MoviesController {
    @GetMapping("")
    public String index(){
        return "public/movies/index";
    }

    @GetMapping("/movie-detail")
    public String detail(){
        return "public/movies/movie-detail";
    }
}
