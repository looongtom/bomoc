package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book_bookcategory", schema = "bo_mo_c", catalog = "")
@IdClass(BookBookcategoryEntityPK.class)
public class BookBookcategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "BookId")
    private int bookId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "BookCategoryId")
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

        BookBookcategoryEntity that = (BookBookcategoryEntity) o;

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
