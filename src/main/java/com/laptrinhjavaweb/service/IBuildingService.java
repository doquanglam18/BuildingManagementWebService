package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.dto.response.BuildingTypeResponseDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingResponseDTO> findAll();
    List<BuildingResponseDTO> findByCondition(BuildingDTO buildingDTO);
    void save(BuildingCreateDTO buildingCreateDTO);
    void update(BuildingCreateDTO buildingCreateDTO);
    void delete(List<Long> buildingIds);
    Map<String,String> getDistrictMap();
    Map<String,String> getBuildingTypeMap();
    List<BuildingResponseDTO> getAllUsers(Pageable pageable, BuildingDTO buildingDTO);
    int countTotalItems(BuildingDTO buildingDTO);
    BuildingCreateDTO findById(Long buildingId);
    List<BuildingTypeResponseDTO> loadBuildingType(BuildingCreateDTO buildingCreateDTO);
}
