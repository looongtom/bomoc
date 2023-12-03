package com.project.bookstore.bookstore.model.request;

import java.util.Objects;

public class AddToCartRequest {
    private int userId;
    private Integer mobileId;
    private Integer bookId;
    private Integer clothesId;
    private int quantity;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getMobileId() {
        return mobileId;
    }

    public void setMobileId(Integer mobileId) {
        this.mobileId = mobileId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getClothesId() {
        return clothesId;
    }

    public void setClothesId(Integer clothesId) {
        this.clothesId = clothesId;
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
        AddToCartRequest that = (AddToCartRequest) o;
        return userId == that.userId && quantity == that.quantity && mobileId.equals(that.mobileId) && bookId.equals(that.bookId) && clothesId.equals(that.clothesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, mobileId, bookId, clothesId, quantity);
    }
}
