package it.sevenbits.courses.example.auth.core.repository.users;

import it.sevenbits.courses.example.auth.core.model.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserRepository {

    User findUserByEmail(String username);
    User findUserById(String userId);
    List<User> getAllUsers();

    Map<String, BigDecimal[]> getEmbeddings();

    User addUser(User user) throws SQLException;
    User updateUser(final User user);

}
