-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: phoenix-v2
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
INSERT INTO `fw_exception` VALUES (1,'AUTH_001','Wrong user credentials',400),(2,'AUTH_002','Your account has been locked',401),(3,'AUTH_003','Your account has expired',401),(4,'AUTH_004','Access denied, You do not have permission to access this feature.',403),(5,'DB_001','There was an error saving data to the database',500),(6,'AUTH_005','Invalid JWT refresh token',400),(7,'COM_001','Bad request',400),(8,'COM_002','Internal error',500);
/*!40000 ALTER TABLE `fw_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_filter_metadata`
--

LOCK TABLES `fw_filter_metadata` WRITE;
/*!40000 ALTER TABLE `fw_filter_metadata` DISABLE KEYS */;
INSERT INTO `fw_filter_metadata` VALUES (1,'com.phoenix.api.business.model.User','fw_user','id','fu','id','java.lang.Long',0,NULL),(2,'com.phoenix.api.business.model.User','profile','name','p','name','java.lang.String',0,NULL),(3,'com.phoenix.api.business.model.User','profile','date_of_birth','p','dateOfBirth','java.util.Date',0,NULL),(4,'com.phoenix.api.business.model.User','profile','gender','p','gender','java.lang.String',0,NULL),(5,'com.phoenix.api.business.model.User','profile','phone_number','p','phoneNumber','java.lang.String',0,NULL),(6,'com.phoenix.api.business.model.User','profile','avatar','p','avatar','java.lang.String',0,NULL),(7,'com.phoenix.api.business.model.User','fw_user','username','fu','username','java.lang.String',0,NULL),(8,'com.phoenix.api.business.model.User','fw_user','password','fu','password','java.lang.String',0,NULL);
/*!40000 ALTER TABLE `fw_filter_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_resource_action`
--

LOCK TABLES `fw_resource_action` WRITE;
/*!40000 ALTER TABLE `fw_resource_action` DISABLE KEYS */;
INSERT INTO `fw_resource_action` VALUES (1,'update','com.phoenix.api.business.services.imp.UserServiceImp',NULL),(2,'delete','com.phoenix.api.business.services.imp.UserServiceImp',NULL),(3,'create','com.phoenix.api.business.services.imp.UserServiceImp',NULL),(4,'deleteAll','com.phoenix.api.business.services.imp.UserServiceImp',NULL),(5,'findByCondition','com.phoenix.api.business.services.imp.UserServiceImp',NULL),(6,'countByCondition','com.phoenix.api.business.services.imp.UserServiceImp',NULL);
/*!40000 ALTER TABLE `fw_resource_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
INSERT INTO `fw_user` VALUES (1,'raw','123456',NULL,NULL,NULL,'42be671d-347e-4f25-9df8-8bc72cb903f7',1,'admin','NONE','2021-07-23 22:55:24','NONE','2021-08-11 20:07:17'),(2,'raw','123456',NULL,NULL,NULL,'a35511e3-ccb6-4ba3-a21b-484c3716091c',1,'user','NONE','2021-07-23 22:55:24','NONE','2021-08-11 00:28:14'),(3,'raw','123456',NULL,NULL,NULL,'25516805-6a92-4e64-9230-45c74a53fff9',1,'student_01','NONE','2021-07-23 22:55:24','NONE','2021-07-24 12:43:28'),(4,'raw','123456',NULL,NULL,NULL,'25516805-6a92-4e64-9230-45c74a53fff9',1,'student_02','NONE','2021-07-23 22:55:24','NONE','2021-07-24 12:43:28'),(5,'raw','123456',NULL,NULL,NULL,'25516805-6a92-4e64-9230-45c74a53fff9',1,'teacher_01','NONE','2021-07-23 22:55:24','NONE','2021-07-24 12:43:28');
/*!40000 ALTER TABLE `fw_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group`
--

LOCK TABLES `fw_user_group` WRITE;
/*!40000 ALTER TABLE `fw_user_group` DISABLE KEYS */;
INSERT INTO `fw_user_group` VALUES (1,'STUDENT'),(2,'TEACHER');
/*!40000 ALTER TABLE `fw_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group_mapping`
--

LOCK TABLES `fw_user_group_mapping` WRITE;
/*!40000 ALTER TABLE `fw_user_group_mapping` DISABLE KEYS */;
INSERT INTO `fw_user_group_mapping` VALUES (3,1),(4,1),(1,2),(5,2);
/*!40000 ALTER TABLE `fw_user_group_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_group_resource_action_mapping`
--

LOCK TABLES `fw_user_group_resource_action_mapping` WRITE;
/*!40000 ALTER TABLE `fw_user_group_resource_action_mapping` DISABLE KEYS */;
INSERT INTO `fw_user_group_resource_action_mapping` VALUES (1,2),(2,2),(3,2),(4,2),(5,2),(6,2);
/*!40000 ALTER TABLE `fw_user_group_resource_action_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_status`
--

LOCK TABLES `fw_user_status` WRITE;
/*!40000 ALTER TABLE `fw_user_status` DISABLE KEYS */;
INSERT INTO `fw_user_status` VALUES (1,'T√†i kho·∫£n ƒë√£ ƒë∆∞·ª£c k√≠ch ho·∫°t','ENABLED'),(2,'T√†i kho·∫£n ƒë√£ b·ªã kh√≥a','LOCKED'),(3,'T√†i kho·∫£n ƒë√£ h·∫øt h·∫°n','EXPIRED');
/*!40000 ALTER TABLE `fw_user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'Cyb Dignam','2021-07-23','MALE','719-906-1288','https://robohash.org/temporereprehenderitipsam.png?size=500x500&set=set1',1),(2,'ADaphna Sanbrooke','1998-01-23','FEMALE','193-887-1222','https://robohash.org/liberovoluptasid.png?size=500x500&set=set1',2);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('39cbeea6-97fa-402e-a78b-8e7f49237fe6','8d4b9314-0100-41ef-b911-7b51fa7d3ce6',1628687228541,1628689308000,1800,1628691108000,'admin');
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('39cbeea6-97fa-402e-a78b-8e7f49237fe6','SPRING_SECURITY_CONTEXT',_binary '¨\Ì\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0&\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0&\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0&\0L\0\rremoteAddresst\0Ljava/lang/String;L\0	sessionIdq\0~\0xpt\0	127.0.0.1t\0$8d4b9314-0100-41ef-b911-7b51fa7d3ce6t\0{raw}123456t\0admin');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'phoenix-v2'
--

--
-- Dumping routines for database 'phoenix-v2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-11 20:54:48
