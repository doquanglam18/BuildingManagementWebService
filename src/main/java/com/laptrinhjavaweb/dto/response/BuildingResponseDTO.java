package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.dto.AbstractDTO;

public class BuildingResponseDTO extends AbstractDTO {
    private Long id;
    private String name;
    private String address;
    private String nameOfUser;
    private String phoneOfUser;
    private Integer floorArea;
    private String rentArea;
    private String rentPrice;
    private String serviceFee;
    private String brokerageFee;
    private String image;

    private String imageBase64;

    private String imageName;

    public String getImageBase64() {
        if (imageBase64 != null) {
            return imageBase64.split(",")[1];
        }
        return null;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNameOfUser() {
        return nameOfUser;
    }
    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }
    public String getPhoneOfUser() {
        return phoneOfUser;
    }
    public void setPhoneOfUser(String phoneOfUser) {
        this.phoneOfUser = phoneOfUser;
    }
    public Integer getFloorArea() {
        return floorArea;
    }
    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }
    public String getRentArea() {
        return rentArea;
    }
    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }
    public String getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }
    public String getServiceFee() {
        return serviceFee;
    }
    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }
    public String getBrokerageFee() {
        return brokerageFee;
    }
    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}
