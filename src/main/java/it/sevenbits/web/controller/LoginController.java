package it.sevenbits.web.controller;

import it.sevenbits.core.model.User;
import it.sevenbits.core.service.login.LoginService;
import it.sevenbits.web.model.Login;
import it.sevenbits.web.model.Token;
import it.sevenbits.web.security.JwtTokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Performs login action.
 */
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final JwtTokenService tokenService;

    public LoginController(final LoginService loginService, final JwtTokenService tokenService) {
        this.loginService = loginService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @ResponseBody
    public Token create(@RequestBody Login login) {
        User user = loginService.login(login);
        String token = tokenService.createToken(user);
        return new Token(token);
    }

}
