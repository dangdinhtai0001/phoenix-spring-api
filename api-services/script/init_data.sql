# =================================
# select * from fw_user;
# SELECT CONV(BINARY ('100'),2, 10) from dual;
# =================================
insert into fw_user(username, password, hash_algorithm, status)
    value ('admin_test', '123456', 'raw', 4);

# =================================
# select * from fw_user_group;
# delete from fw_user_group;
# =================================
insert into fw_user_group(user_id, group_id)
values (1, 1),
       (1, 2)
;

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
# select * from fw_resource_type;
# =================================
insert into fw_resource_type(description, name)
values ('Các path để truy cập vào api', upper('api')),
       ('Menu của ứng dụng', upper('menu'));

# =================================
# select * from fw_resource;
# =================================
insert into fw_resource (name, details, description, type)
values ('SIGN_IN', '/auth/login', 'api để đăng nhập', 1),
       ('FETCH_USER', '/user/current', 'api để lấy thông tin user hiện tại', 1);

# =================================
# select * from fw_permission order by code;
# =================================
insert into fw_permission(name, description, code)
select upper('create')                      name,
       'Quyền tạo mới'                      description,
       (select count(*) from fw_permission) code
from dual;

insert into fw_permission(name, description, code)
select upper('read')                        name,
       'Quyền xem thông tin'                description,
       (select count(*) from fw_permission) code
from dual;

insert into fw_permission(name, description, code)
select upper('update')                      name,
       'Quyền cập nhật thông tin'           description,
       (select count(*) from fw_permission) code
from dual;

insert into fw_permission(name, description, code)
select upper('delete')                      name,
       'Quyền xóa thông tin'                description,
       (select count(*) from fw_permission) code
from dual;

insert into fw_permission(name, description, code)
select upper('Execute')                     name,
       'Quyền thực thi'                     description,
       (select count(*) from fw_permission) code
from dual;

insert into fw_permission(name, description, code)
select upper('Access')                      name,
       'Quyền truy cập'                     description,
       (select count(*) from fw_permission) code
from dual;
insert into fw_permission(name, description, code)
select upper('Grant')                      name,
       'Quyền gán quyền '                     description,
       (select count(*) from fw_permission) code
from dual;

# =================================
# select * from fw_group_resources;
# =================================
insert into fw_group_resources(group_id, resource_id, permission)
values (1, 1, 32),
       (1, 2, 32);

commit;