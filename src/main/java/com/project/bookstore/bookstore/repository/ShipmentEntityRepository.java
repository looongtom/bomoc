package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentEntityRepository extends JpaRepository<ShipmentEntity, Integer> {
}