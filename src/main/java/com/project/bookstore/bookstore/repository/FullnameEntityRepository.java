package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.BrandEntity;
import com.project.bookstore.bookstore.model.entity.FullnameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FullnameEntityRepository extends JpaRepository<FullnameEntity, Integer> {
    Page<FullnameEntity> findAllByLastNameContaining(String keyword, Pageable pageable);

}