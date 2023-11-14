package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingEntityRepository extends JpaRepository<RatingEntity, Integer> {
}