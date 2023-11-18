package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.BookEntity;
import com.project.bookstore.bookstore.repository.BookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookEntityController {
    @Autowired
    private BookEntityRepository bookEntityRepository;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword,int page,int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<BookEntity> bookEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            bookEntityPage = bookEntityRepository.findAll(pageable);
        }else{
            bookEntityPage = bookEntityRepository.findByTitleContaining(keyword,pageable);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listBooks",bookEntityPage.getContent());
        response.put("currentPage",bookEntityPage.getNumber());
        response.put("totalItems",bookEntityPage.getTotalElements());
        response.put("totalPages",bookEntityPage.getTotalPages());
        if(bookEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return bookEntityRepository.findById(id)
                .map(bookEntity -> ResponseEntity.ok(bookEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody BookEntity bookEntity){
        if(bookEntityRepository.existsByTitleAndAuthor(bookEntity.getTitle(),bookEntity.getAuthorId())){
            return ResponseEntity.badRequest().body("Book already exists with the same title and author.");
        }
        BookEntity savedBook=bookEntityRepository.save(bookEntity);
        return ResponseEntity.ok(savedBook);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody BookEntity updateBookEntity){
        BookEntity bookEntity = bookEntityRepository.findById(updateBookEntity.getId()).orElse(null);
        if(bookEntity == null){
            return ResponseEntity.notFound().build();
        }
        if(bookEntityRepository.existsByTitleAndAuthor(updateBookEntity.getTitle(),updateBookEntity.getAuthorId())){
            return ResponseEntity.badRequest().body("Book already exists with the same title and author.");
        }
        BookEntity savedBook=bookEntityRepository.save(updateBookEntity);
        return ResponseEntity.ok(savedBook);
    }

}
