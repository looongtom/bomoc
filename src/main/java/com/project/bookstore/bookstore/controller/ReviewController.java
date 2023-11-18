//package com.project.bookstore.bookstore.controller;
//
//import com.project.bookstore.bookstore.model.Review;
//import com.project.bookstore.bookstore.repository.ReviewRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000/")
//@RestController
//public class ReviewController {
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @GetMapping("/reviews/book/{bookId}")
//    public List<Review> getReviewsByBookId(@PathVariable Long bookId) {
//        return reviewRepository.findByBookId(bookId);
//    }
//
//    @PostMapping("/review")
//    public ResponseEntity<?> addNewReview(@RequestBody Review review) {
//        try {
//            Review savedReview = reviewRepository.save(review);
//            return ResponseEntity.ok(savedReview);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add review");
//        }
//    }
//
//}