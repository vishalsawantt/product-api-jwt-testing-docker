package com.product.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.time.*;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenHelper {
  
    @Value("${app.jwt.secret}")
    private String secretKey;

    private static final long REFRESH_TOKEN_EXPIRY = 1000 * 60 * 60 * 24 * 7;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());

        return buildToken(claims, userDetails);
    }

    private String buildToken(Map<String, Object> claims, UserDetails userDetails) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(getNextSixAM()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private long getNextSixAM() {
        LocalDate today = LocalDate.now();
        LocalDate future = today.plusDays(30);
        LocalDateTime at6 = future.atTime(6, 0);
        return at6.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRY))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isRefreshTokenExpired(String token) {
        try {
            return isTokenExpired(token);
        } catch (Exception e) {
            return true;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
