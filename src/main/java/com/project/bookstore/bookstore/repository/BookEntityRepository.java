package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Integer> {
    @Query("select (count(b) > 0) from BookEntity b where b.title = ?1 and b.authorId = ?2")
    boolean existsByTitleAndAuthor(String title, int authorId);

    Page<BookEntity> findByTitleContaining(String keyword, Pageable pageable);
}