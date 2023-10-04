package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingDeleteDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.service.impl.AssignmentBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public BuildingCreateDTO createBuilding(@RequestBody BuildingCreateDTO buildingCreateDTO){
        buildingService.save(buildingCreateDTO);
        return buildingCreateDTO;
    }

    @PutMapping
    public BuildingCreateDTO updateBuilding(@RequestBody BuildingCreateDTO buildingCreateDTO){
        buildingService.save(buildingCreateDTO);
        return buildingCreateDTO;
    }


    @GetMapping("/staffs")
    public ResponseDTO loadStaff(@RequestParam(value = "buildingid") Long buildingId){
        ResponseDTO responseDTO = new ResponseDTO();
        List<StaffResponseDTO> staffResponseDTOS = assignmentBuildingService.findStaffByBuildingId(buildingId);//
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return  responseDTO;
    }

    @PostMapping("/user-assignment")
    public AssignmentBuildingRequestDTO handOverBuilding(@RequestBody AssignmentBuildingRequestDTO assignmentBuildingRequestDTO) {
        assignmentBuildingService.handOverBuilding(assignmentBuildingRequestDTO);
        return assignmentBuildingRequestDTO;
    }

    @DeleteMapping
    public void deleteBuilding (@RequestBody BuildingDeleteDTO buildingDeleteDTO){
        buildingService.delete(buildingDeleteDTO.getBuildingIds());
    }
}

