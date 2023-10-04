package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.dto.response.BuildingTypeResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.BuildingTypeUtils;
import com.laptrinhjavaweb.utils.DistrictUtils;
import com.laptrinhjavaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingRepositoryImpl buildingRepositoryImpl;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Override
    public List<BuildingResponseDTO> findAll() {
        List<BuildingEntity> entities = buildingRepository.findAll();
        List<BuildingResponseDTO> results = buildingConverter.buildingResponseConverter(entities, rentAreaRepository);
        return results;
    }

    @Override
    public BuildingCreateDTO findById(Long buildingId) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(buildingId);
        BuildingCreateDTO buildingDTO = buildingConverter.buildingRequestDTO(buildingEntity.get());
        return buildingDTO;
    }

    @Override
    public List<BuildingResponseDTO> findByCondition(BuildingDTO buildingDTO) {
//        List<BuildingEntity> buildingEntities = buildingRepositoryImpl.findByCondition(buildingDTO);
//        return buildingConverter.buildingResponseConverter(buildingEntities, rentAreaRepository);
        return null;
    }

    @Override
    @Transactional
    public void save(BuildingCreateDTO buildingCreateDTO) {
        Long buildingIdOld = buildingCreateDTO.getId();
        BuildingEntity buildingEntity = buildingConverter.buildingEntity(buildingCreateDTO);
        List<UserEntity> userEntities = new ArrayList<>();
        if(buildingIdOld != null){
            userEntities = buildingRepository.findById(buildingIdOld).get().getUsers();
        }
        if (buildingIdOld != null) { // update
            BuildingEntity foundBuilding = buildingRepository.findById(buildingIdOld)
                    .orElseThrow(() -> new NotFoundException("Building not found!"));
            buildingEntity.setImage(foundBuilding.getImage());
            buildingEntity.setUsers(userEntities);
        }
        saveThumbnail(buildingCreateDTO, buildingEntity);
        List<String> values = rentAreaService.converterToList(buildingCreateDTO.getRentArea());
        List<RentAreaEntity> rentAreaEntities = rentAreaConverter.rentAreaConverterToEntity(buildingEntity,values);
        buildingEntity.setRentAreaEntities(rentAreaEntities);
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void update(BuildingCreateDTO buildingCreateDTO) {
//        Long buildingIdOld = buildingCreateDTO.getId();
//        List<RentAreaEntity> rentAreaEntities = null;
//        List<AssignmentBuildingEntity> assignmentBuildingEntities = null;
//        if(buildingIdOld != null){
//            rentAreaEntities = rentAreaService.getAllByBuildingId(buildingIdOld);
//            assignmentBuildingEntities = assignmentBuildingService.getAllByBuildingId(buildingIdOld);
//        }if(assignmentBuildingEntities != null && !assignmentBuildingEntities.isEmpty()){
//            assignmentBuildingService.deleteByBuildingId(buildingIdOld);
//        }
//        if(rentAreaEntities != null && !rentAreaEntities.isEmpty()){
//            rentAreaService.deleteByBuildingId(buildingIdOld);
//        }
//        BuildingEntity buildingEntity = buildingConverter.buildingEntity(buildingCreateDTO);
//        if (buildingIdOld != null) { // update
//            BuildingEntity foundBuilding = buildingRepository.findById(buildingIdOld)
//                    .orElseThrow(() -> new NotFoundException("Building not found!"));
//            buildingEntity.setImage(foundBuilding.getImage());
////            buildingEntity.setAssignmentBuildingEntities(assignmentBuildingEntities);
//        }
//        saveThumbnail(buildingCreateDTO, buildingEntity);
//        buildingRepository.save(buildingEntity);
//        if(!StringUtils.isNullOrEmpty(buildingCreateDTO.getRentArea())){
//            Long buildingId = buildingEntity.getId();
//            List<String> values = rentAreaService.converterToList(buildingCreateDTO.getRentArea());
//            rentAreaService.save(buildingId, values);
//        }
//        if(assignmentBuildingEntities != null || !assignmentBuildingEntities.isEmpty()){
//            assignmentBuildingService.save(assignmentBuildingEntities);
//        }
    }

    @Transactional
    @Override
    public void delete(List<Long> buildingIds) {
//        rentAreaRepository.deleteByBuilding_IdIn(buildingIds);
        buildingRepository.deleteByIdIn(buildingIds);
    }

    @Override
    public Map<String, String> getDistrictMap() {
        return DistrictUtils.getDistrict();
    }
    @Override
    public Map<String, String> getBuildingTypeMap() {
        return BuildingTypeUtils.getBuildingTypes();
    }

    @Override
    public List<BuildingResponseDTO> getAllUsers(Pageable pageable, BuildingDTO buildingDTO) {
        List<BuildingEntity> buildingEntities = buildingRepository.getAllUsers(pageable, buildingDTO);
        List<BuildingResponseDTO> results = buildingConverter.buildingResponseConverter(buildingEntities,rentAreaRepository);
        return results;
    }

    @Override
    public int countTotalItems(BuildingDTO buildingDTO) {
        return buildingRepository.countTotalItem(buildingDTO);
    }

    private void saveThumbnail(BuildingCreateDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

    @Override
    public List<BuildingTypeResponseDTO> loadBuildingType(BuildingCreateDTO buildingCreateDTO){
        Map<String,String> buildingMaps =  BuildingTypeUtils.getBuildingTypes();
        List<BuildingTypeResponseDTO> buildingTypeResponseDTOS = new ArrayList<>();
        for(Map.Entry<String, String> entry : buildingMaps.entrySet()){
            BuildingTypeResponseDTO buildingTypeResponseDTO = new BuildingTypeResponseDTO();
            buildingTypeResponseDTO.setName(entry.getValue());
            buildingTypeResponseDTO.setCode(entry.getKey());
            buildingTypeResponseDTOS.add(buildingTypeResponseDTO);
        }
        if(buildingCreateDTO.getBuildingTypes() == null || buildingCreateDTO.getBuildingTypes().isEmpty()){
            return buildingTypeResponseDTOS;
        }
        List<String> buildingTypes = converterToList(buildingCreateDTO.getBuildingTypes());
        buildingTypeResponseDTOS = assignmentBuildingService.findBuildingTypeToCheck(buildingTypes, buildingTypeResponseDTOS);
        return buildingTypeResponseDTOS;
    }

    public List<String> converterToList(String id){
        List<String> buildingTypes = new ArrayList<>();
        String[] ids = id.split("[,\\s]+");
        for( String item : ids) {
            buildingTypes.add(item);
        }
        return buildingTypes;
    }
}