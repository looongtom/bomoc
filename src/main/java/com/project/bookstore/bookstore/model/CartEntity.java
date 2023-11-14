package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

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
    @Basic
    @Column(name = "ItemId")
    private int itemId;
    @Basic
    @Column(name = "OrderId")
    private int orderId;

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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartEntity that = (CartEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.cartTotalPrice, cartTotalPrice) != 0) return false;
        if (itemId != that.itemId) return false;
        if (orderId != that.orderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(cartTotalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + itemId;
        result = 31 * result + orderId;
        return result;
    }
}
