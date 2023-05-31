-- MariaDB dump 10.17  Distrib 10.4.14-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: galacticenergies
-- ------------------------------------------------------
-- Server version	10.4.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- -----------------------------------------------------
-- Schema GalacticEnergies
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GalacticEnergies
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `galacticenergies` DEFAULT CHARACTER SET utf8 ;
USE `galacticenergies` ;

---------------------------------------------------
-- Create User galacticenergies
---------------------------------------------------

CREATE USER IF NOT EXISTS 'galacticenergies' IDENTIFIED BY 'galacticenergies';

--------------------------------------------------
-- Set Permissons for user
--------------------------------------------------

GRANT ALL PRIVILEGES ON *.* to 'galacticenergies'@'%';

--
-- Table structure for table `energydata`
--

DROP TABLE IF EXISTS `energydata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `energydata` (
  `idenergydata` int(11) NOT NULL AUTO_INCREMENT,
  `devicename` varchar(45) DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  PRIMARY KEY (`idenergydata`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energydata`
--

LOCK TABLES `energydata` WRITE;
/*!40000 ALTER TABLE `energydata` DISABLE KEYS */;
INSERT INTO energydata (idenergydata, devicename, power)
VALUES 
(15,'You could use a Smartphone for', 20),
(16,'You could use a TV for', 150),
(17,'You could use a Laptop for', 80),
(18,'You could use a Fridge for', 150),
(19,'You could use a Freezer for', 150),
(20,'You could use a Dishwasher for', 1250),
(21,'You could use a Washing machine for', 2400000),
(22,'You could use a LED lamp for', 25),
(23,'You could use a light bulb for',60),
(24,'You could use a Kettle for',2200),
(25,'You could use a Electric cooker for',3000),
(26,'You could use a Hairdryer for',1750),
(27,'You could use a Microwave for',800),
(28,'You could use a Playstation for',150),
(29,'You could watch TikTok for', 30),
(30,'You could use Snapchat for', 30),
(31,'You could use WhatsApp for', 21);
/*!40000 ALTER TABLE `energydata` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `totalpower`
--

DROP TABLE IF EXISTS `totalpower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `totalpower` (
  `idtotalpower` int(11) NOT NULL AUTO_INCREMENT,
  `producedpower` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`idtotalpower`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `totalpower`
--

LOCK TABLES `totalpower` WRITE;
/*!40000 ALTER TABLE `totalpower` DISABLE KEYS */;
/*!40000 ALTER TABLE `totalpower` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-29 11:17:15
