//package com.project.bookstore.bookstore.repository;
//
//import com.project.bookstore.bookstore.model.Book;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface BookRepository extends JpaRepository<Book, Long> {
//
//    boolean existsByTitleAndAuthor(String title, String author);
//
//    boolean existsByTitleAndAuthorAndIdNot(String title, String author, Long id);
//
//    List<Book> findAllByOrderByIdAsc();
//
//    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String query, String query2);
//
//
//
//}
