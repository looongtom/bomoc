package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, String> {
}