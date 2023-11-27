package com.project.bookstore.bookstore.model.dto.AppUser;

import com.project.bookstore.bookstore.model.entity.AppUser;

public class AppUserMapper {

    public static AppUserDTO appUserDTO(AppUser appUser){
        return new AppUserDTO()
                .setIdAppUser(appUser.getId())
                .setFirstName(appUser.getFirstName())
                .setLastName(appUser.getLastName())
                .setEmail(appUser.getEmail())
                .setPhoto(appUser.getPhoto());
    }

    public static AppUser toAppUser(AppUserDTO appUserDTO){
        return new AppUser()
                .setId(appUserDTO.getIdAppUser())
                .setFirstName(appUserDTO.getFirstName())
                .setLastName(appUserDTO.getLastName())
                .setEmail(appUserDTO.getEmail())
                .setPhoto(appUserDTO.getPhoto());
    }

}
