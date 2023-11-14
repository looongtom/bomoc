package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewer;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column
    private int rating;
    @Column(name = "book_id")
    private int bookId;

    public Review(Long id, String reviewer, String text, int rating, int bookId) {
        super();
        this.id = id;
        this.reviewer = reviewer;
        this.text = text;
        this.rating = rating;
        this.bookId = bookId;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public Review() {

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBook(int bookId) {
        this.bookId = bookId;
    }
    public String getReviewer() {
        return reviewer;
    }
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

}
