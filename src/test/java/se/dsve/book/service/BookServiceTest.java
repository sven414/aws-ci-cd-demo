package se.dsve.book.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.dsve.book.exceptions.ResourceNotFoundException;
import se.dsve.book.model.Book;
import se.dsve.book.repository.BookRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565");
    Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780446310789");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        assertEquals(2, bookService.getAllBooks().size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));

        Book book = bookService.getBookById(1L).orElse(null);
        assertNotNull(book);
        assertEquals("The Great Gatsby", book.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void addBook() {
        when(bookRepository.save(book1)).thenReturn(book1);

        Book savedBook = bookService.addBook(book1);
        assertNotNull(savedBook);
        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    void updateBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.save(book1)).thenReturn(book1);

        Book updatedBook = bookService.updateBook(1L, book1);
        assertNotNull(updatedBook);
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    void deleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}