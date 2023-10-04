package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BuildingRepositoryImpl  extends JdbcDaoImpl<BuildingEntity> implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    BuildingConverter buildingConverter;

    public String buildNativeQuery(BuildingDTO buildingDTO) {
        StringBuilder finalQuery = new StringBuilder();
//        String name = buildingDTO.getName();
//        String ward = buildingDTO.getWard();
//        String street = buildingDTO.getStreet();
//        String nameOfManager = buildingDTO.getNameOfManager();
//        String phoneOfManager = buildingDTO.getPhoneOfManager();
//        String floorArea = buildingDTO.getFloorArea();
//        String numberOfBasement = buildingDTO.getNumberOfBasement();
//        String district = buildingDTO.getDistrict();
        String rentPriceFrom = buildingDTO.getRentPriceFrom();
        String rentPriceTo = buildingDTO.getRentPriceTo();
        String areaFrom = buildingDTO.getAreaFrom();
        String areaTo = buildingDTO.getAreaTo();
        String staffId = buildingDTO.getStaffId();
        List<String> types = buildingDTO.getBuildingTypes();

        finalQuery.append(SystemConstant.ONE_EQUAL_ONE + " \n");
        if(!StringUtils.isNullOrEmpty(staffId)){
            finalQuery.append("and b.id IN (\n" +
                    "          SELECT buildingid FROM assignmentbuilding WHERE staffid = '" + staffId + "')");
        }
        if(!StringUtils.isNullOrEmpty(areaFrom) || !StringUtils.isNullOrEmpty(areaTo)) {
                finalQuery.append(" and b.id IN (select c.buildingid from rentarea c where 1 = 1 ");
            if (!StringUtils.isNullOrEmpty(areaFrom)) {
                finalQuery.append(" and c.value >= " + areaFrom + " ");
            }
            if (!StringUtils.isNullOrEmpty(areaTo)) {
                finalQuery.append(" and c.value <= " + areaTo + " ");
            }
            finalQuery.append(" )");
        }
        if (!StringUtils.isNullOrEmpty(rentPriceFrom)) {
            finalQuery.append(" and rentprice >= " + rentPriceFrom + "");
        }
        if (!StringUtils.isNullOrEmpty(rentPriceTo)) {
            finalQuery.append(" and rentprice <= " + rentPriceTo + "");
        }
        if (types != null && !types.isEmpty()) {
            finalQuery.append("and ( 1 = 2");
            for (String item : types) {
                finalQuery.append(" OR b.type LIKE'%" + item + "%'");
            }
            finalQuery.append(" ) ");
        }
        return finalQuery.toString();
    }

    public String buildQuery(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.converToEntity(buildingDTO);
        StringBuilder reflectionQuery = new StringBuilder();
        StringBuilder nativeQuery = new StringBuilder();
        reflectionQuery.append(sqlSearch(buildingEntity) + " ");
        nativeQuery.append(buildNativeQuery(buildingDTO));
        StringBuilder finalQuery = new StringBuilder("SELECT * FROM `building` b \n");
        finalQuery.append(nativeQuery)
                  .append(" " + reflectionQuery)
                  .append(" \nGROUP BY b.id");
        return finalQuery.toString();
    }

    @Override
    public List<BuildingEntity> getAllUsers(Pageable pageable, BuildingDTO buildingDTO ) {
        StringBuilder sql = new StringBuilder(buildQuery(buildingDTO));
                      sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                         .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql.toString());

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(BuildingDTO buildingDTO ) {
        String sql = buildQuery(buildingDTO);
//        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    @Override
    public void update(BuildingCreateDTO buildingCreateDTO) {
        BuildingEntity buildingEntity = buildingConverter.buildingEntity(buildingCreateDTO);
        update(buildingEntity);
    }


    @Override
    public List<BuildingEntity> findByCondition(BuildingDTO buildingDTO) {
//        StringBuilder sql = new StringBuilder(buildQuery(buildingDTO));
//        System.out.println("Final query: " + sql.toString());
//        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
//        return query.getResultList();
//        List<BuildingEntity> results = new ArrayList<>();
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            con = ConnectionUtils.getCon();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(buildQuery(params, types));
//
//            while(rs.next()) {
//                BuildingEntity buildingEntity = new BuildingEntity();
//                buildingEntity.setCreatedDate(rs.getDate("createddate"));
//                buildingEntity.setId(rs.getLong("id"));
//                buildingEntity.setName(rs.getString("name"));
//                buildingEntity.setStreet(rs.getString("street"));
//                buildingEntity.setWard(rs.getString("ward"));
//                buildingEntity.setDistrict(rs.getString("district"));
//                buildingEntity.setFullName(rs.getString("fullname"));
//                buildingEntity.setPhone(rs.getString("phone"));
//                buildingEntity.setFloorArea(rs.getInt("floorArea"));
//                buildingEntity.setRentPrice(rs.getInt("rentprice"));
//                buildingEntity.setServiceFee(rs.getString("servicefee"));
//                buildingEntity.setCarFee(rs.getString("carfee"));
//                buildingEntity.setMotorbikeFee(rs.getString("motorbikefee"));
//                buildingEntity.setOvertimeFee(rs.getString("overtimefee"));
//                buildingEntity.setWaterFee(rs.getString("waterfee"));
//                buildingEntity.setElectricityFee(rs.getString("electricityfee"));
//                buildingEntity.setDeposit(rs.getString("deposit"));
//                buildingEntity.setPayment(rs.getString("payment"));
//                buildingEntity.setBrokeragefee(rs.getString("brokeragefee"));
//                results.add(buildingEntity);
//            }
//            return results;
//        }
//        catch(SQLException e) {
//            e.printStackTrace();
//        }
//        catch(NullPointerException e) {
//            e.printStackTrace();
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                stmt.close();
//                con.close();
//                rs.close();
//            }
//            catch(SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return new ArrayList<>();
        return null;
    }

}