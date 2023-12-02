package com.project.bookstore.bookstore.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cart", schema = "bo_mo_c", catalog = "")
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "CartTotalPrice")
    private double cartTotalPrice;

    @Column(name = "userId")
    private int userId;

    // status: trường này để check xem giỏ hàng đã được thanh toán hay chưa
    // 0: chưa thanh toán
    // 1: đã thanh toán rồi
    @Basic
    @Column(name = "status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(double cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return id == that.id && Double.compare(that.cartTotalPrice, cartTotalPrice) == 0 && userId == that.userId && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartTotalPrice, userId, status);
    }
}
