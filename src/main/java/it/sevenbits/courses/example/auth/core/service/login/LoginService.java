package it.sevenbits.courses.example.auth.core.service.login;

import it.sevenbits.courses.example.auth.core.model.User;
import it.sevenbits.courses.example.auth.core.repository.users.UserRepository;
import it.sevenbits.courses.example.auth.core.security.PasswordEncoder;
import it.sevenbits.courses.example.auth.web.model.Login;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(Login login) {
        System.out.println(login.getEmail());
        User user = users.findUserByEmail(login.getEmail());
        if (user == null) {
            throw new LoginFailedException("User '" + login.getEmail() + "' not found");
        }

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new LoginFailedException("Wrong password");
        }
        return user;
    }

}
