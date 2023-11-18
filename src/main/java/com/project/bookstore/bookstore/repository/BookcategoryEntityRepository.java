package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.BookcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookcategoryEntityRepository extends JpaRepository<BookcategoryEntity, Integer> {
}