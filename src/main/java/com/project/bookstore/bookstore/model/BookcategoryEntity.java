package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookcategory", schema = "bo_mo_c", catalog = "")
public class BookcategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookcategoryEntity that = (BookcategoryEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
