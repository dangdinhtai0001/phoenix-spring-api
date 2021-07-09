/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/21/21, 11:08 PM
 */

package com.phoenix.api.base.constant;

/**
 * Định nghĩa các câu query khi cần phải sử dụng native query
 */
public class DatabaseConstant {
    public static final String FIND_USER_BY_USERNAME = "select fu.id, fu.username, fu.password, fu.hash_algorithm, fu.password_salt, fu.status, GROUP_CONCAT(fg.name SEPARATOR ', ') group_name from fw_user fu left join fw_user_group fug on fu.id = fug.user_id left join fw_group fg on fug.group_id = fg.id where fu.username = ?";

    public static final String FW_USER_UPDATE_REFRESH_TOKEN_BY_USERNAME = "update fw_user set refresh_token = ? where username = ?";

    public static final String FIND_PERMISSIONS_BY_USERNAME = "select DISTINCT fr.name  resource\n" +
            "              , fsr.mask mask\n" +
            "#               , fs.id    sid\n" +
            "#               , fs.name  name\n" +
            "from fw_sid fs\n" +
            "         left join fw_sid_resource fsr on fs.id = fsr.sid\n" +
            "         left join fw_resource fr on fsr.resource_id = fr.id\n" +
            "where (fs.name = ?\n" +
            "    or fs.name in (select fg.name\n" +
            "                   from fw_user fu\n" +
            "                            left join fw_user_group fug on fu.id = fug.user_id\n" +
            "                            left join fw_group fg on fug.group_id = fg.id\n" +
            "                   where fu.username = ?))\n" +
            "  and fr.name IS NOT NULL";
    public static final String FW_ALL_RESOURCE_PERMISSIONS_REQUIRED = "select\n" +
            "       fr.name             name,\n" +
            "       fra.action_name     action,\n" +
            "       fra.permission_mask mask\n" +
            "\n" +
            "from fw_resource fr\n" +
            "         left join fw_resource_action fra on fr.id = fra.resource_id\n" +
            "where fra.action_name is not null";
}
