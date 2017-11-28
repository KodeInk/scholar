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


-- Dumping database structure for scholar-engine
CREATE DATABASE IF NOT EXISTS `scholar-engine` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `scholar-engine`;

-- Dumping structure for table scholar-engine.addresses
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

-- Dumping data for table scholar-engine.addresses: ~0 rows (approximately)
DELETE FROM `addresses`;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.books
CREATE TABLE IF NOT EXISTS `books` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `book_author` varchar(255) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.books: ~0 rows (approximately)
DELETE FROM `books`;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.book_category
CREATE TABLE IF NOT EXISTS `book_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.book_category: ~0 rows (approximately)
DELETE FROM `book_category`;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.book_type
CREATE TABLE IF NOT EXISTS `book_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.book_type: ~0 rows (approximately)
DELETE FROM `book_type`;
/*!40000 ALTER TABLE `book_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_type` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.classes
CREATE TABLE IF NOT EXISTS `classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `ranking` bigint(20) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Classes';

-- Dumping data for table scholar-engine.classes: ~0 rows (approximately)
DELETE FROM `classes`;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.class_stream
CREATE TABLE IF NOT EXISTS `class_stream` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` bigint(20) DEFAULT NULL,
  `stream_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.class_stream: ~0 rows (approximately)
DELETE FROM `class_stream`;
/*!40000 ALTER TABLE `class_stream` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_stream` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.contacts
CREATE TABLE IF NOT EXISTS `contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_type` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `contact_type` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.contacts: ~0 rows (approximately)
DELETE FROM `contacts`;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.curriculum
CREATE TABLE IF NOT EXISTS `curriculum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '0',
  `code` varchar(255) DEFAULT '0',
  `description` varchar(500) DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Curriculum';

-- Dumping data for table scholar-engine.curriculum: ~0 rows (approximately)
DELETE FROM `curriculum`;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.curriculum_details
CREATE TABLE IF NOT EXISTS `curriculum_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `curriculum_id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.curriculum_details: ~0 rows (approximately)
DELETE FROM `curriculum_details`;
/*!40000 ALTER TABLE `curriculum_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `curriculum_details` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.exams
CREATE TABLE IF NOT EXISTS `exams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `%contribution` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Exams';

-- Dumping data for table scholar-engine.exams: ~0 rows (approximately)
DELETE FROM `exams`;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.exam_class
CREATE TABLE IF NOT EXISTS `exam_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.exam_class: ~0 rows (approximately)
DELETE FROM `exam_class`;
/*!40000 ALTER TABLE `exam_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_class` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.exam_tem
CREATE TABLE IF NOT EXISTS `exam_tem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) DEFAULT NULL,
  `term_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.exam_tem: ~0 rows (approximately)
DELETE FROM `exam_tem`;
/*!40000 ALTER TABLE `exam_tem` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_tem` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.exam_timetable
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.exam_timetable: ~0 rows (approximately)
DELETE FROM `exam_timetable`;
/*!40000 ALTER TABLE `exam_timetable` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_timetable` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.grading
CREATE TABLE IF NOT EXISTS `grading` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Grading';

-- Dumping data for table scholar-engine.grading: ~0 rows (approximately)
DELETE FROM `grading`;
/*!40000 ALTER TABLE `grading` DISABLE KEYS */;
/*!40000 ALTER TABLE `grading` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.grading_details
CREATE TABLE IF NOT EXISTS `grading_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grading_id` bigint(20) NOT NULL DEFAULT '0',
  `grading_scale` bigint(20) NOT NULL DEFAULT '0',
  `symbol` varchar(50) NOT NULL DEFAULT '0',
  `mingrade` bigint(20) NOT NULL DEFAULT '0',
  `maxgrade` bigint(20) NOT NULL DEFAULT '0',
  `value` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Grading Details';

-- Dumping data for table scholar-engine.grading_details: ~0 rows (approximately)
DELETE FROM `grading_details`;
/*!40000 ALTER TABLE `grading_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `grading_details` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.library_section
CREATE TABLE IF NOT EXISTS `library_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `external_id` varchar(255) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.library_section: ~0 rows (approximately)
DELETE FROM `library_section`;
/*!40000 ALTER TABLE `library_section` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_section` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.library_stock
CREATE TABLE IF NOT EXISTS `library_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_number` varchar(255) NOT NULL,
  `stock_title` varchar(255) NOT NULL,
  `number_of_books` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Library Stock';

-- Dumping data for table scholar-engine.library_stock: ~0 rows (approximately)
DELETE FROM `library_stock`;
/*!40000 ALTER TABLE `library_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_stock` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.library_stock_inventory
CREATE TABLE IF NOT EXISTS `library_stock_inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `isbnumber` varchar(255) NOT NULL,
  `serialnumber` varchar(255) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Library Stock Inventory';

-- Dumping data for table scholar-engine.library_stock_inventory: ~0 rows (approximately)
DELETE FROM `library_stock_inventory`;
/*!40000 ALTER TABLE `library_stock_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_stock_inventory` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.library_transactions
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Library Transactions';

-- Dumping data for table scholar-engine.library_transactions: ~0 rows (approximately)
DELETE FROM `library_transactions`;
/*!40000 ALTER TABLE `library_transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_transactions` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.permissions
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.permissions: ~0 rows (approximately)
DELETE FROM `permissions`;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.profile
CREATE TABLE IF NOT EXISTS `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT '0',
  `middle_name` varchar(255) DEFAULT '0',
  `last_name` varchar(255) DEFAULT '0',
  `prefix` enum('MR','MISS','MRS','OTHER') DEFAULT 'OTHER',
  `date_of_birth` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `parent_type` enum('STUDENT','USER') DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Person Details';

-- Dumping data for table scholar-engine.profile: ~0 rows (approximately)
DELETE FROM `profile`;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '0',
  `isSystem` tinyint(4) NOT NULL DEFAULT '0',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Manage Roles';

-- Dumping data for table scholar-engine.roles: ~0 rows (approximately)
DELETE FROM `roles`;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.role_permission
CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.role_permission: ~0 rows (approximately)
DELETE FROM `role_permission`;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.streams
CREATE TABLE IF NOT EXISTS `streams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `code` varchar(255) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Streams';

-- Dumping data for table scholar-engine.streams: ~0 rows (approximately)
DELETE FROM `streams`;
/*!40000 ALTER TABLE `streams` DISABLE KEYS */;
/*!40000 ALTER TABLE `streams` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.student_admission
CREATE TABLE IF NOT EXISTS `student_admission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL COMMENT 'extends Profile table where parent type is student',
  `admission_no` varchar(255) NOT NULL,
  `external_id` varchar(255) NOT NULL,
  `date_of_admission` date NOT NULL,
  `term_id` bigint(20) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `stream_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.student_admission: ~0 rows (approximately)
DELETE FROM `student_admission`;
/*!40000 ALTER TABLE `student_admission` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_admission` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.student_exam_registration
CREATE TABLE IF NOT EXISTS `student_exam_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `term_registration_id` bigint(20) NOT NULL COMMENT 'Extends Student Term Registration',
  `exam_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Student Exam Registration';

-- Dumping data for table scholar-engine.student_exam_registration: ~0 rows (approximately)
DELETE FROM `student_exam_registration`;
/*!40000 ALTER TABLE `student_exam_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_exam_registration` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.student_subject_registration
CREATE TABLE IF NOT EXISTS `student_subject_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_term_registration_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.student_subject_registration: ~0 rows (approximately)
DELETE FROM `student_subject_registration`;
/*!40000 ALTER TABLE `student_subject_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_subject_registration` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.student_term_registration
CREATE TABLE IF NOT EXISTS `student_term_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admission_id` bigint(20) NOT NULL,
  `term_id` bigint(20) NOT NULL,
  `stream_id` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Student Term Registration';

-- Dumping data for table scholar-engine.student_term_registration: ~0 rows (approximately)
DELETE FROM `student_term_registration`;
/*!40000 ALTER TABLE `student_term_registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_term_registration` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.study_year
CREATE TABLE IF NOT EXISTS `study_year` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `theme` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `author_id` bigint(20) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.study_year: ~0 rows (approximately)
DELETE FROM `study_year`;
/*!40000 ALTER TABLE `study_year` DISABLE KEYS */;
/*!40000 ALTER TABLE `study_year` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.study_year_curriculum
CREATE TABLE IF NOT EXISTS `study_year_curriculum` (
  `study_year_id` bigint(20) DEFAULT NULL,
  `curriculum_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Study Year Curriculum';

-- Dumping data for table scholar-engine.study_year_curriculum: ~0 rows (approximately)
DELETE FROM `study_year_curriculum`;
/*!40000 ALTER TABLE `study_year_curriculum` DISABLE KEYS */;
/*!40000 ALTER TABLE `study_year_curriculum` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.subjects
CREATE TABLE IF NOT EXISTS `subjects` (
  `id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Subjects';

-- Dumping data for table scholar-engine.subjects: ~0 rows (approximately)
DELETE FROM `subjects`;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.subject_class
CREATE TABLE IF NOT EXISTS `subject_class` (
  `subject_id` bigint(20) DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.subject_class: ~0 rows (approximately)
DELETE FROM `subject_class`;
/*!40000 ALTER TABLE `subject_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_class` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.subject_curriculum
CREATE TABLE IF NOT EXISTS `subject_curriculum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) NOT NULL,
  `curriculum_id` bigint(20) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.subject_curriculum: ~0 rows (approximately)
DELETE FROM `subject_curriculum`;
/*!40000 ALTER TABLE `subject_curriculum` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_curriculum` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.subject_grading
CREATE TABLE IF NOT EXISTS `subject_grading` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) NOT NULL DEFAULT '0',
  `grading_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Subject Grading';

-- Dumping data for table scholar-engine.subject_grading: ~0 rows (approximately)
DELETE FROM `subject_grading`;
/*!40000 ALTER TABLE `subject_grading` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_grading` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.subject_papers
CREATE TABLE IF NOT EXISTS `subject_papers` (
  `id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Subject Papers';

-- Dumping data for table scholar-engine.subject_papers: ~0 rows (approximately)
DELETE FROM `subject_papers`;
/*!40000 ALTER TABLE `subject_papers` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_papers` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.subject_teachers
CREATE TABLE IF NOT EXISTS `subject_teachers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `subject_id` bigint(20) NOT NULL DEFAULT '0',
  `paper_id` bigint(20) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Subject Teachers';

-- Dumping data for table scholar-engine.subject_teachers: ~0 rows (approximately)
DELETE FROM `subject_teachers`;
/*!40000 ALTER TABLE `subject_teachers` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_teachers` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.teaching_timetable
CREATE TABLE IF NOT EXISTS `teaching_timetable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) NOT NULL DEFAULT '0',
  `paper_id` bigint(20) NOT NULL DEFAULT '0',
  `term_id` bigint(20) NOT NULL DEFAULT '0',
  `teacher_id` bigint(20) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='teaching time table';

-- Dumping data for table scholar-engine.teaching_timetable: ~0 rows (approximately)
DELETE FROM `teaching_timetable`;
/*!40000 ALTER TABLE `teaching_timetable` DISABLE KEYS */;
/*!40000 ALTER TABLE `teaching_timetable` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.terms
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table scholar-engine.terms: ~0 rows (approximately)
DELETE FROM `terms`;
/*!40000 ALTER TABLE `terms` DISABLE KEYS */;
/*!40000 ALTER TABLE `terms` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','DISABLED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='System Users';

-- Dumping data for table scholar-engine.users: ~0 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table scholar-engine.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='User Role';

-- Dumping data for table scholar-engine.user_role: ~0 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
