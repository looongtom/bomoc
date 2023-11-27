package com.project.bookstore.bookstore.service;


import com.project.bookstore.bookstore.model.dto.TaiKhoan.TaiKhoanDTO;
import com.project.bookstore.bookstore.model.entity.TaiKhoan;
import com.project.bookstore.bookstore.model.request.TaiKhoan.FindTaiKhoanReq;

import java.util.List;


public interface ITaiKhoanService {
    List<TaiKhoanDTO> getListTaiKhoan(Integer idTaiKhoan);

    TaiKhoanDTO findTaiKhoanByEmail(String email);

    TaiKhoanDTO signup(TaiKhoanDTO taiKhoanDTO);

    TaiKhoan update(Integer userID, TaiKhoanDTO taiKhoanDTO);

    List<TaiKhoanDTO>findUser(FindTaiKhoanReq findTaiKhoanReq);
}
