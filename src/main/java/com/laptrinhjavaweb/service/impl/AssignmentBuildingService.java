package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.AssingmentBuildingConverter;
import com.laptrinhjavaweb.converter.StaffResponseDTOConverter;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.response.BuildingTypeResponseDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    AssingmentBuildingConverter newAssingmentBuildingConverter;
    @Autowired
    StaffResponseDTOConverter staffResponseDTOConverter;
    @Autowired
    UserService userService;
    @Autowired
    AssignmentBuildingService assignmentBuildingService;
    @Autowired
    BuildingRepository buildingRepository;

    @Override
    public List<Long> findStaffIdByBuildingId(Long buildingId) {
        Optional<BuildingEntity> buildingEntities = buildingRepository.findById(buildingId);
        return newAssingmentBuildingConverter.NewAssignmentBuildingConverter(buildingEntities.get());
    }

    @Override
    public List<StaffResponseDTO> findStaffByBuildingId(Long buildingId) {
        List<Long> staffIds = findStaffIdByBuildingId(buildingId);
        List<StaffResponseDTO> staffResponseDTOS = userService.findAllUser();
        return staffResponseDTOConverter.converToStaffResponseDTOCheck(staffResponseDTOS,staffIds);
    }

    @Override
    public List<BuildingTypeResponseDTO> findBuildingTypeToCheck(List<String> buildingTypes, List<BuildingTypeResponseDTO> buildingTypeResponseDTOS){
        for (BuildingTypeResponseDTO buildingTypeResponseDTO : buildingTypeResponseDTOS){
            for (String item : buildingTypes){
                if(buildingTypeResponseDTO.getCode().equals(item)){
                    buildingTypeResponseDTO.setCheck("checked");
                }
            }
        }
        return buildingTypeResponseDTOS;
    }

    //

    public List<Long> converterToList(String id){
        List<Long> staffId = new ArrayList<>();
        String[] ids = id.split("[,\\s]+");
        for( String item : ids) {
            staffId.add(Long.valueOf(item));
        }
        return staffId;
    }

    public String converterToString(List<Long> ids){
        StringBuilder staffId = new StringBuilder();
        for (Long item : ids){
            staffId.append(item + " ");
        }
        return staffId.toString();
    }


    public void insertAssignmentBuilding(Long buildingId, List<Long> staffId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> userEntities = new ArrayList<>();
        for(Long item : staffId) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(item);
            userEntities.add(userEntity);

        }
        buildingEntity.setUsers(userEntities);
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void handOverBuilding(AssignmentBuildingRequestDTO assignmentBuildingRequestDTO) {
        Long buildingId = assignmentBuildingRequestDTO.getBuildingId();
//        String staffId1 = assignmentBuildingService.converterToString(assignmentRequestDTO.getStaffId());

        List<Long> staffIdInsert = assignmentBuildingRequestDTO.getStaffId();
//        if(!StringUtils.isNullOrEmpty(staffId1)){
//            staffIdInsert = converterToList(staffId1);
//        }
        insertAssignmentBuilding(buildingId, staffIdInsert);
    }

    @Transactional
    @Override
    public void deleteByBuildingId(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        buildingEntity.setUsers(new ArrayList<>());
        buildingRepository.save(buildingEntity);
    }

}
