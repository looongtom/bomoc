package com.project.bookstore.bookstore.controller;

import com.project.bookstore.bookstore.model.ResponseObject;
import com.project.bookstore.bookstore.model.User;
import com.project.bookstore.bookstore.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository repo;

    @PostMapping("/signup")
    ResponseEntity<ResponseObject> signup(@RequestBody User newUser) {
        List<User> foundUser = repo.findByEmail(newUser.getEmail().trim());
        if(foundUser.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Email already taken", "", "")
            );
        }
        newUser.setRole("User");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Signup successfully", String.valueOf(repo.save(newUser)), "")
        );
    }

    @PostMapping("/login")
    ResponseEntity<ResponseObject> login(@RequestBody User user, HttpSession session) {
        List<User> foundUser = repo.findByEmail(user.getEmail().trim());
        if (foundUser.size() == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", "Invalid credentials", "", "")
            );
        }

        User existingUser = foundUser.get(0);
        if (!existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", "Invalid credentials", "", "")
            );
        }

        //if (!existingUser.getRole().equals(user.getRole())) {
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    //new ResponseObject("failed", "Invalid role","")
            //);
        //}

        session.setAttribute("username", existingUser.getName());
        String userRole = existingUser.getRole();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Login successful", existingUser.getName(), userRole)
        );

    }
    @GetMapping("/user/{email}")
    public List<User> getUserByEmail(@PathVariable String email) {
        return repo.findByEmail(email.trim());
    }




}
