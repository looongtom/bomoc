package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.BookEntity;
import com.project.bookstore.bookstore.model.entity.ClothesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClothesEntityRepository extends JpaRepository<ClothesEntity, Integer> {
    @Query("select (count(b) > 0) from ClothesEntity b where b.name = ?1 and b.brandId = ?2")
    boolean existsByNameAndBrand(String name, int brandId);

    Page<ClothesEntity> findByNameContaining(String keyword, Pageable pageable);
}