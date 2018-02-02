/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.24 : Database - carservice
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`carservice` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `carservice`;

/*Table structure for table `applicationuser` */

DROP TABLE IF EXISTS `applicationuser`;

CREATE TABLE `applicationuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` datetime NOT NULL,
  `isactive` bit(1) NOT NULL,
  `loginattempts` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `createby_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3j5p3pl1p7fkmdvj9kv0w8ijr` (`createby_id`),
  KEY `FK_fh1o5t3h2cdo1oq246l7puwa5` (`employee_id`),
  CONSTRAINT `FK_3j5p3pl1p7fkmdvj9kv0w8ijr` FOREIGN KEY (`createby_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK_fh1o5t3h2cdo1oq246l7puwa5` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `applicationuser` */

insert  into `applicationuser`(`id`,`createdate`,`isactive`,`loginattempts`,`password`,`username`,`createby_id`,`employee_id`) values (1,'2018-01-28 01:22:39','',5,'sharan','sharan',1,1),(2,'2018-01-28 01:22:40','',5,'amaresh','amaresh',1,2),(3,'2018-01-28 01:22:40','',5,'suresh','suresh',1,3);

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contenttype` varchar(255) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `attachmentcontent_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_949h28s3rt7p336e9hcpj9uam` (`attachmentcontent_id`),
  CONSTRAINT `FK_949h28s3rt7p336e9hcpj9uam` FOREIGN KEY (`attachmentcontent_id`) REFERENCES `attachmentcontent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `attachment` */

/*Table structure for table `attachmentcontent` */

DROP TABLE IF EXISTS `attachmentcontent`;

CREATE TABLE `attachmentcontent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contentblob` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `attachmentcontent` */

/*Table structure for table `carmake` */

DROP TABLE IF EXISTS `carmake`;

CREATE TABLE `carmake` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `carmake` */

insert  into `carmake`(`id`,`code`,`name`) values (1,'BNZ','Benz'),(2,'SUZUKI','Suzuki'),(3,'HYUNDAI','Hyundai');

/*Table structure for table `carmodel` */

DROP TABLE IF EXISTS `carmodel`;

CREATE TABLE `carmodel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `carmake_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_94yel58y7n7i8lb3vjelu86sp` (`carmake_id`),
  CONSTRAINT `FK_94yel58y7n7i8lb3vjelu86sp` FOREIGN KEY (`carmake_id`) REFERENCES `carmake` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `carmodel` */

insert  into `carmodel`(`id`,`name`,`carmake_id`) values (1,'2017',1),(2,'2017',2),(3,'2017',3);

/*Table structure for table `checknomenclature` */

DROP TABLE IF EXISTS `checknomenclature`;

CREATE TABLE `checknomenclature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ischeck` bit(1) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `inventory_id` bigint(20) NOT NULL,
  `nomenclature_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2omi8264v4mq6ynbsmo5nsh5y` (`inventory_id`),
  KEY `FK_it01rkaomss1jn31y9bo6unt` (`nomenclature_id`),
  CONSTRAINT `FK_2omi8264v4mq6ynbsmo5nsh5y` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FK_it01rkaomss1jn31y9bo6unt` FOREIGN KEY (`nomenclature_id`) REFERENCES `nomenclature` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `checknomenclature` */

insert  into `checknomenclature`(`id`,`ischeck`,`remarks`,`inventory_id`,`nomenclature_id`) values (1,'','Remarks 5',1,4),(2,'','Remarks 1',1,1),(3,'','Remarks 4',1,8),(4,'','Remarks 2',1,2),(5,'','Remarks 3',1,5),(6,'','Remarks 6',1,7);

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ogqc1b0omhdvgo6vojoj95hv7` (`state_id`),
  CONSTRAINT `FK_ogqc1b0omhdvgo6vojoj95hv7` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `city` */

insert  into `city`(`id`,`code`,`description`,`state_id`) values (1,'BNG','Bangalore',1),(2,'MY','Mysore',1),(3,'HYD','Hyderabad',2),(4,'CHE','Chennai',3),(5,'DHL','Delhi',2);

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `country` */

insert  into `country`(`id`,`code`,`description`) values (1,'IND','India'),(2,'USA','United State America'),(3,'AUS','Australia'),(4,'UK','United Kingdom'),(5,'NIC','Nicaragua'),(6,'PHI','Phillipnes');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `emailid` varchar(255) DEFAULT NULL,
  `idproof` varchar(255) NOT NULL,
  `idproofno` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phoneno` varchar(255) NOT NULL,
  `pincode` varchar(255) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jlc87hfhrhqpq4pc1my9447wf` (`city_id`),
  CONSTRAINT `FK_jlc87hfhrhqpq4pc1my9447wf` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `customer` */

insert  into `customer`(`id`,`address`,`emailid`,`idproof`,`idproofno`,`name`,`phoneno`,`pincode`,`city_id`) values (1,'Bangalore','sharan@sharan.com','pan','111000','sharan','9900990099','560001',1),(2,'Bangalore','amaresh@amaresh.com','pan','111000','amaresh','9900990099','560001',2),(3,'Bangalore','suresh@suresh.com','pan','111000','suresh','9900990099','560001',3),(4,'Bangalore','devu@devu.com','pan','111000','devu','9900990099','560001',5);

/*Table structure for table `customerdemandedrepair` */

DROP TABLE IF EXISTS `customerdemandedrepair`;

CREATE TABLE `customerdemandedrepair` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `vehcustregtion_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pab54moo7q2kgtbuopaa6v65j` (`vehcustregtion_id`),
  CONSTRAINT `FK_pab54moo7q2kgtbuopaa6v65j` FOREIGN KEY (`vehcustregtion_id`) REFERENCES `vehcustregtion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customerdemandedrepair` */

/*Table structure for table `designation` */

DROP TABLE IF EXISTS `designation`;

CREATE TABLE `designation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `designation` */

insert  into `designation`(`id`,`code`,`description`) values (1,'TECHNICIAN','Technician'),(2,'SPARE_MANAGER','Spare Manager'),(3,'ADVISOR','Advisor'),(4,'ADMIN','admin'),(5,'HR','Human Recruitment');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `applicationid` varchar(255) NOT NULL,
  `dob` datetime NOT NULL,
  `emailid` varchar(255) DEFAULT NULL,
  `gardianname` varchar(255) NOT NULL,
  `idproof` varchar(255) NOT NULL,
  `idproofno` varchar(255) NOT NULL,
  `isactive` bit(1) NOT NULL,
  `joingdate` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `officeemailid` varchar(255) DEFAULT NULL,
  `officephoneno` varchar(255) DEFAULT NULL,
  `permanentaddress` varchar(255) NOT NULL,
  `permanentpincode` varchar(255) NOT NULL,
  `phoneno` varchar(255) NOT NULL,
  `pincode` varchar(255) NOT NULL,
  `tittle` varchar(255) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `designation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d7qxnluxvwgqihmldw2fqoqg8` (`city_id`),
  KEY `FK_fvanju2gyowte98s1drrw2g2s` (`designation_id`),
  CONSTRAINT `FK_d7qxnluxvwgqihmldw2fqoqg8` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FK_fvanju2gyowte98s1drrw2g2s` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `employee` */

insert  into `employee`(`id`,`address`,`applicationid`,`dob`,`emailid`,`gardianname`,`idproof`,`idproofno`,`isactive`,`joingdate`,`name`,`officeemailid`,`officephoneno`,`permanentaddress`,`permanentpincode`,`phoneno`,`pincode`,`tittle`,`city_id`,`designation_id`) values (1,'Bangalore','0001','2018-01-28 01:22:04','sharan@sharan.com','M','pan','111000','','2018-01-28 01:22:05','sharan',NULL,NULL,'Gulbarga','585325','9900990099','560001','Mr.',1,1),(2,'Bangalore','0002','2018-01-28 01:22:13','sharan@sharan.com','M','pan','111000','','2018-01-28 01:22:14','amaresh',NULL,NULL,'Gulbarga','585325','9900990099','560001','Mr.',1,1),(3,'Bangalore','0003','2018-01-28 01:22:19','sharan@sharan.com','M','pan','111000','','2018-01-28 01:22:19','Suresh',NULL,NULL,'Gulbarga','585325','9900990099','560001','Mr.',1,1);

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batterymake` varchar(255) DEFAULT NULL,
  `batteryno` varchar(255) DEFAULT NULL,
  `fuelguage` varchar(255) DEFAULT NULL,
  `kmreading` varchar(255) DEFAULT NULL,
  `total` decimal(19,2) NOT NULL,
  `tyremake` varchar(255) DEFAULT NULL,
  `cardamage_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kfpbtk0ba5xxovh4se01vpsr0` (`cardamage_id`),
  CONSTRAINT `FK_kfpbtk0ba5xxovh4se01vpsr0` FOREIGN KEY (`cardamage_id`) REFERENCES `attachment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `inventory` */

insert  into `inventory`(`id`,`batterymake`,`batteryno`,`fuelguage`,`kmreading`,`total`,`tyremake`,`cardamage_id`) values (1,'CEATE','0001','30','1001','1200.00','CE',NULL);

/*Table structure for table `inventoryitem` */

DROP TABLE IF EXISTS `inventoryitem`;

CREATE TABLE `inventoryitem` (
  `inventory_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  PRIMARY KEY (`inventory_id`,`item_id`),
  KEY `FK_a2m2d9ngnaxvby4dkid0ivoqd` (`item_id`),
  CONSTRAINT `FK_6388rm8svnfmeyw8trxpyl14b` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FK_a2m2d9ngnaxvby4dkid0ivoqd` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `inventoryitem` */

insert  into `inventoryitem`(`inventory_id`,`item_id`) values (1,1),(1,2),(1,3),(1,4);

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `item` */

insert  into `item`(`id`,`code`,`name`) values (1,'keyChain','keyChain'),(2,'idol','idol'),(3,'remote','remote'),(4,'antenna','antenna');

/*Table structure for table `nomenclature` */

DROP TABLE IF EXISTS `nomenclature`;

CREATE TABLE `nomenclature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkTypeenum` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `nomenclature` */

insert  into `nomenclature`(`id`,`checkTypeenum`,`code`,`name`) values (1,'INSIDE_HOOD','bonnetLock','Nonnet Lock'),(2,'INSIDE_HOOD','engineOil','Engine Oil'),(3,'INSIDE_CAR','clutchPedalPlay','Clutch Pedal Play'),(4,'INSIDE_CAR','parkingBrake','Parking Brake'),(5,'OUTSIDE_CAR','frontWiper','Front Wiper'),(6,'OUTSIDE_CAR','rearWiper','Rear Wiper'),(7,'UNDER_BODY','brake','Brake'),(8,'UNDER_BODY','suspension','Suspension');

/*Table structure for table `parameter` */

DROP TABLE IF EXISTS `parameter`;

CREATE TABLE `parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `applicationid` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `parameter` */

/*Table structure for table `primaryapprovelestimation` */

DROP TABLE IF EXISTS `primaryapprovelestimation`;

CREATE TABLE `primaryapprovelestimation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `labour` decimal(19,2) NOT NULL,
  `material` decimal(19,2) NOT NULL,
  `total` decimal(19,2) NOT NULL,
  `inventory_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_13cq6uqyo558q9b2clvgby64x` (`inventory_id`),
  CONSTRAINT `FK_13cq6uqyo558q9b2clvgby64x` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `primaryapprovelestimation` */

insert  into `primaryapprovelestimation`(`id`,`description`,`labour`,`material`,`total`,`inventory_id`) values (1,'desc3','200.00','1400.00','1600.00',1),(2,'desc2','200.00','1200.00','1400.00',1),(3,'desc1','200.00','1000.00','1200.00',1);

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lxoqjm8644epv72af3k3jpalx` (`country_id`),
  CONSTRAINT `FK_lxoqjm8644epv72af3k3jpalx` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `state` */

insert  into `state`(`id`,`code`,`description`,`country_id`) values (1,'KA','Karnataka',1),(2,'AP','Andra Pradesh',1),(3,'TN','Tamilnaadu',1),(4,'KL','Kerala',1);

/*Table structure for table `vehcustregtion` */

DROP TABLE IF EXISTS `vehcustregtion`;

CREATE TABLE `vehcustregtion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `currentdatetime` datetime NOT NULL,
  `deliverydatetime` datetime DEFAULT NULL,
  `distancereading` bigint(20) NOT NULL,
  `jobcardno` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL,
  `customersignature_id` bigint(20) DEFAULT NULL,
  `inventory_id` bigint(20) DEFAULT NULL,
  `vehicle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i7gv49mri6b4vr4wi6ow804c9` (`customer_id`),
  KEY `FK_7ur936yjw2fkyw2ovr6wh86n9` (`customersignature_id`),
  KEY `FK_cqm2nt7qugh96ks175ypr00vk` (`inventory_id`),
  KEY `FK_mf5i5p91j22f4nimn2472u09c` (`vehicle_id`),
  CONSTRAINT `FK_7ur936yjw2fkyw2ovr6wh86n9` FOREIGN KEY (`customersignature_id`) REFERENCES `attachment` (`id`),
  CONSTRAINT `FK_cqm2nt7qugh96ks175ypr00vk` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FK_i7gv49mri6b4vr4wi6ow804c9` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_mf5i5p91j22f4nimn2472u09c` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `vehcustregtion` */

insert  into `vehcustregtion`(`id`,`currentdatetime`,`deliverydatetime`,`distancereading`,`jobcardno`,`customer_id`,`customersignature_id`,`inventory_id`,`vehicle_id`) values (1,'2018-01-30 16:17:45','2018-01-30 16:17:45',1111,'0001',1,NULL,1,1);

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chessisno` varchar(255) DEFAULT NULL,
  `engineno` varchar(255) DEFAULT NULL,
  `fuel` varchar(255) DEFAULT NULL,
  `registerno` varchar(255) NOT NULL,
  `carmodel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mnojx0oyprsrd0yojnblel51x` (`carmodel_id`),
  CONSTRAINT `FK_mnojx0oyprsrd0yojnblel51x` FOREIGN KEY (`carmodel_id`) REFERENCES `carmodel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `vehicle` */

insert  into `vehicle`(`id`,`chessisno`,`engineno`,`fuel`,`registerno`,`carmodel_id`) values (1,'0011','0001','30','KA01A0001',1),(2,'0012','0002','40','KA01A0002',2),(3,'0013','0003','10','KA01A0003',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
