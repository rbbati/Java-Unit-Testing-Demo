CREATE DATABASE  IF NOT EXISTS `java_unit_testing`;

USE `java_unit_testing`;

DROP TABLE IF EXISTS `java_grade`;
DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `student` VALUES
	(1,'David','Adams', DATE('2000-11-25')),
	(2,'John','Doe',DATE('1998-01-13')),
	(3,'Ajay','Rao',DATE('2001-10-01')),
	(4,'Mary','Public',DATE('1999-11-01')),
	(5,'Maxwell','Dixon',DATE('2002-09-29'));

CREATE TABLE `java_grade` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(11) DEFAULT NULL,
  `grade` double(5, 2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`student_id`) REFERENCES student(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `java_grade` VALUES
	(1, 1, 80),
	(2, 1, 90),
	(3, 1, 72),
    (4, 2, 85);