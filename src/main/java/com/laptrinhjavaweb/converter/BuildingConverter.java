package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.utils.DistrictUtils;
import com.laptrinhjavaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  RentAreaConverter newRentAreaConverter;

    public BuildingCreateDTO buildingRequestDTO (BuildingEntity entity){
        BuildingCreateDTO result = modelMapper.map(entity, BuildingCreateDTO.class);
        result.setRentArea(converRentArea(entity.getRentAreaEntities()));
        return result;
    }

    public BuildingDTO converToBuildingDTO (BuildingEntity entity){
        BuildingDTO result = modelMapper.map(entity,BuildingDTO.class);
        return  result;
    }

    public BuildingEntity converToEntity (BuildingDTO buildingDTO){
        BuildingEntity result = new BuildingEntity();
        result.setName(buildingDTO.getName());
        if(!StringUtils.isNullOrEmpty(buildingDTO.getFloorArea())){
            result.setFloorArea(Integer.valueOf(buildingDTO.getFloorArea()));
        }
        result.setDistrict(buildingDTO.getDistrict());
        result.setStreet(buildingDTO.getStreet());
        result.setWard(buildingDTO.getWard());
        if(!StringUtils.isNullOrEmpty(buildingDTO.getNumberOfBasement())){
            result.setNumberOfBasement(Integer.valueOf(buildingDTO.getNumberOfBasement()));
        }
        result.setFullName(buildingDTO.getNameOfManager());
        result.setPhone(buildingDTO.getPhoneOfManager());
        return result;
    }

    public BuildingEntity buildingEntity (BuildingCreateDTO buildingCreateDTO){
        BuildingEntity result = modelMapper.map(buildingCreateDTO, BuildingEntity.class);
        return result;
    }

    public Map<String,String> buildingMap(BuildingDTO buildingDTO){
        Map<String, String> params = new HashMap<>();
        params.put("name", buildingDTO.getName());
        params.put("floorarea", buildingDTO.getFloorArea());
        params.put("district", buildingDTO.getDistrict());
        params.put("ward", buildingDTO.getWard());
        params.put("street", buildingDTO.getStreet());
        params.put("numberofbasement", buildingDTO.getNumberOfBasement());
        params.put("areafrom", buildingDTO.getAreaFrom());
        params.put("areato", buildingDTO.getAreaTo());
        params.put("rentpricefrom", buildingDTO.getRentPriceFrom());
        params.put("rentpriceto", buildingDTO.getRentPriceTo());
        params.put("nameofmanager", buildingDTO.getNameOfManager());
        params.put("phoneofmanager", buildingDTO.getPhoneOfManager());
        params.put("staffid", buildingDTO.getStaffId());
        params.put("nameofstaff", buildingDTO.getNameOfStaff());
        return params;
    }

    public List<BuildingResponseDTO> buildingResponseConverter(List<BuildingEntity> buildingEntities,RentAreaRepository rentAreaRepository) {
        Map<String,String> districtUtils = DistrictUtils.getDistrict();
        List<BuildingResponseDTO> buildingOutPuts = new ArrayList<>();
        Integer servicePrice = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for(BuildingEntity item : buildingEntities) {
            BuildingResponseDTO buildingOutPut = new BuildingResponseDTO();
            if(item.getCreatedDate() != null ) {
                buildingOutPut.setCreatedDate(item.getCreatedDate());
            }
            buildingOutPut.setId(item.getId());
            buildingOutPut.setName(item.getName());
            buildingOutPut.setAddress(item.getStreet() + " " + item.getWard() + " " + districtUtils.get(item.getDistrict()));
            buildingOutPut.setNameOfUser(item.getFullName());
            buildingOutPut.setPhoneOfUser(item.getPhone());
            buildingOutPut.setFloorArea(item.getFloorArea());
            if(item.getFloorArea() == null || item.getRentPrice() == null){
                buildingOutPut.setRentPrice("0$");
            }
            else {
                Integer floorArea = item.getFloorArea();
                Integer rentPrice = item.getRentPrice();
                buildingOutPut.setRentPrice((rentPrice * floorArea) + "$");
            }
            buildingOutPut.setRentArea( newRentAreaConverter.rentAreaConverter(rentAreaRepository.findByBuildingId(item.getId())));
            if(!StringUtils.isNullOrEmpty(item.getBrokeragefee())) {
                buildingOutPut.setBrokerageFee(item.getBrokeragefee());
            }
            if(!StringUtils.isNullOrEmpty(item.getCarFee())) {
                servicePrice += Integer.parseInt(item.getCarFee());
            }
            if(!StringUtils.isNullOrEmpty(item.getMotorbikeFee())) {
                servicePrice += Integer.valueOf(item.getMotorbikeFee());
            }
            if(!StringUtils.isNullOrEmpty(item.getOvertimeFee())) {
                servicePrice += Integer.valueOf(item.getOvertimeFee());
            }
            if(!StringUtils.isNullOrEmpty(item.getWaterFee())) {
                servicePrice += Integer.valueOf(item.getWaterFee());
            }
            if(!StringUtils.isNullOrEmpty(item.getElectricityFee())) {
                servicePrice += Integer.valueOf(item.getElectricityFee());
            }
            if(!StringUtils.isNullOrEmpty(item.getDeposit())) {
                servicePrice += Integer.valueOf(item.getDeposit());
            }
            if(!StringUtils.isNullOrEmpty(item.getPayment())) {
                servicePrice += Integer.valueOf(item.getPayment());
            }
            buildingOutPut.setServiceFee(servicePrice + "$");
            buildingOutPuts.add(buildingOutPut);
        }
        return buildingOutPuts;
    }

    private String converType(List<String> types ){
        StringBuilder type = new StringBuilder();
        for (String item : types){
            type.append(item + " ");
        }
        return type.toString();
    }

    private String converRentArea(List<RentAreaEntity> rentAreaEntities){
        List<String> rentAreas = new ArrayList<>();
        for (RentAreaEntity rentAreaEntity : rentAreaEntities){
            rentAreas.add(rentAreaEntity.getValue());
        }
        String rentArea = String.join(",",rentAreas);
        return rentArea;
    }
}