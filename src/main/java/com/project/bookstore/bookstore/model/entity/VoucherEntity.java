package com.project.bookstore.bookstore.model.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "voucher", schema = "bo_mo_c", catalog = "")
public class VoucherEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "NameDiscount")
    private String nameDiscount;
    @Basic
    @Column(name = "ExprireTime")
    private Date exprireTime;
    @Basic
    @Column(name = "DiscountPercent")
    private double discountPercent;
    @Basic
    @Column(name = "ConditionUsing")
    private String conditionUsing;

    @Column(name = "VoucherType")
    private Integer voucherType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDiscount() {
        return nameDiscount;
    }

    public void setNameDiscount(String nameDiscount) {
        this.nameDiscount = nameDiscount;
    }

    public Date getExprireTime() {
        return exprireTime;
    }

    public void setExprireTime(Date exprireTime) {
        this.exprireTime = exprireTime;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getConditionUsing() {
        return conditionUsing;
    }

    public void setConditionUsing(String conditionUsing) {
        this.conditionUsing = conditionUsing;
    }
    public Integer getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Integer voucherType) {
        this.voucherType = voucherType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoucherEntity that = (VoucherEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.discountPercent, discountPercent) != 0) return false;
        if (!nameDiscount.equals(that.nameDiscount)) return false;
        if (!exprireTime.equals(that.exprireTime)) return false;
        if (!conditionUsing.equals(that.conditionUsing)) return false;
        return voucherType.equals(that.voucherType);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + nameDiscount.hashCode();
        result = 31 * result + exprireTime.hashCode();
        temp = Double.doubleToLongBits(discountPercent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + conditionUsing.hashCode();
        result = 31 * result + voucherType.hashCode();
        return result;
    }
}
