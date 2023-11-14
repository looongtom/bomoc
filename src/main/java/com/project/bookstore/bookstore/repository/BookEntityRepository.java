package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookEntityRepository extends JpaRepository<BookEntity, Integer> {
}