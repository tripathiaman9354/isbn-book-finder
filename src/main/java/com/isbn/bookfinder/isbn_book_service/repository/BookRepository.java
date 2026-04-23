package com.isbn.bookfinder.isbn_book_service.repository;

import com.isbn.bookfinder.isbn_book_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>{
    boolean existsByIsbn(String isbn);

}
