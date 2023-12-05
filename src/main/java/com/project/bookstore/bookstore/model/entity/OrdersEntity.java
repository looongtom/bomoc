package com.project.bookstore.bookstore.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "orders", schema = "bo_mo_c", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "CustomerIdCus")
    private int customerIdCus;
    @Basic
    @Column(name = "VoucherId")
    private Integer voucherId;
    @Basic
    @Column(name = "OrderTotalPrice")
    private double orderTotalPrice;
    @Basic
    @Column(name = "Status")
    private Integer status;

    @Basic
    @Column(name = "CartId")
    private int cartId;

    @Basic
    @Column(name = "PaymentType")
    private int paymentType;

    @Basic
    @Column(name = "ShipmentType")
    private int shipmentType;

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

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
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

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(int shipmentType) {
        this.shipmentType = shipmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id && customerIdCus == that.customerIdCus && Double.compare(that.orderTotalPrice, orderTotalPrice) == 0 && cartId == that.cartId && paymentType == that.paymentType && shipmentType == that.shipmentType && Objects.equals(voucherId, that.voucherId) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerIdCus, voucherId, orderTotalPrice, status, cartId, paymentType, shipmentType);
    }
}
