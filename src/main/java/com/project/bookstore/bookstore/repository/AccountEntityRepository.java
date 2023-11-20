package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.AccountEntity;
import com.project.bookstore.bookstore.model.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Integer> {
    Page<AccountEntity> findAllByUsernameContaining(String keyword, Pageable pageable);

}