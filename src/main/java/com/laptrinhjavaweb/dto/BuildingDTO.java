package com.laptrinhjavaweb.dto;

import java.util.List;

public class BuildingDTO extends AbstractDTO{

    private String name;
    private String floorArea;
    private String district;
    private String ward;
    private String street;
    private String numberOfBasement;
    private String areaFrom;
    private String areaTo;
    private String rentPriceFrom;
    private String rentPriceTo;
    private String nameOfManager;
    private String phoneOfManager;
    private String staffId;
    private String nameOfStaff;
    private String address;
    private Integer rentPrice;
    private Integer serviceFee;
    private String nameOfUser;
    private String phoneOfUser;
    private List<String> buildingTypes;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
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

    public List<String> getBuildingTypes() {
        return buildingTypes;
    }
    public void setBuildingTypes(List<String> buildingTypes) {
        this.buildingTypes = buildingTypes;
    }
    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFloorArea() {
        return floorArea;
    }
    public void setFloorArea(String floorArea) {
        this.floorArea = floorArea;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getNumberOfBasement() {
        return numberOfBasement;
    }
    public void setNumberOfBasement(String numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
    public String getAreaFrom() {
        return areaFrom;
    }
    public void setAreaFrom(String areaFrom) {
        this.areaFrom = areaFrom;
    }
    public String getAreaTo() {
        return areaTo;
    }
    public void setAreaTo(String areaTo) {
        this.areaTo = areaTo;
    }
    public String getRentPriceFrom() {
        return rentPriceFrom;
    }
    public void setRentPriceFrom(String rentPriceFrom) {
        this.rentPriceFrom = rentPriceFrom;
    }
    public String getRentPriceTo() {
        return rentPriceTo;
    }
    public void setRentPriceTo(String rentPriceTo) {
        this.rentPriceTo = rentPriceTo;
    }
    public String getNameOfManager() {
        return nameOfManager;
    }
    public void setNameOfManager(String nameOfManager) {
        this.nameOfManager = nameOfManager;
    }
    public String getPhoneOfManager() {
        return phoneOfManager;
    }
    public void setPhoneOfManager(String phoneOfManager) {
        this.phoneOfManager = phoneOfManager;
    }
    public String getNameOfStaff() {
        return nameOfStaff;
    }
    public void setNameOfStaff(String nameOfStaff) {
        this.nameOfStaff = nameOfStaff;
    }
}
