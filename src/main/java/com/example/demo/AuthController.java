package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${RENDER_URL}")
    private String renderURL;

    @CrossOrigin(origins = {"${RENDER_URL}"})  // Allow POST and OPTIONS methods
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        // Implement user creation logic
        UserDetails newUser = User.withUsername(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .roles("USER") // Assign role (you can change this dynamically)
                .build();
        ((InMemoryUserDetailsManager) userDetailsService).createUser(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

    @CrossOrigin(origins = {"${RENDER_URL}"})
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Spring Security automatically handles login; return a success message
        return ResponseEntity.ok("Login successful");
    }
}
