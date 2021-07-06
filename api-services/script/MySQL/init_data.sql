-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: phoenix
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
INSERT INTO `fw_exception` VALUES (1,'AUTH_001','com.phoenix.api.services.auth.AuthServiceImp','wrong user credentials',400);
/*!40000 ALTER TABLE `fw_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_group`
--

LOCK TABLES `fw_group` WRITE;
/*!40000 ALTER TABLE `fw_group` DISABLE KEYS */;
INSERT INTO `fw_group` VALUES (1,'nhóm quản trị','ADMIN','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:03:08'),(2,'nhóm hệ thống','SYSTEM','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:03:08'),(3,'nhóm người dùng','USER','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:03:08'),(4,'nhóm khách','GUEST','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:03:08');
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
INSERT INTO `fw_permission` VALUES (1,1,'CREATE','CR',NULL),(2,2,'READ','RE',NULL),(3,3,'UPDATE','UP',NULL),(4,4,'DELETE','DE',NULL),(5,5,'ADMIN','AD',NULL);
/*!40000 ALTER TABLE `fw_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_resource`
--

LOCK TABLES `fw_resource` WRITE;
/*!40000 ALTER TABLE `fw_resource` DISABLE KEYS */;
INSERT INTO `fw_resource` VALUES (1,'fw_menu','TABLE');
/*!40000 ALTER TABLE `fw_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_resource_entry`
--

LOCK TABLES `fw_resource_entry` WRITE;
/*!40000 ALTER TABLE `fw_resource_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `fw_resource_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_resource_identity`
--

LOCK TABLES `fw_resource_identity` WRITE;
/*!40000 ALTER TABLE `fw_resource_identity` DISABLE KEYS */;
INSERT INTO `fw_resource_identity` VALUES (1,1,1,NULL,NULL),(2,1,2,NULL,NULL),(3,1,3,NULL,NULL),(4,1,4,NULL,NULL),(5,1,5,NULL,NULL),(6,1,6,NULL,NULL),(7,1,7,NULL,NULL),(8,1,8,NULL,NULL);
/*!40000 ALTER TABLE `fw_resource_identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_sid`
--

LOCK TABLES `fw_sid` WRITE;
/*!40000 ALTER TABLE `fw_sid` DISABLE KEYS */;
INSERT INTO `fw_sid` VALUES (1,'admin_test',1),(2,'ADMIN',0),(3,'SYSTEM',0),(4,'USER',0),(5,'GUEST',0);
/*!40000 ALTER TABLE `fw_sid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
INSERT INTO `fw_user` VALUES (1,'raw','123456',NULL,NULL,NULL,'451b7cba-b9e0-4098-b534-7ba9e2ed8cc7',4,'admin_test','NONE','2021-07-03 22:03:08','NONE','2021-07-03 22:09:33');
/*!40000 ALTER TABLE `fw_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group`
--

LOCK TABLES `fw_user_group` WRITE;
/*!40000 ALTER TABLE `fw_user_group` DISABLE KEYS */;
INSERT INTO `fw_user_group` VALUES (1,1),(1,2);
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

-- Dump completed on 2021-07-06 16:23:17
