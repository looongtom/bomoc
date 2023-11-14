package com.project.bookstore.bookstore.model;

public class ResponseObject {
    private String status;
    private String message;
    private String name;
    private String role;
    public ResponseObject() {}

    public ResponseObject(String status, String message, String name, String role) {
        this.status = status;
        this.message = message;
        this.name = name;
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.name = role;
    }
}
