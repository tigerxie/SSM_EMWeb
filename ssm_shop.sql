/*
SQLyog Ultimate v8.32 
MySQL - 5.5.20 : Database - ssm_shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm_shop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `ssm_shop`;

/*Table structure for table `adminuser` */

DROP TABLE IF EXISTS `adminuser`;

CREATE TABLE `adminuser` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `adminuser` */

insert  into `adminuser`(`uid`,`username`,`password`) values (2,'admin','admin');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`cid`,`cname`) values (1,'蔬菜系列'),(2,'肉类系列'),(3,'豆蛋奶类'),(4,'水果系列'),(5,'干果系列'),(6,'谷署杂粮'),(7,'食用油系列'),(20,'茶系列');

/*Table structure for table `categorysecond` */

DROP TABLE IF EXISTS `categorysecond`;

CREATE TABLE `categorysecond` (
  `csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`csid`),
  KEY `FK936FCAF21DB1FD15` (`cid`),
  CONSTRAINT `FK936FCAF21DB1FD15` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

/*Data for the table `categorysecond` */

insert  into `categorysecond`(`csid`,`csname`,`cid`) values (1,'无公害蔬菜',1),(2,'特菜类',1),(3,'有机蔬菜',1),(4,'猪肉',2),(5,'牛羊肉',2),(6,'家禽',2),(7,'鱼',2),(9,'干制坚果',5),(10,'干制坚果/果肉',5),(26,'干制果仁',5),(65,'豆制品',3),(66,'蛋',3),(67,'奶',3),(68,'国产',4),(69,'进口',4),(70,'米类',6),(71,'杂粮',6),(72,'面粉',6),(73,'薯类',6),(74,'茶油',7),(75,'核桃油',7),(76,'橄榄油',7),(77,'芥花籽油',7),(78,'玉米油',7),(79,'葡萄籽油',7),(80,'绿茶',20),(81,'红茶',20),(82,'乌龙茶',20),(83,'白茶',20);

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `FKE8B2AB6166C01961` (`oid`),
  KEY `FKE8B2AB6171DB7AE4` (`pid`),
  KEY `FKE8B2AB6140ACF87A` (`oid`),
  CONSTRAINT `FKE8B2AB6140ACF87A` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `FKE8B2AB6171DB7AE4` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

/*Data for the table `orderitem` */

insert  into `orderitem`(`itemid`,`count`,`subtotal`,`pid`,`oid`) values (67,1,25,89,9049),(68,1,25,89,9050),(69,1,8.98,51,9051),(70,1,2.78,68,9052),(71,3,26.94,51,9053),(72,1,2.48,2,9054),(73,1,5.38,67,9055),(74,1,2.78,68,9055),(75,1,2.58,60,9055),(76,1,2.48,2,9056),(77,1,2.78,68,9057),(78,1,666,84,9058),(79,1,666,84,9059),(80,1,444,83,9060),(81,1,355,80,9061),(82,1,444,83,9062),(83,1,25,89,9063),(84,1,666,84,9064),(85,1,444,83,9065),(86,1,355,80,9066),(87,1,666,84,9067),(88,1,666,84,9068),(89,1,444,83,9069),(90,1,666,84,9070),(91,1,666,84,9071),(92,1,15,82,9072),(93,1,2.78,68,9073),(94,1,8.98,51,9075),(95,1,2.78,68,9075),(96,1,5.38,67,9075),(97,1,2.48,2,9075),(98,1,8.98,51,9076),(99,2,5.56,68,9076),(100,1,1.68,90,9077),(101,1,15,82,9078),(102,1,8.98,51,9079),(103,1,1.68,90,9080),(104,1,2.78,68,9081),(105,1,8.98,51,9082),(106,1,2.78,68,9082),(107,1,2.58,60,9083),(108,1,8.98,51,9084),(109,3,5.04,90,9085),(110,1,2.78,68,9085);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FKC3DF62E5AA3D9C7` (`uid`),
  CONSTRAINT `FKC3DF62E5AA3D9C7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9086 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`name`,`phone`,`addr`,`uid`) values (9049,25,'2016-03-07 22:41:55',1,'111','1111111','1',15),(9050,25,'2016-03-07 22:42:05',2,NULL,NULL,NULL,15),(9051,8.98,'2016-03-07 22:42:12',2,NULL,NULL,NULL,15),(9052,2.78,'2016-03-07 22:42:16',2,NULL,NULL,NULL,15),(9053,26.94,'2016-03-07 22:42:22',2,NULL,NULL,NULL,15),(9054,2.48,'2016-03-07 22:42:27',3,NULL,NULL,NULL,15),(9055,10.74,'2016-03-07 22:42:37',4,NULL,NULL,NULL,15),(9056,2.48,'2016-03-07 22:42:42',3,NULL,NULL,NULL,15),(9057,2.78,'2016-03-07 22:42:46',2,NULL,NULL,NULL,15),(9058,666,'2016-03-07 22:42:51',2,NULL,NULL,NULL,15),(9059,666,'2016-03-07 22:42:55',4,NULL,NULL,NULL,15),(9060,444,'2016-03-07 22:43:00',2,NULL,NULL,NULL,15),(9061,355,'2016-03-07 22:43:04',3,NULL,NULL,NULL,15),(9062,444,'2016-03-07 22:43:09',4,NULL,NULL,NULL,15),(9063,25,'2016-03-07 22:43:55',4,NULL,NULL,NULL,15),(9064,666,'2016-03-07 22:46:11',1,NULL,NULL,NULL,15),(9065,444,'2016-03-07 22:46:14',1,NULL,NULL,NULL,15),(9066,355,'2016-03-07 22:46:19',1,'111','1111111','1',15),(9067,666,'2016-03-07 22:47:12',1,NULL,NULL,NULL,15),(9068,666,'2016-03-07 22:47:16',1,NULL,NULL,NULL,15),(9069,444,'2016-03-07 22:47:21',1,NULL,NULL,NULL,15),(9070,666,'2016-03-07 22:47:24',1,NULL,NULL,NULL,15),(9071,666,'2016-03-07 22:47:27',1,NULL,NULL,NULL,15),(9072,15,'2016-03-08 01:26:10',1,NULL,NULL,NULL,15),(9073,2.78,'2016-03-13 14:39:26',1,'111','1111111','1',15),(9074,0,'2016-03-13 14:41:08',1,NULL,NULL,NULL,15),(9075,19.62,'2016-03-15 13:11:30',1,NULL,NULL,NULL,15),(9076,14.54,'2016-03-15 13:11:52',1,NULL,NULL,NULL,15),(9077,1.68,'2016-03-18 19:17:30',1,'111dfgdsfg','1111111sdfgd','1ertert',15),(9078,15,'2016-03-18 19:20:00',1,'111','1111111','1',15),(9079,8.98,'2016-03-18 19:20:50',1,'111','1111111','1',15),(9080,1.68,'2016-03-18 20:45:41',1,NULL,NULL,NULL,15),(9081,2.78,'2016-03-18 22:52:37',1,NULL,NULL,NULL,15),(9082,11.76,'2016-03-18 22:55:32',1,NULL,NULL,NULL,15),(9083,2.58,'2016-03-18 23:24:54',1,NULL,NULL,NULL,15),(9084,8.980000000000002,'2016-03-18 23:32:09',1,NULL,NULL,NULL,15),(9085,7.82,'2016-03-18 23:32:48',1,NULL,NULL,NULL,15);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `csid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `FKED8DCCEFB9B74E02` (`csid`),
  CONSTRAINT `FKED8DCCEFB9B74E02` FOREIGN KEY (`csid`) REFERENCES `categorysecond` (`csid`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`pid`,`pname`,`market_price`,`shop_price`,`image`,`pdesc`,`is_hot`,`pdate`,`csid`) values (1,'荷兰豆',9.28,9.28,'image/2fd0fcd1-ab7b-4028-800a-268b86527b2d.jpg','荷兰豆',1,'2016-03-07 18:20:05',1),(2,'韭菜',2.38,2.48,'image/cc45318e-e831-434a-ba71-f6c6989de41d.jpg','韭菜韭菜韭菜韭菜韭菜韭菜韭菜韭菜韭菜韭菜韭菜韭菜韭菜',1,'2016-03-07 18:24:35',1),(3,'紫甘蓝',5.98,5.98,'image/f9e8b037-2e8f-43d7-8113-3200f8848b4d.jpg','紫甘蓝',1,'2016-03-07 18:19:11',1),(4,'胡萝卜',1.58,1.58,'image/1f6def19-c124-4d97-ac07-4e5959589581.jpg','胡萝卜',0,'2016-03-07 18:05:30',3),(5,'尖椒',2.48,2.58,'image/932b6ee3-7cb6-4675-9045-aaa7497ab6d5.jpg','尖椒尖椒尖椒尖椒尖椒尖椒',1,'2016-03-07 18:21:03',1),(7,'香菜',2.98,2.98,'image/2eb82e19-eb0b-43ba-a86e-51c8a52a73c9.jpg','香菜香菜香菜香菜香菜香菜',0,'2016-03-07 18:21:40',1),(30,'土豆',2.48,2.68,'image/ab678a6c-7329-4106-b520-50f12c716e66.jpg','土豆土豆土豆土豆土豆土豆土豆土豆土豆土豆',1,'2016-03-07 18:25:08',3),(45,'红鸡蛋',50,50,'image/50551586-4352-4b49-9722-97a4e5e84362.jpg','红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋红鸡蛋',0,'2016-03-07 18:04:47',66),(51,'大白菜',8.78,8.98,'image/92b718c2-e0fa-4d50-8661-ae21e89e3b85.jpg','大白菜大白菜大白菜大白菜大白菜大白菜大白菜大白菜大白菜大白菜',1,'2016-03-07 18:27:22',1),(57,'土鸡蛋',100,100,'image/d798f6aa-e7de-4544-9de5-e151b1d6938a.jpg','土鸡蛋',1,'2016-03-07 18:05:39',66),(60,'黄瓜',2.58,2.58,'image/f07124b7-ddd1-43b8-ba8a-ab4d796f51b4.jpg','黄瓜黄瓜黄瓜黄瓜黄瓜黄瓜黄瓜黄瓜',1,'2016-03-07 18:25:55',2),(66,'小香葱',2.98,2.98,'image/1ed2c805-fc0c-4df8-a0f2-b12cda0ae4b9.jpg','小香葱小香葱小香葱小香葱小香葱小香葱小香葱小香葱小香葱',1,'2016-03-07 18:23:22',3),(67,'花菜',5.38,5.38,'image/c6253e47-f4c4-41bb-9660-3ffbe607dde0.jpg','花菜花菜花菜花菜花菜花菜花菜花菜花菜',1,'2016-03-07 18:23:54',1),(68,'西红柿',2.58,2.78,'image/43e48e79-eb47-47ce-a35c-fc9722b6c96b.jpg','西红柿',1,'2016-03-07 18:26:41',1),(72,'甜玉米',4.48,4.68,'image/6d7a8201-74e5-44cf-8667-5ad86c7bb62f.jpg','甜玉米',1,'2016-03-07 18:06:41',71),(73,'圆茄子',1.58,1.78,'image/740388e2-cff9-458c-9e05-b588812d5a0f.jpg','圆茄子',1,'2016-03-07 18:06:52',1),(74,'大冬瓜',4.78,4.78,'image/959d7f1a-308d-4da0-a50f-be8891915e36.jpg','大冬瓜',1,'2016-03-07 18:06:28',1),(75,'桃子',5,5,'image/5da0e034-485c-4681-8b39-528dc03a2b2b.jpg','桃子',1,'2016-03-07 18:06:11',69),(76,'小小西红柿',6,6,'image/cd456006-bfea-4f85-aaa7-f024ab8bf522.jpg','小小西红柿',1,'2016-03-07 18:05:51',1),(79,'圆白菜',1.78,2,'image/8a838475-0a9d-4ec0-94f4-6bc847627097.jpg','圆白菜',1,'2016-03-07 18:07:14',1),(80,'绿茶',355,355,'image/40006a16-bd4b-4ba2-ba53-d91bd18233a3.jpg','绿茶绿茶绿茶绿茶绿茶绿茶绿茶绿茶绿茶绿茶绿茶',1,'2016-03-07 18:04:28',80),(81,'卷心菜',1.5,1.5,'image/131fdc54-058e-4595-8d68-61c5e6713ecd.jpg','卷心菜卷心菜卷心菜卷心菜卷心菜卷心菜',1,'2016-03-07 18:04:14',1),(82,'面粉',15,15,'image/280f16b2-403e-4835-a868-a450648698cd.jpg','面粉面粉面粉面粉面粉面粉面粉面粉面粉面粉面粉',1,'2016-03-07 18:03:50',72),(83,'公秦茶',444,444,'image/12e0339d-b7eb-46f6-b9a2-5f1dbc0db772.jpg','公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶公秦茶',1,'2016-03-07 18:01:23',80),(84,'公秦绿茶',666,666,'image/80ff0b17-d09a-45a7-94a2-85396bcef2c4.jpg','公秦绿茶公秦绿茶公秦绿茶公秦绿茶公秦绿茶公秦绿茶',1,'2016-03-07 18:01:03',80),(85,'小西红柿',6.68,6.9,'image/44e1074b-5e1d-4017-aa2d-c85b1f648a4c.jpg','小西红柿小西红柿小西红柿小西红柿小西红柿小西红柿',1,'2016-03-07 18:00:49',1),(86,'白萝卜',3.98,3.98,'image/743cec51-de9e-4239-b7ac-504ee31bb919.jpg','白萝卜白萝卜白萝卜白萝卜白萝卜白萝卜白萝卜白萝卜白萝卜',1,'2016-03-07 18:00:17',1),(87,'芹菜',2.18,2.5,'image/c90da1c5-ea35-427f-9630-bc0c6cc6b699.jpg','芹菜芹菜芹菜芹菜芹菜芹菜芹菜芹菜芹菜芹菜',1,'2016-03-07 17:57:41',1),(88,'菠菜',3.48,3.48,'image/1cc4343c-74e9-4597-abd0-ad0fb836bb62.jpg','菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜菠菜',1,'2016-03-07 17:56:39',1),(89,'红烧肉',20,25,'image/0a70e427-fb04-41e7-8454-e027a913d117.jpg','红烧肉红烧肉红烧肉红烧肉红烧肉红烧肉红烧肉红烧肉',1,'2016-03-07 22:28:28',4),(90,'西瓜',1.58,1.68,'image/0e03f6cf-e1b1-484e-9a87-936d076401a9.jpg','西瓜西瓜西瓜西瓜西瓜西瓜西瓜西瓜西瓜西瓜西瓜西瓜西瓜西瓜',1,'2016-03-07 22:28:19',68);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`,`name`,`email`,`phone`,`addr`,`state`,`code`) values (12,'aaaa','aaaa','aaaa','aaa1@tg.com','aaaa','aaaa',1,'4e8f4b66a6684402961dd4e19829be8cef75df3456fb49fe9cecb769fee554b7'),(13,'bbbb','bbbb','bbbb','bbb1@tg.com','bbbb','bbbb',0,'c80b29bfd7944c4db2cbd3b66c88d13ae1de0b24a1f34df790932e8583dd3393'),(14,'aaaaaw','aaaa','aa','aaa1@tg.com','aaa','aa',0,'86aad15240144c08b7d09de492fa4a53ec2f4d4d179a4ec1ac75e90f84af69b8'),(15,'111','111','1','aaa@tg.com','1111111','1',1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
