package com.project.bookstore.bookstore.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book", schema = "bo_mo_c", catalog = "")
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "AuthorId", nullable = false)
    private int authorId;
    @Basic
    @Column(name = "PublisherId", nullable = false)
    private int publisherId;
    @Basic
    @Column(name = "Title", nullable = true, length = 255)
    private String title;
    @Basic
    @Column(name = "Description", nullable = true, length = 255)
    private String description;
    @Basic
    @Column(name = "PublishYear", nullable = false)
    private int publishYear;
    @Basic
    @Column(name = "NumerOfPages", nullable = false)
    private int numerOfPages;
    @Basic
    @Column(name = "Image", nullable = true, length = 255)
    private String image;
    @Basic
    @Column(name = "Price", nullable = false, precision = 0)
    private double price;
    @Basic
    @Column(name = "Language", nullable = true, length = 255)
    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getNumerOfPages() {
        return numerOfPages;
    }

    public void setNumerOfPages(int numerOfPages) {
        this.numerOfPages = numerOfPages;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (id != that.id) return false;
        if (authorId != that.authorId) return false;
        if (publisherId != that.publisherId) return false;
        if (publishYear != that.publishYear) return false;
        if (numerOfPages != that.numerOfPages) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + authorId;
        result = 31 * result + publisherId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + publishYear;
        result = 31 * result + numerOfPages;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
