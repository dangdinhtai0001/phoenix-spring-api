/*
 * @Author Đặng Đình Tài
 * @Date 6/25/21, 11:03 AM
 */

# =================================
# select * from fw_user;
# SELECT CONV(BINARY ('100'),2, 10) from dual;
# =================================
insert into fw_user(username, password, hash_algorithm, status)
    value ('admin_test', '123456', 'raw', 4);

# =================================
# select * from fw_group order by id;
# =================================
insert into fw_group(name, description)
values (upper('admin'), 'nhóm quản trị'),
       (upper('system'), 'nhóm hệ thống'),
       (upper('user'), 'nhóm người dùng'),
       (upper('guest'), 'nhóm khách');

# =================================
# select * from fw_user_status order by code;
# =================================
insert into fw_user_status(name, description, code)
select upper('enabled')                      name,
       'Tài khoản đã được kích hoạt'         description,
       (select count(*) from fw_user_status) code
from dual;
insert into fw_user_status(name, description, code)
select upper('locked')                       name,
       'Tài khoản đã bị khóa'                description,
       (select count(*) from fw_user_status) code
from dual;
insert into fw_user_status(name, description, code)
select upper('Expired')                      name,
       'Tài khoản đã hết hạn'                description,
       (select count(*) from fw_user_status) code
from dual;


# =================================
# select * from fw_user_group;
# delete from fw_user_group;
# =================================
insert into fw_user_group(user_id, group_id)
values (1, 1),
       (1, 2)
;

# =================================
# select * from fw_exception;
# delete from fw_exception;
# =================================
insert into fw_exception(code_, RESOURCE_, MESSAGE_, HTTP_CODE)
values ('AUTH_001', 'com.phoenix.api.services.auth.AuthServiceImp', 'wrong user credentials', 400)
;


commit;