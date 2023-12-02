package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.CartEntity;
import com.project.bookstore.bookstore.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartEntityRepository extends JpaRepository<CartEntity, Integer> {
    @Query("select r.id from CartEntity r where r.userId = ?1 and r.status = 0")
    Integer getCartIdByUserId(int userId);

    @Query("select r.cartTotalPrice from CartEntity r where r.userId = ?1 and r.status = 0")
    Integer getCartTotalPriceByUserId(int userId);

    @Query("select r.status from CartEntity r where r.userId = ?1 and r.status = 0")
    Integer getCartStatusByUserId(int userId);

    @Query("select i from ItemEntity i, CartEntity c where c.userId = ?1 and c.status = 0 and i.cartId = c.id")
    List<ItemEntity> getAllItemInCartByUserId(int userId);
}