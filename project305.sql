-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.11.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for cpit305-project
CREATE DATABASE IF NOT EXISTS `cpit305-project` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cpit305-project`;

-- Dumping structure for table cpit305-project.gameplay
CREATE TABLE IF NOT EXISTS `gameplay` (
  `GameID` varchar(3) NOT NULL,
  `Player1` varchar(25) NOT NULL,
  `Player2` varchar(25) NOT NULL,
  `Winner` varchar(10) NOT NULL,
  `result` varchar(10) NOT NULL,
  `Gdate` date NOT NULL,
  PRIMARY KEY (`GameID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table cpit305-project.game_player
CREATE TABLE IF NOT EXISTS `game_player` (
  `Game_ID` varchar(3) NOT NULL,
  `PID` int(11) NOT NULL,
  PRIMARY KEY (`Game_ID`,`PID`),
  KEY `game_player_FK` (`PID`),
  CONSTRAINT `game_player_FK` FOREIGN KEY (`Game_ID`) REFERENCES `gameplay` (`GameID`),
  CONSTRAINT `game_player_FK_1` FOREIGN KEY (`PID`) REFERENCES `player` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table cpit305-project.player
CREATE TABLE IF NOT EXISTS `player` (
  `ID` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(10) NOT NULL,
  `Wins` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
