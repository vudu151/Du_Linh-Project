package org.example.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/actors")
public class ActorsController {
    @GetMapping("")
    public String index(){
        return "public/actors/index";
    }

    @GetMapping("/actor-detail")
    public String detail(){
        return "public/actors/actor-detail";
    }
}
