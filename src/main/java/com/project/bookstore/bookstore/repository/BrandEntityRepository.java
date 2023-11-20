package com.project.bookstore.bookstore.repository;


import com.project.bookstore.bookstore.model.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandEntityRepository extends JpaRepository<BrandEntity, Integer> {
    Page<BrandEntity> findByNameContaining(String keyword, Pageable pageable);
}