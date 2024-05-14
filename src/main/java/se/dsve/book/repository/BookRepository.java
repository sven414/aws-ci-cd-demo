package se.dsve.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.dsve.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Here you can add custom methods for interacting with the Book entity in the database, if needed.
}
