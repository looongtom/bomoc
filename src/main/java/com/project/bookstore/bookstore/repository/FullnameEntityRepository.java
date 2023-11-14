package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.FullnameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FullnameEntityRepository extends JpaRepository<FullnameEntity, Integer> {
}