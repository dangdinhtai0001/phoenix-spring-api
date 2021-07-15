-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: student-management-sample
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `fw_exception`
--

LOCK TABLES `fw_exception` WRITE;
/*!40000 ALTER TABLE `fw_exception` DISABLE KEYS */;
INSERT INTO `fw_exception` VALUES (1,'AUTH_001','Wrong user credentials',400),(2,'AUTH_002','Your account has been locked',401),(3,'AUTH_003','Your account has expired',401),(4,'AUTH_004','Access denied, You do not have permission to access this feature.',403);
/*!40000 ALTER TABLE `fw_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_group`
--

LOCK TABLES `fw_group` WRITE;
/*!40000 ALTER TABLE `fw_group` DISABLE KEYS */;
INSERT INTO `fw_group` VALUES (1,'nhóm quản trị','ADMIN','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:03:08'),(2,'nhóm hệ thống','SYSTEM','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:03:08'),(3,'nhóm người dùng','USER','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:03:08'),(4,'nhóm khách','GUEST','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:03:08'),(5,'Nhóm giáo viên','TEACHER','NONE','2021-07-15 16:19:08','NONE','2021-07-15 16:19:08'),(6,'Nhóm sinh viên','STUDENT','NONE','2021-07-15 16:19:08','NONE','2021-07-15 16:19:08');
/*!40000 ALTER TABLE `fw_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_menu`
--

LOCK TABLES `fw_menu` WRITE;
/*!40000 ALTER TABLE `fw_menu` DISABLE KEYS */;
INSERT INTO `fw_menu` VALUES (1,'menu_1','/menu_1','menu_1',NULL),(2,'menu_2','/menu_2','menu_2',NULL),(3,'menu_3','/menu_3','menu_3',NULL),(4,'menu_1_1','/menu_1_1','menu_1_1',1),(5,'menu_1_2','/menu_1_2','menu_1_2',1),(6,'menu_2_1','/menu_2_1','menu_2_1',2),(7,'menu_3_1','/menu_3_1','menu_3_1',3),(8,'menu_1_2_1','/menu_1_2_1','menu_1_1',5);
/*!40000 ALTER TABLE `fw_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_permission`
--

LOCK TABLES `fw_permission` WRITE;
/*!40000 ALTER TABLE `fw_permission` DISABLE KEYS */;
INSERT INTO `fw_permission` VALUES (1,0,'CREATE','CR',NULL),(2,1,'READ','RE',NULL),(3,2,'UPDATE','UP',NULL),(4,3,'DELETE','DE',NULL),(5,4,'ADMIN','AD',NULL),(6,5,'EXECUTE','EX',NULL);
/*!40000 ALTER TABLE `fw_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_resource`
--

LOCK TABLES `fw_resource` WRITE;
/*!40000 ALTER TABLE `fw_resource` DISABLE KEYS */;
INSERT INTO `fw_resource` VALUES (1,'fw_menu','TABLE',NULL),(2,'com.phoenix.api.services.common.MenuService','SERVICE',NULL),(5,'com.phoenix.api.services.ProfileServiceImp','SERVICE','Service quản lí profile của người dùng');
/*!40000 ALTER TABLE `fw_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_resource_action`
--

LOCK TABLES `fw_resource_action` WRITE;
/*!40000 ALTER TABLE `fw_resource_action` DISABLE KEYS */;
INSERT INTO `fw_resource_action` VALUES (1,2,'findAll',2,'Tìm kiếm tất cả'),(2,2,'findById',2,'Tìm kiếm theo id'),(3,2,'findBy',2,'Tìm kiếm theo điều kiện'),(4,5,'add',0,'Thêm profile'),(5,5,'findAll',2,'Tìm tất cả frofile'),(6,5,'update',4,'Cập nhật profile'),(7,5,'remove',8,'Xóa profile'),(8,5,'findBy',2,'Tìm profile theo điều kiện'),(9,5,'findById',2,'Tìm profile theo id');
/*!40000 ALTER TABLE `fw_resource_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_sid`
--

LOCK TABLES `fw_sid` WRITE;
/*!40000 ALTER TABLE `fw_sid` DISABLE KEYS */;
INSERT INTO `fw_sid` VALUES (1,'admin_test',1),(2,'ADMIN',0),(3,'SYSTEM',0),(4,'USER',0),(5,'GUEST',0),(9,'guest',1),(10,'TEACHER',0),(11,'STUDENT',0);
/*!40000 ALTER TABLE `fw_sid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_sid_resource`
--

LOCK TABLES `fw_sid_resource` WRITE;
/*!40000 ALTER TABLE `fw_sid_resource` DISABLE KEYS */;
INSERT INTO `fw_sid_resource` VALUES (1,2,2),(2,1,31),(2,2,31),(10,5,15),(11,5,6);
/*!40000 ALTER TABLE `fw_sid_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
INSERT INTO `fw_user` VALUES (1,'raw','123456',NULL,NULL,NULL,'4f380bab-4644-407f-a237-c7d385a2d37f',1,'admin_test','NONE','2021-07-03 22:03:08','NONE','2021-07-10 11:52:06'),(2,'raw','123456',NULL,NULL,NULL,'2bdbd032-ff28-417d-9a3b-3fe64f5df876',1,'guest','NONE','2021-07-09 09:42:01','NONE','2021-07-10 10:37:27'),(3,'raw','123456',NULL,NULL,NULL,NULL,1,'teacher','NONE','2021-07-15 16:09:24','NONE','2021-07-15 16:09:24'),(4,'raw','123456',NULL,NULL,NULL,'46089bcc-ece9-4ab2-b27c-a265d05fd127',1,'student','NONE','2021-07-15 16:12:00','NONE','2021-07-15 17:20:58');
/*!40000 ALTER TABLE `fw_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group`
--

LOCK TABLES `fw_user_group` WRITE;
/*!40000 ALTER TABLE `fw_user_group` DISABLE KEYS */;
INSERT INTO `fw_user_group` VALUES (1,1),(1,2),(2,4),(3,5),(4,6);
/*!40000 ALTER TABLE `fw_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_status`
--

LOCK TABLES `fw_user_status` WRITE;
/*!40000 ALTER TABLE `fw_user_status` DISABLE KEYS */;
INSERT INTO `fw_user_status` VALUES (1,0,'Tài khoản đã được kích hoạt','ENABLED'),(2,1,'Tài khoản đã bị khóa','LOCKED'),(3,2,'Tài khoản đã hết hạn','EXPIRED');
/*!40000 ALTER TABLE `fw_user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-15 22:23:41
