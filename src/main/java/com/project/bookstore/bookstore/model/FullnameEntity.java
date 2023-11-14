package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fullname", schema = "bo_mo_c", catalog = "")
public class FullnameEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdFullName")
    private int idFullName;
    @Basic
    @Column(name = "CustomerIdCus")
    private int customerIdCus;
    @Basic
    @Column(name = "FirstName")
    private String firstName;
    @Basic
    @Column(name = "LastName")
    private String lastName;

    public int getIdFullName() {
        return idFullName;
    }

    public void setIdFullName(int idFullName) {
        this.idFullName = idFullName;
    }

    public int getCustomerIdCus() {
        return customerIdCus;
    }

    public void setCustomerIdCus(int customerIdCus) {
        this.customerIdCus = customerIdCus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullnameEntity that = (FullnameEntity) o;

        if (idFullName != that.idFullName) return false;
        if (customerIdCus != that.customerIdCus) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFullName;
        result = 31 * result + customerIdCus;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
