-- --------------------------------------------------------
-- Host:                         C:\Users\kheda\Desktop\cpit305-course-project\mydata
-- Server version:               3.34.0
-- Server OS:                    
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for mydata
DROP DATABASE IF EXISTS "mydata";
CREATE DATABASE IF NOT EXISTS "mydata";
-- USE "mydata" neither supported nor required;

-- Dumping structure for table mydata.books
DROP TABLE IF EXISTS "books";
CREATE TABLE IF NOT EXISTS books (
	book_id INTEGER NOT NULL,
	bookName TEXT(90) NOT NULL,
	bookSec TEXT(20),
	isAvailable INTEGER DEFAULT 1 NOT NULL,
	CONSTRAINT books_PK PRIMARY KEY (book_id)
);

-- Dumping data for table mydata.books: 6 rows
DELETE FROM "books";
/*!40000 ALTER TABLE "books" DISABLE KEYS */;
INSERT INTO "books" ("book_id", "bookName", "bookSec", "isAvailable") VALUES
	(1, 'cpit305', 'Sciences', 1),
	(2, 'cpit305', 'Sciences', 1),
	(3, 'cpit305', 'Sciences', 1),
	(4, 'cpit305', 'Sciences', 1),
	(5, 'cpit305', 'Sciences', 1),
	(6, 'cpit305', 'Sciences', 1);
/*!40000 ALTER TABLE "books" ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
