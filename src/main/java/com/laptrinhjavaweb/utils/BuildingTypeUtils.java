package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.constant.SystemConstant;

import java.util.HashMap;
import java.util.Map;

public class BuildingTypeUtils {
    public static Map<String,String> getBuildingTypes() {
        Map<String,String> data = new HashMap<>();
        data.put(SystemConstant.CODE_NGUYEN_CAN, SystemConstant.NGUYEN_CAN);
        data.put(SystemConstant.CODE_NOI_THAT, SystemConstant.NOI_THAT);
        data.put(SystemConstant.CODE_TANG_TRET, SystemConstant.TANG_TRET);
        return data;
    }
}
