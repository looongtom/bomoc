package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.BookBookcategoryEntity;
import com.project.bookstore.bookstore.repository.BookBookcategoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book_bookcategory")
public class Book_BookcategoryController {
    @Autowired
    private BookBookcategoryEntityRepository repository;

    @GetMapping("/getCategoriesByBookId")
    public List<BookBookcategoryEntity> getCategoriesByBookId(@RequestParam int id){
        List<BookBookcategoryEntity> list=repository.findByBookId(id);
        return  list;

    }
    @GetMapping("/getBookByCategoryId")
    public List<BookBookcategoryEntity> getBookByCategoryId(@RequestParam int id){
        List<BookBookcategoryEntity> list=repository.findByBookCategoryId(id);
        return  list;

    }

}
