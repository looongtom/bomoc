package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Integer> {
}