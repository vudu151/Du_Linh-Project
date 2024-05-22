package org.example.film.controllers;

import org.example.film.models.dtos.LoginRequest;
import org.example.film.models.dtos.RegisterDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    @GetMapping("")
    public String index(){
        return "public/home/home";
    }

    @GetMapping("/register")
    public String register(Model model, @ModelAttribute("registerDto") RegisterDto registerDto){
        model.addAttribute("registerDto", registerDto);
        return "public/auth/register";
    }

    @GetMapping("/login")
    public String Login(Model model,
                        @ModelAttribute("loginRequest") LoginRequest loginRequest) {
        model.addAttribute("loginRequest", loginRequest);
        return "public/auth/login";
    }







    //============================================
    @GetMapping("/directors")
    public String directors(){
        return "public/directors/index";
    }

    @GetMapping("/directors/director-detail")
    public String director_detail(){
        return "public/directors/director-detail";
    }

    @GetMapping("/actors")
    public String actors(){
        return "public/actors/index";
    }

    @GetMapping("/actors/actor-detail")
    public String actor_detail(){
        return "public/actors/actor-detail";
    }
    @GetMapping("/writers")
    public String writers(){
        return "public/writers/index";
    }

    @GetMapping("/writers/writer-detail")
    public String writer_detail(){
        return "public/writers/writer-detail";
    }

    @GetMapping("/news")
    public String news(){
        return "public/news/index";
    }

    @GetMapping("/news/news-detail")
    public String new_detail(){
        return "public/news/news-detail";
    }

    @GetMapping("/movies")
    public String movies(){
        return "public/movies/index";
    }

    @GetMapping("/movies/movie-detail")
    public String movie_detail(){
        return "public/movies/movie-detail";
    }
}
