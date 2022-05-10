package it.sevenbits.courses.example.auth.core.repository.users;

import it.sevenbits.courses.example.auth.core.model.User;
import it.sevenbits.courses.example.auth.web.controller.exception.BadRequestException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.*;

/**
 * Repository to list all users.
 */
public class DatabaseUsersRepository implements UsersRepository {
    private final JdbcOperations jdbcOperations;
    private final String ROLE = "role";
    private final String USERID = "user_id";
    private final String FULLNAME = "full_name";
    private final String EMBEDDING = "embedding";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";

    public DatabaseUsersRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public User findByUserName(String email) {
        User user;
        List<String> roles;

        try {
            user = jdbcOperations.queryForObject(
                    "SELECT user_id, full_name, embedding, password, enabled FROM users u" +
                            " WHERE u.enabled = true AND u.email = ?",
                    (resultSet, i) -> {
                        String userId =  resultSet.getString(USERID);
                        String fullName = resultSet.getString(FULLNAME);
                        float[] embedding = (float[])resultSet.getArray(EMBEDDING)
                                .getArray();
                        String password = resultSet.getString(PASSWORD);
                        return new User(userId, email, fullName, null, embedding, password);
                    },
                    email
            );
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
        if (user == null) {
            return null;
        }

        roles = getUserRoles(user.getUserId());
        user.setRoles(roles);

        return user;
    }

    public User findByUserId(String userId) {
        User user;
        List<String> roles;

        try {
            user = jdbcOperations.queryForObject(
                    "SELECT email, full_name, embedding, password, enabled FROM users u" +
                            " WHERE u.enabled = true AND u.user_id = ?",
                    (resultSet, i) -> {
                        String email =  resultSet.getString(EMAIL);
                        String fullName = resultSet.getString(FULLNAME);
                        float[] embedding = (float[])resultSet.getArray(EMBEDDING)
                                .getArray();
                        String password = resultSet.getString(PASSWORD);
                        return new User(userId, email, fullName, null, embedding, password);
                    },
                    userId
            );
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }

        roles = getUserRoles(userId);
        if (user == null) {
            return null;
        }

        user.setRoles(roles);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            jdbcOperations.query(
                    "SELECT user_id, email, full_name, embedding, password, enabled FROM users u" +
                            " WHERE u.enabled = true",
                    (resultSet, i) -> {
                        String userId =  resultSet.getString(USERID);
                        String email =  resultSet.getString(EMAIL);
                        String fullName = resultSet.getString(FULLNAME);
                        float[] embedding = (float[])resultSet.getArray(EMBEDDING)
                                .getArray();
                        String password = resultSet.getString(PASSWORD);
                        List<String> roles = getUserRoles(userId);
                        return users.add(new User(userId, email, fullName, roles, embedding, password));
                    }
            );
        } catch (IncorrectResultSizeDataAccessException e){
            return users;
        }

        return users;
    }

    @Override
    public User addUser(final User user) {
        String userId = user.getUserId();
        String email = user.getEmail();
        String fullName = user.getFullName();
        float[] embedding = user.getEmbedding();
        String password = user.getPassword();
        List<String> roles = user.getRoles();

        try {
            jdbcOperations.update(
                    "INSERT INTO users(user_id, email, full_name, embedding, password, enabled) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)",
                    userId, email, fullName, embedding, password, true
            );
        } catch (Exception e) {
            throw new BadRequestException("User already exists");
        }

        for (String role : roles) {
            jdbcOperations.update(
                    "INSERT INTO user_roles(userId, role) VALUES (?, ?)",
                    userId, role
            );
        }

        return findByUserId(userId);
    }

    @Override
    public User updateUser(final User user) {
        String userId = user.getUserId();
        String email = user.getEmail();
        String fullName = user.getFullName();
        float[] embedding = user.getEmbedding();
        String password = user.getPassword();
        List<String> roles = user.getRoles();

        try {
            jdbcOperations.update(
                    "UPDATE users SET email = ?, full_name = ?," +
                            " embedding = ?, password = ? WHERE user_id = ? ",
                    email, fullName, embedding, password, userId
            );
        } catch (Exception e) {
            throw new BadRequestException("User already exists");
        }

        List<String> currentRoles = getUserRoles(userId);
        for(String role : currentRoles) {
            if(!roles.contains(role)) {
                jdbcOperations.update(
                        "DELETE FROM user_roles WHERE user_id = ? AND role = ?",
                        userId, role
                );
            }
        }
        for(String role : roles) {
            if(!currentRoles.contains(role)) {
                jdbcOperations.update(
                        "INSERT INTO user_roles(userId, role) VALUES (?, ?)",
                        userId, role
                );
            }
        }

        return findByUserId(userId);
    }

    private List<String> getUserRoles(final String userId) {
        List<String> roles = new ArrayList<>();
        jdbcOperations.query(
                "SELECT userId, role FROM user_roles" +
                        " WHERE userId = ?",
                resultSet -> {
                    String role = resultSet.getString(ROLE);
                    roles.add(role);
                },
                userId
        );
        return roles;
    }

}
