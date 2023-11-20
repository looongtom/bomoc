package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.MobileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MobileEntityRepository extends JpaRepository<MobileEntity, Integer> {
    @Query("select (count(b) > 0) from MobileEntity b where b.name = ?1 and b.producerId = ?2")
    boolean existsByNameAndProcucerId(String name, int producerId);


    Page<MobileEntity> findByNameContaining(String keyword, Pageable pageable);


}