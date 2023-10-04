package com.laptrinhjavaweb.enums;

public enum BuildingTypesEnum {
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất"),
    TANG_TRET("Tầng trệt");

    private final String buildingTypeValue;

    BuildingTypesEnum(String buildingTypeValue) {
        this.buildingTypeValue = buildingTypeValue;
    }

    public String getBuildingTypeValue() {
        return buildingTypeValue;
    }
}
