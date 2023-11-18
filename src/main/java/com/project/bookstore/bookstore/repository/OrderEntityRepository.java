package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {
}