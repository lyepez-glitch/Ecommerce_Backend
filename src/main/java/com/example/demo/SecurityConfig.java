package com.example.demo;

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

//     CORS configuration
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true); // Allow credentials if needed
//        configuration.addAllowedOrigin("https://oracle-ecommerce-fgbe9ltuj-lucas-projects-f61d5cb5.vercel.app");
////        configuration.addAllowedOrigin("http://127.0.0.1:5173");
////        configuration.addAllowedOrigin("http://localhost:5173");
////        configuration.addAllowedOrigin("https://ecommerce-backend-1-yn41.onrender.com");
//
//        configuration.addAllowedMethod("*"); // Allow all methods
//        configuration.addAllowedHeader("*"); // Allow all headers
//        configuration.setMaxAge(3600L); // Cache for an hour
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration
//
//        return source;
//    }

//     CORS Filter Bean
//    @Bean
//    public CorsFilter corsFilter() {
//        return new CorsFilter(corsConfigurationSource());
//    }

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
//                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .httpBasic(withDefaults()); // Use basic authentication

        return http.build();
    }
}

