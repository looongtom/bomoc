package com.project.bookstore.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address", schema = "bo_mo_c", catalog = "")
public class AddressEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IdAddress")
    private int idAddress;
    @Basic
    @Column(name = "CustomerIdCus")
    private int customerIdCus;
    @Basic
    @Column(name = "HouseNumber")
    private String houseNumber;
    @Basic
    @Column(name = "Street")
    private String street;
    @Basic
    @Column(name = "City")
    private String city;
    @Basic
    @Column(name = "District")
    private String district;

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getCustomerIdCus() {
        return customerIdCus;
    }

    public void setCustomerIdCus(int customerIdCus) {
        this.customerIdCus = customerIdCus;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (idAddress != that.idAddress) return false;
        if (customerIdCus != that.customerIdCus) return false;
        if (houseNumber != null ? !houseNumber.equals(that.houseNumber) : that.houseNumber != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAddress;
        result = 31 * result + customerIdCus;
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        return result;
    }
}
