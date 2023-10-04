package com.laptrinhjavaweb.utils;

import java.util.HashMap;
import java.util.Map;

public class DistrictUtils {

    public static Map<String,String> getDistrict() {
        Map<String,String> data = new HashMap<>();
        data.put("Q1", "Quận 1");
        data.put("Q2", "Quận 2");
        data.put("Q4", "Quận 4");
        return data;
    }
}
