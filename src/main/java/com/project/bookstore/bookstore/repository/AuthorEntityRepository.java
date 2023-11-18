package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.AuthorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEntityRepository extends JpaRepository<AuthorEntity, Integer> {
    Page<AuthorEntity> findByNameContaining(String keyword, Pageable pageable);
}