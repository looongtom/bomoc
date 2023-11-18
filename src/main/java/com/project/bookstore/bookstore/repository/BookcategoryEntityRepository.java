package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.BookcategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookcategoryEntityRepository extends JpaRepository<BookcategoryEntity, Integer> {
    Page<BookcategoryEntity> findByNameContaining(String keyword, Pageable pageable);
    @Query("select (count(b) > 0) from BookcategoryEntity b where b.name = ?1")

    boolean existsByName(String name);
}