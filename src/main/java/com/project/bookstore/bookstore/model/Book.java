package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "pages")
    private int pages;

    @Column(name = "category")
    private String category;

    @Column(name = "book_cover")
    private String bookCover;

    @Column(name = "sold_quantity", columnDefinition = "int default 0")
    private int soldQuantity;

    @Column
    private  double bookPrice;

    public Book() {

    }
    public double getBookPrice(){
        return bookPrice;
    }
    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getSoldQuantity() {
        return soldQuantity;
    }
    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
    public String getBookCover() {
        return bookCover;
    }
    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }



    public Book(Long id, String title, String author, String description, Date publishedDate, int pages,
                String category, String bookCover, int soldQuantity) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.category = category;
        this.bookCover = bookCover;
        this.soldQuantity = soldQuantity;

    }
}
