package com.project.bookstore.bookstore.controller;

import com.project.bookstore.bookstore.exception.ResourceNotFoundException;
import com.project.bookstore.bookstore.model.Book;
import com.project.bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController

public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooksSortedById() {
        return bookRepository.findAllByOrderByIdAsc();
    }
    @GetMapping("/book/{id}")
    Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book" + id +"not exist"));
    }

    @PostMapping("/book")
    public ResponseEntity<?> addNewBook(@RequestBody Book book) {
        if (bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book already exists with the same title and author.");
        }

        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }
    @PutMapping("/book/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book newBook, @PathVariable Long id) {
        if (bookRepository.existsByTitleAndAuthorAndIdNot(newBook.getTitle(), newBook.getAuthor(), id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title and author already exist for another book.");
        }
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setDescription(newBook.getDescription());
                    book.setPublishedDate(newBook.getPublishedDate());
                    book.setPages(newBook.getPages());
                    book.setCategory(newBook.getCategory());
                    book.setBookCover(newBook.getBookCover());
                    Book updatedBook = bookRepository.save(book);
                    return ResponseEntity.ok(updatedBook); // Trả về sách đã được cập nhật
                })
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }


    @DeleteMapping("/book/{id}")
    String deleteBook(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("Book not exist with id: " + id);
        }
        bookRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }
    @GetMapping("/books/search")
    public List<Book> searchBooks(@RequestParam(required = false) String query) {
        if (query != null && !query.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
        } else {
            return bookRepository.findAll();
        }
    }



}