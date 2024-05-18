package org.example.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/writers")
public class WritersController {
    @GetMapping("")
    public String index(){
        return "public/writers/index";
    }

    @GetMapping("/writer-detail")
    public String detail(){
        return "public/writers/writer-detail";
    }
}
