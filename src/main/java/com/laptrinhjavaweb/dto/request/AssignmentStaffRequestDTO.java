package com.laptrinhjavaweb.dto.request;

import java.util.List;

public class AssignmentStaffRequestDTO {
    private Long customerId;
    private List<Long> staffId;
    public List<Long> getStaffId() {
        return staffId;
    }

    public void setStaffId(List<Long> staffId) {
        this.staffId = staffId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
