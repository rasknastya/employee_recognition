package it.sevenbits.courses.example.auth.core.repository.users;

import it.sevenbits.courses.example.auth.core.model.User;

import java.util.List;

public interface UserRepository {

    User findUserByEmail(String username);
    User findUserById(String userId);
    List<User> getAllUsers();
    User addUser(User user);
    User updateUser(final User user);

}
