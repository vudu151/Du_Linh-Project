package org.example.film.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.film.commons.cqrs.ISender;
import org.example.film.commons.jwt.JwtService;
import org.example.film.models.dtos.LoginRequest;
import org.example.film.models.dtos.RegisterDto;
import org.example.film.repositories.IRolesRepository;
import org.example.film.services.IAccountsService;
import org.example.film.services.auth.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private ISender iSender;
    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Invalid registration data.");
        }

        var result = iSender.send(registerDto);
        if(result.hasError()){
            return ResponseEntity.badRequest().body(result.getError());
        }
        return ResponseEntity.ok().body("Account registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest loginRequest,
                                        BindingResult bindingResult,
                                        HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid login data");
        }

        String token = loginService.authenticatedAndGenerateJWT(loginRequest);
        if(token!=null && !token.isEmpty()){
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
