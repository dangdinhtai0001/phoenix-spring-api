/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

DROP database `phoenix`;
CREATE SCHEMA `phoenix` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci ;

DROP user 'phoenix'@'localhost';
CREATE user 'phoenix'@'localhost' IDENTIFIED by 'Abc123456';

GRANT ALL ON phoenix.* TO 'phoenix'@'localhost';
GRANT FILE ON *.* TO 'phoenix'@'localhost';
FLUSH PRIVILEGES;