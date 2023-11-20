package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.BrandEntity;
import com.project.bookstore.bookstore.model.entity.RatingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingEntityRepository extends JpaRepository<RatingEntity, Integer> {

    @Query("select r from RatingEntity r where (r.ratingScore =:keyword or :keyword is null)")
    Page<RatingEntity> findAllByRatingScoreContaining(Integer keyword, Pageable pageable);

}