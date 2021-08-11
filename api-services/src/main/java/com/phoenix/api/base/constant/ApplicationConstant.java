package com.phoenix.api.base.constant;

public class ApplicationConstant {
    public static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";
    public static final String JWT_TOKEN_TYPE = "Bearer ";

    public static final String BASE_PACKAGE_NAME = "com.phoenix.api";

    public static final String PASSWORD_ENCODER_PBKDF2_ID = "pbkdf2";
    public static final String PASSWORD_ENCODER_BCRYPT_ID = "bcrypt";
    public static final String PASSWORD_ENCODER_SCRYPT_ID = "scrypt";
    public static final String PASSWORD_ENCODER_RAW_ID = "raw";

    public static final String USER_DETAILS_STATUS_LOCKED = "LOCKED";
    public static final String USER_DETAILS_STATUS_ENABLED = "ENABLED";
    public static final String USER_DETAILS_STATUS_EXPIRED = "EXPIRED";


    public static final String[] PUBLIC_URLS_MATCHER = {
            "/**/ping", "/**/login", "/common/ping", "/auth/refresh"};
}
