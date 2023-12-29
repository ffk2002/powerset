package com.example.powerset.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
        }else{
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);
            //CHECK CURRENT CONTEXT AND USERNAME EDGE CASE
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails details = this.userDetailsService.loadUserByUsername(username);
                if(jwtService.isTokenValid(jwt, details)){
                    //TOKEN VALID, USER GOOD - UPDATE SECURITY CONTEXT FOR CURRENT USER
                    //CREATE TOKEN WITH DETAILS
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            details, null, details.getAuthorities()
                    );
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
            filterChain.doFilter(request, response);
        }

    }
}
