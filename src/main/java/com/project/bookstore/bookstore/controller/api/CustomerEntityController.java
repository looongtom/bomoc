package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.dto.CustomerDTO;
import com.project.bookstore.bookstore.model.entity.AccountEntity;
import com.project.bookstore.bookstore.model.entity.CustomerEntity;
import com.project.bookstore.bookstore.model.entity.ProducerEntity;
import com.project.bookstore.bookstore.repository.*;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/customer")
public class CustomerEntityController {
    @Autowired
    private CustomerEntityRepository customerEntityRepository;
    @Autowired
    private AccountEntityRepository accountEntityRepository;
    @Autowired
    private FullnameEntityRepository fullnameEntityRepository;
    @Autowired
    private  AddressEntityRepository addressEntityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<CustomerEntity> customerEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            customerEntityPage =customerEntityRepository.findAll(pageable);
        }else{
            customerEntityPage = customerEntityRepository.findByNameContaining(keyword,pageable);
        }

        Page<CustomerDTO> customerDTOS =  customerEntityPage.map(this::convertToDTO);

        Map<String,Object> response = new HashMap<>();
        response.put("listCustomer",customerDTOS.getContent());
        response.put("currentPage",customerEntityPage.getNumber());
        response.put("totalItems",customerEntityPage.getTotalElements());
        response.put("totalPages",customerEntityPage.getTotalPages());
        response.put("search",keyword);

        if(customerDTOS.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return customerEntityRepository.findById(id)
                .map(customerEntity -> ResponseEntity.ok(convertToDTO(customerEntity)))
                .orElse(ResponseEntity.notFound().build());
    }




    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody CustomerEntity updateCustomerEntity){
        CustomerEntity CustomerEntity = customerEntityRepository.findById(updateCustomerEntity.getIdCus()).orElse(null);
        if(CustomerEntity == null){
            return ResponseEntity.notFound().build();
        }
        if(customerEntityRepository.existsByNameAndIdCus(updateCustomerEntity.getName(), updateCustomerEntity.getIdCus())){
            return ResponseEntity.badRequest().body("Customer already exists with the same id.");
        }
        CustomerEntity savedCustomer=customerEntityRepository.save(updateCustomerEntity);
        return ResponseEntity.ok(savedCustomer);
    }

    private CustomerDTO convertToDTO(CustomerEntity customerEntity)
    {
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        AccountEntity findAccount = accountEntityRepository.findById(customerEntity.getIdCus()).orElse(null);
        if(findAccount != null)
            customerDTO.setUsername(findAccount.getUsername());
        else customerDTO.setUsername("Unknown account");
        return  customerDTO;
    }
}
