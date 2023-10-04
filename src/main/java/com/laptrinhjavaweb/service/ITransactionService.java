package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.response.TransactionResponseDTO;
import com.laptrinhjavaweb.dto.response.TransactionTypeResponseDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionResponseDTO> findByCustomerId(Long customerId);
    List<TransactionTypeResponseDTO> findTransactionResponseByCustomerId(Long customerId);
    TransactionResponseDTO newTransaction(TransactionResponseDTO transactionResponseDTO);
}
