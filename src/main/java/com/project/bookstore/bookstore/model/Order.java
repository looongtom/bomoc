package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name="orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userEmail;

    @Column
    private String userName;
    @Column
    private String bookTitle;
    @Column
    private String bookAuthor;
    @Column
    private String bookCategory;
    @Column
    private String bookPages;
    @Column
    private Long bookId;
    @Column
    private int quantity;
    @Column
    private boolean orderStatus;
    @Column
    private Integer orderQuantity;

    public Order() {

    }


    public Order(Long id,String userEmail, String userName, String bookTitle, String bookAuthor, String bookCategory, String bookPages,
                 Long bookId, int quantity, boolean orderStatus, Integer orderQuantity) {
        super();
        this.id = id;
        this.userEmail = userEmail;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookPages = bookPages;
        this.bookId = bookId;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.orderQuantity = orderQuantity;
    }


    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public  void setUserEmail(){
        this.userEmail = userEmail;
    }

    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getBookCategory() {
        return bookCategory;
    }
    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }
    public String getBookPages() {
        return bookPages;
    }
    public void setBookPages(String bookPages) {
        this.bookPages = bookPages;
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public boolean isOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
    public boolean getOrderStatus() {
        // TODO Auto-generated method stub
        return orderStatus;
    }
}
