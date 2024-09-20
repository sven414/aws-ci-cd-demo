package se.dsve.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.dsve.book.model.Books;

public interface BookRepository extends JpaRepository<Books, Long> {
    // Here you can add custom methods for interacting with the Book entity in the database, if needed.
}
