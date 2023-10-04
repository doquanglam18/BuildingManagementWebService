package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.util.List;

public interface IRentAreaService {
    void save(BuildingEntity buildingEntity, List<String> values);
    void save(List<RentAreaEntity> rentAreaEntities);
    void deleteByBuildingId(Long buildingId);
    void deleteByBuilding_IdIn(List<Long> buildingIds);
    List<RentAreaEntity> getAllByBuildingId(Long buildingId);
}
