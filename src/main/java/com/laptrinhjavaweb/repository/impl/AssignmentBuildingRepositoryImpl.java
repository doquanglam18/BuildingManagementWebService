package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.utils.ConnectionUtils;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class AssignmentBuildingRepositoryImpl {

    public void deleteByStaffIdAndBuildingId(Long builidngId , Long staffId){
        String sql = "delete FROM assignmentbuilding WHERE buildingid = ? AND staffid = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con =  ConnectionUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setLong(1, builidngId);
            ps.setLong(2,staffId);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
