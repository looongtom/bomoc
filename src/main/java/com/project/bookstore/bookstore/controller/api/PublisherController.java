package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.BookEntity;
import com.project.bookstore.bookstore.model.entity.PublisherEntity;
import com.project.bookstore.bookstore.repository.PublisherEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
    @Autowired
    private PublisherEntityRepository publisherEntityRepository;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<PublisherEntity> publisherEntities  = null;
        if(keyword == null || keyword.isEmpty() ){
            publisherEntities = publisherEntityRepository.findAll(pageable);
        }else{
            publisherEntities = publisherEntityRepository.findByName(keyword,pageable);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listPublishers",publisherEntities.getContent());
        response.put("currentPage",publisherEntities.getNumber());
        response.put("totalItems",publisherEntities.getTotalElements());
        response.put("totalPages",publisherEntities.getTotalPages());
        response.put("search",keyword);

        if(publisherEntities.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return publisherEntityRepository.findById(id)
                .map(publisherEntity -> ResponseEntity.ok(publisherEntity))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody PublisherEntity publisherEntity){
        if(publisherEntityRepository.existsByNameAndHeadQuarter(publisherEntity.getName(),publisherEntity.getHeadquarter())){
            return ResponseEntity.badRequest().body("Book already exists with the same title and author.");
        }
        PublisherEntity publisher=publisherEntityRepository.save(publisherEntity);
        return ResponseEntity.ok(publisher);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody PublisherEntity updatePublisherEntity){
        PublisherEntity findPublisher = publisherEntityRepository.findById(updatePublisherEntity.getId()).orElse(null);
        if(findPublisher == null){
            return ResponseEntity.notFound().build();
        }
        if(publisherEntityRepository.existsByNameAndHeadQuarter(updatePublisherEntity.getName(),updatePublisherEntity.getHeadquarter())){
            return ResponseEntity.badRequest().body("Book already exists with the same title and author.");
        }
        PublisherEntity publisher=publisherEntityRepository.save(updatePublisherEntity);
        return ResponseEntity.ok(publisher);
    }
}
