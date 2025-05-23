package com.example.shopapp.config;

import com.example.shopapp.filters.JwtTokenFilter;
import com.example.shopapp.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                .csrf(AbstractHttpConfigurer::disable)
                 .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                 .authorizeHttpRequests(requests -> {
                     requests.requestMatchers(String.format("%s/users/register", apiPrefix),
                                     String.format("%s/users/login", apiPrefix))
                             .permitAll()
                     .requestMatchers(POST,String.format("%s/orders/**",apiPrefix)).hasAnyRole(Role.USER)
                     .requestMatchers(GET,String.format("%s/orders/**",apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN)
                     .requestMatchers(PUT,String.format("%s/orders/**",apiPrefix)).hasRole(Role.ADMIN)
                     .requestMatchers(DELETE,String.format("%s/orders/**",apiPrefix)).hasRole(Role.ADMIN)
                     .anyRequest().authenticated();
                 });

         return http.build();

    }
}
