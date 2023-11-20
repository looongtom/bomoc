package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.ProducerEntity;
import com.project.bookstore.bookstore.repository.ProducerEntityRepository;
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
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    private ProducerEntityRepository producerEntityRepository;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<ProducerEntity> producerEntities  = null;
        if(keyword == null || keyword.isEmpty() ){
            producerEntities = producerEntityRepository.findAll(pageable);
        }else{
            producerEntities = producerEntityRepository.findByName(keyword,pageable);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listProducers",producerEntities.getContent());
        response.put("currentPage",producerEntities.getNumber());
        response.put("totalItems",producerEntities.getTotalElements());
        response.put("totalPages",producerEntities.getTotalPages());
        response.put("search",keyword);

        if(producerEntities.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return producerEntityRepository.findById(id)
                .map(producerEntity -> ResponseEntity.ok(producerEntity))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody ProducerEntity producerEntity){
        if(producerEntityRepository.existsByNameAndHeadQuarter(producerEntity.getName(),producerEntity.getHeadquarter())){
            return ResponseEntity.badRequest().body("Book already exists with the same title and author.");
        }
        ProducerEntity Producer=producerEntityRepository.save(producerEntity);
        return ResponseEntity.ok(Producer);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProducerEntity updateProducerEntity){
        ProducerEntity findProducer = producerEntityRepository.findById(updateProducerEntity.getId()).orElse(null);
        if(findProducer == null){
            return ResponseEntity.notFound().build();
        }
        if(producerEntityRepository.existsByNameAndHeadQuarter(updateProducerEntity.getName(),updateProducerEntity.getHeadquarter())){
            return ResponseEntity.badRequest().body("Mobile already exists with the same name and producer.");
        }
        ProducerEntity producer=producerEntityRepository.save(updateProducerEntity);
        return ResponseEntity.ok(producer);
    }
}
