//package com.project.bookstore.bookstore.controller;
//
//import com.project.bookstore.bookstore.exception.ResourceNotFoundException;
//import com.project.bookstore.bookstore.model.Order;
//import com.project.bookstore.bookstore.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000/")
//@RestController
//public class OrderController {
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @GetMapping("/orders")
//    public List<Order> getAllOrdersSortedById() {
//        return orderRepository.findAllByOrderByIdAsc();
//    }
//    @PostMapping("/order")
//    public ResponseEntity<?> addNewReview(@RequestBody Order order) {
//        try {
//            Order savedOrder = orderRepository.save(order);
//            return ResponseEntity.ok(savedOrder);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to order");
//        }
//    }
//    @GetMapping("/order/{userEmail}")
//    public List<Order> getCartItemsByEmail(@PathVariable String userEmail) {
//        return orderRepository.findByUserEmail(userEmail);
//    }
//    @DeleteMapping("/order/{id}")
//    String deleteOrder(@PathVariable Long id){
//        if(!orderRepository.existsById(id)){
//            throw new ResourceNotFoundException("Order not exist with id: " + id);
//        }
//        orderRepository.deleteById(id);
//        return  "Order with id "+id+" has been deleted success.";
//    }
//    @PutMapping("/order/{id}")
//    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody Order updatedOrder) {
//        try {
//            Order existingOrder = orderRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
//
//            existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
//            existingOrder.setQuantity(updatedOrder.getQuantity());
//            Order savedOrder = orderRepository.save(existingOrder);
//
//            return ResponseEntity.ok(savedOrder);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update order");
//        }
//    }
//}
