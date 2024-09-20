package se.dsve.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.dsve.book.exceptions.ResourceNotFoundException;
import se.dsve.book.model.Books;
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

    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Books> getBookById(Long id) {
        try {
            return Optional.of(getBookOrFail(id));
        } catch (ResourceNotFoundException e) {
            return Optional.empty();
        }
    }

    public Books addBook(Books book) {
        return bookRepository.save(book);
    }

    public Books updateBook(Long id, Books bookDetails) {
        Books book = getBookOrFail(id);

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Books book = getBookOrFail(id);
        bookRepository.delete(book);
    }

    private Books getBookOrFail(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }
}
