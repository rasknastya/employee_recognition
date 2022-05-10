package it.sevenbits.courses.example.auth.core.repository.books;


import it.sevenbits.courses.example.auth.core.model.Book;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

/**
 * Database books repository - implementation of BooksRepository
 *
 */
public class DatabaseBooksRepository implements BooksRepository {
    private JdbcOperations jdbcOperations;

    /**
     * The basic constructor
     *
     * @param jdbcOperations instance of a interface for work with database
     */
    public DatabaseBooksRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcOperations.query(
                "SELECT id, book_name, author FROM books",
                (resultSet, i) -> {
                    long id = resultSet.getLong(1);
                    String book_name = resultSet.getString(2);
                    String author = resultSet.getString(3);
                    return new Book(id, book_name, author);
                });
    }

    @Override
    public Book getBookById(long id) {
        return jdbcOperations.queryForObject(
                "SELECT id, book_name, author FROM books WHERE id = ?",
                (resultSet, i) -> {
                    long book_id = resultSet.getLong(1);
                    String book_name = resultSet.getString(2);
                    String author = resultSet.getString(3);
                    return new Book(book_id, book_name, author);
                },
                id);
    }


}
