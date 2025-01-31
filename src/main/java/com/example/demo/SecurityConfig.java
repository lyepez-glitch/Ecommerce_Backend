package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Value("${RENDER_URL}")
    private String renderURL;

    // Create users with respective roles and encoded passwords
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123")) // Encrypt admin password
                .roles("ADMIN")
                .build();

        UserDetails employee = User.withUsername("employee")
                .password(passwordEncoder.encode("employee123")) // Encrypt employee password
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(admin, employee);
    }

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt to encode passwords
    }


//     Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity (consider enabling in production)

                .cors(withDefaults()) // Enable CORS support
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/signup", "/api/auth/login").permitAll() // Allow signup and login without authentication
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Restrict admin endpoints
                        .requestMatchers("/employee/**").hasRole("EMPLOYEE") // Restrict employee endpoints
                        .anyRequest().permitAll() // Require authentication for all other requests
                )
                .httpBasic(withDefaults()); // Use basic authentication

        return http.build();
    }
}

