package com.project.bookstore.bookstore.model.dto.TaiKhoan;

import com.project.bookstore.bookstore.model.entity.TaiKhoan;

public class TaiKhoanMapper {
    public static TaiKhoanDTO toTaiKhoanDTO(TaiKhoan admin){
        return new TaiKhoanDTO()
                .setIdUser(admin.getId())
                .setUsername(admin.getUserName())
                .setPhone(admin.getPhone())
                .setPass(admin.getPass())
                .setEmail(admin.getEmail())
                .setIdRole(admin.getIdRole());
    }

    public static TaiKhoan toTaiKhoan(TaiKhoanDTO taiKhoanDTO){
        return new TaiKhoan()
                .setId(taiKhoanDTO.getIdUser())
                .setPass(taiKhoanDTO.getPass())
                .setPhone(taiKhoanDTO.getPhone())
                .setEmail(taiKhoanDTO.getEmail())
                .setUserName(taiKhoanDTO.getUsername())
                .setIdRole(taiKhoanDTO.getIdRole());
    }

}
