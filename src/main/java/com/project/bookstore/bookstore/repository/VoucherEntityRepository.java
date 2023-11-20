package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.VoucherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherEntityRepository extends JpaRepository<VoucherEntity, Integer> {
    Page<VoucherEntity> findAllByNameDiscountContains(String keyword, Pageable pageable);
}