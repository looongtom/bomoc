package com.project.bookstore.bookstore.controller.api;


import com.project.bookstore.bookstore.model.entity.AccountEntity;
import com.project.bookstore.bookstore.model.entity.BrandEntity;
import com.project.bookstore.bookstore.model.entity.RatingEntity;
import com.project.bookstore.bookstore.repository.AccountEntityRepository;
import com.project.bookstore.bookstore.repository.BrandEntityRepository;
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
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountEntityRepository accountEntityRepository;


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<AccountEntity> accountEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            accountEntityPage = accountEntityRepository.findAll(pageable);
        }else{
            accountEntityPage = accountEntityRepository.findAllByUsernameContaining(keyword,pageable);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("listAccounts",accountEntityPage.getContent());
        response.put("currentPage",accountEntityPage.getNumber());
        response.put("totalItems",accountEntityPage.getTotalElements());
        response.put("totalPages",accountEntityPage.getTotalPages());
        response.put("search",keyword);

        if(accountEntityPage.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return accountEntityRepository.findById(id)
                .map(AccountEntity -> ResponseEntity.ok(AccountEntity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody AccountEntity updateAccountEntity){
        AccountEntity AccountEntity = accountEntityRepository.findById(updateAccountEntity.getIdAccount()).orElse(null);
        if(AccountEntity == null){
            return ResponseEntity.notFound().build();
        }
        AccountEntity accountEntity= accountEntityRepository.save(updateAccountEntity);
        return ResponseEntity.ok(accountEntity);
    }

}
