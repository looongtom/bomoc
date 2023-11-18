package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.BookBookcategoryEntity;
import com.project.bookstore.bookstore.model.entity.BookBookcategoryEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookBookcategoryEntityRepository extends JpaRepository<BookBookcategoryEntity, BookBookcategoryEntityPK> {
    List<BookBookcategoryEntity> findByBookId(int id);

    List<BookBookcategoryEntity> findByBookCategoryId(int id);
}