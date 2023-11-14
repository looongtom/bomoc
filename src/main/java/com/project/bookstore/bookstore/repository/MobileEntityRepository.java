package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.MobileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileEntityRepository extends JpaRepository<MobileEntity, Integer> {
}