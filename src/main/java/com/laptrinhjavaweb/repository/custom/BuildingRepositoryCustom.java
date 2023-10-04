package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom extends JdbcDaoCustom<BuildingEntity> {
    List<BuildingEntity> findByCondition(BuildingDTO buildingDTO);
    List<BuildingEntity> getAllUsers(Pageable pageable, BuildingDTO buildingDTO);
    int countTotalItem(BuildingDTO buildingDTO );
    void update(BuildingCreateDTO buildingCreateDTO);
}
