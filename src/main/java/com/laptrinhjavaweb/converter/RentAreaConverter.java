package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {

    public String rentAreaConverter(List<RentAreaEntity> rentAreaEntities) {
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        StringBuilder sql = new StringBuilder();
        for(RentAreaEntity item : rentAreaEntities) {
            sql.append(item.getValue() + " ");
        }
//        rentAreaEntity.setBuildingId(rentAreaEntities.get(0).getBuildingId());
        rentAreaEntity.setValue(sql + "");
        return rentAreaEntity.getValue();
    }

    public List<RentAreaEntity> rentAreaConverterToEntity(BuildingEntity buildingEntity , List<String> values){
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for(String item : values){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(item);
            rentAreaEntities.add(rentAreaEntity);
        }
        return  rentAreaEntities;
    }
}
