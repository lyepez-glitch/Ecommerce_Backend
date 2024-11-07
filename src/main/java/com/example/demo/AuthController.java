package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin(origins = "https://oracle-ecommerce-fgbe9ltuj-lucas-projects-f61d5cb5.vercel.app")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "https://oracle-ecommerce-k7omklaqi-lucas-projects-f61d5cb5.vercel.app")  // Allow POST and OPTIONS methods
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

    @CrossOrigin(origins = "https://oracle-ecommerce-k7omklaqi-lucas-projects-f61d5cb5.vercel.app")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Spring Security automatically handles login; return a success message
        return ResponseEntity.ok("Login successful");
    }
}
