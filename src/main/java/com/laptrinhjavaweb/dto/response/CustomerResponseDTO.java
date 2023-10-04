package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.dto.AbstractDTO;

public class CustomerResponseDTO extends AbstractDTO {

    private String fullName;

    private String nameOfStaff;

    private String phone;

    private String email;

    private String demand;

    private String status;

    private String note;

    private String staffId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNameOfStaff() {
        return nameOfStaff;
    }

    public void setNameOfStaff(String nameOfStaff) {
        this.nameOfStaff = nameOfStaff;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
