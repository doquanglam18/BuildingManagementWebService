package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.response.TransactionResponseDTO;
import com.laptrinhjavaweb.dto.response.TransactionTypeResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionConverter transactionConverter;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<TransactionResponseDTO> findByCustomerId(Long customerId) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomerId(customerId);
        List<TransactionResponseDTO> transactionResponseDTOs = transactionConverter.converToListDTO(transactionEntities);
        return transactionResponseDTOs;
    }

    @Override
    public List<TransactionTypeResponseDTO> findTransactionResponseByCustomerId(Long customerId) {
        return transactionConverter.converToListTransactionType(customerId);
    }

    @Override
    public TransactionResponseDTO newTransaction(TransactionResponseDTO transactionResponseDTO){
        Optional<CustomerEntity> customerEntity = customerRepository.findById(transactionResponseDTO.getCustomerId());
        if(!customerEntity.isPresent() ){
            throw new NotFoundException("không có khách hàng với id là " + transactionResponseDTO.getCustomerId());
        }
        TransactionEntity transactionEntity = transactionConverter.converToEntity(transactionResponseDTO);
        transactionEntity = transactionRepository.save(transactionEntity);
        return transactionConverter.converToDTO(transactionEntity);
    }
}
