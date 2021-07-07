-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: phoenix
-- ------------------------------------------------------
-- Server version	8.0.22

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
INSERT INTO `fw_exception` VALUES (1,'AUTH_001','com.phoenix.api.services.auth.AuthServiceImp','Wrong user credentials',400),(2,'AUTH_002','com.phoenix.api.services.auth.AuthServiceImp','Your account has been locked',401),(3,'AUTH_003','com.phoenix.api.services.auth.AuthServiceImp','Your account has expired',401);
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
INSERT INTO `fw_permission` VALUES (1,0,'CREATE','CR',NULL),(2,1,'READ','RE',NULL),(3,2,'UPDATE','UP',NULL),(4,3,'DELETE','DE',NULL),(5,4,'ADMIN','AD',NULL),(6,5,'EXECUTE','EX',NULL);
/*!40000 ALTER TABLE `fw_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_resource`
--

LOCK TABLES `fw_resource` WRITE;
/*!40000 ALTER TABLE `fw_resource` DISABLE KEYS */;
INSERT INTO `fw_resource` VALUES (1,'fw_menu','TABLE'),(2,'com.phoenix.api.services.common.MenuService','SERVICE'),(3,'com.phoenix.api.services.common.MenuService.findAll','METHOD'),(4,'com.phoenix.api.services.common.MenuService.findById','METHOD');
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
-- Dumping data for table `fw_resource_requirement`
--

LOCK TABLES `fw_resource_requirement` WRITE;
/*!40000 ALTER TABLE `fw_resource_requirement` DISABLE KEYS */;
INSERT INTO `fw_resource_requirement` VALUES (1,4,32);
/*!40000 ALTER TABLE `fw_resource_requirement` ENABLE KEYS */;
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
-- Dumping data for table `fw_sid_resource`
--

LOCK TABLES `fw_sid_resource` WRITE;
/*!40000 ALTER TABLE `fw_sid_resource` DISABLE KEYS */;
INSERT INTO `fw_sid_resource` VALUES (1,2,2),(2,1,31),(2,2,31),(2,4,32);
/*!40000 ALTER TABLE `fw_sid_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
INSERT INTO `fw_user` VALUES (1,'raw','123456',NULL,NULL,NULL,'5198c832-3148-4e82-a37d-8d3056f150f3',1,'admin_test','NONE','2021-07-03 22:03:08','NONE','2021-07-07 22:54:45');
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
INSERT INTO `spring_session` VALUES ('570b7130-8c14-46aa-89d0-36d3ed1844ee','9b4f8a6d-99ff-45ea-a027-92c068c6e52a',1625670649287,1625673785456,1800,1625675585456,'admin_test');
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('570b7130-8c14-46aa-89d0-36d3ed1844ee','SPRING_SECURITY_CONTEXT',_binary '�\�\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0&\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0&\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0&\0L\0rolet\0Ljava/lang/String;xpt\0\rfw_menu__READsq\0~\0t\03com.phoenix.api.services.common.MenuService__UPDATEsq\0~\0t\0fw_menu__CREATEsq\0~\0t\02com.phoenix.api.services.common.MenuService__ADMINsq\0~\0t\0fw_menu__DELETEsq\0~\0t\01com.phoenix.api.services.common.MenuService__READsq\0~\0t\0=com.phoenix.api.services.common.MenuService.findById__EXECUTEsq\0~\0t\0fw_menu__UPDATEsq\0~\0t\0fw_menu__ADMINsq\0~\0t\03com.phoenix.api.services.common.MenuService__CREATEsq\0~\0t\03com.phoenix.api.services.common.MenuService__DELETExq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0&\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0	127.0.0.1t\0$9b4f8a6d-99ff-45ea-a027-92c068c6e52at\0{raw}123456t\0\nadmin_test');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'phoenix'
--

--
-- Dumping routines for database 'phoenix'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-07 23:30:09
