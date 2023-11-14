package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillEntityRepository extends JpaRepository<BillEntity, Integer> {
}