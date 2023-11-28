package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.AccountEntity;
import com.project.bookstore.bookstore.model.entity.BrandEntity;
import com.project.bookstore.bookstore.model.entity.FullnameEntity;
import com.project.bookstore.bookstore.repository.AccountEntityRepository;
import com.project.bookstore.bookstore.repository.FullnameEntityRepository;
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
@RequestMapping("/api/fullname")
public class FullNameController {
    @Autowired
    private FullnameEntityRepository fullnameEntityRepository;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<FullnameEntity> fullnameEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            fullnameEntityPage = fullnameEntityRepository.findAll(pageable);
        }else{
            fullnameEntityPage = fullnameEntityRepository.findAllByLastNameContaining(keyword,pageable);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listFullNames",fullnameEntityPage.getContent());
        response.put("currentPage",fullnameEntityPage.getNumber());
        response.put("totalItems",fullnameEntityPage.getTotalElements());
        response.put("totalPages",fullnameEntityPage.getTotalPages());
        response.put("search",keyword);

        if(fullnameEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return fullnameEntityRepository.findById(id)
                .map(FullNameEntity -> ResponseEntity.ok(FullNameEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody FullnameEntity FullnameEntity){
        FullnameEntity fullnameEntity=fullnameEntityRepository.save(FullnameEntity);
        return ResponseEntity.ok(fullnameEntity);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody FullnameEntity updateFullnameEnitity){
        FullnameEntity FullnameEntity = fullnameEntityRepository.findById(updateFullnameEnitity.getIdFullName()).orElse(null);
        if(FullnameEntity == null){
            return ResponseEntity.notFound().build();
        }
        FullnameEntity fullnameEntity= fullnameEntityRepository.save(updateFullnameEnitity);
        return ResponseEntity.ok(fullnameEntity);
    }


}
