package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "shipment", schema = "bo_mo_c", catalog = "")
public class ShipmentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "OrderId")
    private int orderId;
    @Basic
    @Column(name = "ShippingCost")
    private double shippingCost;
    @Basic
    @Column(name = "ShippingAddress")
    private String shippingAddress;
    @Basic
    @Column(name = "ShippingMethod")
    private String shippingMethod;
    @Basic
    @Column(name = "ShipperName")
    private String shipperName;
    @Basic
    @Column(name = "EstimateDeliveryDate")
    private Date estimateDeliveryDate;
    @Basic
    @Column(name = "ActualDeliveryDate")
    private Date actualDeliveryDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public Date getEstimateDeliveryDate() {
        return estimateDeliveryDate;
    }

    public void setEstimateDeliveryDate(Date estimateDeliveryDate) {
        this.estimateDeliveryDate = estimateDeliveryDate;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipmentEntity that = (ShipmentEntity) o;

        if (id != that.id) return false;
        if (orderId != that.orderId) return false;
        if (Double.compare(that.shippingCost, shippingCost) != 0) return false;
        if (shippingAddress != null ? !shippingAddress.equals(that.shippingAddress) : that.shippingAddress != null)
            return false;
        if (shippingMethod != null ? !shippingMethod.equals(that.shippingMethod) : that.shippingMethod != null)
            return false;
        if (shipperName != null ? !shipperName.equals(that.shipperName) : that.shipperName != null) return false;
        if (estimateDeliveryDate != null ? !estimateDeliveryDate.equals(that.estimateDeliveryDate) : that.estimateDeliveryDate != null)
            return false;
        if (actualDeliveryDate != null ? !actualDeliveryDate.equals(that.actualDeliveryDate) : that.actualDeliveryDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + orderId;
        temp = Double.doubleToLongBits(shippingCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (shippingMethod != null ? shippingMethod.hashCode() : 0);
        result = 31 * result + (shipperName != null ? shipperName.hashCode() : 0);
        result = 31 * result + (estimateDeliveryDate != null ? estimateDeliveryDate.hashCode() : 0);
        result = 31 * result + (actualDeliveryDate != null ? actualDeliveryDate.hashCode() : 0);
        return result;
    }
}
