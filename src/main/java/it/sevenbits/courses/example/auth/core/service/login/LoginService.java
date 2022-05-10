package it.sevenbits.courses.example.auth.core.service.login;

import it.sevenbits.courses.example.auth.core.model.User;
import it.sevenbits.courses.example.auth.core.repository.users.UsersRepository;
import it.sevenbits.courses.example.auth.core.security.PasswordEncoder;
import it.sevenbits.courses.example.auth.web.model.Login;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    private final UsersRepository users;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UsersRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(Login login) {
        User user = users.findByUserName(login.getLogin());

        if (user == null) {
            throw new LoginFailedException("User '" + login.getLogin() + "' not found");
        }

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new LoginFailedException("Wrong password");
        }
        return new User(user.getEmail(), fullName, user.getRoles(), embedding);
    }

}
