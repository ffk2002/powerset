package com.example.powerset.user;

import com.example.powerset.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8081/")
public class UserRegistrationController {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationController(UserRepository repo, PasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerUser(@RequestBody PowersetUserDTO PUDTO){
        PowersetUser u = PowersetUser.builder()
                .username(PUDTO.getUsername())
                .password(passwordEncoder.encode(PUDTO.getPassword()))
                .name(PUDTO.getName())
                .build();

        repo.save(u);
    }
}
