package it.sevenbits.core.service.login;

import it.sevenbits.core.model.User;
import it.sevenbits.core.repository.users.UserRepository;
import it.sevenbits.core.security.PasswordEncoder;
import it.sevenbits.web.model.Login;
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
        User user = users.findUserByEmail(login.getEmail());
        if (user == null) {
            throw new LoginFailedException("User '" + login.getEmail() + "' not found");
        }

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new LoginFailedException("Wrong password");
        }
        System.out.println(user.getRoles());
        return user;
    }

}
