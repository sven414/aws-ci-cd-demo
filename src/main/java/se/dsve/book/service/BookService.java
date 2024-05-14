package se.dsve.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dsve.book.exceptions.ResourceNotFoundException;
import se.dsve.book.model.Book;
import se.dsve.book.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * The BookService class is responsible for handling business logic related to books.
 * It provides methods for retrieving, adding, updating, and deleting books.
 * Data access is handled by the BookRepository class.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        // TODO: Skriv din kod här
        return null;
    }

    public Optional<Book> getBookById(Long id) {
        // TODO: Skriv din kod här
        return null;
    }

    public Book addBook(Book book) {
        // TODO: Skriv din kod här
        return null;
    }

    public Book updateBook(Long id, Book bookDetails) {
        // TODO: Skriv din kod här
        return null;
    }

    public void deleteBook(Long id) {
        // TODO: Skriv din kod här
    }

    private Book getBookOrFail(Long id) {
        // TODO: Skriv din kod här
        return null;
    }
}
