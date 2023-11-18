package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.ClothesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesEntityRepository extends JpaRepository<ClothesEntity, Integer> {
}