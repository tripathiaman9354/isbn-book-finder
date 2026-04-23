package com.isbn.bookfinder.isbn_book_service.controller;

import com.isbn.bookfinder.isbn_book_service.entity.Book;
import com.isbn.bookfinder.isbn_book_service.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/search/{isbn}")
    public ResponseEntity<?> searchBook(@PathVariable String isbn){

        Book book = bookService.fetchBookFromAPI(isbn);

        if(book == null){
            return ResponseEntity.status(404).body("Book Not Found");
        }

        return ResponseEntity.ok(book);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestBody Book book){
        try {
            Book saved = bookService.saveBook(book);
            return ResponseEntity.ok(saved);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(400) // 👈 500 nahi, 400 better hai
                    .body(e.getMessage()); // 👈 message frontend ko bhejo
        }
    }
}