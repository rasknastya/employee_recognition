package it.sevenbits.config;

import it.sevenbits.core.repository.users.UserRepository;
import it.sevenbits.core.security.BCryptPasswordEncoder;
import it.sevenbits.core.security.PasswordEncoder;
import it.sevenbits.web.security.JsonWebTokenService;
import it.sevenbits.web.security.JwtSettings;
import it.sevenbits.web.security.JwtTokenService;
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
