-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.16-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for 123456789
CREATE DATABASE IF NOT EXISTS `123456789` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `123456789`;

-- Dumping structure for table 123456789.addresses
CREATE TABLE IF NOT EXISTS `addresses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_type` varchar(255) DEFAULT NULL,
  `address_type` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.addresses: ~0 rows (approximately)
DELETE FROM `addresses`;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;

-- Dumping structure for table 123456789.books
CREATE TABLE IF NOT EXISTS `books` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `book_author` varchar(255) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_books_book_category` (`category_id`),
  KEY `FK_books_book_type` (`type_id`),
  KEY `FK_books_users` (`author_id`),
  CONSTRAINT `FK_books_book_category` FOREIGN KEY (`category_id`) REFERENCES `book_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_books_book_type` FOREIGN KEY (`type_id`) REFERENCES `book_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_books_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.books: ~0 rows (approximately)
DELETE FROM `books`;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table 123456789.book_category
CREATE TABLE IF NOT EXISTS `book_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_book_category_users` (`author_id`),
  CONSTRAINT `FK_book_category_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.book_category: ~0 rows (approximately)
DELETE FROM `book_category`;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;

-- Dumping structure for table 123456789.book_type
CREATE TABLE IF NOT EXISTS `book_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_book_type_users` (`author_id`),
  CONSTRAINT `FK_book_type_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.book_type: ~0 rows (approximately)
DELETE FROM `book_type`;
/*!40000 ALTER TABLE `book_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_type` ENABLE KEYS */;

-- Dumping structure for table 123456789.classes
CREATE TABLE IF NOT EXISTS `classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `ranking` bigint(20) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_classes_users` (`author_id`),
  CONSTRAINT `FK_classes_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Classes';

-- Dumping data for table 123456789.classes: ~1 rows (approximately)
DELETE FROM `classes`;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` (`id`, `name`, `code`, `ranking`, `status`, `date_created`, `author_id`) VALUES
	(1, 'Class One', '1', 1, 'ACTIVE', '2018-03-08 20:56:12', 1);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;

-- Dumping structure for table 123456789.class_stream
CREATE TABLE IF NOT EXISTS `class_stream` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` bigint(20) DEFAULT NULL,
  `stream_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_class_stream_users` (`author_id`),
  KEY `FK_class_stream_classes` (`class_id`),
  KEY `FK_class_stream_streams` (`stream_id`),
  CONSTRAINT `FK_class_stream_classes` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_class_stream_streams` FOREIGN KEY (`stream_id`) REFERENCES `streams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_class_stream_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.class_stream: ~0 rows (approximately)
DELETE FROM `class_stream`;
/*!40000 ALTER TABLE `class_stream` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_stream` ENABLE KEYS */;

-- Dumping structure for table 123456789.contacts
CREATE TABLE IF NOT EXISTS `contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_type` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `contact_type` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_contacts_users` (`author_id`),
  CONSTRAINT `FK_contacts_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.contacts: ~0 rows (approximately)
DELETE FROM `contacts`;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;

-- Dumping structure for table 123456789.curriculum
CREATE TABLE IF NOT EXISTS `curriculum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '0',
  `code` varchar(255) DEFAULT '0',
  `description` varchar(500) DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_curriculum_users` (`author_id`),
  CONSTRAINT `FK_curriculum_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Curriculum';

-- Dumping data for table 123456789.curriculum: ~1 rows (approximately)
DELETE FROM `curriculum`;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` (`id`, `name`, `code`, `description`, `status`, `date_created`, `author_id`) VALUES
	(1, 'LOWER PRIMARY CURRICULUM', 'CPOP1', 'Lower Primary Curriclum', 'ACTIVE', '2018-03-10 00:39:05', 1);
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;

-- Dumping structure for table 123456789.curriculum_details
CREATE TABLE IF NOT EXISTS `curriculum_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `curriculum_id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_curriculum_details_users` (`author_id`),
  KEY `FK_curriculum_details_curriculum` (`curriculum_id`),
  CONSTRAINT `FK_curriculum_details_curriculum` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_curriculum_details_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.curriculum_details: ~0 rows (approximately)
DELETE FROM `curriculum_details`;
/*!40000 ALTER TABLE `curriculum_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_details` ENABLE KEYS */;

-- Dumping structure for table 123456789.departments
CREATE TABLE IF NOT EXISTS `departments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '0',
  `code` varchar(255) DEFAULT '0',
  `description` varchar(500) DEFAULT '0',
  `isSystem` bit(1) DEFAULT b'1',
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `parent_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_code` (`name`,`code`),
  KEY `FK_departments_users` (`author_id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `FK_departments_departments` FOREIGN KEY (`parent_id`) REFERENCES `departments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_departments_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Manage Department';

-- Dumping data for table 123456789.departments: ~1 rows (approximately)
DELETE FROM `departments`;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` (`id`, `name`, `code`, `description`, `isSystem`, `status`, `parent_id`, `date_created`, `author_id`) VALUES
	(1, 'Teaching Department', 'TCD', 'Mathmatic Department', b'1', 'ACTIVE', NULL, '2018-03-16 16:28:47', 1);
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;

-- Dumping structure for table 123456789.exams
CREATE TABLE IF NOT EXISTS `exams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `%contribution` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_exams_users` (`author_id`),
  CONSTRAINT `FK_exams_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Exams';

-- Dumping data for table 123456789.exams: ~0 rows (approximately)
DELETE FROM `exams`;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;

-- Dumping structure for table 123456789.exam_class
CREATE TABLE IF NOT EXISTS `exam_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_exam_class_users` (`author_id`),
  KEY `FK_exam_class_exams` (`exam_id`),
  CONSTRAINT `FK_exam_class_classes` FOREIGN KEY (`exam_id`) REFERENCES `classes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_class_exams` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_class_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.exam_class: ~0 rows (approximately)
DELETE FROM `exam_class`;
/*!40000 ALTER TABLE `exam_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_class` ENABLE KEYS */;

-- Dumping structure for table 123456789.exam_tem
CREATE TABLE IF NOT EXISTS `exam_tem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) DEFAULT NULL,
  `term_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_exam_tem_exams` (`exam_id`),
  KEY `FK_exam_tem_terms` (`term_id`),
  KEY `FK_exam_tem_users` (`author_id`),
  CONSTRAINT `FK_exam_tem_exams` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_tem_terms` FOREIGN KEY (`term_id`) REFERENCES `terms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_tem_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.exam_tem: ~0 rows (approximately)
DELETE FROM `exam_tem`;
/*!40000 ALTER TABLE `exam_tem` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_tem` ENABLE KEYS */;

-- Dumping structure for table 123456789.exam_timetable
CREATE TABLE IF NOT EXISTS `exam_timetable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_exam_timetable_exams` (`exam_id`),
  KEY `FK_exam_timetable_subjects` (`subject_id`),
  KEY `FK_exam_timetable_subject_papers` (`paper_id`),
  KEY `FK_exam_timetable_users` (`teacher_id`),
  KEY `FK_exam_timetable_users_2` (`author_id`),
  CONSTRAINT `FK_exam_timetable_exams` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_timetable_subject_papers` FOREIGN KEY (`paper_id`) REFERENCES `subject_papers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_timetable_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_timetable_users` FOREIGN KEY (`teacher_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_timetable_users_2` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.exam_timetable: ~0 rows (approximately)
DELETE FROM `exam_timetable`;
/*!40000 ALTER TABLE `exam_timetable` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_timetable` ENABLE KEYS */;

-- Dumping structure for table 123456789.grading
CREATE TABLE IF NOT EXISTS `grading` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_grading_users` (`author_id`),
  CONSTRAINT `FK_grading_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Grading';

-- Dumping data for table 123456789.grading: ~1 rows (approximately)
DELETE FROM `grading`;
/*!40000 ALTER TABLE `grading` DISABLE KEYS */;
INSERT INTO `grading` (`id`, `name`, `code`, `description`, `status`, `date_created`, `author_id`) VALUES
	(1, 'Tests Grading', 'TSTGRADE', 'This Grading is for Tests in the School', 'ACTIVE', '2018-03-13 00:35:10', 1);
/*!40000 ALTER TABLE `grading` ENABLE KEYS */;

-- Dumping structure for table 123456789.grading_details
CREATE TABLE IF NOT EXISTS `grading_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grading_id` bigint(20) NOT NULL DEFAULT '0',
  `grading_scale` bigint(20) NOT NULL DEFAULT '0',
  `symbol` varchar(50) NOT NULL DEFAULT '0',
  `mingrade` bigint(20) NOT NULL DEFAULT '0',
  `maxgrade` bigint(20) NOT NULL DEFAULT '0',
  `value` varchar(50) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_grading_details_users` (`author_id`),
  KEY `FK_grading_details_grading` (`grading_id`),
  CONSTRAINT `FK_grading_details_grading` FOREIGN KEY (`grading_id`) REFERENCES `grading` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_grading_details_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Grading Details';

-- Dumping data for table 123456789.grading_details: ~0 rows (approximately)
DELETE FROM `grading_details`;
/*!40000 ALTER TABLE `grading_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `grading_details` ENABLE KEYS */;

-- Dumping structure for table 123456789.library_section
CREATE TABLE IF NOT EXISTS `library_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `external_id` varchar(255) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_library_section_users` (`author_id`),
  CONSTRAINT `FK_library_section_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.library_section: ~0 rows (approximately)
DELETE FROM `library_section`;
/*!40000 ALTER TABLE `library_section` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_section` ENABLE KEYS */;

-- Dumping structure for table 123456789.library_stock
CREATE TABLE IF NOT EXISTS `library_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_number` varchar(255) NOT NULL,
  `stock_title` varchar(255) NOT NULL,
  `number_of_books` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_library_stock_library_section` (`section_id`),
  KEY `FK_library_stock_users` (`author_id`),
  CONSTRAINT `FK_library_stock_library_section` FOREIGN KEY (`section_id`) REFERENCES `library_section` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_library_stock_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Library Stock';

-- Dumping data for table 123456789.library_stock: ~0 rows (approximately)
DELETE FROM `library_stock`;
/*!40000 ALTER TABLE `library_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_stock` ENABLE KEYS */;

-- Dumping structure for table 123456789.library_stock_inventory
CREATE TABLE IF NOT EXISTS `library_stock_inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `isbnumber` varchar(255) NOT NULL,
  `serialnumber` varchar(255) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  KEY `FK_library_stock_inventory_library_stock` (`stock_id`),
  KEY `FK_library_stock_inventory_books` (`book_id`),
  KEY `FK_library_stock_inventory_users` (`author_id`),
  CONSTRAINT `FK_library_stock_inventory_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_library_stock_inventory_library_stock` FOREIGN KEY (`stock_id`) REFERENCES `library_stock` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_library_stock_inventory_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Library Stock Inventory';

-- Dumping data for table 123456789.library_stock_inventory: ~0 rows (approximately)
DELETE FROM `library_stock_inventory`;
/*!40000 ALTER TABLE `library_stock_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_stock_inventory` ENABLE KEYS */;

-- Dumping structure for table 123456789.library_transactions
CREATE TABLE IF NOT EXISTS `library_transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inventory_id` bigint(20) NOT NULL,
  `borrower_id` bigint(20) NOT NULL,
  `borrower_type` enum('STUDENT','TEACHER','OTHER') NOT NULL DEFAULT 'STUDENT',
  `expected_return_date` date NOT NULL,
  `actual_return_date` datetime NOT NULL,
  `transaction_type` enum('BORROW','RETURN') NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `parent_transaction_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_library_transactions_library_stock_inventory` (`inventory_id`),
  KEY `FK_library_transactions_users` (`author_id`),
  CONSTRAINT `FK_library_transactions_library_stock_inventory` FOREIGN KEY (`inventory_id`) REFERENCES `library_stock_inventory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_library_transactions_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Library Transactions';

-- Dumping data for table 123456789.library_transactions: ~0 rows (approximately)
DELETE FROM `library_transactions`;
/*!40000 ALTER TABLE `library_transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_transactions` ENABLE KEYS */;

-- Dumping structure for table 123456789.marksheet
CREATE TABLE IF NOT EXISTS `marksheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL DEFAULT '0',
  `subject_id` bigint(20) NOT NULL DEFAULT '0',
  `paper_id` bigint(20) NOT NULL DEFAULT '0',
  `class_id` bigint(20) NOT NULL DEFAULT '0',
  `exam_id` bigint(20) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `mark` bigint(20) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_marksheet_subjects` (`subject_id`),
  KEY `FK_marksheet_subject_papers` (`paper_id`),
  KEY `FK_marksheet_classes` (`class_id`),
  KEY `FK_marksheet_exams` (`exam_id`),
  KEY `FK_marksheet_users` (`author_id`),
  CONSTRAINT `FK_marksheet_classes` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_marksheet_exams` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_marksheet_subject_papers` FOREIGN KEY (`paper_id`) REFERENCES `subject_papers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_marksheet_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_marksheet_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Marksheet';

-- Dumping data for table 123456789.marksheet: ~0 rows (approximately)
DELETE FROM `marksheet`;
/*!40000 ALTER TABLE `marksheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `marksheet` ENABLE KEYS */;

-- Dumping structure for table 123456789.permissions
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.permissions: ~1 rows (approximately)
DELETE FROM `permissions`;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` (`id`, `name`, `code`) VALUES
	(1, 'ALL_FUNCTIONS', 'AF');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;

-- Dumping structure for table 123456789.profile
CREATE TABLE IF NOT EXISTS `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT '0',
  `middle_name` varchar(255) DEFAULT '0',
  `last_name` varchar(255) DEFAULT '0',
  `prefix` enum('MR','MIS','MRS','OTHER') DEFAULT 'OTHER',
  `date_of_birth` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `parent_type` enum('STUDENT','USER') DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_profile_users` (`author_id`),
  CONSTRAINT `FK_profile_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='Person Details';

-- Dumping data for table 123456789.profile: ~12 rows (approximately)
DELETE FROM `profile`;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` (`id`, `first_name`, `middle_name`, `last_name`, `prefix`, `date_of_birth`, `image`, `parent_type`, `parent_id`, `status`, `date_created`, `author_id`) VALUES
	(1, 'Muyinda', 'M', 'Rogers', 'OTHER', NULL, NULL, NULL, NULL, 'ACTIVE', '2018-02-20 22:02:59', NULL),
	(2, 'kilo', NULL, 'kilo', 'MR', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(3, 'kilo', NULL, 'kilo', 'MR', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(4, 'kilo', NULL, 'kilo', 'MR', NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(5, 'kilo', NULL, 'kilo', 'MR', NULL, NULL, NULL, NULL, NULL, '2018-04-25 22:34:34', 1),
	(6, 'kilo', NULL, 'kilo', 'MR', NULL, NULL, NULL, NULL, 'ACTIVE', '2018-04-25 22:36:59', 1),
	(7, 'kilo', NULL, 'kilo', 'MR', NULL, NULL, NULL, NULL, 'ACTIVE', '2018-04-25 23:13:26', 1),
	(8, 'pilowet', NULL, 'kilowet', 'MR', NULL, NULL, NULL, NULL, 'ACTIVE', '2018-04-25 23:21:23', 1),
	(9, 'usiusius', NULL, 'owowepw', 'MR', NULL, NULL, NULL, NULL, 'ACTIVE', '2018-04-26 02:44:36', 1),
	(10, 'vumbi', 'n', 'vumbi', 'MR', '2018-04-27', NULL, NULL, NULL, 'ACTIVE', '2018-04-26 21:01:20', 1),
	(11, 'mose', 'n', 'se', 'MR', '2018-04-11', NULL, NULL, NULL, 'ACTIVE', '2018-04-26 21:01:22', 1),
	(12, 'Misse', NULL, 'Misse', 'MIS', '2018-04-11', NULL, NULL, NULL, 'ACTIVE', '2018-04-28 23:52:24', 1);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;

-- Dumping structure for table 123456789.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '0',
  `isSystem` tinyint(4) NOT NULL DEFAULT '0',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_roles_users` (`author_id`),
  CONSTRAINT `FK_roles_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Manage Roles';

-- Dumping data for table 123456789.roles: ~1 rows (approximately)
DELETE FROM `roles`;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`, `code`, `description`, `isSystem`, `date_created`, `author_id`) VALUES
	(1, 'SUPER_ADMIN', 'SADMIN', 'MANAGERS IDEARLY EVEYTHING IN THE SYSTEM', 1, '2017-12-21 22:14:24', NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table 123456789.role_permission
CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  KEY `FK_role_permission_roles` (`role_id`),
  KEY `FK_role_permission_permissions` (`permission_id`),
  CONSTRAINT `FK_role_permission_permissions` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_role_permission_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.role_permission: ~1 rows (approximately)
DELETE FROM `role_permission`;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;

-- Dumping structure for table 123456789.staff
CREATE TABLE IF NOT EXISTS `staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `profile_id` bigint(20) NOT NULL DEFAULT '0',
  `isTeacher` bit(1) NOT NULL DEFAULT b'1',
  `join_date` date NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_staff_users` (`author_id`),
  KEY `profile_id` (`profile_id`),
  CONSTRAINT `FK_staff_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_staff_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='Staff';

-- Dumping data for table 123456789.staff: ~8 rows (approximately)
DELETE FROM `staff`;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`id`, `profile_id`, `isTeacher`, `join_date`, `status`, `date_created`, `author_id`) VALUES
	(1, 1, b'1', '2018-03-21', 'ACTIVE', '2018-03-21 22:29:11', 1),
	(2, 6, b'0', '2018-04-11', 'ACTIVE', '2018-04-25 22:37:00', 1),
	(3, 7, b'0', '2018-04-11', 'ACTIVE', '2018-04-25 23:13:27', 1),
	(4, 8, b'1', '2018-04-11', 'ACTIVE', '2018-04-25 23:21:24', 1),
	(5, 9, b'1', '2018-04-20', 'ACTIVE', '2018-04-26 02:44:36', 1),
	(6, 10, b'1', '2018-04-13', 'ACTIVE', '2018-04-26 20:18:43', 1),
	(7, 11, b'1', '2018-04-20', 'ACTIVE', '2018-04-26 20:25:07', 1),
	(8, 12, b'1', '2018-04-12', 'ACTIVE', '2018-04-28 23:52:25', 1);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;

-- Dumping structure for table 123456789.staff_department
CREATE TABLE IF NOT EXISTS `staff_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `staff_id` bigint(20) NOT NULL DEFAULT '0',
  `department_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_staff_department_staff` (`staff_id`),
  KEY `FK_staff_department_departments` (`department_id`),
  CONSTRAINT `FK_staff_department_departments` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_staff_department_staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.staff_department: ~0 rows (approximately)
DELETE FROM `staff_department`;
/*!40000 ALTER TABLE `staff_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_department` ENABLE KEYS */;

-- Dumping structure for table 123456789.streams
CREATE TABLE IF NOT EXISTS `streams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_streams_users` (`author_id`),
  CONSTRAINT `FK_streams_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Streams';

-- Dumping data for table 123456789.streams: ~0 rows (approximately)
DELETE FROM `streams`;
/*!40000 ALTER TABLE `streams` DISABLE KEYS */;
/*!40000 ALTER TABLE `streams` ENABLE KEYS */;

-- Dumping structure for table 123456789.student_admission
CREATE TABLE IF NOT EXISTS `student_admission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `profile_id` bigint(20) NOT NULL DEFAULT '0',
  `admission_no` varchar(255) NOT NULL,
  `external_id` varchar(255) NOT NULL,
  `date_of_admission` date NOT NULL,
  `term_id` bigint(20) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `stream_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_admission_users` (`author_id`),
  KEY `FK_student_admission_terms` (`term_id`),
  KEY `FK_student_admission_classes` (`class_id`),
  KEY `FK_student_admission_streams` (`stream_id`),
  KEY `profile_id` (`profile_id`),
  CONSTRAINT `FK_student_admission_classes` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_admission_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_admission_streams` FOREIGN KEY (`stream_id`) REFERENCES `streams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_admission_terms` FOREIGN KEY (`term_id`) REFERENCES `terms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_admission_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.student_admission: ~0 rows (approximately)
DELETE FROM `student_admission`;
/*!40000 ALTER TABLE `student_admission` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_admission` ENABLE KEYS */;

-- Dumping structure for table 123456789.student_exam_registration
CREATE TABLE IF NOT EXISTS `student_exam_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `term_registration_id` bigint(20) NOT NULL COMMENT 'Extends Student Term Registration',
  `exam_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_exam_registration_student_term_registration` (`term_registration_id`),
  KEY `FK_student_exam_registration_exams` (`exam_id`),
  KEY `FK_student_exam_registration_users` (`author_id`),
  CONSTRAINT `FK_student_exam_registration_exams` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_exam_registration_student_term_registration` FOREIGN KEY (`term_registration_id`) REFERENCES `student_term_registration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_exam_registration_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Student Exam Registration';

-- Dumping data for table 123456789.student_exam_registration: ~0 rows (approximately)
DELETE FROM `student_exam_registration`;
/*!40000 ALTER TABLE `student_exam_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_exam_registration` ENABLE KEYS */;

-- Dumping structure for table 123456789.student_subject_registration
CREATE TABLE IF NOT EXISTS `student_subject_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_term_registration_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_subject_registration_student_term_registration` (`student_term_registration_id`),
  KEY `FK_student_subject_registration_subjects` (`subject_id`),
  KEY `FK_student_subject_registration_subject_papers` (`paper_id`),
  KEY `FK_student_subject_registration_users` (`author_id`),
  CONSTRAINT `FK_student_subject_registration_student_term_registration` FOREIGN KEY (`student_term_registration_id`) REFERENCES `student_term_registration` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_subject_registration_subject_papers` FOREIGN KEY (`paper_id`) REFERENCES `subject_papers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_subject_registration_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_subject_registration_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.student_subject_registration: ~0 rows (approximately)
DELETE FROM `student_subject_registration`;
/*!40000 ALTER TABLE `student_subject_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_subject_registration` ENABLE KEYS */;

-- Dumping structure for table 123456789.student_term_registration
CREATE TABLE IF NOT EXISTS `student_term_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admission_id` bigint(20) NOT NULL,
  `term_id` bigint(20) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `stream_id` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_term_registration_student_admission` (`admission_id`),
  KEY `FK_student_term_registration_terms` (`term_id`),
  KEY `FK_student_term_registration_streams` (`stream_id`),
  KEY `FK_student_term_registration_users` (`author_id`),
  KEY `FK_student_term_registration_classes` (`class_id`),
  CONSTRAINT `FK_student_term_registration_classes` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_term_registration_streams` FOREIGN KEY (`stream_id`) REFERENCES `streams` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_term_registration_student_admission` FOREIGN KEY (`admission_id`) REFERENCES `student_admission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_term_registration_terms` FOREIGN KEY (`term_id`) REFERENCES `terms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_term_registration_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Student Term Registration';

-- Dumping data for table 123456789.student_term_registration: ~0 rows (approximately)
DELETE FROM `student_term_registration`;
/*!40000 ALTER TABLE `student_term_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_term_registration` ENABLE KEYS */;

-- Dumping structure for table 123456789.study_year
CREATE TABLE IF NOT EXISTS `study_year` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `theme` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_study_year_users` (`author_id`),
  CONSTRAINT `FK_study_year_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.study_year: ~1 rows (approximately)
DELETE FROM `study_year`;
/*!40000 ALTER TABLE `study_year` DISABLE KEYS */;
INSERT INTO `study_year` (`id`, `theme`, `start_date`, `end_date`, `status`, `author_id`, `date_created`) VALUES
	(1, '2018 Study Year', '2018-01-13', '2018-12-13', 'ACTIVE', 1, '2018-03-13 23:02:26');
/*!40000 ALTER TABLE `study_year` ENABLE KEYS */;

-- Dumping structure for table 123456789.study_year_curriculum
CREATE TABLE IF NOT EXISTS `study_year_curriculum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `study_year_id` bigint(20) DEFAULT NULL,
  `curriculum_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_study_year_curriculum_study_year` (`study_year_id`),
  KEY `FK_study_year_curriculum_curriculum` (`curriculum_id`),
  KEY `FK_study_year_curriculum_users` (`author_id`),
  CONSTRAINT `FK_study_year_curriculum_curriculum` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_study_year_curriculum_study_year` FOREIGN KEY (`study_year_id`) REFERENCES `study_year` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_study_year_curriculum_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Study Year Curriculum';

-- Dumping data for table 123456789.study_year_curriculum: ~0 rows (approximately)
DELETE FROM `study_year_curriculum`;
/*!40000 ALTER TABLE `study_year_curriculum` DISABLE KEYS */;
/*!40000 ALTER TABLE `study_year_curriculum` ENABLE KEYS */;

-- Dumping structure for table 123456789.subjects
CREATE TABLE IF NOT EXISTS `subjects` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subjects_users` (`author_id`),
  CONSTRAINT `FK_subjects_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Subjects';

-- Dumping data for table 123456789.subjects: ~1 rows (approximately)
DELETE FROM `subjects`;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` (`id`, `name`, `code`, `status`, `date_created`, `author_id`) VALUES
	(1, 'Mathmatcs', 'MATH', 'ACTIVE', '2018-03-11 09:48:45', 1);
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;

-- Dumping structure for table 123456789.subject_class
CREATE TABLE IF NOT EXISTS `subject_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subject_class_subjects` (`subject_id`),
  KEY `FK_subject_class_classes` (`class_id`),
  KEY `FK_subject_class_users` (`author_id`),
  CONSTRAINT `FK_subject_class_classes` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_class_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_class_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.subject_class: ~0 rows (approximately)
DELETE FROM `subject_class`;
/*!40000 ALTER TABLE `subject_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_class` ENABLE KEYS */;

-- Dumping structure for table 123456789.subject_curriculum
CREATE TABLE IF NOT EXISTS `subject_curriculum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) NOT NULL,
  `curriculum_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subject_curriculum_subjects` (`subject_id`),
  KEY `FK_subject_curriculum_curriculum` (`curriculum_id`),
  KEY `FK_subject_curriculum_users` (`author_id`),
  CONSTRAINT `FK_subject_curriculum_curriculum` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_curriculum_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_curriculum_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.subject_curriculum: ~0 rows (approximately)
DELETE FROM `subject_curriculum`;
/*!40000 ALTER TABLE `subject_curriculum` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_curriculum` ENABLE KEYS */;

-- Dumping structure for table 123456789.subject_grading
CREATE TABLE IF NOT EXISTS `subject_grading` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) NOT NULL DEFAULT '0',
  `grading_id` bigint(20) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subject_grading_subjects` (`subject_id`),
  KEY `FK_subject_grading_grading` (`grading_id`),
  KEY `FK_subject_grading_users` (`author_id`),
  CONSTRAINT `FK_subject_grading_grading` FOREIGN KEY (`grading_id`) REFERENCES `grading` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_grading_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_grading_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Subject Grading';

-- Dumping data for table 123456789.subject_grading: ~0 rows (approximately)
DELETE FROM `subject_grading`;
/*!40000 ALTER TABLE `subject_grading` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_grading` ENABLE KEYS */;

-- Dumping structure for table 123456789.subject_papers
CREATE TABLE IF NOT EXISTS `subject_papers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subject_papers_subjects` (`subject_id`),
  KEY `FK_subject_papers_users` (`author_id`),
  CONSTRAINT `FK_subject_papers_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_papers_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Subject Papers';

-- Dumping data for table 123456789.subject_papers: ~0 rows (approximately)
DELETE FROM `subject_papers`;
/*!40000 ALTER TABLE `subject_papers` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_papers` ENABLE KEYS */;

-- Dumping structure for table 123456789.subject_teachers
CREATE TABLE IF NOT EXISTS `subject_teachers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `teacher_id` bigint(20) NOT NULL DEFAULT '0',
  `subject_id` bigint(20) NOT NULL DEFAULT '0',
  `paper_id` bigint(20) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subject_teachers_users` (`teacher_id`),
  KEY `FK_subject_teachers_subjects` (`subject_id`),
  KEY `FK_subject_teachers_subject_papers` (`paper_id`),
  KEY `FK_subject_teachers_users_2` (`author_id`),
  CONSTRAINT `FK_subject_teachers_subject_papers` FOREIGN KEY (`paper_id`) REFERENCES `subject_papers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_teachers_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_teachers_users` FOREIGN KEY (`teacher_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_teachers_users_2` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Subject Teachers';

-- Dumping data for table 123456789.subject_teachers: ~0 rows (approximately)
DELETE FROM `subject_teachers`;
/*!40000 ALTER TABLE `subject_teachers` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_teachers` ENABLE KEYS */;

-- Dumping structure for table 123456789.teaching_timetable
CREATE TABLE IF NOT EXISTS `teaching_timetable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) NOT NULL DEFAULT '0',
  `paper_id` bigint(20) NOT NULL DEFAULT '0',
  `term_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'this ',
  `teacher_id` bigint(20) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_teaching_timetable_subjects` (`subject_id`),
  KEY `FK_teaching_timetable_subject_papers` (`paper_id`),
  KEY `FK_teaching_timetable_terms` (`term_id`),
  KEY `FK_teaching_timetable_users` (`author_id`),
  CONSTRAINT `FK_teaching_timetable_subject_papers` FOREIGN KEY (`paper_id`) REFERENCES `subject_papers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_teaching_timetable_subjects` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_teaching_timetable_terms` FOREIGN KEY (`term_id`) REFERENCES `terms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_teaching_timetable_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='teaching time table';

-- Dumping data for table 123456789.teaching_timetable: ~0 rows (approximately)
DELETE FROM `teaching_timetable`;
/*!40000 ALTER TABLE `teaching_timetable` DISABLE KEYS */;
/*!40000 ALTER TABLE `teaching_timetable` ENABLE KEYS */;

-- Dumping structure for table 123456789.terms
CREATE TABLE IF NOT EXISTS `terms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `study_year_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `ranking` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_terms_users` (`author_id`),
  KEY `FK_terms_study_year` (`study_year_id`),
  CONSTRAINT `FK_terms_study_year` FOREIGN KEY (`study_year_id`) REFERENCES `study_year` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_terms_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table 123456789.terms: ~1 rows (approximately)
DELETE FROM `terms`;
/*!40000 ALTER TABLE `terms` DISABLE KEYS */;
INSERT INTO `terms` (`id`, `study_year_id`, `name`, `start_date`, `end_date`, `ranking`, `status`, `author_id`, `date_created`) VALUES
	(1, 1, 'Term One', '2018-03-15 20:56:29', '2018-04-15 20:56:34', 1, 'ACTIVE', 1, '2018-03-15 20:56:49');
/*!40000 ALTER TABLE `terms` ENABLE KEYS */;

-- Dumping structure for table 123456789.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','DISABLED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='System Users';

-- Dumping data for table 123456789.users: ~20 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `status`, `date_created`) VALUES
	(1, 'mover', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ACTIVE', '2017-12-21 21:52:39'),
	(4, 'mover', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ACTIVE', '2017-12-22 21:39:36'),
	(5, 'mover', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ACTIVE', '2017-12-22 21:42:38'),
	(6, 'mover', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ACTIVE', '2017-12-22 22:20:23'),
	(7, 'mover', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ACTIVE', '2017-12-22 22:36:44'),
	(8, 'mover', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ACTIVE', '2017-12-22 22:38:14'),
	(9, 'mover', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ACTIVE', '2017-12-22 23:42:59'),
	(10, 'mover', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'ACTIVE', '2017-12-25 01:26:11'),
	(11, 'wewe', 'f7b4653c9b1bdd02319cb1a40b4bdc4002a66257ca29689fe3be11a1a2c49687', 'ACTIVE', '2018-04-21 21:47:10'),
	(12, 'wewe', 'f7b4653c9b1bdd02319cb1a40b4bdc4002a66257ca29689fe3be11a1a2c49687', 'ACTIVE', '2018-04-21 21:49:36'),
	(13, 'etaeaea', '65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5', 'ACTIVE', '2018-04-21 22:05:00'),
	(14, 'addad', '', 'ACTIVE', '2018-04-24 07:58:04'),
	(15, 'klop', '', 'ACTIVE', '2018-04-24 07:59:07'),
	(16, 'tyu', 'd29448b6dfbe23536588222a91506aebb8c4744d0c3b7ee57d6ddbdfdb754f62', 'ACTIVE', '2018-04-25 22:36:58'),
	(17, 'koolwt', '74c6c2ecfaaad19969bcab6c15d5ebce8fe7a8ac639c590530ed6a8cb33e1e7d', 'ACTIVE', '2018-04-25 23:13:25'),
	(18, 'benae', '74c6c2ecfaaad19969bcab6c15d5ebce8fe7a8ac639c590530ed6a8cb33e1e7d', 'ACTIVE', '2018-04-25 23:21:22'),
	(19, 'qwerty', 'ebf1cce064da7d55332ebc443a3598172344057d32977862aac173131f366dfe', 'ACTIVE', '2018-04-26 02:44:32'),
	(20, 'vumbi', 'a85e754d06f19233a9086030a6a4a421321bdede57d324273a4164a412ff01dd', 'ACTIVE', '2018-04-26 20:18:37'),
	(21, 'koslo', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'ACTIVE', '2018-04-26 20:25:05'),
	(22, 'misse', '614b2577afd9582f79a2ad6d347e262f0964a416330aa7133f8dd6cb393b5017', 'ACTIVE', '2018-04-28 23:52:23');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table 123456789.user_profile
CREATE TABLE IF NOT EXISTS `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT '0',
  `profile_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_user_profile_users` (`user_id`),
  KEY `FK_user_profile_profile` (`profile_id`),
  CONSTRAINT `FK_user_profile_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_profile_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='Merges User to a Given Profile';

-- Dumping data for table 123456789.user_profile: ~7 rows (approximately)
DELETE FROM `user_profile`;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` (`id`, `user_id`, `profile_id`) VALUES
	(1, 1, 1),
	(2, 17, 7),
	(3, 18, 8),
	(4, 19, 9),
	(5, 20, 10),
	(6, 21, 11),
	(7, 22, 12);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;

-- Dumping structure for table 123456789.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_role_users` (`user_id`),
  KEY `FK_user_role_roles` (`role_id`),
  CONSTRAINT `FK_user_role_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_role_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='User Role';

-- Dumping data for table 123456789.user_role: ~15 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1),
	(2, 9, 1),
	(3, 10, 1),
	(4, 11, 1),
	(5, 12, 1),
	(6, 13, 1),
	(7, 14, 1),
	(8, 15, 1),
	(9, 16, 1),
	(10, 17, 1),
	(11, 18, 1),
	(12, 19, 1),
	(13, 20, 1),
	(14, 21, 1),
	(15, 22, 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
