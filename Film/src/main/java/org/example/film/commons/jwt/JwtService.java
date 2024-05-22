package org.example.film.commons.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationTime}")
    private Long expirationTime;

    //Create JWT token to UserDetails
    public String generateToken(Map<String, Object> claims, Long expirationTimeInMilliseconds) {
        return doGenerateToken(claims, expirationTimeInMilliseconds);
    }

    //Create JWT token to username
    private String doGenerateToken(Map<String, Object> claims, Long expirationTimeInMilliseconds) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDateTime = now.plusSeconds(expirationTimeInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant())) // Chuyển đổi LocalDateTime thành Date
                .setExpiration(Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant()))  // Chuyển đổi LocalDateTime thành Date
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    //Get info to JWT Token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //Get username to JWT token
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //Check if JWT token is expired or not
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    }

    //Get Date Expired to JWT token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Check Jwt token authentication
    public Boolean validateToken(String token, UserDetails userDetails){
        final  String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
