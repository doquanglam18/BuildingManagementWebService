package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.PasswordDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.dto.response.TransactionResponseDTO;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.AssignmentCustomerService;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    private IUserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    TransactionService transactionService;

    @Autowired
    AssignmentCustomerService assignmentCustomerService;

    @PostMapping
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
        return ResponseEntity.ok(userService.insert(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePasswordUser(@PathVariable("id") long id, @RequestBody PasswordDTO passwordDTO) {
        try {
            userService.updatePassword(id, passwordDTO);
            return ResponseEntity.ok(SystemConstant.UPDATE_SUCCESS);
        } catch (MyException e) {
//            LOGGER.error(e.getMessage());
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PutMapping("/password/{id}/reset")
    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody long[] idList) {
        if (idList.length > 0) {
            userService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
//    @PutMapping("/profile")
//    public ResponseEntity<CustomerResponseDTO> updateProfileOfUser(@RequestBody CustomerRequestDTO userDTO) {
//        return ResponseEntity.ok(customerService.newProfileOfUser(userDTO));
//    }

//    @PostMapping("/profile")
//    public ResponseEntity<CustomerResponseDTO> newProfileOfUser(@RequestBody CustomerRequestDTO userDTO) {
//        return ResponseEntity.ok(customerService.newProfileOfUser(userDTO));
//    }

//    @GetMapping("/staffs")
//    public ResponseDTO loadStaff(@RequestParam(value = "customerid") Long customerId){
//        ResponseDTO responseDTO = new ResponseDTO();
//        List<StaffResponseDTO> staffResponseDTOS = assignmentCustomerService.findStaffByCustomerId(customerId);//
//        responseDTO.setData(staffResponseDTOS);
//        responseDTO.setMessage("success");
//        return responseDTO;
//    }

//    @PostMapping("/user-assignment")
//    public AssignmentStaffRequestDTO handOverBuilding(@RequestBody AssignmentStaffRequestDTO assignmentStaffRequestDTO) {
//        assignmentCustomerService.handOverStaff(assignmentStaffRequestDTO);
//        return assignmentStaffRequestDTO;
//    }

//    @GetMapping(value = "/transaction/{customerid}")
//    public ResponseDTO getCustomer(@PathVariable("customerid") Long id) {
//        ResponseDTO responseDTO = new ResponseDTO();
//        List<TransactionResponseDTO> transactionResponseDTOS = transactionService.findByCustomerId(id);
//        responseDTO.setData(transactionResponseDTOS);
//        responseDTO.setMessage("success");
//        return responseDTO;
//    }

//    @PostMapping(value = "/transaction")
//    public TransactionResponseDTO newTransaction(@RequestBody TransactionResponseDTO transactionResponseDTO){
//        return transactionService.newTransaction(transactionResponseDTO);
//    }

//    @DeleteMapping("/customer")
//    public void deleteCustomer (@RequestBody BuildingDeleteDTO buildingDeleteDTO){
//        customerService.delete(buildingDeleteDTO.getBuildingIds());
//    }
}
