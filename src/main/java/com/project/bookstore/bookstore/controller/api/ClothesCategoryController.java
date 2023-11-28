package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.ClothescategoryEntity;
import com.project.bookstore.bookstore.repository.ClothescategoryEntityRepository;
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
@RequestMapping("/api/clothesCategory")
public class ClothesCategoryController {
    @Autowired
    private ClothescategoryEntityRepository clothesCategoryRepository;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<ClothescategoryEntity> clothescategoryEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            clothescategoryEntityPage = clothesCategoryRepository.findAll(pageable);
        }else{
            clothescategoryEntityPage = clothesCategoryRepository.findByNameContaining(keyword,pageable);
        }

        Map<String,Object> response = new HashMap<>();
        response.put("listBooks",clothescategoryEntityPage.getContent());
        response.put("currentPage",clothescategoryEntityPage.getNumber());
        response.put("totalItems",clothescategoryEntityPage.getTotalElements());
        response.put("totalPages",clothescategoryEntityPage.getTotalPages());
        response.put("search",keyword);

        if(clothescategoryEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return clothesCategoryRepository.findById(id)
                .map(ClothescategoryEntity -> ResponseEntity.ok(ClothescategoryEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody ClothescategoryEntity clothescategoryEntity){
        if(clothesCategoryRepository.existsByName(clothescategoryEntity.getName())){
            return ResponseEntity.badRequest().body("Clothes category already exists with the same name.");
        }
        ClothescategoryEntity savedClothes=clothesCategoryRepository.save(clothescategoryEntity);
        return ResponseEntity.ok(savedClothes);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClothescategoryEntity updateClothescategoryEntity){
        ClothescategoryEntity ClothescategoryEntity = clothesCategoryRepository.findById(updateClothescategoryEntity.getId()).orElse(null);
        if(ClothescategoryEntity == null){
            return ResponseEntity.notFound().build();
        }
        if(clothesCategoryRepository.existsByName(updateClothescategoryEntity.getName())){
            return ResponseEntity.badRequest().body("Clothes category already exists with the same name.");
        }
        ClothescategoryEntity savedClothes=clothesCategoryRepository.save(updateClothescategoryEntity);
        return ResponseEntity.ok(savedClothes);
    }
}
