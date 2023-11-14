package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherEntityRepository extends JpaRepository<VoucherEntity, Integer> {
}