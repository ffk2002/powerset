package com.example.powerset.auth;

import com.example.powerset.config.JWTService;
import com.example.powerset.user.User;
import com.example.powerset.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repo;
    private final JWTService service;
    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;
    public AuthResponse register(RegisterRequest req){
        var user = User.builder()
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .email(req.getEmail())
                .dob(req.getDob())
                .name(req.getName())
                .weight(req.getWeight())
                .build();
        
        repo.save(user);
        
        var jwt = service.genToken(user);
        return AuthResponse.builder().token(jwt).build();
    }

    public AuthResponse login(LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        var user = repo.findByUsername(req.getUsername()).orElseThrow();
        var jwt = service.genToken(user);
        return AuthResponse.builder().token(jwt).build();

    }
}
