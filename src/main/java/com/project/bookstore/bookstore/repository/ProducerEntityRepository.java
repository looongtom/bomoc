package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerEntityRepository extends JpaRepository<ProducerEntity, Integer> {
}