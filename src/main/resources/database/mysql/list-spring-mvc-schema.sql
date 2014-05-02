CREATE DATABASE  IF NOT EXISTS `mylist-spring` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mylist-spring`;
-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: 192.168.100.3    Database: mylist-spring
-- ------------------------------------------------------
-- Server version	5.0.38-Ubuntu_0ubuntu1.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table Structure for table `Activities`
--

DROP TABLE IF EXISTS `Activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Activities` (
  `Id` int(11) NOT NULL auto_increment,
  `Title` varchar(45) NOT NULL,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Title` (`Title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `Id` int(11) NOT NULL auto_increment,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(60) default NULL,
  `Email` varchar(45) NOT NULL,
  `Role` int(11) NOT NULL,
  `Enabled` tinyint(4) NOT NULL default '0',
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Username` (`Username`),
  UNIQUE KEY `UNIQUE_Email` (`Email`),
  KEY `FK_User_Role` (`Role`),
  CONSTRAINT `FK_User_Role` FOREIGN KEY (`Role`) REFERENCES `Roles` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roles` (
  `Id` int(11) NOT NULL auto_increment,
  `Title` varchar(15) character set latin1 NOT NULL,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Title` (`Title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CommentsItems`
--

DROP TABLE IF EXISTS `CommentsItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CommentsItems` (
  `Id` int(11) NOT NULL auto_increment,
  `IdItem` int(11) NOT NULL,
  `IdComment` int(11) NOT NULL,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Comment_Item` (`IdItem`,`IdComment`),
  KEY `FK_CommentsItems_Comment` (`IdComment`),
  KEY `FK_CommentsItems_Item` (`IdItem`),
  CONSTRAINT `FK_CommentsItems_Comment` FOREIGN KEY (`IdComment`) REFERENCES `Comments` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_CommentsItems_Item` FOREIGN KEY (`IdItem`) REFERENCES `Items` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Items`
--

DROP TABLE IF EXISTS `Items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Items` (
  `Id` int(11) NOT NULL auto_increment,
  `TitleEng` varchar(255) NOT NULL,
  `TitleEll` varchar(255) default NULL,
  `Category` int(11) NOT NULL,
  `PhotoPath` varchar(45) default NULL,
  `Description` text,
  `Year` int(11) default NULL,
  `Rating` int(11) default NULL,
  `Subtitles` int(11) default NULL,
  `Discs` int(11) NOT NULL default '1',
  `Place` int(11) default NULL,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  KEY `FK_Item_Category` (`Category`),
  KEY `FK_Item_Subtitles` (`Subtitles`),
  CONSTRAINT `FK_Item_Category` FOREIGN KEY (`Category`) REFERENCES `Categories` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Item_Subtitles` FOREIGN KEY (`Subtitles`) REFERENCES `Subtitles` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Comments`
--

DROP TABLE IF EXISTS `Comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comments` (
  `Id` int(11) NOT NULL auto_increment,
  `Title` varchar(45) NOT NULL,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Title` (`Title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Categories`
--

DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categories` (
  `Id` int(11) NOT NULL auto_increment,
  `Title` varchar(45) NOT NULL,
  `Parent` int(11) default NULL,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Title` (`Title`),
  KEY `FK_Category_Parent` (`Parent`),
  CONSTRAINT `FK_Category_Parent` FOREIGN KEY (`Parent`) REFERENCES `Categories` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Artists`
--

DROP TABLE IF EXISTS `Artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Artists` (
  `Id` int(11) NOT NULL auto_increment,
  `Title` varchar(255) NOT NULL,
  `Description` text,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Title` (`Title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Subtitles`
--

DROP TABLE IF EXISTS `Subtitles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Subtitles` (
  `Id` int(11) NOT NULL auto_increment,
  `Title` varchar(45) NOT NULL,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Title` (`Title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ArtistsActivitiesItems`
--

DROP TABLE IF EXISTS `ArtistsActivitiesItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ArtistsActivitiesItems` (
  `Id` int(11) NOT NULL auto_increment,
  `IdArtist` int(11) NOT NULL,
  `IdItem` int(11) NOT NULL,
  `IdActivity` int(11) NOT NULL,
  `DateUpdated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Id`),
  UNIQUE KEY `UNIQUE_Artist_Activity_Item` (`IdArtist`,`IdItem`,`IdActivity`),
  KEY `FK_ArtistsActivitiesItems_Item` (`IdItem`),
  KEY `FK_ArtistsActivitiesItems_Artist` (`IdArtist`),
  KEY `FK_ArtistsActivitiesItems_Activity` (`IdActivity`),
  CONSTRAINT `FK_ArtistsActivitiesItems_Activity` FOREIGN KEY (`IdActivity`) REFERENCES `Activities` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_ArtistsActivitiesItems_Artist` FOREIGN KEY (`IdArtist`) REFERENCES `Artists` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_ArtistsActivitiesItems_Item` FOREIGN KEY (`IdItem`) REFERENCES `Items` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-02  8:17:52
