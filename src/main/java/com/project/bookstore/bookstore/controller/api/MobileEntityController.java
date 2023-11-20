package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.dto.MobileDTO;
import com.project.bookstore.bookstore.model.entity.MobileEntity;
import com.project.bookstore.bookstore.model.entity.ProducerEntity;
import com.project.bookstore.bookstore.repository.MobileEntityRepository;
import com.project.bookstore.bookstore.repository.ProducerEntityRepository;
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
@RequestMapping("/mobiles")
public class MobileEntityController {
    @Autowired
    private MobileEntityRepository mobileEntityRepository;
    @Autowired
    private ProducerEntityRepository producerEntityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword, int page, int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<MobileEntity> mobileEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            mobileEntityPage = mobileEntityRepository.findAll(pageable);
        }else{
            mobileEntityPage = mobileEntityRepository.findByNameContaining(keyword,pageable);
        }

        Page<MobileDTO> mobileDTOS =  mobileEntityPage.map(this::convertToDTO);

        Map<String,Object> response = new HashMap<>();
        response.put("listMobiles",mobileDTOS.getContent());
        response.put("currentPage",mobileEntityPage.getNumber());
        response.put("totalItems",mobileEntityPage.getTotalElements());
        response.put("totalPages",mobileEntityPage.getTotalPages());
        response.put("search",keyword);

        if(mobileDTOS.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return mobileEntityRepository.findById(id)
                .map(mobileEntity -> ResponseEntity.ok(convertToDTO(mobileEntity)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody MobileEntity mobileEntity){
        if(mobileEntityRepository.existsByNameAndProcucerId(mobileEntity.getName(), mobileEntity.getProducerId())){
            return ResponseEntity.badRequest().body("Mobile already exists with the same name and producer.");
        }
        MobileEntity savedMobile=mobileEntityRepository.save(mobileEntity);
        return ResponseEntity.ok(savedMobile);
    }


    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody MobileEntity updateMobileEntity){
        MobileEntity MobileEntity = mobileEntityRepository.findById(updateMobileEntity.getId()).orElse(null);
        if(MobileEntity == null){
            return ResponseEntity.notFound().build();
        }
        if(mobileEntityRepository.existsByNameAndProcucerId(updateMobileEntity.getName(),updateMobileEntity.getProducerId())){
            return ResponseEntity.badRequest().body("Mobile already exists with the same name and producer.");
        }
        MobileEntity savedMobile=mobileEntityRepository.save(updateMobileEntity);
        return ResponseEntity.ok(savedMobile);
    }

    private MobileDTO convertToDTO(MobileEntity mobileEntity)
    {
        MobileDTO mobileDTO = modelMapper.map(mobileEntity, MobileDTO.class);
        ProducerEntity findProducer = producerEntityRepository.findById(mobileEntity.getProducerId()).orElse(null);
        if(findProducer != null)
            mobileDTO.setProducerName(findProducer.getName());
        else mobileDTO.setProducerName("Unknown producer");
        return  mobileDTO;
    }
}
