package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequestDTO;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerRepositoryCustom customerRepositoryCustom;

    @Autowired
    CustomerConverter customerConverter;

    @Override
    public List<CustomerResponseDTO> findAll(Integer page, Integer size) {
        if (page == null || page < 0 || size == null || size < 0) {
            return customerConverter.converToListDto(customerRepository.findAll(PageRequest.of(0,1)).getContent());
        }
        Page<CustomerEntity> userEntities = customerRepository.findAll(PageRequest.of(page, size));
        List<CustomerEntity> userEntityList = userEntities.getContent();
        return customerConverter.converToListDto(userEntityList);
    }

    @Override
    public CustomerResponseDTO findOneByFullName(String fullName) {
        CustomerEntity customerEntity = customerRepository.findOneByFullName(fullName);
        CustomerResponseDTO customerResponseDTO = customerConverter.convertToDto(customerEntity);
        return customerResponseDTO;
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomer(Pageable pageable, CustomerRequestDTO customerRequestDTO) {
        List<CustomerEntity> customerEntities = customerRepositoryCustom.getAllCustomer(pageable, customerRequestDTO);
        List<CustomerResponseDTO> results = customerConverter.converToListDto(customerEntities);
        return results;
    }

    @Override
    public int countTotalItems(CustomerRequestDTO customerRequestDTO) {
        return customerRepositoryCustom.countTotalItem(customerRequestDTO);
    }

    public CustomerResponseDTO findById(Long id){
        return customerConverter.convertToDto(customerRepository.findById(id).get());
    }

    @Override
    @Transactional
    public CustomerResponseDTO newProfileOfUser(CustomerRequestDTO user) {
        CustomerEntity entity = new CustomerEntity();
        if(user.getId() != null){
            entity = customerRepository.findOneByFullName(user.getFullName());
            List<UserEntity> userEntities = entity.getUsers();
            entity = customerConverter.convertToEntity(user);
            entity.setUsers(userEntities);
        }
        else{
            entity = customerConverter.convertToEntity(user);
        }
        return customerConverter.convertToDto(customerRepository.save(entity));
    }

    @Transactional
    @Override
    public void delete(List<Long> buildingIds) {
//        rentAreaRepository.deleteByBuilding_IdIn(buildingIds);
        customerRepository.deleteByIdIn(buildingIds);
    }
}
