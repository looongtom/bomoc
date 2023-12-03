package com.project.bookstore.bookstore.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "item", schema = "bo_mo_c", catalog = "")
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "MobileId")
    private Integer mobileId;
    @Basic
    @Column(name = "ClothesId")
    private Integer clothesId;
    @Basic
    @Column(name = "BookId")
    private Integer bookId;
    @Basic
    @Column(name = "Quantity")
    private int quantity;
    @Basic
    @Column(name = "cartId")
    private int cartId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMobileId() {
        return mobileId;
    }

    public void setMobileId(Integer mobileId) {
        this.mobileId = mobileId;
    }

    public Integer getClothesId() {
        return clothesId;
    }

    public void setClothesId(Integer clothesId) {
        this.clothesId = clothesId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return id == that.id && quantity == that.quantity && cartId == that.cartId && Objects.equals(mobileId, that.mobileId) && Objects.equals(clothesId, that.clothesId) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mobileId, clothesId, bookId, quantity, cartId);
    }
}
