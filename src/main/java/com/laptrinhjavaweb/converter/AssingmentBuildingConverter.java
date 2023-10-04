package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AssingmentBuildingConverter {

	//Converter list AssingmentBuildingEntity cùng id thành 1 AssingmentBuildingEntity và lấy ra list staffId
    public List<Long> NewAssignmentBuildingConverter(BuildingEntity buildingEntity) {
        List<Long> staffIds = new ArrayList<>();
        for(UserEntity item : buildingEntity.getUsers()) {
//            staffIds.add(item.getStaffId());
            staffIds.add(item.getId());
        }
        return staffIds;
    }
}
//ổn
