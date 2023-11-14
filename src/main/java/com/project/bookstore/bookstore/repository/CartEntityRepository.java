package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntityRepository extends JpaRepository<CartEntity, Integer> {
}