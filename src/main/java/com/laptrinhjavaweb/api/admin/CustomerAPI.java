package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.AssignmentStaffRequestDTO;
import com.laptrinhjavaweb.dto.request.BuildingDeleteDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequestDTO;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.dto.response.TransactionResponseDTO;
import com.laptrinhjavaweb.service.impl.AssignmentCustomerService;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    CustomerService customerService;
    @Autowired
    AssignmentCustomerService assignmentCustomerService;
    @Autowired
    TransactionService transactionService;

    @PostMapping("/user-assignment")
    public AssignmentStaffRequestDTO handOverBuilding(@RequestBody AssignmentStaffRequestDTO assignmentStaffRequestDTO) {
        assignmentCustomerService.handOverStaff(assignmentStaffRequestDTO);
        return assignmentStaffRequestDTO;
    }

    @DeleteMapping
    public void deleteCustomer (@RequestBody BuildingDeleteDTO buildingDeleteDTO){
        customerService.delete(buildingDeleteDTO.getBuildingIds());
    }

    @GetMapping("/staffs")
    public ResponseDTO loadStaff(@RequestParam(value = "customerid") Long customerId){
        ResponseDTO responseDTO = new ResponseDTO();
        List<StaffResponseDTO> staffResponseDTOS = assignmentCustomerService.findStaffByCustomerId(customerId);//
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @PostMapping(value = "/transaction")
    public TransactionResponseDTO newTransaction(@RequestBody TransactionResponseDTO transactionResponseDTO){
        return transactionService.newTransaction(transactionResponseDTO);
    }

    @PutMapping("/profile")
    public ResponseEntity<CustomerResponseDTO> updateProfileOfUser(@RequestBody CustomerRequestDTO userDTO) {
        return ResponseEntity.ok(customerService.newProfileOfUser(userDTO));
    }

    @PostMapping("/profile")
    public ResponseEntity<CustomerResponseDTO> newProfileOfUser(@RequestBody CustomerRequestDTO userDTO) {
        return ResponseEntity.ok(customerService.newProfileOfUser(userDTO));
    }
}
