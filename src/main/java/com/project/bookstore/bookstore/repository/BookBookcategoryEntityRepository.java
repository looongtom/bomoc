package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.BookBookcategoryEntity;
import com.project.bookstore.bookstore.model.BookBookcategoryEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBookcategoryEntityRepository extends JpaRepository<BookBookcategoryEntity, BookBookcategoryEntityPK> {
}