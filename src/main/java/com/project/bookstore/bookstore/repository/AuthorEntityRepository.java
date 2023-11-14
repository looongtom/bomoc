package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEntityRepository extends JpaRepository<AuthorEntity, Integer> {
}