package org.example.film.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.film.dtos.AccountsDto;
import org.example.film.entities.Accounts;
import org.example.film.entities.Roles;
import org.example.film.repositories.IRolesRepository;
import org.example.film.services.IAccountsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountsController {
    private final IAccountsService iAccountsService;
    private final IRolesRepository iRolesRepository;

    @GetMapping("/showRegister")
    public String showRegister(Model model){
        AccountsDto accountsDto = new AccountsDto();
        model.addAttribute("accountsDto", accountsDto);
        return "fragments/public/signup";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("accountsDto") AccountsDto accountsDto,
                      BindingResult bindingResult,
                      Model model,
                      HttpSession httpSession){  //httpSession khi user reenter thi van giu nguyen thong tin cu
        String username = accountsDto.getUserName();
        if(bindingResult.hasErrors()){
            return "fragments/public/signup";
        }

        //Check if the user exists
        Accounts accountExisting = iAccountsService.findByUserName(username);
        if(accountExisting!=null){
            model.addAttribute("accountsDto", new AccountsDto());
            model.addAttribute("my_error", "Account already exists.");
            return "123";
        }

        //If the user does not exist yet => save
        Accounts accounts = new Accounts();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        accounts.setUserName(accountsDto.getUserName());
        accounts.setEmail(accountsDto.getEmail());
        accounts.setPassword(bCryptPasswordEncoder.encode(accountsDto.getPassword()));

        accounts.setAvatar("/assets/public/images/avatar.png");     //Default: Avatar
        accounts.setLevel(0);                                       //Default:  Account Free

        //Set Role default
        Roles roleDefault = iRolesRepository.findByName("ROLE_USER");
        Collection<Roles> roles = new ArrayList<>();
        roles.add(roleDefault);
        accounts.setRoles(roles);

        iAccountsService.save(accounts);

        //Notification success register
        httpSession.setAttribute("my_account", accounts);
        return "fragments/public/confirmation";
    }


}
