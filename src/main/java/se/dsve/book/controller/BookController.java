package se.dsve.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dsve.book.exceptions.ResourceNotFoundException;
import se.dsve.book.model.Book;
import se.dsve.book.service.BookService;

import java.util.List;

/**
 * The BookController class is responsible for handling HTTP requests related to books.
 * It provides methods for retrieving, adding, updating, and deleting books.
 * Business logic is handled by the BookService class.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        // TODO: Skriv din kod här
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        // TODO: Skriv din kod här
        return null;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        // TODO: Skriv din kod här
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        // TODO: Skriv din kod här
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // TODO: Skriv din kod här
        return null;
    }
}
