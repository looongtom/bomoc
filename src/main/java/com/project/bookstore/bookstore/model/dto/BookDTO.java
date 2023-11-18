package com.project.bookstore.bookstore.model.dto;

import lombok.Data;

@Data
public class BookDTO {
    private int id;
    private int authorId;
    private int publisherId;
    private String title;
    private String description;
    private int publishYear;
    private int numerOfPages;
    private String image;
    private double price;
    private String language;
    private String authorName;
    private String publisherName;

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

        BookDTO bookDTO = (BookDTO) o;

        if (id != bookDTO.id) return false;
        if (authorId != bookDTO.authorId) return false;
        if (publisherId != bookDTO.publisherId) return false;
        if (publishYear != bookDTO.publishYear) return false;
        if (numerOfPages != bookDTO.numerOfPages) return false;
        if (Double.compare(bookDTO.price, price) != 0) return false;
        if (title != null ? !title.equals(bookDTO.title) : bookDTO.title != null) return false;
        if (description != null ? !description.equals(bookDTO.description) : bookDTO.description != null) return false;
        if (image != null ? !image.equals(bookDTO.image) : bookDTO.image != null) return false;
        if (language != null ? !language.equals(bookDTO.language) : bookDTO.language != null) return false;

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
