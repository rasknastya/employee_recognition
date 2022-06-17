package it.sevenbits.config;

import it.sevenbits.core.repository.marks.DatabaseMarkRepository;
import it.sevenbits.core.repository.marks.MarkRepository;
import it.sevenbits.core.repository.requests.DatabaseRequestRepository;
import it.sevenbits.core.repository.requests.RequestRepository;
import it.sevenbits.core.repository.users.DatabaseUserRepository;
import it.sevenbits.core.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The type Repository config.
 */
@Configuration
public class RepositoryConfig {

    /**
     * The method creates instance of users repository
     *
     * @param jdbcTemplate instance of jdbcTemplate
     * @return instance of the users repository
     */
    @Bean
    public UserRepository userRepository(final @Qualifier("JdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new DatabaseUserRepository(jdbcTemplate);
    }

    /**
     * Mark repository mark repository.
     *
     * @param jdbcTemplate the jdbc template
     * @return the mark repository
     */
    @Bean
    public MarkRepository markRepository(final @Qualifier("JdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new DatabaseMarkRepository(jdbcTemplate);
    }

    /**
     * Request repository request repository.
     *
     * @param jdbcTemplate the jdbc template
     * @return the request repository
     */
    @Bean
    public RequestRepository requestRepository(final @Qualifier("JdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new DatabaseRequestRepository(jdbcTemplate);
    }
}
