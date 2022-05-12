package it.sevenbits.courses.example.auth.config;

import it.sevenbits.courses.example.auth.core.repository.users.UserRepository;
import it.sevenbits.courses.example.auth.core.security.BCryptPasswordEncoder;
import it.sevenbits.courses.example.auth.core.security.PasswordEncoder;
import it.sevenbits.courses.example.auth.web.security.JsonWebTokenService;
import it.sevenbits.courses.example.auth.web.security.JwtSettings;
import it.sevenbits.courses.example.auth.web.security.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenService jwtTokenService(final JwtSettings settings, final UserRepository userRepository) {
        return new JsonWebTokenService(settings, userRepository);
    }

}
