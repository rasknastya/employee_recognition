package it.sevenbits.core.repository.users;

import it.sevenbits.core.model.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * The interface User repository.
 */
public interface UserRepository {

    /**
     * Find user by email user.
     *
     * @param username the username
     * @return the user
     */
    User findUserByEmail(String username);

    /**
     * Find user by id user.
     *
     * @param userId the user id
     * @return the user
     */
    User findUserById(String userId);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<User> getAllUsers();

    /**
     * Gets embeddings.
     *
     * @return the embeddings
     */
    Map<String, BigDecimal[]> getEmbeddings();

    /**
     * Add user user.
     *
     * @param user the user
     * @return the user
     * @throws SQLException the sql exception
     */
    User addUser(User user) throws SQLException;

    /**
     * Update user user.
     *
     * @param user the user
     * @return the user
     */
    User updateUser(final User user);

}
