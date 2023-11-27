package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Integer> {
    List<TaiKhoan>findByIdRole(Integer id);
    List<TaiKhoan> findByEmail(String email);
}
