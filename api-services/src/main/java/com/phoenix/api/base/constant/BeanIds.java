/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Created_date: 6/22/21, 9:54 AM
 */

package com.phoenix.api.base.constant;

/**
 *  Định nghĩa tên của các bean được dev định nghĩa trong application
 */
public class BeanIds {
    //====================================================
    // Application config
    //====================================================
    public static final String ALL_USER_STATUS = "FW_ALL_USER_STATUS";
    public static final String ALL_PERMISSIONS = "FW_ALL_PERMISSIONS";
    public static final String ALL_RESOURCE_PERMISSIONS_REQUIRED = "FW_ALL_RESOURCE_PERMISSIONS_REQUIRED";
    public static final String ALL_EXCEPTION = "FW_ALL_EXCEPTION";
    public static final String JWT_PROVIDER = "FW_JWT_PROVIDER";
    public static final String JWT_SECRET_KEY = "FW_SECRET_KEY";
    public static final String UUID_Factory = "FW_UUID_Factory";

    // ====================================================
    // controller
    //====================================================
    public static final String MENU_CONTROLLER = "FW_MENU_CONTROLLER";
    public static final String AUTH_CONTROLLER = "FW_AUTH_CONTROLLER";

    //====================================================
    // services
    //====================================================
    public static final String DEFAULT_USER_DETAIL_SERVICES = "FW_DEFAULT_USER_DETAIL_SERVICES";
    public static final String AUTH_SERVICES = "FW_AUTH_SERVICES";
    public static final String MENU_SERVICES = "FW_MENU_SERVICES";
    public static final String PERMISSION_EVALUATOR = "FW_PERMISSION_EVALUATOR";

    // ====================================================
    // Repository
    //====================================================
    public static final String SPECIFICATION_FACTORY = "FW_SPECIFICATION_FACTORY";
    public static final String AUTH_REPOSITORY_IMP = "FW_AUTH_REPOSITORY_IMP";
    public static final String USER_STATUS_REPOSITORY = "FW_USER_STATUS_REPOSITORY";
    public static final String USER_REPOSITORY = "FW_USER_REPOSITORY";
    public static final String EXCEPTION_REPOSITORY_IMP = "FW_EXCEPTION_REPOSITORY_IMP";
    public static final String MENU_REPOSITORY_IMP = "FW_MENU_REPOSITORY_IMP";
    public static final String PERMISSION_REPOSITORY_IMP = "FW_PERMISSION_REPOSITORY_IMP";


    //====================================================
    // Entry point
    public static final String JWT_AUTHENTICATION_ENTRY_POINT = "FW_JWT_AUTHENTICATION_ENTRY_POINT";
    public static final String DEFAULT_ACCESS_DENIED_ENTRY_POINT = "FW_DEFAULT_ACCESS_DENIED_ENTRY_POINT";
    //====================================================

    // ====================================================
    // Security config
    //====================================================
    public static final String DEFAULT_AUTHENTICATION_MANAGER = "FW_DEFAULT_AUTHENTICATION_MANAGER";
    public static final String PASSWORD_ENCODER = "FW_PASSWORD_ENCODER";
    public static final String JWT_AUTHENTICATION_FILTER = "FW_JWT_AUTHENTICATION_FILTER";

}
