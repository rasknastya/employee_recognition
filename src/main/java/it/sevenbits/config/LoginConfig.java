package it.sevenbits.config;

import it.sevenbits.core.service.login.LoginService;
import it.sevenbits.web.controller.LoginController;
import it.sevenbits.web.security.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {

    @Bean
    public Object loginController(final LoginService loginService, final JwtTokenService jwtTokenService) {
        return new LoginController(loginService, jwtTokenService);
    }

}
