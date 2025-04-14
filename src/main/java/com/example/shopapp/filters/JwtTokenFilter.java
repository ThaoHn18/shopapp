package com.example.shopapp.filters;

import com.example.shopapp.components.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${api.prefix}")
    private String apiPrefix;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
     if (isBypassToken(request)) {
         filterChain.doFilter(request, response);
     }
     final String authorizationHeader = request.getHeader("Authorization");
     if (authorizationHeader == null  && authorizationHeader.startsWith("Bearer ")) {
         final String token = authorizationHeader.substring(7);
         jwtTokenUtil.extractPhonenumber(token);
     }
    }

    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of(String.format("%s/products",apiPrefix ), "GET"),
                Pair.of(String.format("%s/categories",apiPrefix ), "GET"),
                Pair.of(String.format("%s/users/login",apiPrefix ), "POST"),
                Pair.of(String.format("%s/users/register",apiPrefix ), "POST")
        );

        for (Pair<String, String> bypassToken : bypassTokens) {
            if(request.getServletPath().contains(bypassToken.getFirst()) && request.getMethod().equals(bypassToken.getSecond())) {
                return true;
            }
        }
        return false;
    }
}
