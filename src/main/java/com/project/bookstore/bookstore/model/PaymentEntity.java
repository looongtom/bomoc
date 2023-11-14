package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment", schema = "bo_mo_c", catalog = "")
public class PaymentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private String id;
    @Basic
    @Column(name = "OrderId")
    private int orderId;
    @Basic
    @Column(name = "TotalPrice")
    private double totalPrice;
    @Basic
    @Column(name = "Status")
    private boolean status;
    @Basic
    @Column(name = "CardNumber")
    private Integer cardNumber;
    @Basic
    @Column(name = "GiveMoney")
    private Double giveMoney;
    @Basic
    @Column(name = "ChangeMoney")
    private Double changeMoney;
    @Basic
    @Column(name = "Discriminator")
    private String discriminator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(Double giveMoney) {
        this.giveMoney = giveMoney;
    }

    public Double getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(Double changeMoney) {
        this.changeMoney = changeMoney;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (orderId != that.orderId) return false;
        if (Double.compare(that.totalPrice, totalPrice) != 0) return false;
        if (status != that.status) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (giveMoney != null ? !giveMoney.equals(that.giveMoney) : that.giveMoney != null) return false;
        if (changeMoney != null ? !changeMoney.equals(that.changeMoney) : that.changeMoney != null) return false;
        if (discriminator != null ? !discriminator.equals(that.discriminator) : that.discriminator != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + orderId;
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (giveMoney != null ? giveMoney.hashCode() : 0);
        result = 31 * result + (changeMoney != null ? changeMoney.hashCode() : 0);
        result = 31 * result + (discriminator != null ? discriminator.hashCode() : 0);
        return result;
    }
}
