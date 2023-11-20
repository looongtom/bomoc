package com.project.bookstore.bookstore.controller.api;


import com.project.bookstore.bookstore.model.entity.BookBookcategoryEntity;
import com.project.bookstore.bookstore.model.entity.ClothesClothescategoryEntity;

import com.project.bookstore.bookstore.repository.ClothesClothescategoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clothes_clothescategory")
public class Clothes_ClothesCategoryController {
    @Autowired
    private ClothesClothescategoryEntityRepository repository;

    @GetMapping("/getCategoriesByClothesId")
    public List<ClothesClothescategoryEntity> getCategoriesByClothesId(@RequestParam int id){
        List<ClothesClothescategoryEntity> list=repository.findByClothesId(id);
        return  list;

    }
    @GetMapping("/getClothesByCategoryId")
    public List<ClothesClothescategoryEntity> getClothesByCategoryId(@RequestParam int id){
        List<ClothesClothescategoryEntity> list=repository.findByClothesCategoryId(id);
        return  list;

    }
}
