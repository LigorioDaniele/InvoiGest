-- MySQL dump 10.13  Distrib 5.1.33, for Win32 (ia32)
--
-- Host: localhost    Database: InvoiGest
-- ------------------------------------------------------
-- Server version	5.1.33-community

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
-- Table structure for table `Clienti`
--

DROP TABLE IF EXISTS `Clienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Clienti` (
  `ID_Cliente` int(11) NOT NULL,
  `Ragionesociale` varchar(200) NOT NULL,
  `Codicefiscale` varchar(200) DEFAULT NULL,
  `Partitaiva` varchar(200) DEFAULT NULL,
  `Citta` varchar(200) DEFAULT NULL,
  `Indirizzo` varchar(200) DEFAULT NULL,
  `Telefono` varchar(200) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `Cap` varchar(200) DEFAULT NULL,
    KEY (`Ragionesociale`),
  PRIMARY KEY (`ID_Cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `Fornitori`
--

DROP TABLE IF EXISTS `Fornitori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fornitori` (
  `ID_Fornitore` int(11) NOT NULL,
  `Ragionesociale` varchar(200) NOT NULL,
  `Codicefiscale` varchar(200) DEFAULT NULL,
  `Partitaiva` varchar(200) DEFAULT NULL,
  `Citta` varchar(200) DEFAULT NULL,
  `Indirizzo` varchar(200) DEFAULT NULL,
  `Telefono` varchar(200) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `Cap` varchar(200) DEFAULT NULL,
  KEY (`Ragionesociale`),
  PRIMARY KEY (`ID_Fornitore`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Table structure for table `FattureEmesse`
--

DROP TABLE IF EXISTS `FattureEmesse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FattureEmesse` (
  `ID_FattureEmesse` int(11) NOT NULL,
  `Ragionesociale` varchar(200) NOT NULL,
  `Numerofattura` varchar(200) DEFAULT NULL,
  `Dataf` varchar(200) DEFAULT NULL,
  `Iva` varchar(200) DEFAULT NULL,
  `Totale` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID_FattureEmesse`),
  FOREIGN KEY(`Ragionesociale`) REFERENCES `Clienti`(`Ragionesociale`) ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FattureEmesse`
--

DROP TABLE IF EXISTS `FattureRicevute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FattureRicevute` (
  `ID_FattureRicevute` int(11) NOT NULL,
  `Ragionesociale` varchar(200) NOT NULL,
  `Numerofattura` varchar(200) DEFAULT NULL,
  `Dataf` varchar(200) DEFAULT NULL,
  `Iva` varchar(200) DEFAULT NULL,
  `Totale` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID_FattureRicevute`),
  FOREIGN KEY(`Ragionesociale`) REFERENCES `Fornitori`(`Ragionesociale`) ON DELETE CASCADE
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `Prodotti`
--

DROP TABLE IF EXISTS `Prodotti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Prodotti` (
  `ID_Prodotto` int(11) NOT NULL,
  `Descrizione` varchar(200) DEFAULT NULL,
  `Codice` varchar(200) DEFAULT NULL,
  `Quantita` varchar(200) DEFAULT NULL,
  `Prezzo` varchar(200) DEFAULT NULL,
  `Numerofattura` varchar(200) NOT NULL,
  PRIMARY KEY (`ID_Prodotto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-06-19  9:46:48
