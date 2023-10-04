//package com.laptrinhjavaweb.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "assignmentbuilding")
//public class AssignmentBuildingEntity extends BaseEntity{
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "staffid")
//    private UserEntity users;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "buildingid")
//    private BuildingEntity building;
//
//    public UserEntity getUser() {
//        return users;
//    }
//
//    public void setUser(UserEntity users) {
//        this.users = users;
//    }
//
//    public BuildingEntity getBuilding() {
//        return building;
//    }
//
//    public void setBuilding(BuildingEntity building) {
//        this.building = building;
//    }
//}
