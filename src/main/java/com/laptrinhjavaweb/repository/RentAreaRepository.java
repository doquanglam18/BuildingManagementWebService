package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    List<RentAreaEntity> findByBuildingId(Long buildingId);
    Long deleteByBuilding_Id(Long buildingId);
    void deleteByBuilding_IdIn(List<Long> ids);
}
