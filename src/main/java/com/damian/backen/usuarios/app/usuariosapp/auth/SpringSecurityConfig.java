package com.damian.backen.usuarios.app.usuariosapp.auth;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.damian.backen.usuarios.app.usuariosapp.auth.filters.JwtAuthenticationFilter;
import com.damian.backen.usuarios.app.usuariosapp.auth.filters.JwtValidationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration autenticacionConfiguration;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return autenticacionConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
        return http.authorizeHttpRequests()
        .requestMatchers(HttpMethod.GET,"/usuarios").permitAll()
        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasAnyRole("USER","ADMIN")
        .requestMatchers(HttpMethod.POST, "/usuarios").hasRole("ADMIN")
        /* una forma 
        .requestMatchers(HttpMethod.PUT, "/usuarios/{id}").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/usuarios/{id}").hasRole("ADMIN")
        */
        // otra forma
        .requestMatchers("/usuarios/**").hasRole("ADMIN")

        .anyRequest().authenticated()
        .and()
        .addFilter(new JwtAuthenticationFilter(autenticacionConfiguration.getAuthenticationManager()))
        .addFilter(new JwtValidationFilter(autenticacionConfiguration.getAuthenticationManager()))
        .csrf(config-> config.disable())
        .sessionManagement(managet-> managet.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
        .build();
    }
    // para el front
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:4200"));
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
    @Bean
    FilterRegistrationBean<CorsFilter>corsFilter(){
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    
}
