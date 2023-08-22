package com.example.powerset.auth;

import com.example.powerset.user.DBUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DBUserDetailsService dbUserDetailsService;

    public SecurityConfig(DBUserDetailsService dbUserDetailsService) {
        this.dbUserDetailsService = dbUserDetailsService;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/register", "/login").permitAll();
                    auth.anyRequest().authenticated();
                })
                .csrf()
                .disable()
                .build();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider dAP = new DaoAuthenticationProvider();
        dAP.setPasswordEncoder(passwordEncoder());
        dAP.setUserDetailsService(this.dbUserDetailsService);
        return dAP;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
