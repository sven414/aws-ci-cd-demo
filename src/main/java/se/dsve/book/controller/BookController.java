package se.dsve.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.dsve.book.exceptions.ResourceNotFoundException;
import se.dsve.book.model.Books;
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
    public List<Books> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    @PostMapping
    public Books addBook(@RequestBody Books book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books bookDetails) {
        Books updatedBook = bookService.updateBook(id, bookDetails);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
