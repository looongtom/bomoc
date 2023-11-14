package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order", schema = "bo_mo_c", catalog = "")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "VoucherId")
    private int voucherId;
    @Basic
    @Column(name = "CustomerIdCus")
    private int customerIdCus;
    @Basic
    @Column(name = "OrderTotalPrice")
    private double orderTotalPrice;
    @Basic
    @Column(name = "Status")
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getCustomerIdCus() {
        return customerIdCus;
    }

    public void setCustomerIdCus(int customerIdCus) {
        this.customerIdCus = customerIdCus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (voucherId != that.voucherId) return false;
        if (customerIdCus != that.customerIdCus) return false;
        if (Double.compare(that.orderTotalPrice, orderTotalPrice) != 0) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + voucherId;
        result = 31 * result + customerIdCus;
        temp = Double.doubleToLongBits(orderTotalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
