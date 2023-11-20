package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.ClothesClothescategoryEntity;
import com.project.bookstore.bookstore.model.entity.ClothesClothescategoryEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothesClothescategoryEntityRepository extends JpaRepository<ClothesClothescategoryEntity, ClothesClothescategoryEntityPK> {
    List<ClothesClothescategoryEntity> findByClothesId(int id);

    List<ClothesClothescategoryEntity> findByClothesCategoryId(int id);
}