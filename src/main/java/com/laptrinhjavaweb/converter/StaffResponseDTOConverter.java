package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaffResponseDTOConverter {

    public List<StaffResponseDTO> converToStaffResponseDTO(List<UserEntity> userEntities){
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for(UserEntity item : userEntities){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(item.getId());
            staffResponseDTO.setFullName(item.getFullName());
            staffResponseDTOS.add(staffResponseDTO);
        }
        return staffResponseDTOS;
    }

    public List<StaffResponseDTO> converToStaffResponseDTOCheck(List<StaffResponseDTO> staffResponseDTOS, List<Long> staffIds){
        for(StaffResponseDTO item : staffResponseDTOS){
            for (Long staffId : staffIds){
                if (staffId.equals(item.getStaffId())){
                    item.setCheck("checked");
                }
            }
        }
        return staffResponseDTOS;
    }
    //ổn
}
