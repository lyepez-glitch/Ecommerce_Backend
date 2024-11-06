package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CORS configuration for the signup endpoint
    @CrossOrigin(origins = {
            "https://oracle-ecommerce-i24uanlqd-lucas-projects-f61d5cb5.vercel.app",
            "http://127.0.0.1:5173",
            "http://localhost:5173",
            "https://ecommerce-backend-1-yn41.onrender.com"
    })
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        // Implement user creation logic
        UserDetails newUser = User.withUsername(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .roles("USER") // Assign default role
                .build();

        // Cast the userDetailsService to InMemoryUserDetailsManager to create a user
        ((InMemoryUserDetailsManager) userDetailsService).createUser(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

    // CORS is not required for login, but can be added if necessary
    @CrossOrigin(origins = {
            "https://oracle-ecommerce-i24uanlqd-lucas-projects-f61d5cb5.vercel.app",
            "http://127.0.0.1:5173",
            "http://localhost:5173",
            "https://ecommerce-backend-1-yn41.onrender.com"
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Spring Security automatically handles login; return a success message
        return ResponseEntity.ok("Login successful");
    }
}
