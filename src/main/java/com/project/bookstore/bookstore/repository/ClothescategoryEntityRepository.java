package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.BookcategoryEntity;
import com.project.bookstore.bookstore.model.entity.ClothescategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClothescategoryEntityRepository extends JpaRepository<ClothescategoryEntity, Integer> {
    Page<ClothescategoryEntity> findByNameContaining(String keyword, Pageable pageable);
    @Query("select (count(b) > 0) from ClothescategoryEntity b where b.name = ?1")

    boolean existsByName(String name);
}