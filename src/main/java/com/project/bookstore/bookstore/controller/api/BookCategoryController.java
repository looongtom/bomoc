package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.dto.BookDTO;
import com.project.bookstore.bookstore.model.entity.BookcategoryEntity;
import com.project.bookstore.bookstore.repository.BookcategoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bookCategories")

public class BookCategoryController {
    @Autowired
    private BookcategoryEntityRepository bookCategoryRepository;
    
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<BookcategoryEntity> bookcategoryEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            bookcategoryEntityPage = bookCategoryRepository.findAll(pageable);
        }else{
            bookcategoryEntityPage = bookCategoryRepository.findByNameContaining(keyword,pageable);
        }

        Map<String,Object> response = new HashMap<>();
        response.put("listBooks",bookcategoryEntityPage.getContent());
        response.put("currentPage",bookcategoryEntityPage.getNumber());
        response.put("totalItems",bookcategoryEntityPage.getTotalElements());
        response.put("totalPages",bookcategoryEntityPage.getTotalPages());
        response.put("search",keyword);

        if(bookcategoryEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return bookCategoryRepository.findById(id)
                .map(BookcategoryEntity -> ResponseEntity.ok(BookcategoryEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody BookcategoryEntity bookcategoryEntity){
        if(bookCategoryRepository.existsByName(bookcategoryEntity.getName())){
            return ResponseEntity.badRequest().body("Book category already exists with the same name.");
        }
        BookcategoryEntity savedBook=bookCategoryRepository.save(bookcategoryEntity);
        return ResponseEntity.ok(savedBook);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody BookcategoryEntity updateBookcategoryEntity){
        BookcategoryEntity BookcategoryEntity = bookCategoryRepository.findById(updateBookcategoryEntity.getId()).orElse(null);
        if(BookcategoryEntity == null){
            return ResponseEntity.notFound().build();
        }
        if(bookCategoryRepository.existsByName(updateBookcategoryEntity.getName())){
            return ResponseEntity.badRequest().body("Book category already exists with the same name.");
        }
        BookcategoryEntity savedBook=bookCategoryRepository.save(updateBookcategoryEntity);
        return ResponseEntity.ok(savedBook);
    }
}
