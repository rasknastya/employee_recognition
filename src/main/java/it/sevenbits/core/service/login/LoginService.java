package it.sevenbits.core.service.login;

import it.sevenbits.core.model.User;
import it.sevenbits.core.repository.users.UserRepository;
import it.sevenbits.core.security.PasswordEncoder;
import it.sevenbits.web.model.Login;
import org.springframework.stereotype.Service;


/**
 * The type Login service.
 */
@Service
public class LoginService {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new Login service.
     *
     * @param users           the users
     * @param passwordEncoder the password encoder
     */
    public LoginService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Login user.
     *
     * @param login the login
     * @return the user
     */
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
