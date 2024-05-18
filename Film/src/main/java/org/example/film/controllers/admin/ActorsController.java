package org.example.film.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin-actors")
@RequestMapping("admin-actors")
public class ActorsController {
    @GetMapping("")
    public String actors(){
        return "admin/actors/index";
    }

    @GetMapping("/add")
    public String add(){
        return "admin/actors/add";
    }
}
