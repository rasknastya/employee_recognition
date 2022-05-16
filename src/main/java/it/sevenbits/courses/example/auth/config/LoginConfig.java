package it.sevenbits.courses.example.auth.config;

import it.sevenbits.courses.example.auth.core.service.login.LoginService;
import it.sevenbits.courses.example.auth.web.controller.LoginController;
import it.sevenbits.courses.example.auth.web.security.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {

    @Bean
    public Object loginController(final LoginService loginService, final JwtTokenService jwtTokenService) {
        return new LoginController(loginService, jwtTokenService);
    }

}
