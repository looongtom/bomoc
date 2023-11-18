package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandEntityRepository extends JpaRepository<BrandEntity, Integer> {
}