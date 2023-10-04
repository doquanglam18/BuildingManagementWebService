package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.response.BuildingTypeResponseDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;

import java.util.List;

public interface IAssignmentBuildingService {
    List<StaffResponseDTO> findStaffByBuildingId(Long buildingId);
    List<Long> findStaffIdByBuildingId(Long buildingId);
    void handOverBuilding(AssignmentBuildingRequestDTO assignmentBuildingRequestDTO);
    void deleteByBuildingId(Long building);
    List<BuildingTypeResponseDTO> findBuildingTypeToCheck(List<String> buildingTypes, List<BuildingTypeResponseDTO> buildingTypeResponseDTOS);
//    public List<AssignmentBuildingEntity> getAllByBuildingId(Long buildingId);
//    public void save(List<AssignmentBuildingEntity> assignmentBuildingEntities);
}
