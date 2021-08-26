package com.phoenix.api.base.constant;

public class BeanIds {
    //====================================================
    // Application config
    //====================================================
    public static final String ALL_EXCEPTION = "FW_ALL_EXCEPTION";
    public static final String JWT_PROVIDER = "FW_JWT_PROVIDER";
    public static final String JWT_SECRET_KEY = "FW_SECRET_KEY";
    public static final String UUID_FACTORY = "FW_UUID_FACTORY";
    public static final String AUTHORIZATION_ENFORCE = "FW_AUTHORIZATION_ENFORCE";
    public static final String SQL_QUERY_FACTORY = "SQL_QUERY_FACTORY";
    public static final String KIE_CONTAINER = "KIE_CONTAINER";
    public static final String KIE_FILE_SYSTEM = "KIE_FILE_SYSTEM";

    // ====================================================
    // controller
    //====================================================

    //====================================================
    // services
    //====================================================
    public static final String DEFAULT_USER_DETAIL_SERVICES = "FW_DEFAULT_USER_DETAIL_SERVICES";
    public static final String AUTHENTICATION_SERVICES = "FW_AUTHENTICATION_SERVICES";
    public static final String AUTHORIZATION_SERVICES = "FW_AUTHORIZATION_SERVICES";
    public static final String RESOURCE_ACTION_SERVICES = "FW_RESOURCE_ACTION_SERVICES";
    public static final String MENU_SERVICES = "FW_MENU_SERVICES";


    public static final String USER_SERVICES = "FW_USER_SERVICES";

    // ====================================================
    // Repository
    //====================================================
    public static final String BASE_USER_REPOSITORY_IMP = "FW_USER_REPOSITORY_IMP";
    public static final String EXCEPTION_REPOSITORY_IMP = "FW_EXCEPTION_REPOSITORY_IMP";
    public static final String AUTHORIZATION_REPOSITORY_IMP = "FW_AUTHORIZATION_REPOSITORY_IMP";
    public static final String RESOURCE_ACTION_REPOSITORY_IMP = "FW_RESOURCE_ACTION_REPOSITORY_IMP";
    public static final String MENU_REPOSITORY_IMP = "FW_MENU_REPOSITORY_IMP";
    public static final String USER_REPOSITORY_IMP = "USER_REPOSITORY_IMP";


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
