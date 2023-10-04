//package com.laptrinhjavaweb.repository;
//
//import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
//import com.laptrinhjavaweb.entity.BuildingEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
////    @Query(value ="SELECT * FROM `assignmentbuilding` \n" +
////            "WHERE buildingid = ?1 AND staffid IN (SELECT users.id FROM users WHERE users.id IN (\n" +
////            "SELECT user_role.user_id FROM user_role WHERE user_role.role_id IN (\n" +
////            "SELECT role.id FROM role WHERE role.code = 'USER')))", nativeQuery = true)
//    List<AssignmentBuildingEntity> findByBuildingId(Long buildingId);
//
//    Long deleteByStaffIdAndBuildingId(Long staffId, Long buildingId);
//    //@Query("DELETE FROM AssignmentBuildingEntity u WHERE u.id = ?1")
//    Long deleteByBuilding_Id(Long buildingId);
//
//}
//
////check
