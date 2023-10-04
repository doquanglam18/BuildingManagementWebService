package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.response.TransactionResponseDTO;
import com.laptrinhjavaweb.dto.response.TransactionTypeResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.TransactionEnum;
import com.laptrinhjavaweb.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TransactionRepository transactionRepository;


    public TransactionResponseDTO converToDTO(TransactionEntity entity){
        TransactionResponseDTO result = modelMapper.map(entity, TransactionResponseDTO.class);
        result.setCode(entity.getCode());
        return result;
    }

    public TransactionEntity converToEntity (TransactionResponseDTO dto){
        TransactionEntity result = modelMapper.map(dto,TransactionEntity.class);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(dto.getCustomerId());
        result.setCustomerEntity(customerEntity);
        if(dto.getCode().equals(TransactionEnum.TYPE_1.getTransactionTypeValue())){
            result.setCode("TYPE_1");
        }
        else if(dto.getCode().equals(TransactionEnum.TYPE_2.getTransactionTypeValue())){
            result.setCode("TYPE_2");
        }
        return result;
    }

    public List<TransactionResponseDTO> converToListDTO(List<TransactionEntity> transactionEntities){
        List<TransactionResponseDTO> transactionResponseDTOS = new ArrayList<>();
        for(TransactionEntity item : transactionEntities){
            TransactionResponseDTO transactionResponseDTO = converToDTO(item);
            transactionResponseDTOS.add(transactionResponseDTO);
        }
        return transactionResponseDTOS;
    }

    public List<TransactionTypeResponseDTO> converToListTransactionType(Long id){
         List<TransactionTypeResponseDTO> transactionTypeResponseDTOS = new ArrayList<>();
        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomerId(id);
        Arrays.stream(TransactionEnum.values())
                .forEach(transactionEnum -> {
                    TransactionTypeResponseDTO transactionResponseDTO = new TransactionTypeResponseDTO();
                    transactionResponseDTO.setTransactionTypeName(transactionEnum.getTransactionTypeValue());
                    transactionResponseDTO.setTransactionCode(transactionEnum.toString());
                    transactionTypeResponseDTOS.add(transactionResponseDTO);
                });
        transactionTypeResponseDTOS.forEach(response -> {
            String code = response.getTransactionCode();
            for (TransactionEntity transactionEntity : transactionEntities) {
                if (code.equals(transactionEntity.getCode())) {
                    TransactionResponseDTO transactionResponseDTO = converToDTO(transactionEntity);
                    response.getTransactionResponseDTOS().add(transactionResponseDTO);
                }
            }

        });
        return transactionTypeResponseDTOS;
    }
}
