package com.damian.backen.usuarios.app.usuariosapp.auth;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
        return http.authorizeHttpRequests()
        .requestMatchers(HttpMethod.GET,"/usuarios").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf(config-> config.disable())
        .sessionManagement(managet-> managet.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }
    
}
