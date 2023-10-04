package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AssignmentCustomerConverter {
    //Converter list AssingmentBuildingEntity cùng id thành 1 AssingmentBuildingEntity và lấy ra list staffId
    public List<Long> NewAssignmentCustomerConverter(CustomerEntity customerEntity) {
        List<Long> staffIds = new ArrayList<>();
        for(UserEntity item : customerEntity.getUsers()) {
//            staffIds.add(item.getStaffId());
            staffIds.add(item.getId());
        }
        return staffIds;
    }
}
