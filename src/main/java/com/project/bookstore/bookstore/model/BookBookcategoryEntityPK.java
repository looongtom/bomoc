package com.project.bookstore.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class BookBookcategoryEntityPK implements Serializable {
    @Column(name = "BookId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "BookCategoryId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookCategoryId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(int bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookBookcategoryEntityPK that = (BookBookcategoryEntityPK) o;

        if (bookId != that.bookId) return false;
        if (bookCategoryId != that.bookCategoryId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + bookCategoryId;
        return result;
    }
}
