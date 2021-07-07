/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/21/21, 11:08 PM
 */

package com.phoenix.api.constant;

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

}
