package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.CustomerRequestDTO;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICustomerService {
    List<CustomerResponseDTO> findAll(Integer page, Integer size );
    CustomerResponseDTO findOneByFullName(String userName);
    List<CustomerResponseDTO> getAllCustomer(Pageable pageable, CustomerRequestDTO customerRequestDTO);
    int countTotalItems(CustomerRequestDTO customerRequestDTO);
    @Transactional
    CustomerResponseDTO newProfileOfUser(CustomerRequestDTO updateUser);

    void delete(List<Long> buildingIds);
}
