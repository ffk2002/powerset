package com.example.powerset.user;

import com.example.powerset.error_handling.PowersetUserExistsException;

import com.example.powerset.error_handling.PowersetUserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        if (repo.findByUsername(PUDTO.getUsername()).isPresent()){
            throw new PowersetUserExistsException(PUDTO.getUsername());
        } else {
            PowersetUser u = PowersetUser.builder()
                    .username(PUDTO.getUsername())
                    .password(passwordEncoder.encode(PUDTO.getPassword()))
                    .name(PUDTO.getName())
                    .build();
            repo.save(u);
        }
    }

    @GetMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public boolean authenticateUser(@RequestBody PowersetUserDTO PUDTO){
        Optional<PowersetUser> stored = repo.findByUsername(PUDTO.getUsername());
        if (stored.isEmpty()){
            throw new PowersetUserNotFoundException(PUDTO.getUsername());
        } else {
            PowersetUser storedUser = PowersetUser.builder()
                    .username(stored.get().getUsername())
                    .password(stored.get().getPassword())
                    .build();

            return passwordEncoder.matches(PUDTO.getPassword(), storedUser.getPassword());

        }

    }
}
