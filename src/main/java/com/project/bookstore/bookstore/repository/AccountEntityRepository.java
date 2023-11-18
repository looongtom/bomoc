package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Integer> {
}