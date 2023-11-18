package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.PublisherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PublisherEntityRepository extends JpaRepository<PublisherEntity, Integer> {
    Page<PublisherEntity> findByName(String keyword, Pageable pageable);

    @Query("select (count(p) > 0) from PublisherEntity p where p.name = ?1 and p.headquarter = ?2")
    boolean existsByNameAndHeadQuarter(String name, String headquarter);
}