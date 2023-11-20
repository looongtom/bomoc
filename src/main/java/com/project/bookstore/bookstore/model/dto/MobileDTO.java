package com.project.bookstore.bookstore.model.dto;

import lombok.Data;

@Data
public class MobileDTO {
    private int id;
    private int producerId;
    private String name;
    private String image;
    private int osType;
    private String description;
    private double price;
    private  String producerName;



    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileDTO mobileDTO = (MobileDTO) o;

        if (id != mobileDTO.id) return false;
        if (producerId != mobileDTO.producerId) return false;
        if (osType != mobileDTO.osType) return false;
        if (name != null ? !name.equals(mobileDTO.name) : mobileDTO.name != null) return false;
        if (image != null ? !image.equals(mobileDTO.image) : mobileDTO.image != null) return false;
        if (description != null ? !description.equals(mobileDTO.description) : mobileDTO.description != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + producerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + osType;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
