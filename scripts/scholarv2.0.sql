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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Classes';

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Curriculum';

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Grading';

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
-- Dumping structure for table 123456789.permissions
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table 123456789.profile
CREATE TABLE IF NOT EXISTS `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT '0',
  `middle_name` varchar(255) DEFAULT '0',
  `last_name` varchar(255) DEFAULT '0',
  `status` enum('ACTIVE','ARCHIVED') DEFAULT 'ACTIVE',
  `prefix` enum('MR','MIS','MRS','OTHER') DEFAULT 'OTHER',
  `sex` enum('MALE','FEMALE','OTHER') DEFAULT 'OTHER',
  `date_of_birth` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `parent_type` enum('STUDENT','USER') DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_profile_users` (`author_id`),
  CONSTRAINT `FK_profile_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='Person Details';

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Manage Roles';

-- Data exporting was unselected.
-- Dumping structure for table 123456789.role_permission
CREATE TABLE IF NOT EXISTS `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_role_permission_roles` (`role_id`),
  KEY `FK_role_permission_permissions` (`permission_id`),
  CONSTRAINT `FK_role_permission_permissions` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_role_permission_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='Staff';

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
-- Dumping structure for table 123456789.student_admission
CREATE TABLE IF NOT EXISTS `student_admission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `profile_id` bigint(20) NOT NULL DEFAULT '0',
  `admission_no` varchar(255) NOT NULL,
  `external_id` varchar(255) NOT NULL,
  `date_of_admission` date NOT NULL,
  `term_id` bigint(20) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `stream_id` bigint(20) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
-- Dumping structure for table 123456789.student_term_registration
CREATE TABLE IF NOT EXISTS `student_term_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admission_id` bigint(20) NOT NULL,
  `term_id` bigint(20) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `stream_id` bigint(20) DEFAULT NULL,
  `date_registered` datetime NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Student Term Registration';

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
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

-- Data exporting was unselected.
-- Dumping structure for table 123456789.subjects
CREATE TABLE IF NOT EXISTS `subjects` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `status` enum('ACTIVE','ARCHIVED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subjects_users` (`author_id`),
  CONSTRAINT `FK_subjects_users` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Subjects';

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table 123456789.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL DEFAULT '0',
  `status` enum('ACTIVE','DISABLED') NOT NULL DEFAULT 'ACTIVE',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='System Users';

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Merges User to a Given Profile';

-- Data exporting was unselected.
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='User Role';

-- Data exporting was unselected.
-- Dumping structure for table 123456789.user_staff
CREATE TABLE IF NOT EXISTS `user_staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `staff_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_user_staff_users` (`user_id`),
  KEY `FK_user_staff_staff` (`staff_id`),
  CONSTRAINT `FK_user_staff_staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_staff_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
