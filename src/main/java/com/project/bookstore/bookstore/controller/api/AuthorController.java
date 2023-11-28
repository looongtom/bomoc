package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.AuthorEntity;
import com.project.bookstore.bookstore.model.entity.AuthorEntity;
import com.project.bookstore.bookstore.model.entity.AuthorEntity;
import com.project.bookstore.bookstore.repository.AuthorEntityRepository;
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
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorEntityRepository authorEntityRepository;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<AuthorEntity> authorEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            authorEntityPage = authorEntityRepository.findAll(pageable);
        }else{
            authorEntityPage = authorEntityRepository.findByNameContaining(keyword,pageable);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listAuthors",authorEntityPage.getContent());
        response.put("currentPage",authorEntityPage.getNumber());
        response.put("totalItems",authorEntityPage.getTotalElements());
        response.put("totalPages",authorEntityPage.getTotalPages());
        response.put("search",keyword);

        if(authorEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return authorEntityRepository.findById(id)
                .map(AuthorEntity -> ResponseEntity.ok(AuthorEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody AuthorEntity AuthorEntity){
        AuthorEntity authorEntity=authorEntityRepository.save(AuthorEntity);
        return ResponseEntity.ok(authorEntity);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody AuthorEntity updateAuthorEntity){
        AuthorEntity AuthorEntity = authorEntityRepository.findById(updateAuthorEntity.getId()).orElse(null);
        if(AuthorEntity == null){
            return ResponseEntity.notFound().build();
        }
        AuthorEntity authorEntity=authorEntityRepository.save(updateAuthorEntity);
        return ResponseEntity.ok(authorEntity);
    }
}
