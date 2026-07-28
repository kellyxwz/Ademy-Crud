package com.example.academy.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys; // Import que estava faltando!

import java.util.Date;
import java.util.function.Function;

@Component
public class TokenJwt {

    private final SecretKey key = Keys.hmacShaKeyFor(
            "minha-chave-secreta-super-segura-32bytes!!".getBytes()
    );

    private final long EXPIRATION = 1000 * 60 * 60 ;

    public String generateToken(String username, String role){
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().
                verifyWith(key).build().parseSignedClaims(token).
                getPayload();

        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, String username) {
        final String user = extractUsername(token);
        return (user.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}