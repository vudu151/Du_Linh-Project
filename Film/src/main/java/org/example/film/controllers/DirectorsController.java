package org.example.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/directors")
public class DirectorsController {
    @GetMapping("")
    public String index(){
        return "public/directors/index";
    }

    @GetMapping("/director-detail")
    public String detail(){
        return "public/directors/director-detail";
    }
}
