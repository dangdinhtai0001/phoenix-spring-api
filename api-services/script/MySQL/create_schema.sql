/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

DROP database `student-management-sample`;
CREATE SCHEMA `student-management-sample` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci ;

DROP user 'student-management-sample'@'localhost';
CREATE user 'student-management-sample'@'localhost' IDENTIFIED by 'Abc123456';

GRANT ALL ON student-management-sample.* TO 'student-management-sample'@'localhost';
GRANT FILE ON *.* TO 'student-management-sample'@'localhost';
FLUSH PRIVILEGES;