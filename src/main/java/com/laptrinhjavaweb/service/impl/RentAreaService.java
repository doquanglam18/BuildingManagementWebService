package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentAreaService implements IRentAreaService {

    @Autowired
    RentAreaRepository rentAreaRepository;
    @Autowired
    RentAreaConverter rentAreaConverter;

    public List<String> converterToList(String id){
        List<String> staffId = new ArrayList<>();
        String[] ids = id.split("[,\\s]+");
        for( String item : ids) {
            staffId.add(item);
        }
        return staffId;
    }

    @Override
    public void save(BuildingEntity buildingEntity, List<String> values) {
        for(String item : values){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(item);
            rentAreaRepository.save(rentAreaEntity);
        }
    }

    @Override
    public void save(List<RentAreaEntity> rentAreaEntities) {
        for (RentAreaEntity rentAreaEntity : rentAreaEntities){
            rentAreaRepository.save(rentAreaEntity);
        }
    }

    @Transactional
    @Override
    public void deleteByBuildingId(Long buildingId) {
        rentAreaRepository.deleteByBuilding_Id(buildingId);
    }

    @Transactional
    @Override
    public void deleteByBuilding_IdIn(List<Long> buildingIds) {
        rentAreaRepository.deleteByBuilding_IdIn(buildingIds);
    }


    @Override
    public List<RentAreaEntity> getAllByBuildingId(Long buildingId){
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingId);
        return rentAreaEntities;
    }
}
