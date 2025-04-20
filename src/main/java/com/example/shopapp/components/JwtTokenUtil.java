package com.example.shopapp.components;

import com.example.shopapp.exceptions.InvalidParamException;
import com.example.shopapp.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.SecretKey}")
    private String SecretKey;

    public String generateToken(User user) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("phoneNumber", user.getPhoneNumber());
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getPhoneNumber())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;

        } catch (Exception e){
            throw new InvalidParamException("Cannot create token: "+ e.getMessage());


        }
    }

    private Key getSignInKey(){
        byte[] bytes = Decoders.BASE64.decode(SecretKey);
        return Keys.hmacShaKeyFor(bytes);

    }

    public Claims extractToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        try {
            final Claims claims = this.extractToken(token);
            return claimsResolver.apply(claims);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Token hết hạn nhưng vẫn lấy được claims
            System.out.println("Token expired at: " + e.getClaims().getExpiration());
            return claimsResolver.apply(e.getClaims());
        }
    }

    public boolean isTokenExpired(String token){
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public String extractPhonenumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String phoneNumber = this.extractPhonenumber(token);
        return phoneNumber.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

}
