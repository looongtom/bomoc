package com.project.bookstore.bookstore.model.request;

import java.util.Objects;

public class UpdateOrderRequest {
    private int orderId;
    private Integer voucherId;
    private Integer paymentType;
    private Integer shipmentType;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(Integer shipmentType) {
        this.shipmentType = shipmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateOrderRequest that = (UpdateOrderRequest) o;
        return orderId == that.orderId && Objects.equals(voucherId, that.voucherId) && Objects.equals(paymentType, that.paymentType) && Objects.equals(shipmentType, that.shipmentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, voucherId, paymentType, shipmentType);
    }
}
