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
INSERT INTO `fw_exception` VALUES (1,'AUTH_001','Wrong user credentials',400),(2,'AUTH_002','Your account has been locked',401),(3,'AUTH_003','Your account has expired',401),(4,'AUTH_004','Access denied, You do not have permission to access this feature.',403),(5,'DB_001','There was an error saving data to the database',500),(6,'AUTH_005','Invalid JWT refresh token',400),(7,'COM_001','Bad request',400);
/*!40000 ALTER TABLE `fw_exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user`
--

LOCK TABLES `fw_user` WRITE;
/*!40000 ALTER TABLE `fw_user` DISABLE KEYS */;
INSERT INTO `fw_user` VALUES (1,'raw','123456',NULL,NULL,NULL,'b4287d2d-54d5-4aa7-96e7-81df2ea71020',1,'admin','NONE','2021-07-23 22:55:24','NONE','2021-07-24 12:31:59'),(2,'raw','123456',NULL,NULL,NULL,'d9ebeccf-21ef-46a9-9ef2-e9e897766848',1,'user','NONE','2021-07-23 22:55:24','NONE','2021-07-26 18:18:11'),(3,'raw','123456',NULL,NULL,NULL,'25516805-6a92-4e64-9230-45c74a53fff9',1,'student_01','NONE','2021-07-23 22:55:24','NONE','2021-07-24 12:43:28'),(4,'raw','123456',NULL,NULL,NULL,'25516805-6a92-4e64-9230-45c74a53fff9',1,'student_02','NONE','2021-07-23 22:55:24','NONE','2021-07-24 12:43:28'),(5,'raw','123456',NULL,NULL,NULL,'25516805-6a92-4e64-9230-45c74a53fff9',1,'teacher_01','NONE','2021-07-23 22:55:24','NONE','2021-07-24 12:43:28');
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
INSERT INTO `fw_user_group_mapping` VALUES (3,1),(4,1),(5,2);
/*!40000 ALTER TABLE `fw_user_group_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fw_user_status`
--

LOCK TABLES `fw_user_status` WRITE;
/*!40000 ALTER TABLE `fw_user_status` DISABLE KEYS */;
INSERT INTO `fw_user_status` VALUES (1,'Tài khoản đã được kích hoạt','ENABLED'),(2,'Tài khoản đã bị khóa','LOCKED'),(3,'Tài khoản đã hết hạn','EXPIRED');
/*!40000 ALTER TABLE `fw_user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'Cyb Dignam','2021-07-23','MALE','719-906-1288','https://robohash.org/temporereprehenderitipsam.png?size=500x500&set=set1',1),(2,'Daphna Sanbrooke','1998-01-23','FEMALE','193-887-1222','https://robohash.org/liberovoluptasid.png?size=500x500&set=set1',2);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('b2af61d5-8ddd-4fe1-910b-de1aea42300a','5f0fae82-98dc-4687-ae5d-6b2d42639d5e',1627309984190,1627310332685,1800,1627312132685,NULL);
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
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

-- Dump completed on 2021-07-27 23:52:54
