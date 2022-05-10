package it.sevenbits.courses.example.auth.config;

import it.sevenbits.courses.example.auth.core.repository.books.BooksRepository;
import it.sevenbits.courses.example.auth.core.service.books.BooksService;
import it.sevenbits.courses.example.auth.core.service.books.IBooksService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Service configuration
 */
@Configuration
public class ServiceConfig {

     /**
     * The method creates instance of books service
     *
     * @param booksRepository instance of the books repository
     * @return instance of the books service
     */
    @Bean
    public IBooksService booksService(final BooksRepository booksRepository) {
        return new BooksService(booksRepository);
    }
}
