/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/21/21, 11:08 PM
 */

package com.phoenix.api.constant;

public class DatabaseConstant {
    public static final String SQL_FIND_USER_BY_USERNAME = "select fu.id, fu.username, fu.password, fu.hash_algorithm, fu.password_salt, fu.status, GROUP_CONCAT(fg.name SEPARATOR ', ') group_name from fw_user fu left join fw_user_group fug on fu.id = fug.user_id left join fw_group fg on fug.group_id = fg.id where fu.username = ?";
}
