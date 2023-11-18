package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Integer> {
}