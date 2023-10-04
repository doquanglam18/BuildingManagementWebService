package com.laptrinhjavaweb.dto.request;

import java.util.List;

public class AssignmentBuildingRequestDTO {

    private Long buildingId;
    private List<Long> staffId;
    public List<Long> getStaffId() {
        return staffId;
    }

    public void setStaffId(List<Long> staffId) {
        this.staffId = staffId;
    }
    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
