package com.project.bookstore.bookstore.service;

import com.project.bookstore.bookstore.model.dto.AppUser.AppUserDTO;
import com.project.bookstore.bookstore.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAppUserService {
    UserDetails loadUserByUsername(String email);
    String signUpUser(AppUser appUser);
    int enableAppUser(String email);

    AppUserDTO getInfo(Long id);

    AppUserDTO saveUser(AppUserDTO userDTO);

    AppUserDTO findUserByEmail(String email);
}
