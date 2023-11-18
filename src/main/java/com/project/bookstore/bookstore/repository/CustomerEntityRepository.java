package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Integer> {
}