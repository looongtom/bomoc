package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.ClothesEntity;
import com.project.bookstore.bookstore.model.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Integer> {
    @Query("select (count(b) > 0) from CustomerEntity b where b.name = ?1 and  b.idCus = ?2")
    boolean existsByNameAndIdCus(String name, int  idCus);

    Page<CustomerEntity> findByNameContaining(String keyword, Pageable pageable);
}