package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Integer> {
}