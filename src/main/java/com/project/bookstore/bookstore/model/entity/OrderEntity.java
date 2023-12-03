package com.project.bookstore.bookstore.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "order", schema = "bo_mo_c", catalog = "")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "CustomerIdCus")
    private int customerIdCus;
    @Basic
    @Column(name = "VoucherId")
    private int voucherId;
    @Basic
    @Column(name = "OrderTotalPrice")
    private double orderTotalPrice;
    @Basic
    @Column(name = "Status")
    private Integer status;

    @Basic
    @Column(name = "cartId")
    private int cartId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerIdCus() {
        return customerIdCus;
    }

    public void setCustomerIdCus(int customerIdCus) {
        this.customerIdCus = customerIdCus;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        OrderEntity that = (OrderEntity) o;
        return id == that.id && customerIdCus == that.customerIdCus && voucherId == that.voucherId && Double.compare(that.orderTotalPrice, orderTotalPrice) == 0 && cartId == that.cartId && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerIdCus, voucherId, orderTotalPrice, status, cartId);
    }
}
