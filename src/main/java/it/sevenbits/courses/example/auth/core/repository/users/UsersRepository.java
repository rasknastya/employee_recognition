package it.sevenbits.courses.example.auth.core.repository.users;

import it.sevenbits.courses.example.auth.core.model.User;

import java.util.HashMap;
import java.util.List;

public interface UsersRepository {

    User findByUserName(String username);
    User findByUserId(String userId);
    List<User> getAllUsers();
    User addUser(User user);
    User updateUser(final User user);

}
