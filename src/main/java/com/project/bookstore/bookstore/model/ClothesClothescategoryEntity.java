package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clothes_clothescategory", schema = "bo_mo_c", catalog = "")
@IdClass(ClothesClothescategoryEntityPK.class)
public class ClothesClothescategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ClothesId")
    private int clothesId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ClothesCategoryId")
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

        ClothesClothescategoryEntity that = (ClothesClothescategoryEntity) o;

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
