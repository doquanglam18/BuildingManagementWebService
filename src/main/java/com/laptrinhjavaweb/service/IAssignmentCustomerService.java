package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.AssignmentStaffRequestDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;

import java.util.List;

public interface IAssignmentCustomerService {
    List<Long> findStaffIdByCustomerId(Long buildingId);
    List<StaffResponseDTO> findStaffByCustomerId(Long buildingId);

    void handOverStaff(AssignmentStaffRequestDTO assignmentStaffRequestDTO);
}
