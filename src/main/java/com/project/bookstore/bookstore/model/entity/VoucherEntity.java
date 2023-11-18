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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoucherEntity that = (VoucherEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.discountPercent, discountPercent) != 0) return false;
        if (nameDiscount != null ? !nameDiscount.equals(that.nameDiscount) : that.nameDiscount != null) return false;
        if (exprireTime != null ? !exprireTime.equals(that.exprireTime) : that.exprireTime != null) return false;
        if (conditionUsing != null ? !conditionUsing.equals(that.conditionUsing) : that.conditionUsing != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (nameDiscount != null ? nameDiscount.hashCode() : 0);
        result = 31 * result + (exprireTime != null ? exprireTime.hashCode() : 0);
        temp = Double.doubleToLongBits(discountPercent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (conditionUsing != null ? conditionUsing.hashCode() : 0);
        return result;
    }
}
