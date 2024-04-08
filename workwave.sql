-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: workwave
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(50) NOT NULL,
  `passcode` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('abhay newuser','Abhay@123','abhay@gmail.com'),('viraj choubey','Viraj@123','viraj@gmail.com');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timerx_report`
--

DROP TABLE IF EXISTS `timerx_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timerx_report` (
  `username` varchar(50) DEFAULT NULL,
  `workID` int DEFAULT NULL,
  `work` varchar(300) DEFAULT NULL,
  `startTime` varchar(50) DEFAULT NULL,
  `endTime` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timerx_report`
--

LOCK TABLES `timerx_report` WRITE;
/*!40000 ALTER TABLE `timerx_report` DISABLE KEYS */;
INSERT INTO `timerx_report` VALUES ('viraj choubey',10001,'I want to do study','Sat Mar 25 23:19:39 IST 2023','Sat Mar 25 23:21:03 IST 2023'),('viraj choubey',177555,'new work','Sat Mar 25 23:28:45 IST 2023','Sat Mar 25 23:29:00 IST 2023'),('viraj choubey',1908550,'new work02','Sat Mar 25 23:37:50 IST 2023','Sat Mar 25 23:38:01 IST 2023'),('viraj choubey',10001,'I want to do study','Sat Mar 25 23:40:33 IST 2023','Sat Mar 25 23:41:02 IST 2023');
/*!40000 ALTER TABLE `timerx_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_table`
--

DROP TABLE IF EXISTS `work_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_table` (
  `work` varchar(350) NOT NULL,
  `time` int DEFAULT '0',
  `priority` int DEFAULT '0',
  `username` varchar(50) DEFAULT NULL,
  `progress` varchar(100) NOT NULL DEFAULT 'working on it',
  `workID` int DEFAULT NULL,
  UNIQUE KEY `workID` (`workID`),
  KEY `username` (`username`),
  CONSTRAINT `work_table_ibfk_1` FOREIGN KEY (`username`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_table`
--

LOCK TABLES `work_table` WRITE;
/*!40000 ALTER TABLE `work_table` DISABLE KEYS */;
INSERT INTO `work_table` VALUES ('I want to do study',2,8,'viraj choubey','completed',10001),('I want to do dsa',3,7,'viraj choubey','not doing anymore',10002),('I want to dance',1,5,'viraj choubey','not doing anymore',8940676),('new work',4,8,'viraj choubey','completed',177555),('new work02',3,6,'viraj choubey','completed',1908550);
/*!40000 ALTER TABLE `work_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-26 21:25:44
