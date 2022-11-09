-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.11.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

DROP DATABASE IF EXISTS `rooms`;
CREATE DATABASE IF NOT EXISTS `rooms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `rooms`;

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
  `Room_No` int(11) NOT NULL,
  `Room_Type` varchar(20) NOT NULL,
  `Visitor_Name` varchar(25) DEFAULT NULL,
  `In_Date` varchar(20) DEFAULT NULL,
  `Out_Date` varchar(20) DEFAULT NULL,
  `State` int(11) NOT NULL,
  PRIMARY KEY (`Room_No`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

