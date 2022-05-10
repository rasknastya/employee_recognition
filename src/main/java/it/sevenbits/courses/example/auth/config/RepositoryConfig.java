package it.sevenbits.courses.example.auth.config;

import it.sevenbits.courses.example.auth.core.repository.books.BooksRepository;
import it.sevenbits.courses.example.auth.core.repository.books.DatabaseBooksRepository;
import it.sevenbits.courses.example.auth.core.repository.users.DatabaseUsersRepository;
import it.sevenbits.courses.example.auth.core.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RepositoryConfig {

    /**
     * The method creates instance of books repository
     * @param jdbcTemplate instance of jdbcTemplate
     * @return instance of the books repository
     */
    @Bean
    public BooksRepository booksRepository(final @Qualifier("JdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new DatabaseBooksRepository(jdbcTemplate);
    }

    /**
     * The method creates instance of users repository
     * @param jdbcTemplate instance of jdbcTemplate
     * @return instance of the books repository
     */
    @Bean
    public UsersRepository usersRepository(final @Qualifier("JdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new DatabaseUsersRepository(jdbcTemplate);
    }

}
