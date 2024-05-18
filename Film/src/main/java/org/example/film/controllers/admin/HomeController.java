package org.example.film.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin")
@RequestMapping("/admin")
public class HomeController {
    @GetMapping("")
    public String indexAdmin(){
        return "admin/index";
    }

}
