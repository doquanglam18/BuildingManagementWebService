package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.request.CustomerRequestDTO;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepositoryCustom {
    List<CustomerEntity> getAllCustomer(Pageable pageable, CustomerRequestDTO customerRequestDTO );
    int countTotalItem(CustomerRequestDTO customerResponseDTO);
}
