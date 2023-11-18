package com.project.bookstore.bookstore.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "item", schema = "bo_mo_c", catalog = "")
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "MobileId")
    private int mobileId;
    @Basic
    @Column(name = "ClothesId")
    private int clothesId;
    @Basic
    @Column(name = "BookId")
    private int bookId;
    @Basic
    @Column(name = "Quantity")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public int getClothesId() {
        return clothesId;
    }

    public void setClothesId(int clothesId) {
        this.clothesId = clothesId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (id != that.id) return false;
        if (mobileId != that.mobileId) return false;
        if (clothesId != that.clothesId) return false;
        if (bookId != that.bookId) return false;
        if (quantity != that.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mobileId;
        result = 31 * result + clothesId;
        result = 31 * result + bookId;
        result = 31 * result + quantity;
        return result;
    }
}
