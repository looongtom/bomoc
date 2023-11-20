package com.project.bookstore.bookstore.controller.api;


import com.project.bookstore.bookstore.model.dto.ClothesDTO;
import com.project.bookstore.bookstore.model.entity.*;
import com.project.bookstore.bookstore.repository.BrandEntityRepository;
import com.project.bookstore.bookstore.repository.ClothesClothescategoryEntityRepository;
import com.project.bookstore.bookstore.repository.ClothesEntityRepository;
import com.project.bookstore.bookstore.repository.ClothescategoryEntityRepository;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clothes")
public class ClothesEntityController {
    @Autowired
    private ClothesEntityRepository clothesEntityRepository;
    @Autowired
    private BrandEntityRepository brandEntityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClothescategoryEntityRepository clothescategoryEntityRepository;
    @Autowired
    private ClothesClothescategoryEntityRepository clothes_clothescategoryEntityRepository;


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<ClothesEntity> clothesEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            clothesEntityPage = clothesEntityRepository.findAll(pageable);
        }else{
            clothesEntityPage = clothesEntityRepository.findByNameContaining(keyword,pageable);
        }

        Page<ClothesDTO> clothesDTOS =  clothesEntityPage.map(this::convertToDTO);
        System.out.println(clothesDTOS.stream().toList());
        Map<String,Object> response = new HashMap<>();

        response.put("listClothes",clothesDTOS.getContent());
        response.put("currentPage",clothesEntityPage.getNumber());
        response.put("totalItems",clothesEntityPage.getTotalElements());
        response.put("totalPages",clothesEntityPage.getTotalPages());
        response.put("search",keyword);
//
        if(clothesDTOS.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return clothesEntityRepository.findById(id)
                .map(clothesEntity -> ResponseEntity.ok(convertToDTO(clothesEntity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody ClothesEntity clothesEntity){
        if(clothesEntityRepository.existsByNameAndBrand(clothesEntity.getName(),clothesEntity.getBrandId())){
            return ResponseEntity.badRequest().body("Clothes already exists with the same name and brand ID.");
        }
        ClothesEntity savedClothes= clothesEntityRepository.save(clothesEntity);
        return ResponseEntity.ok(savedClothes);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClothesEntity updateClothesEntity){
        ClothesEntity clothesEntity = clothesEntityRepository.findById(updateClothesEntity.getId()).orElse(null);
        if(clothesEntity == null){
            return ResponseEntity.notFound().build();
        }
        if(clothesEntityRepository.existsByNameAndBrand(updateClothesEntity.getName(),updateClothesEntity.getBrandId())){
            return ResponseEntity.badRequest().body("Book already exists with the same title and author.");
        }
        ClothesEntity savedClothes=clothesEntityRepository.save(updateClothesEntity);
        return ResponseEntity.ok(savedClothes);
    }

    private ClothesDTO convertToDTO(ClothesEntity clothesEntity){
        ClothesDTO clothesDTO = modelMapper.map(clothesEntity,ClothesDTO.class);
        BrandEntity findBrand = brandEntityRepository.findById(clothesEntity.getBrandId()).orElse(null);
        if(findBrand != null)
            clothesDTO.setBrandName(findBrand.getName());
        else clothesDTO.setBrandName("Unknown brand");
        if(findBrand != null)
            clothesDTO.setBrandName(findBrand.getName());
        else clothesDTO.setBrandName("Unknown brand");

//        List<ClothesClothescategoryEntity> getClothesCategory = clothes_clothescategoryEntityRepository.findByClothesId(clothesEntity.getId());
//        List<String> categoryName = getClothesCategory.stream().map(clothesClothesCategoryEntity -> {
//            return clothescategoryEntityRepository.findById(clothesClothesCategoryEntity.getClothesCategoryId()).orElse(null).getName();
//        }).collect(Collectors.toList());
//        if(categoryName.isEmpty())
//            categoryName.add("No belong to any categories");
//        else clothesDTO.setCategoryName(categoryName);
        return clothesDTO;
    }


}
