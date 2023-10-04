package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.AssignmentCustomerConverter;
import com.laptrinhjavaweb.converter.StaffResponseDTOConverter;
import com.laptrinhjavaweb.dto.request.AssignmentStaffRequestDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AssignmentCustomerConverter assignmentCustomerConverter;
    @Autowired
    UserService userService;
    @Autowired
    StaffResponseDTOConverter staffResponseDTOConverter;

    @Override
    public List<Long> findStaffIdByCustomerId(Long customerId) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
        return assignmentCustomerConverter.NewAssignmentCustomerConverter(customerEntity.get());
    }

    @Override
    public List<StaffResponseDTO> findStaffByCustomerId(Long staffId) {
        List<Long> staffIds = findStaffIdByCustomerId(staffId);
        List<StaffResponseDTO> staffResponseDTOS = userService.findAllUser();
        return staffResponseDTOConverter.converToStaffResponseDTOCheck(staffResponseDTOS,staffIds);
    }

    @Transactional
    public void insertAssignmentBuilding(Long customerId, List<Long> staffId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        List<UserEntity> userEntities = new ArrayList<>();
        customerEntity.setUsers(userEntities);
        customerRepository.save(customerEntity);
        for(Long item : staffId) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(item);
            userEntities.add(userEntity);

        }
        customerEntity.setUsers(userEntities);
        customerRepository.save(customerEntity);
    }

    @Transactional
    @Override
    public void handOverStaff(AssignmentStaffRequestDTO assignmentStaffRequestDTO) {
        Long customerId = assignmentStaffRequestDTO.getCustomerId();

        List<Long> staffIdInsert = assignmentStaffRequestDTO.getStaffId();
//        if(!StringUtils.isNullOrEmpty(staffId1)){
//            staffIdInsert = converterToList(staffId1);
//        }
        insertAssignmentBuilding(customerId, staffIdInsert);
    }
}
