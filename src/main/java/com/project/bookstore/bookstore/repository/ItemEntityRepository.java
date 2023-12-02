package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Integer> {
    @Query("select b.title from BookEntity b where b.id = ?1")
    String getBookItemName(int id);

    @Query("select b.name from MobileEntity b where b.id = ?1")
    String getMobileItemName(int id);

    @Query("select b.name from ClothesEntity b where b.id = ?1")
    String getClothesItemName(int id);

    @Query("select b.price from BookEntity b where b.id = ?1")
    double getBookItemPrice(int id);

    @Query("select b.price from MobileEntity b where b.id = ?1")
    double getMobileItemPrice(int id);

    @Query("select b.price from ClothesEntity b where b.id = ?1")
    double getClothesItemPrice(int id);

    @Query("select b from ItemEntity b where b.bookId = ?1 and b.cartId = ?2")
    ItemEntity existBookItemInCart(Integer bookId, int cartId);

    @Query("select b from ItemEntity b where b.mobileId = ?1 and b.cartId = ?2")
    ItemEntity existMobileItemInCart(Integer mobileId, int cartId);

    @Query("select b from ItemEntity b where b.clothesId = ?1 and b.cartId = ?2")
    ItemEntity existClothesItemInCart(Integer clothesId, int cartId);
}