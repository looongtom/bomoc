package com.project.bookstore.bookstore.controller.api;


import com.project.bookstore.bookstore.model.entity.RatingEntity;
import com.project.bookstore.bookstore.model.entity.VoucherEntity;
import com.project.bookstore.bookstore.repository.RatingEntityRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rating")
@Log4j2
public class RatingController {
    @Autowired
    private RatingEntityRepository ratingEntityRepository;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<RatingEntity> ratingEntityPage = null;
        log.info("keyword = {}", keyword);
        if(keyword == null || keyword.isEmpty() ){
            ratingEntityPage = ratingEntityRepository.findAll(pageable);
        }else{
            ratingEntityPage = ratingEntityRepository.findAllByRatingScoreContaining(Integer.parseInt(keyword),pageable); //
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listRatings",ratingEntityPage.getContent());
        response.put("currentPage",ratingEntityPage.getNumber());
        response.put("totalItems",ratingEntityPage.getTotalElements());
        response.put("totalPages",ratingEntityPage.getTotalPages());
        response.put("search",keyword);

        if(ratingEntityPage.hasContent()){

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ratingEntityRepository.findById(id)
                .map(RatingEntity -> ResponseEntity.ok(RatingEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody RatingEntity RatingEntity){
        RatingEntity ratingEntity=ratingEntityRepository.save(RatingEntity);
        return ResponseEntity.ok(ratingEntity);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody RatingEntity updateRatingEntity){
        RatingEntity RatingEntity = ratingEntityRepository.findById(updateRatingEntity.getId()).orElse(null);
        if(RatingEntity == null){
            return ResponseEntity.notFound().build();
        }
        RatingEntity ratingEntity=ratingEntityRepository.save(updateRatingEntity);
        return ResponseEntity.ok(ratingEntity);
    }
}
