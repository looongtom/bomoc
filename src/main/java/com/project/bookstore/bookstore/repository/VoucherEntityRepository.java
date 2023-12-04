package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.VoucherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface VoucherEntityRepository extends JpaRepository<VoucherEntity, Integer> {
    List<VoucherEntity> findAllByExprireTimeAfter(LocalDate currentDate);
}