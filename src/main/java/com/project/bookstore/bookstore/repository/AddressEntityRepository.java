package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.AddressEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Integer> {
    Page<AddressEntity> findAllByCityContaining(String keyword, Pageable pageable);
}