package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.AddressEntity;
import com.project.bookstore.bookstore.model.entity.RatingEntity;
import com.project.bookstore.bookstore.model.entity.VoucherEntity;
import com.project.bookstore.bookstore.repository.AddressEntityRepository;
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
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressEntityRepository addressEntityRepository;


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<AddressEntity> addressEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            addressEntityPage = addressEntityRepository.findAll(pageable);
        }else{
            addressEntityPage = addressEntityRepository.findAllByCityContaining(keyword,pageable);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listAddress",addressEntityPage.getContent());
        response.put("currentPage",addressEntityPage.getNumber());
        response.put("totalItems",addressEntityPage.getTotalElements());
        response.put("totalPages",addressEntityPage.getTotalPages());
        response.put("search",keyword);

        if(addressEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return addressEntityRepository.findById(id)
                .map(AddressEntity -> ResponseEntity.ok(AddressEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody AddressEntity updateAddressEntity){
        AddressEntity AddressEntity = addressEntityRepository.findById(updateAddressEntity.getIdAddress()).orElse(null);
        if(AddressEntity == null){
            return ResponseEntity.notFound().build();
        }
        AddressEntity addressEntity= addressEntityRepository.save(updateAddressEntity);
        return ResponseEntity.ok(addressEntity);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody AddressEntity AddressEntity){
        AddressEntity addressEntity=addressEntityRepository.save(AddressEntity);
        return ResponseEntity.ok(AddressEntity);
    }
}
