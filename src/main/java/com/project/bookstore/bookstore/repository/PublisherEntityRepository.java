package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherEntityRepository extends JpaRepository<PublisherEntity, Integer> {
}