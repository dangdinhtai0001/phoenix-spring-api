DROP database `phoenix-v2`;
CREATE SCHEMA `phoenix-v2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci ;

DROP user 'phoenix-v2'@'localhost';
CREATE user 'phoenix-v2'@'localhost' IDENTIFIED by 'Abc123456';

GRANT ALL ON `phoenix-v2`.* TO 'phoenix-v2'@'localhost';
GRANT FILE ON *.* TO 'phoenix-v2'@'localhost';
FLUSH PRIVILEGES;