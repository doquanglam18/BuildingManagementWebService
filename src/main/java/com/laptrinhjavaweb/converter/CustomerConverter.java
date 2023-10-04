package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.request.CustomerRequestDTO;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerResponseDTO convertToDto (CustomerEntity entity){
        CustomerResponseDTO result = modelMapper.map(entity, CustomerResponseDTO.class);
        List<String> nameStaffs = new ArrayList<>();
        List<UserEntity> userEntities = entity.getUsers();
        for(UserEntity item : userEntities ){
            nameStaffs.add(item.getFullName());
        }
        String nameStaff = String.join(", ", nameStaffs);
        result.setNameOfStaff(nameStaff);
        if(entity.getUsers() != null && !entity.getUsers().isEmpty()){
            result.setStatus(SystemConstant.STATUS_PROCESSING);
        }
        else{
            result.setStatus(SystemConstant.STATUS_WAITING);
        }
        return result;
    }

    public CustomerEntity convertToEntity (CustomerResponseDTO dto){
        CustomerEntity result = modelMapper.map(dto, CustomerEntity.class);
        return result;
    }

    public CustomerEntity convertToEntity (CustomerRequestDTO dto){
        CustomerEntity result = modelMapper.map(dto, CustomerEntity.class);
        return result;
    }

    public List<CustomerResponseDTO> converToListDto(List<CustomerEntity> userEntities){
        List<CustomerResponseDTO> userDTOS = new ArrayList<>();
        for(CustomerEntity item : userEntities){
            CustomerResponseDTO userDTO = convertToDto(item);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

//    public List<CustomerEntity> converToListEntity(List<CustomerEntity> userEntities){
//        List<CustomerResponseDTO> userDTOS = new ArrayList<>();
//        for(CustomerEntity item : userEntities){
//            CustomerResponseDTO userDTO = convertToDto(item);
//            userDTOS.add(userDTO);
//        }
//        return userDTOS;
//    }
}
