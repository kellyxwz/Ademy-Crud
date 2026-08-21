package com.example.academy.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityFilter {

    private final JwtFilter jwt;

    public SecurityFilter(JwtFilter jwt) {
        this.jwt = jwt;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception{
        http.csrf( csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( auth ->auth
                        .requestMatchers("/api/auth/login", "/api/auth/register-aluno").permitAll()
                        .requestMatchers("/api/auth/register").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("ALUNO", "PROFESSOR")
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("PROFESSOR")
                        .anyRequest().hasRole("PROFESSOR")
                )
                .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistration (JwtFilter jwt){
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>(jwt);
        registration.setEnabled(false);

        return registration;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


}
