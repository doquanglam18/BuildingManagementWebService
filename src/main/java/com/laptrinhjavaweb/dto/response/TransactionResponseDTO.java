package com.laptrinhjavaweb.dto.response;

public class TransactionResponseDTO {

    private Long id;

    private String createdDate;

    private String code;

    private String note;

    private Long customerId;

    private Long staffId;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionResponseDTO() {
    }

    public TransactionResponseDTO(Long id, String createdDate, String code, String note, Long customerId, Long staffId) {
        this.id = id;
        this.createdDate = createdDate;
        this.code = code;
        this.note = note;
        this.customerId = customerId;
        this.staffId = staffId;
    }
}
