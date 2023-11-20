package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.entity.VoucherEntity;
import com.project.bookstore.bookstore.repository.VoucherEntityRepository;
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
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherEntityRepository voucherEntityRepository;


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<VoucherEntity> voucherEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            voucherEntityPage = voucherEntityRepository.findAll(pageable);
        }else{
            voucherEntityPage = voucherEntityRepository.findAllByNameDiscountContains(keyword,pageable);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listVouchers",voucherEntityPage.getContent());
        response.put("currentPage",voucherEntityPage.getNumber());
        response.put("totalItems",voucherEntityPage.getTotalElements());
        response.put("totalPages",voucherEntityPage.getTotalPages());
        response.put("search",keyword);

        if(voucherEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return voucherEntityRepository.findById(id)
                .map(VoucherEntity -> ResponseEntity.ok(VoucherEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody VoucherEntity VoucherEntity){
        VoucherEntity voucherEntity=voucherEntityRepository.save(VoucherEntity);
        return ResponseEntity.ok(voucherEntity);
    }




    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody VoucherEntity updateVoucherEntity){
        VoucherEntity VoucherEntity = voucherEntityRepository.findById(updateVoucherEntity.getId()).orElse(null);
        if(VoucherEntity == null){
            return ResponseEntity.notFound().build();
        }
        VoucherEntity voucherEntity= voucherEntityRepository.save(updateVoucherEntity);
        return ResponseEntity.ok(voucherEntity);
    }
}
