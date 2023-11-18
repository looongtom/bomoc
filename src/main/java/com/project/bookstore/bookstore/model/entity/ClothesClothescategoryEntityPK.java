package com.project.bookstore.bookstore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ClothesClothescategoryEntityPK implements Serializable {
    @Column(name = "ClothesId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clothesId;
    @Column(name = "ClothesCategoryId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clothesCategoryId;

    public int getClothesId() {
        return clothesId;
    }

    public void setClothesId(int clothesId) {
        this.clothesId = clothesId;
    }

    public int getClothesCategoryId() {
        return clothesCategoryId;
    }

    public void setClothesCategoryId(int clothesCategoryId) {
        this.clothesCategoryId = clothesCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClothesClothescategoryEntityPK that = (ClothesClothescategoryEntityPK) o;

        if (clothesId != that.clothesId) return false;
        if (clothesCategoryId != that.clothesCategoryId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clothesId;
        result = 31 * result + clothesCategoryId;
        return result;
    }
}
