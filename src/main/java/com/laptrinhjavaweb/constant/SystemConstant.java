package com.laptrinhjavaweb.constant;

public class SystemConstant {
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
//    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    public static final String ADMIN_ROLE = "ROLE_MANAGER";
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
//    public static final String USER_ROLE = "ROLE_USER";
    public static final String USER_ROLE = "ROLE_STAFF";
    public static final String MANAGER_ROLE = "MANAGER";
    public static final String HOME = "/trang-chu";
    public static final String ADMIN_HOME = "/admin/home";
    public static final String MODEL = "model";
    public static final String TRANSACTIONS = "load_transaction";
    public static final String INSERT_SUCCESS = "insert_success";
    public static final String UPDATE_SUCCESS = "update_success";
    public static final String DELETE_SUCCESS = "delete_success";
    public static final String ERROR_SYSTEM = "error_system";
    public static final String ALERT = "alert";
    public static final String MESSAGE_RESPONSE = "messageResponse";
    public static final String PASSWORD_DEFAULT = "123456";
    public static final String CHANGE_PASSWORD_FAIL = "change_password_fail";
    public static final String ONE_EQUAL_ONE = "where 1 = 1";
    public static final String TANG_TRET = "Tầng trệt";
    public static final String NOI_THAT = "Nội thất";
    public static final String NGUYEN_CAN = "Nguyên căn";
    public static final String CODE_TANG_TRET = "TANG_TRET";
    public static final String CODE_NOI_THAT = "NOI_THAT";
    public static final String CODE_NGUYEN_CAN = "NGUYEN_CAN";
    public static final String STATUS_PROCESSING = "Đang xử lý";
    public static final String STATUS_WAITING = "Chờ xử lý";
}
