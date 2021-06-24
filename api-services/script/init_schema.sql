/*
 * @Author Đặng Đình Tài
 * @Date 6/24/21, 8:31 AM
 */

DROP database test;
CREATE DATABASE test;

DROP user 'test'@'localhost';
CREATE user 'test'@'localhost' IDENTIFIED by 'Abc123456';


GRANT ALL ON test.* TO 'test'@'localhost';
GRANT FILE ON *.* TO 'test'@'localhost';
FLUSH PRIVILEGES;
