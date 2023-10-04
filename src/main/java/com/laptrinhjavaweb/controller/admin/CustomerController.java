package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.dto.request.CustomerRequestDTO;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import com.laptrinhjavaweb.dto.response.TransactionTypeResponseDTO;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.service.impl.TransactionService;
import com.laptrinhjavaweb.service.impl.UserService;
import com.laptrinhjavaweb.utils.DisplayTagUtils;
import com.laptrinhjavaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserService userService;

    @Autowired
    MessageUtils messageUtil;

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView getListCustomer(@ModelAttribute(SystemConstant.MODEL) CustomerRequestDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/list");
        MyUserDetail myUserDetail = SecurityUtils.getPrincipal();
        if(myUserDetail.getId() != 1){
            model.setNameOfStaff(String.valueOf(myUserDetail.getId()));
        }
        DisplayTagUtils.of(request, model);
        List<CustomerResponseDTO> customerResponseDTOS = customerService.getAllCustomer(new PageRequest(model.getPage() - 1, model.getMaxPageItems()), model);
        model.setListResult(customerResponseDTOS);
        model.setTotalItems(customerService.countTotalItems(model));
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject("staffMaps", userService.getStaffMaps());
        initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/profile-{id}", method = RequestMethod.GET)
    public ModelAndView updateProfile(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/profile");
        CustomerResponseDTO model = customerService.findById(id);
        List<TransactionTypeResponseDTO> transactionTypeResponseDTOS = transactionService.findTransactionResponseByCustomerId(model.getId());
        initMessageResponse(mav, request);
        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject(SystemConstant.TRANSACTIONS,transactionTypeResponseDTOS);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
