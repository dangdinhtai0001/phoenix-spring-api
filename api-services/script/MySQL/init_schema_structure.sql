-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: phoenix
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `fw_group`
--

DROP TABLE IF EXISTS `fw_group`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fw_group`
(
    `id`                 bigint                                    NOT NULL AUTO_INCREMENT,
    `description`        varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
    `name`               varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
    `created_by`         varchar(50) COLLATE utf8mb4_vietnamese_ci  DEFAULT 'NONE',
    `date_created`       datetime                                   DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by`   varchar(50) COLLATE utf8mb4_vietnamese_ci  DEFAULT 'NONE',
    `last_modified_date` datetime                                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fw_user`
--

DROP TABLE IF EXISTS `fw_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fw_user`
(
    `id`                       bigint                                     NOT NULL AUTO_INCREMENT,
    `hash_algorithm`           varchar(50) COLLATE utf8mb4_vietnamese_ci  DEFAULT NULL,
    `password`                 varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
    `password_reminder_expire` datetime                                   DEFAULT NULL,
    `password_reminder_token`  varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
    `password_salt`            varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
    `refresh_token`            varchar(200) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
    `status`                   int                                        DEFAULT NULL,
    `username`                 varchar(50) COLLATE utf8mb4_vietnamese_ci  NOT NULL,
    `created_by`               varchar(50) COLLATE utf8mb4_vietnamese_ci  DEFAULT 'NONE',
    `date_created`             datetime                                   DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by`         varchar(50) COLLATE utf8mb4_vietnamese_ci  DEFAULT 'NONE',
    `last_modified_date`       datetime                                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fw_user_group`
--

DROP TABLE IF EXISTS `fw_user_group`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fw_user_group`
(
    `user_id`  bigint NOT NULL,
    `group_id` bigint NOT NULL,
    KEY `FK_USER__USER_GROUP` (`user_id`),
    KEY `FK_GROUP__USER_GROUP` (`group_id`),
    CONSTRAINT `FK_GROUP__USER_GROUP` FOREIGN KEY (`group_id`) REFERENCES `fw_group` (`id`),
    CONSTRAINT `FK_USER__USER_GROUP` FOREIGN KEY (`user_id`) REFERENCES `fw_user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fw_user_status`
--

DROP TABLE IF EXISTS `fw_user_status`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fw_user_status`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `code`        int                                        DEFAULT NULL,
    `description` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
    `name`        varchar(20) COLLATE utf8mb4_vietnamese_ci  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session`
(
    `PRIMARY_ID`            char(36) COLLATE utf8mb4_vietnamese_ci NOT NULL,
    `SESSION_ID`            char(36) COLLATE utf8mb4_vietnamese_ci NOT NULL,
    `CREATION_TIME`         bigint                                 NOT NULL,
    `LAST_ACCESS_TIME`      bigint                                 NOT NULL,
    `MAX_INACTIVE_INTERVAL` int                                    NOT NULL,
    `EXPIRY_TIME`           bigint                                 NOT NULL,
    `PRINCIPAL_NAME`        varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
    PRIMARY KEY (`PRIMARY_ID`),
    UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
    KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
    KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_vietnamese_ci
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes`
(
    `SESSION_PRIMARY_ID` char(36) COLLATE utf8mb4_vietnamese_ci     NOT NULL,
    `ATTRIBUTE_NAME`     varchar(200) COLLATE utf8mb4_vietnamese_ci NOT NULL,
    `ATTRIBUTE_BYTES`    blob                                       NOT NULL,
    PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`),
    CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_vietnamese_ci
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fw_exception`
--

DROP TABLE IF EXISTS `fw_exception`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fw_exception`
(
    `id`        bigint NOT NULL AUTO_INCREMENT,
    `CODE_`     varchar(50) COLLATE utf8mb4_vietnamese_ci  DEFAULT NULL,
    `RESOURCE_` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
    `MESSAGE_`  varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
    `HTTP_CODE` int    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'phoenix'
--

--
-- Dumping routines for database 'phoenix'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2021-06-25 11:00:06
