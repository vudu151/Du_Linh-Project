package org.example.film.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin-accounts")
@RequestMapping("admin-accounts")
public class AccountsController {
    @GetMapping("")
    public String index(){
        return "admin/accounts/index";
    }


}
