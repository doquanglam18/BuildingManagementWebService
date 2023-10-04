package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.DisplayTagUtils;
import com.laptrinhjavaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    IBuildingService buildingService;

    @Autowired
    IUserService userService;

    @Autowired
    private MessageUtils messageUtil;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/building-list");
        MyUserDetail myUserDetail = SecurityUtils.getPrincipal();
        if(myUserDetail.getId() != 1){
            buildingDTO.setStaffId(String.valueOf(myUserDetail.getId()));
        }
        DisplayTagUtils.of(request, buildingDTO);
//        List<BuildingDTO> buildingDTOS = buildingService.getAllUsers(new PageRequest(buildingDTO.getPage() - 1, buildingDTO.getMaxPageItems()));
        List<BuildingResponseDTO> buildingDTOS = buildingService.getAllUsers(new PageRequest(buildingDTO.getPage() - 1, buildingDTO.getMaxPageItems()), buildingDTO);
        buildingDTO.setListResult(buildingDTOS);
        buildingDTO.setTotalItems(buildingService.countTotalItems(buildingDTO));
        mav.addObject("modelSearch", buildingDTO);
//        mav.addObject("buildings", buildingService.findByCondition(buildingDTO));
        //kiểm tra lại
        mav.addObject("staffMaps", userService.getStaffMaps());
        mav.addObject("districtMaps", buildingService.getDistrictMap());
        mav.addObject("buildingTypeMaps", buildingService.getBuildingTypeMap());
        initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit() {
        ModelAndView mav = new ModelAndView("admin/building/building-edit");
        mav.addObject("buildingModel", new BuildingDTO());
        mav.addObject("districtMaps", buildingService.getDistrictMap());
        mav.addObject("buildingTypeMaps", buildingService.getBuildingTypeMap());
        return mav;
    }

    @RequestMapping(value = "/admin/building-insert", method = RequestMethod.GET)
    public ModelAndView buildingInsert(@RequestParam(value = "buildingId") Long buildingId) {
        ModelAndView mav = new ModelAndView("admin/building/building-insert");
//        mav.addObject("buildingModel", new BuildingDTO());
        BuildingCreateDTO buildingCreateDTO = buildingService.findById(buildingId);
        mav.addObject("buildingModel", buildingCreateDTO);
        mav.addObject("districtMaps", buildingService.getDistrictMap());
        mav.addObject("buildingTypes", buildingService.loadBuildingType(buildingCreateDTO));
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

