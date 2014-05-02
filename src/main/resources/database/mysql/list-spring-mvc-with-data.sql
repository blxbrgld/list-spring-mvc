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
-- Table structure for table `Activities`
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
-- Dumping data for table `Activities`
--

LOCK TABLES `Activities` WRITE;
/*!40000 ALTER TABLE `Activities` DISABLE KEYS */;
INSERT INTO `Activities` VALUES (1,'Musician','2014-02-04 11:14:48'),(2,'Conductor','2014-02-04 11:14:48'),(3,'Director','2014-02-04 11:14:48'),(4,'Actor','2014-02-04 11:14:48');
/*!40000 ALTER TABLE `Activities` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'test','$2a$10$SsSMlZ.6q90D7tk5Ol2gIOMVHXJhSwJ7yDXdqCiaz9Qd./dpyM6Ra','test@user.gr',1,1,'2014-05-02 06:18:37');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
INSERT INTO `Roles` VALUES (1,'Administrator','2014-02-19 10:22:46');
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `CommentsItems`
--

LOCK TABLES `CommentsItems` WRITE;
/*!40000 ALTER TABLE `CommentsItems` DISABLE KEYS */;
INSERT INTO `CommentsItems` VALUES (2,620,1,'2012-09-29 08:00:15'),(3,622,1,'2012-09-29 08:00:15'),(7,642,1,'2012-09-29 08:00:15'),(12,665,1,'2012-09-29 08:00:16'),(13,666,1,'2012-09-29 08:00:16'),(35,617,2,'2012-09-29 08:00:17'),(36,618,2,'2012-09-29 08:00:17'),(37,626,2,'2012-09-29 08:00:17'),(38,634,2,'2012-09-29 08:00:17'),(39,636,2,'2012-09-29 08:00:17'),(41,640,2,'2012-09-29 08:00:17'),(42,645,2,'2012-09-29 08:00:17'),(47,652,2,'2012-09-29 08:00:18'),(48,655,2,'2012-09-29 08:00:18'),(51,685,2,'2012-09-29 08:00:18'),(99,616,3,'2012-09-29 08:00:21'),(102,623,3,'2012-09-29 08:00:21'),(109,632,3,'2012-09-29 08:00:21'),(493,1550,8,'2012-09-29 19:07:41'),(797,1004,12,'2012-09-29 19:08:01'),(1307,5507,12,'2012-09-30 13:50:19'),(1311,5527,7,'2012-09-30 13:50:19'),(1326,5528,11,'2012-09-30 13:50:20'),(1327,5529,11,'2012-09-30 13:50:20'),(1364,5570,14,'2013-03-22 18:49:47'),(1421,664,1,'2013-03-23 10:24:29'),(1445,661,1,'2013-03-23 12:00:34'),(1456,648,2,'2013-03-23 13:14:26'),(1568,1031,11,'2013-03-24 11:17:55'),(1634,650,2,'2013-03-24 18:26:47'),(1646,1260,10,'2013-03-24 18:38:54'),(1647,1260,8,'2013-03-24 18:38:54'),(1710,656,2,'2013-03-30 21:22:09'),(1749,5530,11,'2013-04-13 18:32:08'),(1849,1501,12,'2013-04-29 15:47:33');
/*!40000 ALTER TABLE `CommentsItems` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Items`
--

LOCK TABLES `Items` WRITE;
/*!40000 ALTER TABLE `Items` DISABLE KEYS */;
INSERT INTO `Items` VALUES (4,'12 Monkeys','12 Πίθηκοι',6,NULL,NULL,1995,NULL,NULL,1,NULL,'2012-09-28 16:17:41'),(5,'2001: A Space Odyssey','2001: Η Οδύσσεια του Διαστήματος',6,NULL,NULL,1968,NULL,NULL,1,NULL,'2012-09-28 16:17:41'),(6,'24 Hour Party People',NULL,6,NULL,NULL,2002,5,2,1,NULL,'2014-05-02 05:46:42'),(15,'Accatone',NULL,6,NULL,NULL,1961,5,2,1,NULL,'2014-05-02 05:46:42'),(19,'Aguirre: The Wrath Of God','Αγγίρε, Η Μάστιγα του Θεού',6,NULL,NULL,1972,5,2,1,NULL,'2014-05-02 05:46:42'),(20,'Ali',NULL,6,NULL,NULL,2001,NULL,NULL,1,NULL,'2012-09-28 16:17:41'),(28,'Amarcord',NULL,6,NULL,NULL,1973,5,2,1,NULL,'2014-05-02 05:46:42'),(41,'Assassination Of Jesse James By The Coward Robert Ford','Η Δολοφονία του Τζέσε Τζεϊμς από τον Δειλό Ρόμπερτ Φορντ',6,NULL,NULL,2007,5,2,1,NULL,'2014-05-02 05:46:42'),(56,'Before Night Falls','Πριν Πέσει η Νύχτα',6,NULL,NULL,2000,NULL,NULL,1,NULL,'2012-09-28 16:17:42'),(68,'Birthday Party: Pleasure Heads Must Burn',NULL,6,NULL,NULL,2003,NULL,1,1,NULL,'2014-05-02 05:46:42'),(78,'Bob Dylan: The Other Side Of The Mirror / Bob Dylan Live At The Newport Folk Festival 1963-1965',NULL,6,NULL,NULL,2007,NULL,1,1,NULL,'2014-05-02 05:46:42'),(84,'Breaking The Waves','Δαμάζοντας τα Κύματα',6,NULL,NULL,1996,NULL,NULL,1,NULL,'2012-09-28 16:17:43'),(88,'Bring Me The Head Of Alfredo Garcia','Φέρτε Μου Το Κεφάλι Του Αλφρέντο Γκαρσία',6,NULL,NULL,1974,NULL,NULL,1,NULL,'2012-09-28 16:17:43'),(89,'Broken Flowers','Τσακισμένα Λουλούδια',6,NULL,NULL,2005,NULL,2,1,NULL,'2014-05-02 05:46:42'),(106,'Chaplin',NULL,6,NULL,NULL,1992,NULL,NULL,1,NULL,'2012-09-28 16:17:44'),(110,'Cidade De Deus','Η Πόλη του Θεού',6,NULL,NULL,2002,NULL,NULL,1,NULL,'2012-09-28 16:17:44'),(111,'Citizen Kane','Πολίτης Κέην',6,NULL,NULL,1941,5,2,2,NULL,'2014-05-02 05:46:42'),(119,'Cramps, The: Live At Napa State Mental Hospital',NULL,6,NULL,NULL,2003,NULL,NULL,1,NULL,'2012-09-28 16:17:44'),(128,'Dancer In The Dark','Χορεύοντας στο Σκοτάδι',6,NULL,NULL,2000,5,2,1,NULL,'2014-05-02 05:46:42'),(132,'Dead Man','Ο Νεκρός',6,NULL,NULL,1995,5,2,1,NULL,'2014-05-02 05:46:42'),(146,'Devil And Daniel Johnston',NULL,6,NULL,NULL,2005,NULL,NULL,1,NULL,'2012-09-28 16:17:45'),(158,'Don Quijote De Orson Welles','Δον Κιχώτης του Orson Welles',6,NULL,NULL,1992,NULL,2,1,NULL,'2014-05-02 05:46:42'),(163,'Double Vie De Veronique','Η Διπλή Ζωή της Βερόνικα',6,NULL,NULL,1991,NULL,NULL,1,NULL,'2012-09-28 16:17:45'),(164,'Down By Law','Στην Παγίδα του Νόμου',6,NULL,NULL,1986,5,2,1,NULL,'2014-05-02 05:46:42'),(173,'Einsturzende Neubauten: 1/2 Mensch',NULL,6,NULL,NULL,2005,NULL,NULL,1,NULL,'2012-09-28 16:17:46'),(175,'Element Of Crime','Το Στοιχείο του Εγκλήματος',6,NULL,NULL,1984,5,2,1,NULL,'2014-05-02 05:46:42'),(178,'Enigma Of Kaspar Hauser','Το Αίνιγμα του Κάσπαρ Χάουζερ',6,NULL,NULL,1974,NULL,2,1,NULL,'2014-05-02 05:46:42'),(181,'Eternal Sunshine Of The Spotless Mind','Η Αιώνια Λιακάδα Ενός Καθαρού Μυαλού',6,NULL,NULL,2004,NULL,NULL,1,NULL,'2012-09-28 16:17:46'),(183,'Eyes Wide Shut','Μάτια Ερμητικά Κλειστά',6,NULL,NULL,1999,NULL,NULL,1,NULL,'2012-09-28 16:17:46'),(230,'Great Dictator','Ο Μεγάλος Δικτάτορας',6,NULL,NULL,1940,NULL,NULL,2,NULL,'2012-09-28 16:17:48'),(616,'Vive Le Tour',NULL,7,NULL,NULL,1962,NULL,3,1,45,'2012-09-29 07:31:40'),(617,'Amazing Grace: Jeff Buckley',NULL,7,NULL,NULL,2004,NULL,1,1,52,'2012-09-29 07:31:40'),(618,'Johnny Cash - Riding The Rails: The Great American Train Story',NULL,7,NULL,NULL,2005,NULL,1,1,33,'2012-09-29 07:31:40'),(620,'Mystery Of Picasso',NULL,7,NULL,NULL,1956,NULL,1,1,23,'2012-09-29 07:31:41'),(622,'Pervert\'s Guide To Cinema',NULL,7,NULL,NULL,2006,NULL,3,1,27,'2012-09-29 07:31:41'),(623,'Great Ecstasy Of Woodcarver Steiner',NULL,7,NULL,NULL,1974,NULL,3,1,31,'2012-09-29 07:31:41'),(626,'Cheap Magic Inside: The Flying Club Cup',NULL,7,NULL,NULL,2007,NULL,1,1,52,'2012-09-29 07:31:41'),(632,'Inside Rooms: 26 Bathrooms, London & Oxfordshire, 1985',NULL,7,NULL,NULL,1985,NULL,1,1,30,'2012-09-29 07:31:41'),(633,'Gerry',NULL,7,NULL,NULL,2002,NULL,2,1,30,'2014-05-02 05:46:42'),(634,'White Stripes: Under Great White Northern Lights',NULL,7,NULL,NULL,2010,NULL,1,1,33,'2012-09-29 07:31:41'),(636,'Eels With String: Live At Town Hall',NULL,7,NULL,NULL,2006,NULL,1,1,52,'2012-09-29 07:31:41'),(640,'Einstein On The Beach: The Changing Image Of Opera',NULL,7,NULL,NULL,1985,NULL,1,1,56,'2012-09-29 07:31:41'),(642,'Kurt Cobain: About A Son',NULL,7,NULL,NULL,2006,NULL,1,1,18,'2012-09-29 07:31:41'),(645,'Nirvana: Live! Tonight! Sold Out!!',NULL,7,NULL,NULL,1994,NULL,1,1,24,'2012-09-29 07:31:41'),(648,'Jeff Buckley: Live In Chicago',NULL,7,NULL,NULL,2000,NULL,1,1,25,'2014-05-02 05:46:42'),(650,'Leonard Cohen: Bird On A Wire',NULL,7,NULL,NULL,1974,NULL,1,1,56,'2014-05-02 05:46:42'),(652,'Siouxsie & The Banshees: Nocturne',NULL,7,NULL,NULL,1983,NULL,1,1,33,'2012-09-29 07:31:42'),(654,'Attenberg',NULL,7,NULL,NULL,2010,5,1,1,59,'2014-05-02 05:46:42'),(655,'Tiger Lillies & Alexander Hacke: Mountains Of Madness',NULL,7,NULL,NULL,2006,NULL,1,1,52,'2012-09-29 07:31:42'),(656,'Rust Never Sleeps',NULL,7,NULL,NULL,1979,NULL,1,1,56,'2014-05-02 05:46:42'),(661,'Chambre 666',NULL,7,NULL,NULL,1982,NULL,3,1,22,'2014-05-02 05:46:42'),(664,'Easy Riders, Raging Bulls',NULL,7,NULL,NULL,2003,NULL,1,1,41,'2014-05-02 05:46:42'),(665,'A Decade Under The Influence',NULL,7,NULL,NULL,2003,NULL,1,1,41,'2012-09-29 07:31:42'),(666,'When We Were Kings',NULL,7,NULL,NULL,1996,NULL,2,1,27,'2012-09-29 07:31:42'),(667,'In Cold Blood',NULL,7,NULL,NULL,1967,NULL,2,1,62,'2012-09-29 07:31:42'),(668,'My Left Foot: The Story Of Christy Brown',NULL,7,NULL,NULL,1989,NULL,2,1,7,'2012-09-29 07:31:42'),(672,'Young Mr. Lincoln',NULL,7,NULL,NULL,1939,NULL,3,1,46,'2014-05-02 05:46:42'),(683,'Hamlet Goes Business',NULL,7,NULL,NULL,1987,NULL,3,1,4,'2014-05-02 05:46:42'),(684,'Killer Of Sheep',NULL,7,NULL,NULL,1977,NULL,1,1,3,'2012-09-29 07:31:43'),(685,'Kill Your Idols',NULL,7,NULL,NULL,2004,NULL,1,1,49,'2012-09-29 07:31:43'),(987,'What A Long, Strange Journey This Has Been',NULL,3,NULL,NULL,NULL,NULL,NULL,1,45,'2014-05-02 05:46:42'),(988,'Big Lupu',NULL,3,NULL,NULL,NULL,NULL,NULL,1,22,'2012-09-29 18:23:08'),(994,'13 Blues For Thirteen Moons',NULL,3,NULL,NULL,NULL,5,NULL,1,58,'2014-05-02 05:46:42'),(1004,'M Is…',NULL,3,NULL,NULL,NULL,NULL,NULL,1,18,'2012-09-29 18:23:08'),(1011,'Gentlemen',NULL,3,NULL,NULL,NULL,NULL,NULL,1,7,'2014-05-02 05:46:42'),(1031,'The Virgin Suicides',NULL,3,NULL,NULL,NULL,NULL,NULL,1,24,'2014-05-02 05:46:42'),(1051,'Jar Of Flies',NULL,3,NULL,NULL,NULL,NULL,NULL,1,31,'2014-05-02 05:46:42'),(1065,'The Fun Of Watching Fireworks',NULL,3,NULL,NULL,NULL,NULL,NULL,1,22,'2012-09-29 18:23:10'),(1076,'Armchair Apocrypha',NULL,3,NULL,NULL,NULL,NULL,NULL,1,53,'2012-09-29 18:23:10'),(1082,'Angels Of Light & Akron/Family',NULL,3,NULL,NULL,NULL,NULL,NULL,1,77,'2012-09-29 18:23:10'),(1095,'Strawberry Jam',NULL,3,NULL,NULL,NULL,NULL,NULL,1,52,'2012-09-29 18:23:11'),(1113,'I Am A Bird Now',NULL,3,NULL,NULL,NULL,NULL,NULL,1,22,'2012-09-29 18:23:11'),(1131,'Philophobia',NULL,3,NULL,NULL,NULL,NULL,NULL,1,5,'2012-09-29 18:23:12'),(1141,'Funeral',NULL,3,NULL,NULL,NULL,5,NULL,1,20,'2014-05-02 05:46:42'),(1197,'Amanita',NULL,3,NULL,NULL,NULL,NULL,NULL,1,82,'2012-09-29 18:23:14'),(1230,'Abbey Road',NULL,3,NULL,NULL,NULL,NULL,NULL,1,2,'2012-09-29 18:23:15'),(1260,'Record Club: Songs Of Leonard Cohen',NULL,3,NULL,NULL,NULL,NULL,NULL,1,75,'2014-05-02 05:46:42'),(1283,'If You\'re Feeling Sinister',NULL,3,NULL,NULL,NULL,NULL,NULL,1,26,'2014-05-02 05:46:42'),(1310,'Songs About Fucking',NULL,3,NULL,NULL,NULL,NULL,NULL,1,31,'2014-05-02 05:46:42'),(1335,'Drunk On The Pope\'s Blood',NULL,3,NULL,NULL,NULL,NULL,NULL,1,21,'2014-05-02 05:46:42'),(1381,'Croatian Variations',NULL,3,NULL,NULL,NULL,NULL,NULL,1,81,'2012-09-29 18:23:21'),(1501,'Bonnie & Mariee',NULL,3,NULL,NULL,NULL,NULL,NULL,1,88,'2014-05-02 05:46:42'),(1512,'The Devil And God Are Raging Inside Me',NULL,3,NULL,NULL,NULL,NULL,NULL,1,50,'2014-05-02 05:46:42'),(1550,'Dylanesque',NULL,3,NULL,NULL,NULL,NULL,NULL,1,49,'2012-09-29 18:24:27'),(1613,'Trout Mask Replica',NULL,3,NULL,NULL,NULL,5,NULL,1,1,'2014-05-02 05:46:42'),(1649,'5:55',NULL,3,NULL,NULL,NULL,NULL,NULL,1,42,'2014-05-02 05:46:42'),(1772,'Bloodflowers',NULL,3,NULL,NULL,NULL,NULL,NULL,1,4,'2012-09-29 18:24:40'),(1776,'Japanese Whispers',NULL,3,NULL,NULL,NULL,NULL,NULL,1,4,'2014-05-02 05:46:42'),(1782,'Pornography',NULL,3,NULL,NULL,NULL,NULL,NULL,1,4,'2014-05-02 05:46:42'),(1805,'Christ And The Pale Queens Mighty In Sorrow',NULL,3,NULL,NULL,NULL,NULL,NULL,1,81,'2014-05-02 05:46:42'),(5448,'6 Cello Sonatas',NULL,4,NULL,NULL,NULL,NULL,NULL,1,48,'2012-09-30 10:57:40'),(5449,'The Four Seasons Concertos',NULL,4,NULL,NULL,NULL,NULL,NULL,2,48,'2012-09-30 10:57:40'),(5450,'The Four Seasons, Op. 8 Nos. 1-4',NULL,4,NULL,NULL,NULL,NULL,NULL,1,24,'2012-09-30 10:57:40'),(5451,'Ifigenia In Tauride',NULL,4,NULL,NULL,NULL,NULL,NULL,2,6,'2012-09-30 10:57:40'),(5452,'Claude Debussy: The Complete Works For Piano',NULL,4,NULL,NULL,NULL,NULL,NULL,4,85,'2012-09-30 10:57:40'),(5453,'Complete Symphonies',NULL,4,NULL,NULL,NULL,NULL,NULL,12,57,'2012-09-30 10:57:40'),(5454,'Fryderyk Chopin: Complete Piano Music',NULL,4,NULL,NULL,NULL,NULL,NULL,15,85,'2012-09-30 10:57:40'),(5455,'The Chopin Collection',NULL,4,NULL,NULL,NULL,NULL,NULL,11,27,'2012-09-30 10:57:40'),(5456,'Poliuto',NULL,4,NULL,NULL,NULL,NULL,NULL,2,6,'2012-09-30 10:57:40'),(5457,'La Boheme',NULL,4,NULL,NULL,NULL,NULL,NULL,2,6,'2012-09-30 10:57:40'),(5458,'Tosca',NULL,4,NULL,NULL,NULL,NULL,NULL,2,6,'2012-09-30 10:57:40'),(5459,'Il Barbiere Di Siviglia',NULL,4,NULL,NULL,NULL,NULL,NULL,2,6,'2012-09-30 10:57:40'),(5460,'Il Turco In Italia',NULL,4,NULL,NULL,NULL,NULL,NULL,2,6,'2012-09-30 10:57:40'),(5461,'Il Turco In Italia',NULL,4,NULL,NULL,NULL,NULL,NULL,2,6,'2012-09-30 10:57:40'),(5462,'La Forza Del Destino',NULL,4,NULL,NULL,NULL,NULL,NULL,3,6,'2012-09-30 10:57:40'),(5463,'Rigoletto',NULL,4,NULL,NULL,NULL,NULL,NULL,2,6,'2012-09-30 10:57:40'),(5464,'Symphony No. 1',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:40'),(5465,'Symphony No. 10',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:40'),(5466,'Symphony No. 5',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5467,'Bach Arrangements',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5468,'Bach Unaccompanied Cello Suites-Performed On Double Bass',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5469,'Fantasia And Fugue In A Minor / Aria Variata / Sonata In D Major / Suite In F Minor',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5470,'Fantasia In C Minor / Two-Part Inventions / Three-Part Inventions / Chromatic Fugue',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5471,'Goldberg Variations',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5472,'Great Organ Works',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5473,'Italian Concerto / French Overture / Four Duets / Two Capriccios',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5474,'The English Suites',NULL,4,NULL,NULL,NULL,NULL,NULL,2,27,'2012-09-30 10:57:41'),(5475,'The French Suites',NULL,4,NULL,NULL,NULL,NULL,NULL,2,27,'2012-09-30 10:57:41'),(5476,'The Organ Works',NULL,4,NULL,NULL,NULL,NULL,NULL,12,27,'2012-09-30 10:57:41'),(5477,'The Toccatas',NULL,4,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 10:57:41'),(5507,'Χωρίς Σύνορα',NULL,5,NULL,NULL,NULL,NULL,NULL,1,16,'2012-09-30 13:41:25'),(5508,'Εξ Αδοκήτω',NULL,5,NULL,NULL,NULL,NULL,NULL,1,27,'2012-09-30 13:41:25'),(5511,'Ανθρώπων Έργα',NULL,5,NULL,NULL,NULL,NULL,NULL,1,3,'2012-09-30 13:41:26'),(5512,'Δικαίωμα',NULL,5,NULL,NULL,NULL,NULL,NULL,1,57,'2012-09-30 13:41:26'),(5513,'Ζωντανή Ηχογράφηση - Zoom 91-92',NULL,5,NULL,NULL,NULL,NULL,NULL,2,16,'2012-09-30 13:41:26'),(5514,'Κυκλοφορώ κι Οπλοφορώ',NULL,5,NULL,NULL,NULL,NULL,NULL,1,57,'2012-09-30 13:41:26'),(5516,'Εκτός Τόπου και Χρόνου',NULL,5,NULL,NULL,NULL,NULL,NULL,2,16,'2014-05-02 05:46:42'),(5517,'Νεροποντή',NULL,5,NULL,NULL,NULL,NULL,NULL,1,65,'2014-05-02 05:46:42'),(5519,'Οι Περιπέτειες Ενός Προσκυνητή',NULL,5,NULL,NULL,NULL,NULL,NULL,1,48,'2012-09-30 13:41:26'),(5520,'Όπως Μυστικά και Ήσυχα',NULL,5,NULL,NULL,NULL,NULL,NULL,1,48,'2014-05-02 05:46:42'),(5521,'Στην Αγορά του Κόσμου',NULL,5,NULL,NULL,NULL,NULL,NULL,1,3,'2012-09-30 13:41:26'),(5522,'Που Δύσην Ως Ανατολήν',NULL,5,NULL,NULL,NULL,NULL,NULL,1,37,'2012-09-30 13:41:26'),(5523,'Μια Φορά Θυμάμαι',NULL,5,NULL,NULL,NULL,NULL,NULL,1,3,'2012-09-30 13:41:26'),(5525,'Σπινθήρας',NULL,5,NULL,NULL,NULL,NULL,NULL,1,65,'2012-09-30 13:41:26'),(5527,'Δεν Ξέρω Πόσο Σ\' Αγαπώ (Ζωντανή Ηχογράφηση στο Θέατρο Βράχων Μελίνα Μερκούρη)',NULL,5,NULL,NULL,NULL,NULL,NULL,2,37,'2012-09-30 13:41:26'),(5528,'Ο Χαμένος τα Παίρνει Όλα',NULL,5,NULL,NULL,NULL,NULL,NULL,1,3,'2012-09-30 13:41:26'),(5529,'Χώμα και Νερό',NULL,5,NULL,NULL,NULL,NULL,NULL,1,16,'2012-09-30 13:41:26'),(5530,'Ψυχή Βαθιά',NULL,5,NULL,NULL,NULL,NULL,NULL,1,89,'2014-05-02 05:46:42'),(5531,'Υπέροχο Τίποτα',NULL,5,NULL,NULL,NULL,NULL,NULL,1,16,'2012-09-30 13:41:26'),(5532,'Οι Ανάσες των Λύκων',NULL,5,NULL,NULL,NULL,NULL,NULL,1,55,'2014-05-02 05:46:42'),(5533,'Πότε Θα Φτάσουμε Εδώ',NULL,5,NULL,NULL,NULL,NULL,NULL,1,55,'2012-09-30 13:41:26'),(5534,'Από \'δω και Πάνω',NULL,5,NULL,NULL,NULL,NULL,NULL,2,55,'2014-05-02 05:46:42'),(5535,'\"Ο Στρατής Θαλασσινός Ανάμεσα Στους Αγάπανθους\" και άλλα Τραγούδια',NULL,5,NULL,NULL,NULL,NULL,NULL,1,85,'2012-09-30 13:41:26'),(5542,'Οι Ελεύθεροι Πολιορκημένοι',NULL,5,NULL,NULL,NULL,NULL,NULL,2,85,'2012-09-30 13:41:26'),(5543,'Τα Τραγούδια του Νέου Πατέρα',NULL,5,NULL,NULL,NULL,NULL,NULL,1,85,'2012-09-30 13:41:27'),(5568,'Κ.Π. Καβάφης \"Που γι\' Αλεξανδρινό Γράφει Αλεξανδρινός\"',NULL,5,NULL,NULL,NULL,NULL,NULL,3,65,'2012-09-30 13:41:27'),(5569,'Έγινε η Απώλεια Συνηθειά μας',NULL,5,NULL,NULL,NULL,NULL,NULL,1,3,'2012-09-30 13:41:27'),(5570,'Είναι Που Όλα Ήρθαν Αργά',NULL,5,NULL,NULL,NULL,NULL,NULL,1,37,'2014-05-02 05:46:42'),(5571,'Ευωδιάζουν Αγριοκέρασα οι Σιωπές',NULL,5,NULL,NULL,NULL,NULL,NULL,1,3,'2014-05-02 05:46:42'),(5572,'Κάτι Σαράβαλες Καρδιές',NULL,5,NULL,NULL,NULL,NULL,NULL,1,3,'2014-05-02 05:46:42');
/*!40000 ALTER TABLE `Items` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Comments`
--

LOCK TABLES `Comments` WRITE;
/*!40000 ALTER TABLE `Comments` DISABLE KEYS */;
INSERT INTO `Comments` VALUES (1,'Documentary','2012-09-23 12:38:35'),(2,'Music / Documentary','2012-09-23 12:38:43'),(3,'Short Film','2012-09-23 12:38:51'),(4,'Collection of Short Films','2012-09-23 12:39:00'),(5,'TV Series','2012-09-23 12:39:12'),(6,'Compilation','2012-09-23 14:39:37'),(7,'Live','2012-09-23 14:39:37'),(8,'Tribute Album','2012-09-23 14:39:37'),(9,'Covers Album','2012-09-23 14:39:37'),(10,'Bootleg','2012-09-23 14:39:37'),(11,'OST','2012-09-23 14:39:37'),(12,'EP','2012-09-23 14:39:37'),(13,'Split EP','2012-09-23 14:39:37'),(14,'Single','2012-09-23 14:39:37'),(15,'Split Single','2012-09-23 14:39:37'),(16,'7\'\'','2012-09-23 14:39:37'),(17,'Split 7\'\'','2012-09-23 14:39:37'),(18,'12\'\'','2012-09-23 14:39:37'),(20,'Promo CD','2012-09-23 14:39:38');
/*!40000 ALTER TABLE `Comments` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Categories`
--

LOCK TABLES `Categories` WRITE;
/*!40000 ALTER TABLE `Categories` DISABLE KEYS */;
INSERT INTO `Categories` VALUES (1,'Music',NULL,'2014-03-13 07:26:35'),(2,'Films',NULL,'2014-03-13 07:26:38'),(3,'Popular Music',1,'2014-03-13 09:46:44'),(4,'Classical Music',1,'2014-03-13 09:13:37'),(5,'Greek Music',1,'2012-09-23 08:15:52'),(6,'DVD Films',2,'2012-09-23 08:16:16'),(7,'DivX Films',2,'2012-09-23 08:16:23');
/*!40000 ALTER TABLE `Categories` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Artists`
--

LOCK TABLES `Artists` WRITE;
/*!40000 ALTER TABLE `Artists` DISABLE KEYS */;
INSERT INTO `Artists` VALUES (5,'Aj Schnack',NULL,'2012-09-23 09:59:45'),(6,'Aki Kaurismaki',NULL,'2012-09-23 09:59:45'),(15,'Alexander Hacke',NULL,'2012-09-23 09:59:45'),(40,'Charles Burnett',NULL,'2012-09-23 09:59:46'),(49,'Danielle De Picciotto',NULL,'2012-09-23 09:59:47'),(58,'Dennis Hopper',NULL,'2012-09-23 09:59:47'),(68,'Emmett Malloy',NULL,'2012-09-23 09:59:47'),(93,'Gus Van Sant',NULL,'2012-09-23 10:00:15'),(96,'Henri-Georges Clouzot',NULL,'2012-09-23 10:00:15'),(112,'Jean-Luc Godard',NULL,'2012-09-23 10:00:15'),(114,'Jerry Bryant',NULL,'2012-09-23 10:00:16'),(115,'Jesus Franco',NULL,'2012-09-23 10:00:16'),(116,'Jim Sheridan',NULL,'2012-09-23 10:00:16'),(121,'John Ford',NULL,'2012-09-23 10:00:16'),(124,'John Lurie',NULL,'2012-09-23 10:00:16'),(134,'Julian Schnabel',NULL,'2012-09-23 10:00:16'),(138,'Kenneth Bowser',NULL,'2012-09-23 10:00:16'),(141,'Kevin Kerslake',NULL,'2012-09-23 10:00:16'),(142,'Klaus Kinski',NULL,'2012-09-23 10:00:16'),(143,'Krzysztof Kieslowski',NULL,'2012-09-23 10:00:16'),(148,'Lars Von Trier',NULL,'2012-09-23 10:00:17'),(150,'Laurie Trombley',NULL,'2012-09-23 10:00:17'),(152,'Leon Gast',NULL,'2012-09-23 10:00:17'),(158,'Louis Malle',NULL,'2012-09-23 10:00:17'),(168,'Mark Obenhaus',NULL,'2012-09-23 10:00:17'),(170,'Martin Scorsese',NULL,'2012-09-23 10:00:17'),(178,'Michael Winterbottom',NULL,'2012-09-23 10:00:17'),(179,'Michel Gondry',NULL,'2012-09-23 10:00:17'),(184,'Murray Lerner',NULL,'2012-09-23 10:00:18'),(186,'Neil Young',NULL,'2012-09-23 10:00:18'),(188,'Nicholas Webster',NULL,'2012-09-23 10:00:18'),(190,'Niels Alpert',NULL,'2012-09-23 10:00:18'),(191,'Nyla Bialek Adams',NULL,'2012-09-23 10:00:18'),(192,'Orson Welles',NULL,'2012-09-23 10:00:18'),(195,'Paul Morrissey',NULL,'2012-09-23 10:00:18'),(198,'Peter Bogdanovich',NULL,'2012-09-23 10:00:18'),(202,'Peter Greenaway',NULL,'2012-09-23 10:00:18'),(207,'Pier Paolo Pasolini',NULL,'2012-09-23 10:00:19'),(211,'Richard Brooks',NULL,'2012-09-23 10:00:19'),(212,'Richard LaGravenese',NULL,'2012-09-23 10:00:19'),(219,'Robert Altman',NULL,'2012-09-23 10:00:19'),(223,'Robin Parsons',NULL,'2012-09-23 10:00:19'),(224,'Roger Bunn',NULL,'2012-09-23 10:00:19'),(225,'Roger Corman',NULL,'2012-09-23 10:00:19'),(230,'Sam Peckinpah',NULL,'2012-09-23 10:00:19'),(234,'Scott Crary',NULL,'2012-09-23 10:00:20'),(240,'Sophie Fiennes',NULL,'2012-09-23 10:00:20'),(242,'Spike Lee',NULL,'2012-09-23 10:00:20'),(243,'Stanley Kubrick',NULL,'2012-09-23 10:00:20'),(248,'Ted Demme',NULL,'2012-09-23 10:00:20'),(250,'Terry Gilliam',NULL,'2012-09-23 10:00:20'),(262,'Tony Palmer',NULL,'2012-09-23 10:00:21'),(267,'Vincent Moon',NULL,'2012-09-23 10:00:21'),(276,'Wim Wenders',NULL,'2012-09-23 10:00:21'),(279,'Αθηνά Ραχήλ Τσάγγαρη',NULL,'2012-09-23 10:00:21'),(280,'Γιώργος Λάνθιμος',NULL,'2012-09-23 10:00:21'),(287,'David O\'Connor',NULL,'2012-09-23 11:42:44'),(289,'Frank \'Pancho\' Sampedro',NULL,'2012-09-23 11:42:44'),(291,'Gerald S. O\'Loughlin',NULL,'2012-09-23 11:42:44'),(292,'Hugh O\'Conor',NULL,'2012-09-23 11:42:44'),(305,'Eddie Collins',NULL,'2012-09-23 11:44:45'),(319,'Elina Salo',NULL,'2012-09-23 11:44:46'),(325,'Ellen Burstyn',NULL,'2012-09-23 11:44:46'),(349,'Esko Nikkari',NULL,'2012-09-23 11:44:47'),(350,'Esko Salminen',NULL,'2012-09-23 11:44:47'),(351,'Eugene Cherry',NULL,'2012-09-23 11:44:47'),(353,'Eugene Hutz',NULL,'2012-09-23 11:44:47'),(372,'Fernando Rey',NULL,'2012-09-23 11:44:48'),(418,'Geoffrey Palmer',NULL,'2012-09-23 11:44:50'),(422,'George Foreman',NULL,'2012-09-23 11:44:51'),(444,'Glenn Branca',NULL,'2012-09-23 11:44:52'),(477,'Henry Fonda',NULL,'2012-09-23 11:44:54'),(478,'Henry G. Sanders',NULL,'2012-09-23 11:44:54'),(533,'Jack White',NULL,'2012-09-23 11:44:57'),(547,'James Brown',NULL,'2012-09-23 11:44:58'),(576,'Javier Bardem',NULL,'2012-09-23 11:45:00'),(579,'Jean Bobet',NULL,'2012-09-23 11:45:00'),(599,'Jeff Buckley',NULL,'2012-09-23 11:45:01'),(619,'Jim Jarmusch',NULL,'2012-09-23 11:45:02'),(625,'Joan Baez',NULL,'2012-09-23 11:45:02'),(642,'John Forsythe',NULL,'2012-09-23 11:45:03'),(660,'Johnny Cash',NULL,'2012-09-23 11:45:04'),(661,'Johnny Depp',NULL,'2012-09-23 11:45:04'),(667,'Jon Voight',NULL,'2012-09-23 11:45:05'),(669,'Jonathan Ross',NULL,'2012-09-23 11:45:05'),(709,'Kati Outinen',NULL,'2012-09-23 11:45:07'),(710,'Katrin Cartlidge',NULL,'2012-09-23 11:45:08'),(711,'Kaycee Moore',NULL,'2012-09-23 11:45:08'),(723,'Kid Congo Powers',NULL,'2012-09-23 11:45:08'),(728,'Kirsten Sheridan',NULL,'2012-09-23 11:45:09'),(733,'Kris Kristofferson',NULL,'2012-09-23 11:45:09'),(734,'Krist Novoselic',NULL,'2012-09-23 11:45:09'),(737,'Kurt Cobain',NULL,'2012-09-23 11:45:09'),(760,'Leonard Cohen',NULL,'2012-09-23 11:45:10'),(826,'Marisa Tomei',NULL,'2012-09-23 11:45:14'),(827,'Marjorie Weaver',NULL,'2012-09-23 11:45:14'),(828,'Mark Everett',NULL,'2012-09-23 11:45:14'),(843,'Martyn Jacques',NULL,'2012-09-23 11:45:15'),(850,'Matt Damon',NULL,'2012-09-23 11:45:15'),(852,'Matt Johnson',NULL,'2012-09-23 11:45:16'),(863,'Meg White',NULL,'2012-09-23 11:45:16'),(875,'Michael Azerrad',NULL,'2012-09-23 11:45:17'),(879,'Michael Elphick',NULL,'2012-09-23 11:45:18'),(888,'Michael Tighe',NULL,'2012-09-23 11:45:18'),(897,'Michelangelo Antonioni',NULL,'2012-09-23 11:45:19'),(903,'Mick Grondahl',NULL,'2012-09-23 11:45:19'),(929,'Muhammad Ali',NULL,'2012-09-23 11:45:20'),(952,'Nick Cave',NULL,'2012-09-23 11:45:22'),(962,'Nicole Kidman',NULL,'2012-09-23 11:45:22'),(986,'Pablo Picasso',NULL,'2012-09-23 11:45:24'),(1004,'Paul Stewart',NULL,'2012-09-23 11:45:25'),(1015,'Peter Marshal',NULL,'2012-09-23 11:45:25'),(1019,'Philip Glass',NULL,'2012-09-23 11:45:25'),(1030,'Pirkka-Pekka Petelius',NULL,'2012-09-23 11:45:26'),(1044,'Rainer Werner Fassbinder',NULL,'2012-09-23 11:45:27'),(1047,'Ralph Molina',NULL,'2012-09-23 11:45:27'),(1072,'Richard Attenborough',NULL,'2012-09-23 11:45:29'),(1094,'Robert Blake',NULL,'2012-09-23 11:45:30'),(1106,'Robert Smith',NULL,'2012-09-23 11:45:31'),(1111,'Robert Wilson',NULL,'2012-09-23 11:45:31'),(1125,'Ron Albertson',NULL,'2012-09-23 11:45:32'),(1126,'Ron Cornelius',NULL,'2012-09-23 11:45:32'),(1161,'Sam Rockwell',NULL,'2012-09-23 11:45:34'),(1162,'Sam Shepard',NULL,'2012-09-23 11:45:34'),(1176,'Scott Wilson',NULL,'2012-09-23 11:45:35'),(1179,'Sean Penn',NULL,'2012-09-23 11:45:35'),(1181,'Sebastien Brault',NULL,'2012-09-23 11:45:35'),(1202,'Siouxsie Sioux',NULL,'2012-09-23 11:45:36'),(1204,'Slavoj Zizek',NULL,'2012-09-23 11:45:36'),(1225,'Steven Severin',NULL,'2012-09-23 11:45:37'),(1275,'Tom Cruise',NULL,'2012-09-23 11:45:41'),(1283,'Tom Waits',NULL,'2012-09-23 11:45:41'),(1299,'Udo Kier',NULL,'2012-09-23 11:45:42'),(1335,'Walter Steiner',NULL,'2012-09-23 11:45:44'),(1337,'Warren Beatty',NULL,'2012-09-23 11:45:44'),(1338,'Warren Oates',NULL,'2012-09-23 11:45:45'),(1340,'Werner Herzog',NULL,'2012-09-23 11:45:45'),(1341,'Will Lyman',NULL,'2012-09-23 11:45:45'),(1370,'Zach Condon',NULL,'2012-09-23 11:45:46'),(1379,'Βαγγέλης Μουρίκης',NULL,'2012-09-23 11:45:47'),(1383,'Ευαγγελία Ράντου',NULL,'2012-09-23 11:45:47'),(1398,'Adrian Huge',NULL,'2012-09-23 11:48:20'),(1399,'Adrian Stout',NULL,'2012-09-23 11:48:20'),(1410,'Akim Tamiroff',NULL,'2012-09-23 11:48:21'),(1436,'Alice Brady',NULL,'2012-09-23 11:48:22'),(1441,'Alison Whelan',NULL,'2012-09-23 11:48:23'),(1449,'Ana Lenchantin',NULL,'2012-09-23 11:48:23'),(1459,'Angela Burnett',NULL,'2012-09-23 11:48:24'),(1463,'Angus Andrew',NULL,'2012-09-23 11:48:24'),(1464,'Animal Collective',NULL,'2012-09-23 11:48:24'),(1480,'Anthony Hopkins',NULL,'2012-09-23 11:48:25'),(1487,'Ariane Labed',NULL,'2012-09-23 11:48:25'),(1488,'Arleen Whelan',NULL,'2012-09-23 11:48:25'),(1508,'Beck',NULL,'2012-09-23 11:48:27'),(1531,'Bill Murray',NULL,'2012-09-23 11:48:28'),(1536,'Billy Talbot',NULL,'2012-09-23 11:48:28'),(1538,'Bjork',NULL,'2012-09-23 11:48:28'),(1543,'Bob Johnston',NULL,'2012-09-23 11:48:29'),(1550,'Brad Pitt',NULL,'2012-09-23 11:48:29'),(1552,'Brenda Fricker',NULL,'2012-09-23 11:48:29'),(1565,'Budgie',NULL,'2012-09-23 11:48:30'),(1572,'Casey Affleck',NULL,'2012-09-23 11:48:31'),(1574,'Catherine Deneuve',NULL,'2012-09-23 11:48:31'),(1578,'Charles Bracy',NULL,'2012-09-23 11:48:31'),(1586,'Charlotte Gainsbourg',NULL,'2012-09-23 11:48:32'),(1594,'Chloe Sevigny',NULL,'2012-09-23 11:48:32'),(1635,'Cosey Fanni Tutti',NULL,'2012-09-23 11:48:35'),(1636,'Courtney Love',NULL,'2012-09-23 11:48:35'),(1649,'Daniel Day-Lewis',NULL,'2012-09-23 11:48:36'),(1658,'Dave Grohl',NULL,'2012-09-23 11:48:36'),(1705,'Don King',NULL,'2012-09-23 11:48:39'),(1722,'Andrew Dominik',NULL,'2012-09-23 14:06:44'),(1733,'Charles Chaplin',NULL,'2012-09-23 14:06:44'),(1755,'Federico Fellini',NULL,'2012-09-23 14:06:46'),(1757,'Fernando Meirelles',NULL,'2012-09-23 14:06:46'),(1828,'Michael Mann',NULL,'2012-09-23 14:06:50'),(1850,'Roberto Benigni',NULL,'2012-09-23 14:06:51'),(1862,'Sydney Pollack',NULL,'2012-09-23 14:06:52'),(1902,'Adriana Asti',NULL,'2012-09-23 14:19:52'),(1906,'Agnes Moorehead',NULL,'2012-09-23 14:19:53'),(1907,'Ahmed El Shenawi',NULL,'2012-09-23 14:19:53'),(1930,'Alejandro Chavez',NULL,'2012-09-23 14:19:54'),(1932,'Aleksander Bardini',NULL,'2012-09-23 14:19:54'),(1940,'Alexandre Rodrigues',NULL,'2012-09-23 14:19:55'),(1966,'Andrea Di Stefano',NULL,'2012-09-23 14:19:56'),(1974,'Andy Serkis',NULL,'2012-09-23 14:19:57'),(2030,'Armando Brancia',NULL,'2012-09-23 14:20:00'),(2064,'Beate Bartel',NULL,'2012-09-23 14:20:02'),(2081,'Bill Johnston',NULL,'2012-09-23 14:20:03'),(2087,'Billy Gilbert',NULL,'2012-09-23 14:20:03'),(2097,'Blixa Bargeld',NULL,'2012-09-23 14:20:04'),(2099,'Bob Dylan',NULL,'2012-09-23 14:20:04'),(2124,'Brigitte Mira',NULL,'2012-09-23 14:20:06'),(2128,'Bruce Willis',NULL,'2012-09-23 14:20:06'),(2130,'Bruno S.',NULL,'2012-09-23 14:20:06'),(2204,'Christopher Plummer',NULL,'2012-09-23 14:20:10'),(2250,'Daniel Ades',NULL,'2012-09-23 14:20:13'),(2256,'Daniel Farfan',NULL,'2012-09-23 14:20:14'),(2258,'Daniel Johnston',NULL,'2012-09-23 14:20:14'),(2261,'Daniel Richter',NULL,'2012-09-23 14:20:14'),(2277,'David Morse',NULL,'2012-09-23 14:20:15'),(2316,'Dorothy Comingore',NULL,'2012-09-23 14:20:18'),(2323,'Douglas Silva',NULL,'2012-09-23 14:20:18'),(2349,'Elijah Wood',NULL,'2012-09-23 14:20:19'),(2360,'Ellen Barkin',NULL,'2012-09-23 14:20:20'),(2371,'Emily Watson',NULL,'2012-09-23 14:20:21'),(2390,'Esmond Knight',NULL,'2012-09-23 14:20:22'),(2404,'F.M. Einheit',NULL,'2012-09-23 14:20:22'),(2428,'Franca Pasut',NULL,'2012-09-23 14:20:24'),(2437,'Francisco Reiguera',NULL,'2012-09-23 14:20:25'),(2438,'Franco Citti',NULL,'2012-09-23 14:20:25'),(2465,'Gary Farmer',NULL,'2012-09-23 14:20:26'),(2468,'Gary Lockwood',NULL,'2012-09-23 14:20:26'),(2482,'Geraldine Chaplin',NULL,'2012-09-23 14:20:27'),(2496,'Gilles Gaston-Dreyfus',NULL,'2012-09-23 14:20:28'),(2512,'Giuseppe Ianigro',NULL,'2012-09-23 14:20:29'),(2571,'Helmut Dantine',NULL,'2012-09-23 14:20:32'),(2575,'Henry Daniell',NULL,'2012-09-23 14:20:32'),(2609,'Iggy Pop',NULL,'2012-09-23 14:20:35'),(2617,'Irene Jacob',NULL,'2012-09-23 14:20:35'),(2623,'Isela Vega',NULL,'2012-09-23 14:20:35'),(2631,'Jack Oakie',NULL,'2012-09-23 14:20:36'),(2653,'Jamie Foxx',NULL,'2012-09-23 14:20:37'),(2683,'Jean-Marc Barr',NULL,'2012-09-23 14:20:39'),(2688,'Jeff Tartakov',NULL,'2012-09-23 14:20:39'),(2691,'Jeffrey Wright',NULL,'2012-09-23 14:20:39'),(2697,'Jerold Wells',NULL,'2012-09-23 14:20:40'),(2703,'Jerzy Gudejko',NULL,'2012-09-23 14:20:40'),(2715,'Jim Carrey',NULL,'2012-09-23 14:20:41'),(2716,'Jim Sclavunos',NULL,'2012-09-23 14:20:41'),(2748,'John Hurt',NULL,'2012-09-23 14:20:42'),(2766,'Jon Seda',NULL,'2012-09-23 14:20:43'),(2778,'Jose Mediavilla',NULL,'2012-09-23 14:20:44'),(2782,'Joseph Cotton',NULL,'2012-09-23 14:20:44'),(2796,'Julie Delpy',NULL,'2012-09-23 14:20:45'),(2824,'Kate Winslet',NULL,'2012-09-23 14:20:47'),(2838,'Keir Dullea',NULL,'2012-09-23 14:20:48'),(2852,'Kirsten Dunst',NULL,'2012-09-23 14:20:49'),(2891,'Leandro Firmino',NULL,'2012-09-23 14:20:51'),(2939,'Lorraine Evanoff',NULL,'2012-09-23 14:20:53'),(2941,'Louis Black',NULL,'2012-09-23 14:20:54'),(2957,'Lux Interior',NULL,'2012-09-23 14:20:54'),(2969,'Madeleine Stowe',NULL,'2012-09-23 14:20:55'),(2970,'Madison Eginton',NULL,'2012-09-23 14:20:56'),(2972,'Magali Noel',NULL,'2012-09-23 14:20:56'),(2991,'Margaret Tyzack',NULL,'2012-09-23 14:20:57'),(3019,'Mario Van Peebles',NULL,'2012-09-23 14:20:58'),(3028,'Mark Chung',NULL,'2012-09-23 14:20:59'),(3046,'Mary-Louise Parker',NULL,'2012-09-23 14:21:00'),(3052,'Matheus Nachtergaele',NULL,'2012-09-23 14:21:00'),(3054,'Matt Groening',NULL,'2012-09-23 14:21:00'),(3062,'Me Me Lai',NULL,'2012-09-23 14:21:01'),(3086,'Michael Kroecher',NULL,'2012-09-23 14:21:02'),(3102,'Mick Harvey',NULL,'2012-09-23 14:21:03'),(3153,'Nando Orfei',NULL,'2012-09-23 14:21:06'),(3168,'Nicoletta Braschi',NULL,'2012-09-23 14:21:07'),(3194,'Olivier Martinez',NULL,'2012-09-23 14:21:09'),(3206,'Paddy Considine',NULL,'2012-09-23 14:21:09'),(3207,'Paola Guidi',NULL,'2012-09-23 14:21:09'),(3234,'Penelope Ann Miller',NULL,'2012-09-23 14:21:11'),(3242,'Pete Seeger',NULL,'2012-09-23 14:21:11'),(3243,'Peter Berling',NULL,'2012-09-23 14:21:11'),(3252,'Peter Stormare',NULL,'2012-09-23 14:21:12'),(3256,'Peter, Paul & Mary',NULL,'2012-09-23 14:21:12'),(3257,'Phellipe Haagensen',NULL,'2012-09-23 14:21:12'),(3261,'Phill Calvert',NULL,'2012-09-23 14:21:13'),(3269,'Poison Ivy',NULL,'2012-09-23 14:21:13'),(3272,'Pupella Maggio',NULL,'2012-09-23 14:21:13'),(3274,'Ralf Little',NULL,'2012-09-23 14:21:13'),(3286,'Reginald Gardiner',NULL,'2012-09-23 14:21:14'),(3328,'Robert Downey Jr.',NULL,'2012-09-23 14:21:16'),(3333,'Robert Mitchum',NULL,'2012-09-23 14:21:17'),(3338,'Robert Webber',NULL,'2012-09-23 14:21:17'),(3355,'Ron Silver',NULL,'2012-09-23 14:21:18'),(3365,'Rowland Howard',NULL,'2012-09-23 14:21:19'),(3379,'Ruth Warrick',NULL,'2012-09-23 14:21:20'),(3423,'Sharon Stone',NULL,'2012-09-23 14:21:22'),(3429,'Shirley Henderson',NULL,'2012-09-23 14:21:22'),(3435,'Silvana Corsini',NULL,'2012-09-23 14:21:23'),(3440,'Slim Chance',NULL,'2012-09-23 14:21:23'),(3455,'Stellan Skarsgard',NULL,'2012-09-23 14:21:24'),(3465,'Steve Coogan',NULL,'2012-09-23 14:21:24'),(3534,'Todd Field',NULL,'2012-09-23 14:21:29'),(3541,'Tom Wilkinson',NULL,'2012-09-23 14:21:29'),(3547,'Tracy Pew',NULL,'2012-09-23 14:21:29'),(3602,'Walter Ladengast',NULL,'2012-09-23 14:21:32'),(3609,'Will Smith',NULL,'2012-09-23 14:21:33'),(3619,'William Sylvester',NULL,'2012-09-23 14:21:33'),(3621,'Willy Semmelrogge',NULL,'2012-09-23 14:21:34'),(3703,'Apurimac',NULL,'2012-09-23 15:00:50'),(3706,'Άβατον',NULL,'2012-09-23 15:00:51'),(3710,'Αλκίνοος Ιωαννίδης',NULL,'2012-09-23 15:00:51'),(3711,'Άλκηστις Πρωτοψάλτη',NULL,'2012-09-23 15:00:51'),(3713,'Αρλέτα',NULL,'2012-09-23 15:00:51'),(3714,'Βασίλης Λέκκας',NULL,'2012-09-23 15:00:51'),(3716,'Βίκυ Μοσχολιού',NULL,'2012-09-23 15:00:51'),(3717,'Γιάννης Αγγελάκας',NULL,'2012-09-23 15:00:51'),(3718,'Γιάννης Αγγελάκας & Οι Επισκέπτες',NULL,'2012-09-23 15:00:51'),(3720,'Γιάννης Μαρκόπουλος',NULL,'2012-09-23 15:00:51'),(3725,'Γιώργος Καρράς',NULL,'2012-09-23 15:00:52'),(3731,'Δημήτρης Παπαδημητρίου',NULL,'2012-09-23 15:00:52'),(3732,'Διάφανα Κρίνα',NULL,'2012-09-23 15:00:52'),(3750,'Λίνα Νικολακοπούλου',NULL,'2012-09-23 15:00:53'),(3769,'Μιλτιάδης Παπαστάμου',NULL,'2012-09-23 15:00:54'),(3774,'Νίκος Βελιώτης',NULL,'2012-09-23 15:00:54'),(3796,'Σταμάτης Κραουνάκης',NULL,'2012-09-23 15:00:56'),(3834,'2 By Bukowski',NULL,'2012-09-24 17:26:37'),(3835,'22 Pistepirkko',NULL,'2012-09-24 17:26:37'),(3837,'A Silver Mt. Zion',NULL,'2012-09-24 17:26:37'),(3842,'Aerial M',NULL,'2012-09-24 17:26:37'),(3844,'Afghan Whigs',NULL,'2012-09-24 17:26:37'),(3848,'Air',NULL,'2012-09-24 17:26:37'),(3850,'Akron/Family',NULL,'2012-09-24 17:26:37'),(3860,'Alice In Chains',NULL,'2012-09-24 17:26:38'),(3866,'American Analog Set',NULL,'2012-09-24 17:26:38'),(3874,'Andrew Bird',NULL,'2012-09-24 17:26:38'),(3876,'Angels Of Light',NULL,'2012-09-24 17:26:38'),(3884,'Antony And The Johnsons',NULL,'2012-09-24 17:26:38'),(3890,'Arab Strap',NULL,'2012-09-24 17:26:39'),(3891,'Arcade Fire',NULL,'2012-09-24 17:26:39'),(3920,'Bardo Pond',NULL,'2012-09-24 17:26:40'),(3931,'Beatles',NULL,'2012-09-24 17:26:40'),(3937,'Belle And Sebastian',NULL,'2012-09-24 17:26:40'),(3947,'Big Black',NULL,'2012-09-24 17:26:41'),(3960,'Birthday Party',NULL,'2012-09-24 17:26:41'),(3968,'Blaine L. Reininger',NULL,'2012-09-24 17:26:41'),(3986,'Bonnie \"Prince\" Billy',NULL,'2012-09-24 17:26:42'),(3993,'Brand New',NULL,'2012-09-24 17:26:42'),(4008,'Bryan Ferry',NULL,'2012-09-24 17:26:42'),(4027,'Captain Beefheart & His Magic Band',NULL,'2012-09-24 17:26:43'),(4091,'Cure',NULL,'2012-09-24 17:26:45'),(4092,'Current 93',NULL,'2012-09-24 17:26:45'),(4526,'Mariee Sioux',NULL,'2012-09-24 17:27:06'),(4865,'Steven Brown',NULL,'2012-09-24 17:27:26'),(5043,'Alceo Galliera',NULL,'2012-09-25 17:25:51'),(5045,'Angela Hewitt',NULL,'2012-09-25 17:25:52'),(5047,'Antonino Votto',NULL,'2012-09-25 17:25:52'),(5048,'Antonio Vivaldi',NULL,'2012-09-25 17:25:52'),(5049,'Artur Rubinstein',NULL,'2012-09-25 17:25:52'),(5052,'Christoph Von Dohnanyi',NULL,'2012-09-25 17:25:52'),(5053,'Christoph Willibald Gluck',NULL,'2012-09-25 17:25:52'),(5054,'Christopher Warren-Green',NULL,'2012-09-25 17:25:52'),(5055,'Claude Debussy',NULL,'2012-09-25 17:25:52'),(5057,'Dmitri Shostakovich',NULL,'2012-09-25 17:25:52'),(5060,'Edgar Meyer',NULL,'2012-09-25 17:25:52'),(5061,'Frederic Chopin',NULL,'2012-09-25 17:25:52'),(5062,'Gaetano Donizetti',NULL,'2012-09-25 17:25:52'),(5063,'Giacomo Puccini',NULL,'2012-09-25 17:25:52'),(5064,'Gianandrea Gavazzeni',NULL,'2012-09-25 17:25:52'),(5065,'Gioachino Rossini',NULL,'2012-09-25 17:25:52'),(5067,'Giuseppe Verdi',NULL,'2012-09-25 17:25:52'),(5068,'Gustav Mahler',NULL,'2012-09-25 17:25:52'),(5069,'Helmut Walcha',NULL,'2012-09-25 17:25:52'),(5072,'Idil Biret',NULL,'2012-09-25 17:25:52'),(5073,'Johann Sebastian Bach',NULL,'2012-09-25 17:25:52'),(5080,'Mstislav Rostropovich',NULL,'2012-09-25 17:25:52'),(5082,'Nino Sanzogno',NULL,'2012-09-25 17:25:53'),(5084,'Pierre Boulez',NULL,'2012-09-25 17:25:53'),(5085,'Pieter Wispelwey & Florilegium',NULL,'2012-09-25 17:25:53'),(5094,'Simon Rattle',NULL,'2012-09-25 17:25:53'),(5096,'Sir Neville Marriner',NULL,'2012-09-25 17:25:53'),(5098,'Tullio Serafin',NULL,'2012-09-25 17:25:53'),(5099,'Victor De Sabata',NULL,'2012-09-25 17:25:53'),(5101,'Virgil Fox',NULL,'2012-09-25 17:25:53'),(5104,'Walter Gieseking',NULL,'2012-09-25 17:25:53'),(5107,'None Listed',NULL,'2012-09-30 16:54:13'),(5112,'Joe Rees',NULL,'2012-09-30 17:12:02'),(5113,'Jeff Feuerzeig',NULL,'2012-09-30 17:12:19'),(5114,'Gakuryu Ishii',NULL,'2012-09-30 17:12:30');
/*!40000 ALTER TABLE `Artists` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Subtitles`
--

LOCK TABLES `Subtitles` WRITE;
/*!40000 ALTER TABLE `Subtitles` DISABLE KEYS */;
INSERT INTO `Subtitles` VALUES (1,'No Subtitles','2014-04-01 12:27:02'),(2,'Greek Subtitles','2012-09-23 17:50:03'),(3,'English Subtitles','2012-09-23 17:50:10');
/*!40000 ALTER TABLE `Subtitles` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `ArtistsActivitiesItems`
--

LOCK TABLES `ArtistsActivitiesItems` WRITE;
/*!40000 ALTER TABLE `ArtistsActivitiesItems` DISABLE KEYS */;
INSERT INTO `ArtistsActivitiesItems` VALUES (16,1733,230,3,'2012-09-28 16:32:22'),(39,250,4,3,'2012-09-28 16:32:23'),(40,243,5,3,'2012-09-28 16:32:23'),(77,148,84,3,'2012-09-28 16:32:25'),(102,179,181,3,'2012-09-28 16:32:26'),(111,143,163,3,'2012-09-28 16:32:27'),(143,1757,110,3,'2012-09-28 16:32:29'),(181,243,183,3,'2012-09-28 16:32:31'),(301,134,56,3,'2012-09-28 16:32:38'),(384,230,88,3,'2012-09-28 16:32:44'),(414,1828,20,3,'2012-09-28 16:32:45'),(466,1072,106,3,'2012-09-28 16:32:49'),(691,576,56,4,'2012-09-28 17:05:11'),(802,1275,183,4,'2012-09-28 17:05:18'),(810,1338,88,4,'2012-09-28 17:05:18'),(840,1550,4,4,'2012-09-28 17:05:20'),(873,1733,230,4,'2012-09-28 17:05:22'),(895,1940,110,4,'2012-09-28 17:05:23'),(922,2097,173,4,'2012-09-28 17:05:25'),(957,2258,146,4,'2012-09-28 17:05:27'),(970,2371,84,4,'2012-09-28 17:05:28'),(1020,2617,163,4,'2012-09-28 17:05:31'),(1038,2715,181,4,'2012-09-28 17:05:32'),(1055,2838,5,4,'2012-09-28 17:05:33'),(1069,2957,119,4,'2012-09-28 17:05:34'),(1117,3328,106,4,'2012-09-28 17:05:36'),(1179,3609,20,4,'2012-09-28 17:05:40'),(1289,962,183,4,'2012-09-28 17:11:16'),(1422,2128,4,4,'2012-09-28 17:11:24'),(1490,2404,173,4,'2012-09-28 17:11:28'),(1510,2468,5,4,'2012-09-28 17:11:29'),(1512,2482,106,4,'2012-09-28 17:11:29'),(1546,2623,88,4,'2012-09-28 17:11:31'),(1549,2631,230,4,'2012-09-28 17:11:31'),(1553,2653,20,4,'2012-09-28 17:11:32'),(1564,2703,163,4,'2012-09-28 17:11:32'),(1592,2824,181,4,'2012-09-28 17:11:34'),(1616,2891,110,4,'2012-09-28 17:11:35'),(1659,3054,146,4,'2012-09-28 17:11:38'),(1686,3194,56,4,'2012-09-28 17:11:39'),(1702,3269,119,4,'2012-09-28 17:11:40'),(1745,3455,84,4,'2012-09-28 17:11:43'),(1838,667,20,4,'2012-09-28 17:16:25'),(1839,710,84,4,'2012-09-28 17:16:25'),(1890,1480,106,4,'2012-09-28 17:16:27'),(1915,1862,183,4,'2012-09-28 17:16:28'),(1938,1966,56,4,'2012-09-28 17:16:29'),(1966,2064,173,4,'2012-09-28 17:16:30'),(2203,2939,163,4,'2012-09-28 17:16:41'),(2204,2941,146,4,'2012-09-28 17:16:41'),(2214,2969,4,4,'2012-09-28 17:16:42'),(2282,3257,110,4,'2012-09-28 17:16:46'),(2288,3286,230,4,'2012-09-28 17:16:47'),(2297,3338,88,4,'2012-09-28 17:16:47'),(2322,3440,119,4,'2012-09-28 17:16:49'),(2342,3541,181,4,'2012-09-28 17:16:50'),(2363,3619,5,4,'2012-09-28 17:16:51'),(2413,661,56,4,'2012-09-28 17:20:21'),(2415,723,119,4,'2012-09-28 17:20:21'),(2420,826,106,4,'2012-09-28 17:20:21'),(2574,2261,5,4,'2012-09-28 17:20:30'),(2587,2323,110,4,'2012-09-28 17:20:31'),(2592,2349,181,4,'2012-09-28 17:20:31'),(2630,2496,163,4,'2012-09-28 17:20:33'),(2656,2571,88,4,'2012-09-28 17:20:35'),(2657,2575,230,4,'2012-09-28 17:20:35'),(2681,2683,84,4,'2012-09-28 17:20:36'),(2685,2688,146,4,'2012-09-28 17:20:36'),(2708,2766,4,4,'2012-09-28 17:20:38'),(2767,3019,20,4,'2012-09-28 17:20:41'),(2774,3028,173,4,'2012-09-28 17:20:42'),(2930,3534,183,4,'2012-09-28 17:20:50'),(2975,15,173,4,'2012-09-28 17:23:11'),(3001,733,88,4,'2012-09-28 17:23:13'),(3023,1179,56,4,'2012-09-28 17:23:14'),(3029,1299,84,4,'2012-09-28 17:23:14'),(3070,1932,163,4,'2012-09-28 17:23:17'),(3110,2081,146,4,'2012-09-28 17:23:19'),(3112,2087,230,4,'2012-09-28 17:23:19'),(3139,2204,4,4,'2012-09-28 17:24:13'),(3275,2716,119,4,'2012-09-28 17:24:21'),(3319,2852,181,4,'2012-09-28 17:24:24'),(3346,2970,183,4,'2012-09-28 17:24:25'),(3354,2991,5,4,'2012-09-28 17:24:26'),(3370,3052,110,4,'2012-09-28 17:24:26'),(3420,3234,106,4,'2012-09-28 17:24:29'),(3469,3355,20,4,'2012-09-28 17:24:32'),(3562,15,655,4,'2012-09-29 08:16:42'),(3587,219,665,4,'2012-09-29 08:16:43'),(3642,478,684,4,'2012-09-29 08:16:46'),(3654,533,634,4,'2012-09-29 08:16:47'),(3668,579,616,4,'2012-09-29 08:16:48'),(3676,599,617,4,'2012-09-29 08:16:48'),(3698,660,618,4,'2012-09-29 08:16:49'),(3713,737,642,4,'2012-09-29 08:16:50'),(3714,737,645,4,'2012-09-29 08:16:50'),(3738,828,636,4,'2012-09-29 08:16:52'),(3757,929,666,4,'2012-09-29 08:16:54'),(3771,986,620,4,'2012-09-29 08:16:55'),(3777,1019,640,4,'2012-09-29 08:16:55'),(3796,1094,667,4,'2012-09-29 08:16:56'),(3803,1125,685,4,'2012-09-29 08:16:57'),(3818,1202,652,4,'2012-09-29 08:16:57'),(3819,1204,622,4,'2012-09-29 08:16:58'),(3843,1335,623,4,'2012-09-29 08:16:59'),(3856,1370,626,4,'2012-09-29 08:17:00'),(3906,1635,632,4,'2012-09-29 08:17:03'),(3909,1649,668,4,'2012-09-29 08:17:03'),(3967,418,632,4,'2012-09-29 08:17:07'),(3971,422,666,4,'2012-09-29 08:17:07'),(4031,711,684,4,'2012-09-29 08:17:10'),(4064,843,655,4,'2012-09-29 08:17:55'),(4068,863,634,4,'2012-09-29 08:17:55'),(4070,875,642,4,'2012-09-29 08:17:55'),(4132,1176,667,4,'2012-09-29 08:17:59'),(4171,1337,665,4,'2012-09-29 08:18:01'),(4175,1341,640,4,'2012-09-29 08:18:01'),(4200,1449,636,4,'2012-09-29 08:18:02'),(4203,1463,685,4,'2012-09-29 08:18:02'),(4225,1552,668,4,'2012-09-29 08:18:04'),(4229,1565,652,4,'2012-09-29 08:18:04'),(4253,1658,645,4,'2012-09-29 08:18:06'),(4278,198,665,4,'2012-09-29 08:18:07'),(4312,444,685,4,'2012-09-29 08:18:09'),(4353,642,667,4,'2012-09-29 08:18:12'),(4381,734,645,4,'2012-09-29 08:18:13'),(4460,1111,640,4,'2012-09-29 08:18:18'),(4487,1225,652,4,'2012-09-29 08:18:20'),(4522,1398,655,4,'2012-09-29 08:18:22'),(4533,1441,668,4,'2012-09-29 08:18:22'),(4565,1578,684,4,'2012-09-29 08:19:00'),(4584,1636,642,4,'2012-09-29 08:19:01'),(4605,1705,666,4,'2012-09-29 08:19:02'),(4622,325,665,4,'2012-09-29 08:19:03'),(4679,547,666,4,'2012-09-29 08:19:06'),(4705,669,645,4,'2012-09-29 08:19:08'),(4716,728,668,4,'2012-09-29 08:19:08'),(4774,1004,667,4,'2012-09-29 08:19:12'),(4798,1106,652,4,'2012-09-29 08:19:14'),(4815,1181,685,4,'2012-09-29 08:19:15'),(4879,1399,655,4,'2012-09-29 08:19:18'),(4889,1459,684,4,'2012-09-29 08:19:19'),(4945,225,665,4,'2012-09-29 08:19:22'),(4946,242,666,4,'2012-09-29 08:19:22'),(4949,291,667,4,'2012-09-29 08:19:23'),(4950,292,668,4,'2012-09-29 08:19:23'),(4966,351,684,4,'2012-09-29 08:19:24'),(4967,353,685,4,'2012-09-29 08:19:24'),(5279,158,616,3,'2012-09-29 08:55:00'),(5280,188,618,3,'2012-09-29 08:55:00'),(5282,96,620,3,'2012-09-29 08:55:00'),(5284,240,622,3,'2012-09-29 08:55:00'),(5285,1340,623,3,'2012-09-29 08:55:00'),(5288,267,626,3,'2012-09-29 08:55:00'),(5293,202,632,3,'2012-09-29 08:55:00'),(5295,68,634,3,'2012-09-29 08:55:00'),(5297,190,636,3,'2012-09-29 08:55:00'),(5301,168,640,3,'2012-09-29 08:55:00'),(5303,5,642,3,'2012-09-29 08:55:00'),(5306,141,645,3,'2012-09-29 08:55:01'),(5322,152,666,3,'2012-09-29 08:55:01'),(5323,211,667,3,'2012-09-29 08:55:01'),(5324,116,668,3,'2012-09-29 08:55:01'),(5338,40,684,3,'2012-09-29 08:55:02'),(5339,234,685,3,'2012-09-29 08:55:02'),(5615,248,665,3,'2012-09-29 08:55:12'),(5616,212,665,3,'2012-09-29 08:55:12'),(5620,224,652,3,'2012-09-29 08:55:12'),(5621,223,652,3,'2012-09-29 08:55:12'),(5658,191,617,3,'2012-09-29 08:55:15'),(5659,150,617,3,'2012-09-29 08:55:15'),(5660,49,655,3,'2012-09-29 08:55:15'),(5661,15,655,3,'2012-09-29 08:55:15'),(6184,1464,1095,1,'2012-09-29 19:20:51'),(6505,3835,988,1,'2012-09-29 19:21:11'),(6521,3842,1004,1,'2012-09-29 19:21:11'),(6580,3866,1065,1,'2012-09-29 19:21:15'),(6588,3874,1076,1,'2012-09-29 19:21:15'),(6601,3884,1113,1,'2012-09-29 19:21:16'),(6618,3890,1131,1,'2012-09-29 19:21:17'),(6680,3920,1197,1,'2012-09-29 19:21:21'),(6702,3931,1230,1,'2012-09-29 19:21:22'),(6928,4008,1550,1,'2012-09-29 19:24:34'),(7132,4091,1772,1,'2012-09-29 19:24:47'),(10006,3876,1082,1,'2012-09-30 10:04:51'),(10007,3850,1082,1,'2012-09-30 10:04:51'),(10036,3968,1381,1,'2012-09-30 10:04:52'),(10037,4865,1381,1,'2012-09-30 10:04:52'),(10425,5048,5448,1,'2012-09-30 11:09:05'),(10426,5048,5449,1,'2012-09-30 11:09:05'),(10427,5048,5450,1,'2012-09-30 11:09:05'),(10428,5053,5451,1,'2012-09-30 11:09:05'),(10429,5055,5452,1,'2012-09-30 11:09:05'),(10430,5057,5453,1,'2012-09-30 11:09:05'),(10431,5061,5454,1,'2012-09-30 11:09:05'),(10432,5061,5455,1,'2012-09-30 11:09:05'),(10433,5062,5456,1,'2012-09-30 11:09:05'),(10434,5063,5457,1,'2012-09-30 11:09:05'),(10435,5063,5458,1,'2012-09-30 11:09:05'),(10436,5065,5459,1,'2012-09-30 11:09:05'),(10437,5065,5460,1,'2012-09-30 11:09:05'),(10438,5065,5461,1,'2012-09-30 11:09:05'),(10439,5067,5462,1,'2012-09-30 11:09:05'),(10440,5067,5463,1,'2012-09-30 11:09:05'),(10441,5068,5464,1,'2012-09-30 11:09:05'),(10442,5068,5465,1,'2012-09-30 11:09:05'),(10443,5068,5466,1,'2012-09-30 11:09:05'),(10444,5073,5467,1,'2012-09-30 11:09:05'),(10445,5073,5468,1,'2012-09-30 11:09:05'),(10446,5073,5469,1,'2012-09-30 11:09:05'),(10447,5073,5470,1,'2012-09-30 11:09:05'),(10448,5073,5471,1,'2012-09-30 11:09:05'),(10449,5073,5472,1,'2012-09-30 11:09:05'),(10450,5073,5473,1,'2012-09-30 11:09:06'),(10451,5073,5474,1,'2012-09-30 11:09:06'),(10452,5073,5475,1,'2012-09-30 11:09:06'),(10453,5073,5476,1,'2012-09-30 11:09:06'),(10454,5073,5477,1,'2012-09-30 11:09:06'),(10487,5043,5459,2,'2012-09-30 11:16:21'),(10492,5045,5467,2,'2012-09-30 11:16:21'),(10493,5045,5469,2,'2012-09-30 11:16:21'),(10494,5045,5470,2,'2012-09-30 11:16:21'),(10495,5045,5471,2,'2012-09-30 11:16:21'),(10496,5045,5473,2,'2012-09-30 11:16:21'),(10497,5045,5474,2,'2012-09-30 11:16:21'),(10498,5045,5475,2,'2012-09-30 11:16:21'),(10499,5045,5477,2,'2012-09-30 11:16:21'),(10502,5047,5456,2,'2012-09-30 11:16:21'),(10503,5047,5457,2,'2012-09-30 11:16:21'),(10504,5049,5455,2,'2012-09-30 11:16:21'),(10505,5052,5466,2,'2012-09-30 11:16:21'),(10506,5054,5449,2,'2012-09-30 11:16:21'),(10510,5060,5468,2,'2012-09-30 11:16:22'),(10511,5064,5460,2,'2012-09-30 11:16:22'),(10513,5069,5476,2,'2012-09-30 11:16:22'),(10516,5072,5454,2,'2012-09-30 11:16:22'),(10520,5080,5453,2,'2012-09-30 11:16:22'),(10524,5082,5451,2,'2012-09-30 11:16:22'),(10528,5084,5464,2,'2012-09-30 11:16:22'),(10529,5085,5448,2,'2012-09-30 11:16:22'),(10534,5094,5465,2,'2012-09-30 11:16:23'),(10540,5096,5461,2,'2012-09-30 11:16:23'),(10544,5098,5462,2,'2012-09-30 11:16:23'),(10545,5098,5463,2,'2012-09-30 11:16:23'),(10547,5099,5458,2,'2012-09-30 11:16:23'),(10548,5101,5472,2,'2012-09-30 11:16:23'),(10553,5104,5452,2,'2012-09-30 11:16:23'),(10554,3703,5507,1,'2012-09-30 14:36:47'),(10555,3706,5508,1,'2012-09-30 14:36:47'),(10558,3711,5511,1,'2012-09-30 14:36:47'),(10559,3711,5512,1,'2012-09-30 14:36:47'),(10560,3711,5513,1,'2012-09-30 14:36:47'),(10561,3711,5514,1,'2012-09-30 14:36:47'),(10562,3796,5514,1,'2012-09-30 14:36:47'),(10563,3750,5514,1,'2012-09-30 14:36:47'),(10568,3710,5519,1,'2012-09-30 14:36:48'),(10570,3710,5521,1,'2012-09-30 14:36:48'),(10571,3710,5522,1,'2012-09-30 14:36:48'),(10572,3769,5522,1,'2012-09-30 14:36:48'),(10573,3713,5523,1,'2012-09-30 14:36:48'),(10575,3714,5525,1,'2012-09-30 14:36:48'),(10577,3716,5527,1,'2012-09-30 14:36:48'),(10578,3717,5528,1,'2012-09-30 14:36:48'),(10579,3717,5529,1,'2012-09-30 14:36:48'),(10581,3717,5531,1,'2012-09-30 14:36:48'),(10582,3725,5531,1,'2012-09-30 14:36:48'),(10585,3717,5533,1,'2012-09-30 14:36:49'),(10586,3774,5533,1,'2012-09-30 14:36:49'),(10588,3720,5535,1,'2012-09-30 14:36:49'),(10595,3720,5542,1,'2012-09-30 14:36:49'),(10596,3720,5543,1,'2012-09-30 14:36:49'),(10630,3731,5568,1,'2012-09-30 14:36:51'),(10631,3732,5569,1,'2012-09-30 14:36:51'),(11014,5112,119,3,'2012-09-30 17:20:49'),(11015,5113,146,3,'2012-09-30 17:20:49'),(11016,5114,173,3,'2012-09-30 17:20:49'),(11068,3732,5570,1,'2013-03-22 18:49:47'),(11091,3379,111,4,'2013-03-22 18:58:48'),(11092,1906,111,4,'2013-03-22 18:58:48'),(11093,2316,111,4,'2013-03-22 18:58:48'),(11094,2782,111,4,'2013-03-22 18:58:48'),(11095,192,111,4,'2013-03-22 18:58:48'),(11096,192,111,3,'2013-03-22 18:58:48'),(11225,6,683,3,'2013-03-22 21:44:49'),(11226,349,683,4,'2013-03-22 21:44:50'),(11227,319,683,4,'2013-03-22 21:44:50'),(11228,709,683,4,'2013-03-22 21:44:50'),(11229,350,683,4,'2013-03-22 21:44:50'),(11230,1030,683,4,'2013-03-22 21:44:50'),(11289,5107,68,3,'2013-03-22 22:05:38'),(11290,3261,68,4,'2013-03-22 22:05:38'),(11291,3547,68,4,'2013-03-22 22:05:38'),(11292,3365,68,4,'2013-03-22 22:05:38'),(11293,3102,68,4,'2013-03-22 22:05:38'),(11294,952,68,4,'2013-03-22 22:05:38'),(11398,2512,28,4,'2013-03-23 08:27:08'),(11399,3153,28,4,'2013-03-23 08:27:08'),(11400,2972,28,4,'2013-03-23 08:27:08'),(11401,2030,28,4,'2013-03-23 08:27:08'),(11402,3272,28,4,'2013-03-23 08:27:08'),(11403,1755,28,3,'2013-03-23 08:27:08'),(11444,4092,1805,1,'2013-03-23 08:45:56'),(11501,138,664,3,'2013-03-23 10:24:29'),(11502,219,664,4,'2013-03-23 10:24:29'),(11503,1337,664,4,'2013-03-23 10:24:29'),(11504,198,664,4,'2013-03-23 10:24:29'),(11505,58,664,4,'2013-03-23 10:24:29'),(11506,170,664,4,'2013-03-23 10:24:29'),(11546,3718,5534,1,'2013-03-23 11:00:49'),(11682,3086,178,4,'2013-03-23 11:45:31'),(11683,3621,178,4,'2013-03-23 11:45:31'),(11684,2124,178,4,'2013-03-23 11:45:31'),(11685,3602,178,4,'2013-03-23 11:45:31'),(11686,2130,178,4,'2013-03-23 11:45:31'),(11687,1340,178,3,'2013-03-23 11:45:31'),(11723,2256,19,4,'2013-03-23 11:50:25'),(11724,1930,19,4,'2013-03-23 11:50:25'),(11725,3243,19,4,'2013-03-23 11:50:25'),(11726,2250,19,4,'2013-03-23 11:50:25'),(11727,142,19,4,'2013-03-23 11:50:25'),(11728,1340,19,3,'2013-03-23 11:50:25'),(11796,276,661,3,'2013-03-23 12:00:34'),(11797,195,661,4,'2013-03-23 12:00:34'),(11798,1044,661,4,'2013-03-23 12:00:34'),(11799,1340,661,4,'2013-03-23 12:00:34'),(11800,112,661,4,'2013-03-23 12:00:34'),(11801,897,661,4,'2013-03-23 12:00:34'),(11823,3891,1141,1,'2013-03-23 12:44:24'),(11865,93,633,3,'2013-03-23 12:54:45'),(11866,850,633,4,'2013-03-23 12:54:45'),(11867,1572,633,4,'2013-03-23 12:54:45'),(11954,1902,15,4,'2013-03-23 13:11:24'),(11955,3207,15,4,'2013-03-23 13:11:24'),(11956,3435,15,4,'2013-03-23 13:11:24'),(11957,2428,15,4,'2013-03-23 13:11:24'),(11958,2438,15,4,'2013-03-23 13:11:24'),(11959,207,15,3,'2013-03-23 13:11:24'),(11974,114,648,3,'2013-03-23 13:14:26'),(11975,852,648,4,'2013-03-23 13:14:26'),(11976,903,648,4,'2013-03-23 13:14:26'),(11977,888,648,4,'2013-03-23 13:14:26'),(11978,599,648,4,'2013-03-23 13:14:26'),(11989,4027,1613,1,'2013-03-23 13:35:51'),(12039,2691,89,4,'2013-03-23 13:57:57'),(12040,1594,89,4,'2013-03-23 13:57:57'),(12041,2796,89,4,'2013-03-23 13:57:57'),(12042,3423,89,4,'2013-03-23 13:57:57'),(12043,1531,89,4,'2013-03-23 13:57:57'),(12044,619,89,3,'2013-03-23 13:57:57'),(12057,2609,132,4,'2013-03-23 13:59:42'),(12058,3333,132,4,'2013-03-23 13:59:42'),(12059,2748,132,4,'2013-03-23 13:59:42'),(12060,2465,132,4,'2013-03-23 13:59:42'),(12061,661,132,4,'2013-03-23 13:59:42'),(12062,619,132,3,'2013-03-23 13:59:42'),(12069,2360,164,4,'2013-03-23 14:01:04'),(12070,3168,164,4,'2013-03-23 14:01:04'),(12071,1850,164,4,'2013-03-23 14:01:04'),(12072,124,164,4,'2013-03-23 14:01:04'),(12073,1283,164,4,'2013-03-23 14:01:04'),(12074,619,164,3,'2013-03-23 14:01:04'),(12113,3947,1310,1,'2013-03-23 14:09:52'),(12228,3860,1051,1,'2013-03-23 14:54:33'),(12235,3937,1283,1,'2013-03-23 14:55:59'),(12264,3732,5572,1,'2013-03-23 15:22:16'),(12267,3710,5517,1,'2013-03-23 15:23:59'),(12465,178,6,3,'2013-03-23 20:30:48'),(12466,3465,6,4,'2013-03-23 20:30:48'),(12467,1974,6,4,'2013-03-23 20:30:48'),(12468,3206,6,4,'2013-03-23 20:30:48'),(12469,3429,6,4,'2013-03-23 20:30:48'),(12470,3274,6,4,'2013-03-23 20:30:48'),(12578,372,158,4,'2013-03-23 21:16:51'),(12579,2778,158,4,'2013-03-23 21:16:51'),(12580,192,158,4,'2013-03-23 21:16:51'),(12581,1410,158,4,'2013-03-23 21:16:51'),(12582,2437,158,4,'2013-03-23 21:16:51'),(12583,115,158,3,'2013-03-23 21:16:51'),(12584,192,158,3,'2013-03-23 21:16:51'),(12591,4091,1776,1,'2013-03-23 21:21:22'),(12682,3844,1011,1,'2013-03-23 21:49:12'),(12930,3848,1031,1,'2013-03-24 11:17:55'),(13066,3046,41,4,'2013-03-24 13:16:44'),(13067,1161,41,4,'2013-03-24 13:16:44'),(13068,1162,41,4,'2013-03-24 13:16:44'),(13069,1572,41,4,'2013-03-24 13:16:44'),(13070,1550,41,4,'2013-03-24 13:16:44'),(13071,1722,41,3,'2013-03-24 13:16:44'),(13091,3710,5520,1,'2013-03-24 13:23:00'),(13274,184,78,3,'2013-03-24 17:29:42'),(13275,3256,78,4,'2013-03-24 17:29:42'),(13276,3242,78,4,'2013-03-24 17:29:42'),(13277,660,78,4,'2013-03-24 17:29:42'),(13278,625,78,4,'2013-03-24 17:29:42'),(13279,2099,78,4,'2013-03-24 17:29:42'),(13420,760,650,4,'2013-03-24 18:26:47'),(13421,1126,650,4,'2013-03-24 18:26:47'),(13422,1543,650,4,'2013-03-24 18:26:47'),(13423,1015,650,4,'2013-03-24 18:26:47'),(13424,287,650,4,'2013-03-24 18:26:47'),(13425,262,650,3,'2013-03-24 18:26:47'),(13460,1508,1260,1,'2013-03-24 18:38:54'),(13650,3717,5532,1,'2013-03-25 11:16:37'),(13651,3774,5532,1,'2013-03-25 11:16:37'),(13652,3834,987,1,'2013-03-26 18:05:59'),(13656,1586,1649,1,'2013-03-26 18:08:50'),(13874,186,656,3,'2013-03-30 21:22:09'),(13875,1536,656,4,'2013-03-30 21:22:09'),(13876,289,656,4,'2013-03-30 21:22:09'),(13877,1047,656,4,'2013-03-30 21:22:09'),(13878,186,656,4,'2013-03-30 21:22:09'),(13935,1299,128,4,'2013-03-30 21:47:50'),(13936,3252,128,4,'2013-03-30 21:47:50'),(13937,2277,128,4,'2013-03-30 21:47:50'),(13938,1574,128,4,'2013-03-30 21:47:50'),(13939,1538,128,4,'2013-03-30 21:47:50'),(13940,148,128,3,'2013-03-30 21:47:50'),(14032,121,672,3,'2013-04-05 18:46:14'),(14033,305,672,4,'2013-04-05 18:46:14'),(14034,1488,672,4,'2013-04-05 18:46:14'),(14035,827,672,4,'2013-04-05 18:46:14'),(14036,1436,672,4,'2013-04-05 18:46:14'),(14037,477,672,4,'2013-04-05 18:46:14'),(14129,3732,5571,1,'2013-04-07 17:13:37'),(14133,4091,1782,1,'2013-04-07 17:15:47'),(14189,3717,5530,1,'2013-04-13 18:32:08'),(14344,3710,5516,1,'2013-04-13 19:51:36'),(14530,1907,175,4,'2013-04-13 20:27:16'),(14531,2697,175,4,'2013-04-13 20:27:16'),(14532,3062,175,4,'2013-04-13 20:27:16'),(14533,2390,175,4,'2013-04-13 20:27:16'),(14534,879,175,4,'2013-04-13 20:27:16'),(14535,148,175,3,'2013-04-13 20:27:16'),(14548,3960,1335,1,'2013-04-13 20:35:25'),(14655,279,654,3,'2013-04-14 14:39:52'),(14656,1383,654,4,'2013-04-14 14:39:52'),(14657,1379,654,4,'2013-04-14 14:39:52'),(14658,280,654,4,'2013-04-14 14:39:52'),(14659,1487,654,4,'2013-04-14 14:39:52'),(14888,3993,1512,1,'2013-04-28 13:42:29'),(14892,3837,994,1,'2013-04-28 13:44:46'),(15133,3986,1501,1,'2013-04-29 15:47:33'),(15134,4526,1501,1,'2013-04-29 15:47:33');
/*!40000 ALTER TABLE `ArtistsActivitiesItems` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-02  9:30:41
