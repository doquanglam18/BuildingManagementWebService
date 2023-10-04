package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity extends BaseEntity {

    @Column
    private String code;

    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity userEntity;


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

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
