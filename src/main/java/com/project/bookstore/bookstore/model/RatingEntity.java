package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "rating", schema = "bo_mo_c", catalog = "")
public class RatingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "ClothesId")
    private int clothesId;
    @Basic
    @Column(name = "MobileId")
    private int mobileId;
    @Basic
    @Column(name = "BookId")
    private int bookId;
    @Basic
    @Column(name = "CreatedDate")
    private Date createdDate;
    @Basic
    @Column(name = "RatingScore")
    private int ratingScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClothesId() {
        return clothesId;
    }

    public void setClothesId(int clothesId) {
        this.clothesId = clothesId;
    }

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingEntity that = (RatingEntity) o;

        if (id != that.id) return false;
        if (clothesId != that.clothesId) return false;
        if (mobileId != that.mobileId) return false;
        if (bookId != that.bookId) return false;
        if (ratingScore != that.ratingScore) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clothesId;
        result = 31 * result + mobileId;
        result = 31 * result + bookId;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + ratingScore;
        return result;
    }
}
