package com.laptrinhjavaweb.dto.response;

import java.util.ArrayList;
import java.util.List;

public class TransactionTypeResponseDTO {

    private String transactionCode;

    private String transactionTypeName;

    private List<TransactionResponseDTO> transactionResponseDTOS = new ArrayList<>();

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public List<TransactionResponseDTO> getTransactionResponseDTOS() {
        return transactionResponseDTOS;
    }

    public void setTransactionResponseDTOS(List<TransactionResponseDTO> transactionResponseDTOS) {
        this.transactionResponseDTOS = transactionResponseDTOS;
    }
}
