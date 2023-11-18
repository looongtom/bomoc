package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.dto.BookDTO;
import com.project.bookstore.bookstore.model.entity.AuthorEntity;
import com.project.bookstore.bookstore.model.entity.BookBookcategoryEntity;
import com.project.bookstore.bookstore.model.entity.BookEntity;
import com.project.bookstore.bookstore.model.entity.PublisherEntity;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookEntityController {
    @Autowired
    private BookEntityRepository bookEntityRepository;// khai báo repository của book, các class khác khai báo tương tự (Khác tên class)
    @Autowired
    private AuthorEntityRepository authorEntityRepository;// khai báo để lấy tên author từ author_id
    @Autowired
    private PublisherEntityRepository publisherEntityRepository;// khai báo để lấy tên publisher từ publisher_id
    @Autowired
    private ModelMapper modelMapper; //khai báo để chuyển từ entity sang dto

    @Autowired
    private BookcategoryEntityRepository bookCategoryRepository;//khai báo để lấy tên category name từ category_id
    @Autowired
    private BookBookcategoryEntityRepository book_bookCategoryRepository;//khai báo để lấy tên category từ book_id
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam String keyword,int page,int size, String sortString){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortString));
        Page<BookEntity> bookEntityPage = null;
        if(keyword == null || keyword.isEmpty() ){
            bookEntityPage = bookEntityRepository.findAll(pageable);
        }else{
            bookEntityPage = bookEntityRepository.findByTitleContaining(keyword,pageable);
        }

        Page<BookDTO> bookDTOS =  bookEntityPage.map(this::convertToDTO); // convert từ List<BookEntity> sang List<BookDTO> để hiển thị tên publisher và tên author

        Map<String,Object> response = new HashMap<>();
        response.put("listBooks",bookDTOS.getContent());
        response.put("currentPage",bookEntityPage.getNumber());
        response.put("totalItems",bookEntityPage.getTotalElements());
        response.put("totalPages",bookEntityPage.getTotalPages());
        response.put("search",keyword);

        if(bookDTOS.hasContent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        return bookEntityRepository.findById(id)
                .map(bookEntity -> ResponseEntity.ok(convertToDTO(bookEntity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody BookEntity bookEntity){
        if(bookEntityRepository.existsByTitleAndAuthor(bookEntity.getTitle(),bookEntity.getAuthorId())){
            return ResponseEntity.badRequest().body("Book already exists with the same title and author.");
        }
        BookEntity savedBook=bookEntityRepository.save(bookEntity);
        return ResponseEntity.ok(savedBook);
    }
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody BookEntity updateBookEntity){
        BookEntity bookEntity = bookEntityRepository.findById(updateBookEntity.getId()).orElse(null);
        if(bookEntity == null){
            return ResponseEntity.notFound().build();
        }
        if(bookEntityRepository.existsByTitleAndAuthor(updateBookEntity.getTitle(),updateBookEntity.getAuthorId())){
            return ResponseEntity.badRequest().body("Book already exists with the same title and author.");
        }
        BookEntity savedBook=bookEntityRepository.save(updateBookEntity);
        return ResponseEntity.ok(savedBook);
    }

    private BookDTO convertToDTO(BookEntity bookEntity){
        BookDTO bookDTO = modelMapper.map(bookEntity,BookDTO.class);
        AuthorEntity findAuthor = authorEntityRepository.findById(bookEntity.getAuthorId()).orElse(null);
        PublisherEntity findPublisher = publisherEntityRepository.findById(bookEntity.getPublisherId()).orElse(null);
        if(findAuthor != null)
            bookDTO.setAuthorName(findAuthor.getName());
        else bookDTO.setAuthorName("Unknown author");
        if(findPublisher != null)
            bookDTO.setPublisherName(findPublisher.getName());
        else bookDTO.setPublisherName("Unknown publisher");

        List<BookBookcategoryEntity> getBookCategory = book_bookCategoryRepository.findByBookId(bookEntity.getId());
        List<String> categoryName = getBookCategory.stream().map(bookBookcategoryEntity -> {
            return bookCategoryRepository.findById(bookBookcategoryEntity.getBookCategoryId()).orElse(null).getName();
        }).collect(Collectors.toList());
        if(categoryName.isEmpty())
            categoryName.add("No belong to any categories");
        else bookDTO.setCategoryName(categoryName);
        return bookDTO;
    }

}
