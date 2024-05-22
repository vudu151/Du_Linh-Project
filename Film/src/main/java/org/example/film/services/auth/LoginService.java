package org.example.film.services.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.film.commons.jwt.JwtService;
import org.example.film.configurations.securities.CustomUserDetails;
import org.example.film.models.dtos.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public String authenticatedAndGenerateJWT(LoginRequest loginRequest){
        //Get login information from form
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        //Put the authentication info in the SecurityContextHolder, so the system knows that User is authenticated
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        //Create claims to attached to token
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userDetails.getAccount().getEmail());
        claims.put("id", userDetails.getAccount().getId());

        //Create and return token JWT
        return jwtService.generateToken(claims, Long.valueOf(15 * 60 * 1000));
    }

    private void addTokenToCookie (String token, HttpServletResponse response){
        Cookie cookie = new Cookie("JWT_TOKEN", token);
        cookie.setSecure(true);     //Cai dat bao mat
        cookie.setHttpOnly(true);   //Khong cho JavaScript truy cap vao Cookie
        cookie.setMaxAge(15 * 60);  //Thoi gian song cua Cookie = 900s
        cookie.setPath("/");        //Cookie duoc truy cap tren moi duong dan
        response.addCookie(cookie); //Them Cookie vao HTTP Response
    }
}
