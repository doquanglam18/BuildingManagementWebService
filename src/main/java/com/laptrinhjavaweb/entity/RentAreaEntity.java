package com.laptrinhjavaweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity{

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    @Column(name = "value")
    private String value;

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}

