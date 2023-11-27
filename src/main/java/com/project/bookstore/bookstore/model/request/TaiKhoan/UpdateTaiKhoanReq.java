package com.project.bookstore.bookstore.model.request.TaiKhoan;

import lombok.Data;

@Data
public class UpdateTaiKhoanReq {
    private String email;
    private String pass;
    private String username;
    private String phone;
}
