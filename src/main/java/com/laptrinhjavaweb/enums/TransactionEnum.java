package com.laptrinhjavaweb.enums;

public enum TransactionEnum {

    TYPE_1("QUÁ TRÌNH CSKH"),
    TYPE_2("DẪN ĐI XEM");

    private final String transactionTypeValue;

    TransactionEnum(String transactionTypeValue) {
        this.transactionTypeValue = transactionTypeValue;
    }

    public String getTransactionTypeValue() {
        return transactionTypeValue;
    }
}
