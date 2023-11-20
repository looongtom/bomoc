package com.project.bookstore.bookstore.controller.api;



import com.project.bookstore.bookstore.model.entity.BrandEntity;
import com.project.bookstore.bookstore.repository.BrandEntityRepository;
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
@RequestMapping("/brand")
@Log4j2
public class BrandController {
    @Autowired
    private BrandEntityRepository brandEntityRepository;

    @GetMapping("/search")
        public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
            Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
            Page<BrandEntity> brandEntityPage = null;
            if(keyword == null || keyword.isEmpty() ){
                brandEntityPage = brandEntityRepository.findAll(pageable);
            }else{
                brandEntityPage = brandEntityRepository.findByNameContaining(keyword,pageable);
            }
            Map<String,Object> response = new HashMap<>();
            response.put("listAuthors",brandEntityPage.getContent());
            response.put("currentPage",brandEntityPage.getNumber());
            response.put("totalItems",brandEntityPage.getTotalElements());
            response.put("totalPages",brandEntityPage.getTotalPages());
            response.put("search",keyword);

        if(brandEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return brandEntityRepository.findById(id)
                .map(BrandEntity -> ResponseEntity.ok(BrandEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody BrandEntity BrandEntity){
        BrandEntity brandEntity=brandEntityRepository.save(BrandEntity);
        return ResponseEntity.ok(brandEntity);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody BrandEntity updateBrandEntity){
        BrandEntity BrandEntity = brandEntityRepository.findById(updateBrandEntity.getId()).orElse(null);
        if(BrandEntity == null){
            return ResponseEntity.notFound().build();
        }
        BrandEntity brandEntity=brandEntityRepository.save(updateBrandEntity);
        return ResponseEntity.ok(brandEntity);
    }
}
