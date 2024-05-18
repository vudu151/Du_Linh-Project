package org.example.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsController {
    @GetMapping("")
    public String index(){
        return "public/news/index";
    }

    @GetMapping("/news-detail")
    public String detail(){
        return "public/news/news-detail";
    }
}
