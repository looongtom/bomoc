package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clothes", schema = "bo_mo_c", catalog = "")
public class ClothesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "ItemId")
    private int itemId;
    @Basic
    @Column(name = "BrandId")
    private int brandId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Size")
    private String size;
    @Basic
    @Column(name = "Image")
    private String image;
    @Basic
    @Column(name = "Price")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClothesEntity that = (ClothesEntity) o;

        if (id != that.id) return false;
        if (itemId != that.itemId) return false;
        if (brandId != that.brandId) return false;
        if (price != that.price) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + itemId;
        result = 31 * result + brandId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
