/*
 * @Author Đặng Đình Tài
 * @Created_date: 6/22/21, 9:28 AM
 */

package com.phoenix.api.constant;

public class ApplicationConstant {

    public static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";
    public static final String JWT_TOKEN_TYPE = "Bearer ";


    public static final String USER_DETAILS_STATUS_LOCKED = "LOCKED";
    public static final String USER_DETAILS_STATUS_ENABLED = "ENABLED";
    public static final String USER_DETAILS_STATUS_EXPIRED = "EXPIRED";

    public static final String[] PUBLIC_URLS_MATCHER = {
            "/**/ping", "/**/login"};
}
