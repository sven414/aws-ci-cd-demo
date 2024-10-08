package se.dsve.book.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.dsve.book.exceptions.ResourceNotFoundException;
import se.dsve.book.exceptions.RestExceptionHandler;
import se.dsve.book.model.Books;
import se.dsve.book.service.BookService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void testInvalidId() throws Exception {
        Long invalidId = 99999L; // ogiltigt Book-id

        // Ställer in BookService att kasta undantag när getBookById anropas med invalidId
        doThrow(new ResourceNotFoundException("Book not found with id " + invalidId))
                .when(bookService).getBookById(invalidId);

        // Försöker hämta bok med ogiltigt ID och förväntar oss att ett ResourceNotFoundException kastas
        MvcResult result = mockMvc.perform(get("/api/books/" + invalidId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        // Fånga och skriv ut undantaget om det kastas
        Exception resolvedException = result.getResolvedException();
        if (resolvedException != null) {
            System.out.println("Resolved Exception:");
            resolvedException.printStackTrace();
            // Kontrollera att undantaget är av rätt typ
            assertTrue(resolvedException instanceof ResourceNotFoundException);
            // Kontrollera att meddelandet är korrekt
            assertTrue(resolvedException.getMessage().contains("Book not found with id " + invalidId));
        } else {
            System.out.println("No exception was thrown.");
            fail("Expected a ResourceNotFoundException to be thrown, but none was thrown.");
        }

        // Verifiera att metoden getBookById anropades med invalidId
        verify(bookService, times(1)).getBookById(invalidId);
    }

    @Test
    void getAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(new Books(), new Books()));

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getBookById() throws Exception {
        when(bookService.getBookById(anyLong())).thenReturn(Optional.of(new Books()));

        mockMvc.perform(get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addBook() throws Exception {
        when(bookService.addBook(any(Books.class))).thenReturn(new Books());

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Book\",\"author\":\"Test Author\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void updateBook() throws Exception {
        when(bookService.updateBook(anyLong(), any(Books.class))).thenReturn(new Books());

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Test Book\",\"author\":\"Updated Test Author\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBookWhenBookExists() throws Exception {
        // Given
        Long id = 1L;
        doNothing().when(bookService).deleteBook(id);

        // When & Then
        mockMvc.perform(delete("/api/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBookWhenBookDoesNotExist() throws Exception {
        // Given
        Long id = 1L;
        doThrow(new ResourceNotFoundException("Book not found with id " + id))
                .when(bookService).deleteBook(id);

        // When & Then
        mockMvc.perform(delete("/api/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_updateBook_bookNotFound() throws Exception {
        Long invalidId = 999L;
        Books bookDetails = new Books();
        bookDetails.setTitle("Updated Title");
        bookDetails.setAuthor("Updated Author");
        bookDetails.setIsbn("1234567890");

        doThrow(new ResourceNotFoundException("Book not found with id " + invalidId))
                .when(bookService).updateBook(eq(invalidId), any(Books.class));

        mockMvc.perform(put("/api/books/" + invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"author\":\"Updated Author\",\"isbn\":\"1234567890\"}"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("Book not found with id " +
                        invalidId, result.getResolvedException().getMessage()));
    }
}
