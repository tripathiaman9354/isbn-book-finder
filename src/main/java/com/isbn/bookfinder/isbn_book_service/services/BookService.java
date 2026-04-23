package com.isbn.bookfinder.isbn_book_service.services;

import com.isbn.bookfinder.isbn_book_service.entity.Book;
import com.isbn.bookfinder.isbn_book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book fetchBookFromAPI(String isbn){

        RestTemplate restTemplate = new RestTemplate();
        Book book = new Book();

        boolean dataFound = false;

        // ---------- OpenLibrary ----------
        try {
            String openUrl = "https://openlibrary.org/api/books?bibkeys=ISBN:"
                    + isbn + "&format=json&jscmd=data";

            Map openResponse = restTemplate.getForObject(openUrl, Map.class);

            if(openResponse != null){
                Map bookData = (Map) openResponse.get("ISBN:" + isbn);

                if(bookData != null){

                    dataFound = true;

                    book.setTitle((String) bookData.get("title"));

                    if(bookData.get("authors") != null){
                        List authors = (List) bookData.get("authors");
                        if(!authors.isEmpty()){
                            Map author = (Map) authors.get(0);
                            book.setAuthorName((String) author.get("name"));
                        }
                    }

                    if(bookData.get("publishers") != null){
                        List publishers = (List) bookData.get("publishers");
                        if(!publishers.isEmpty()){
                            Map publisher = (Map) publishers.get(0);
                            book.setPublisher((String) publisher.get("name"));
                        }
                    }

                    book.setPublishedDate((String) bookData.get("publish_date"));

                    if(bookData.get("subjects") != null){
                        List subjects = (List) bookData.get("subjects");
                        if(!subjects.isEmpty()){
                            Map subject = (Map) subjects.get(0);
                            book.setGenreType((String) subject.get("name"));
                        }
                    }

                    if(bookData.get("cover") != null){
                        Map cover = (Map) bookData.get("cover");
                        book.setCoverImage((String) cover.get("medium"));
                    }
                }
            }

        } catch(Exception e){
            System.out.println("OpenLibrary API failed: " + e.getMessage());
        }

        // ---------- Google Books ----------
        try {
            String googleUrl =
                    "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;

            Map googleResponse =
                    restTemplate.getForObject(googleUrl, Map.class);

            if(googleResponse != null){

                List items = (List) googleResponse.get("items");

                if(items != null && !items.isEmpty()){

                    dataFound = true;

                    Map item = (Map) items.get(0);
                    Map volumeInfo = (Map) item.get("volumeInfo");

                    if(book.getTitle() == null)
                        book.setTitle((String) volumeInfo.get("title"));

                    if(volumeInfo.get("authors") != null && book.getAuthorName() == null){
                        List authors = (List) volumeInfo.get("authors");
                        if(!authors.isEmpty())
                            book.setAuthorName(authors.get(0).toString());
                    }

                    if(book.getPublisher() == null)
                        book.setPublisher((String) volumeInfo.get("publisher"));

                    if(book.getPublishedDate() == null)
                        book.setPublishedDate((String) volumeInfo.get("publishedDate"));

                    if(volumeInfo.get("categories") != null && book.getGenreType() == null){
                        List categories = (List) volumeInfo.get("categories");
                        if(!categories.isEmpty())
                            book.setGenreType(categories.get(0).toString());
                    }

                    if(volumeInfo.get("imageLinks") != null && book.getCoverImage() == null){
                        Map img = (Map) volumeInfo.get("imageLinks");
                        book.setCoverImage((String) img.get("thumbnail"));
                    }

                    // PRICE
                    Map saleInfo = (Map) item.get("saleInfo");

                    if(saleInfo != null && saleInfo.get("retailPrice") != null){
                        Map price = (Map) saleInfo.get("retailPrice");
                        book.setPrice(Double.parseDouble(price.get("amount").toString()));
                    }
                }
            }

        } catch(Exception e){
            System.out.println("Google API failed: " + e.getMessage());
        }

        // ❌ If no data found → return null
        if(!dataFound){
            return null;
        }

        book.setIsbn(isbn);

        return book;
    }

    public Book saveBook(Book book){

        // 🔥 Duplicate check
        if(bookRepository.existsByIsbn(book.getIsbn())){
            throw new RuntimeException("Book already saved!");
        }

        return bookRepository.save(book);
    }}