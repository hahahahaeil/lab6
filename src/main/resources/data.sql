/*
Navicat MySQL Data Transfer

Source Server         : MyCon
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : ch9

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2021-01-04 08:45:34
*/

create database ch9;
use ch9;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ausertable`
-- ----------------------------
DROP TABLE IF EXISTS `ausertable`;
CREATE TABLE `ausertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apwd` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ausertable
-- ----------------------------
INSERT INTO ausertable VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for `busertable`
-- ----------------------------
DROP TABLE IF EXISTS `busertable`;
CREATE TABLE `busertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bemail` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `bpwd` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of busertable
-- ----------------------------
INSERT INTO busertable VALUES ('9', 'chenheng@126.com', '78f8a7ae700c91db09facb05a675432b');

-- ----------------------------
-- Table structure for `carttable`
-- ----------------------------
DROP TABLE IF EXISTS `carttable`;
CREATE TABLE `carttable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `busertable_id` int(11) NOT NULL,
  `goodstable_id` int(11) NOT NULL,
  `shoppingnum` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bid` (`busertable_id`),
  KEY `gno` (`goodstable_id`),
  CONSTRAINT `bid` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`),
  CONSTRAINT `gno` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of carttable
-- ----------------------------

-- ----------------------------
-- Table structure for `focustable`
-- ----------------------------
DROP TABLE IF EXISTS `focustable`;
CREATE TABLE `focustable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodstable_id` int(11) NOT NULL,
  `busertable_id` int(11) NOT NULL,
  `focustime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gno1` (`goodstable_id`),
  KEY `bid1` (`busertable_id`),
  CONSTRAINT `bid1` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`),
  CONSTRAINT `gno1` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of focustable
-- ----------------------------
INSERT INTO focustable VALUES ('6', '47', '9', '2021-01-04 05:09:35');
INSERT INTO focustable VALUES ('7', '36', '9', '2021-01-04 05:09:55');

-- ----------------------------
-- Table structure for `goodstable`
-- ----------------------------
DROP TABLE IF EXISTS `goodstable`;
CREATE TABLE `goodstable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `goprice` double DEFAULT NULL,
  `grprice` double DEFAULT NULL,
  `gstore` int(11) DEFAULT NULL,
  `gpicture` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isRecommend` tinyint(2) DEFAULT NULL,
  `isAdvertisement` tinyint(2) DEFAULT NULL,
  `goodstype_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeid` (`goodstype_id`),
  CONSTRAINT `typeid` FOREIGN KEY (`goodstype_id`) REFERENCES `goodstype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of goodstable
-- ----------------------------
INSERT INTO goodstable VALUES ('36', '苹果1', '10', '8', '70', '20210103201023158.jpg', '1', '0', '19');
INSERT INTO goodstable VALUES ('37', '衣服1', '20', '10', '100', '20210103201332703.jpg', '1', '1', '21');
INSERT INTO goodstable VALUES ('38', '鲜花1', '20', '10', '200', '20210103201625494.jpg', '1', '1', '22');
INSERT INTO goodstable VALUES ('39', '鲜花2', '30', '20', '300', '20210103201643847.jpg', '1', '1', '22');
INSERT INTO goodstable VALUES ('40', '鲜花3', '50', '30', '400', '20210103201702145.jpg', '1', '1', '22');
INSERT INTO goodstable VALUES ('41', '鲜花4', '50', '40', '300', '20210103201722153.jpg', '0', '1', '22');
INSERT INTO goodstable VALUES ('42', '衣服11', '30', '20', '300', '20210103204253840.jpg', '1', '0', '21');
INSERT INTO goodstable VALUES ('43', '衣服22', '50', '40', '600', '20210103204317014.jpg', '1', '0', '21');
INSERT INTO goodstable VALUES ('44', '衣服33', '50', '40', '600', '20210103204336541.jpg', '1', '0', '21');
INSERT INTO goodstable VALUES ('45', '衣服144', '50', '40', '400', '20210103204353659.jpg', '1', '0', '21');
INSERT INTO goodstable VALUES ('46', '衣服155', '400', '300', '900', '20210103204450712.jpg', '1', '0', '21');
INSERT INTO goodstable VALUES ('47', '衣服66', '80', '50', '750', '20210103204510116.jpg', '1', '0', '18');

-- ----------------------------
-- Table structure for `goodstype`
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO goodstype VALUES ('18', '家电');
INSERT INTO goodstype VALUES ('19', '水果');
INSERT INTO goodstype VALUES ('20', '文具');
INSERT INTO goodstype VALUES ('21', '服装');
INSERT INTO goodstype VALUES ('22', '鲜花');

-- ----------------------------
-- Table structure for `orderbasetable`
-- ----------------------------
DROP TABLE IF EXISTS `orderbasetable`;
CREATE TABLE `orderbasetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `busertable_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `status` tinyint(4) NOT NULL,
  `orderdate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bid2` (`busertable_id`),
  CONSTRAINT `bid2` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of orderbasetable
-- ----------------------------
INSERT INTO orderbasetable VALUES ('6', '9', '2740', '0', '2021-01-04 05:11:23');

-- ----------------------------
-- Table structure for `orderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderbasetable_id` int(11) NOT NULL,
  `goodstable_id` int(11) NOT NULL,
  `shoppingnum` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `odsn` (`orderbasetable_id`),
  KEY `gno3` (`goodstable_id`),
  CONSTRAINT `gno3` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`),
  CONSTRAINT `odsn` FOREIGN KEY (`orderbasetable_id`) REFERENCES `orderbasetable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO orderdetail VALUES ('7', '6', '47', '50');
INSERT INTO orderdetail VALUES ('8', '6', '36', '30');


INSERT INTO goodstable (gname, goprice, grprice, gstore, gpicture, isRecommend, isAdvertisement, goodstype_id) VALUES
                                                                                                                   ('空调', 2999.99, 2599.99, 100, '/images/airconditioner.jpg', 1, 0, 18),   -- 家电
                                                                                                                   ('洗衣机', 1499.99, 1299.99, 50, '/images/washingmachine.jpg', 0, 0, 18),   -- 家电
                                                                                                                   ('苹果', 5.99, 4.99, 500, '/images/apple.jpg', 1, 1, 19),   -- 水果
                                                                                                                   ('香蕉', 3.99, 3.49, 300, '/images/banana.jpg', 1, 0, 19),  -- 水果
                                                                                                                   ('T恤', 99.99, 79.99, 200, '/images/tshirt.jpg', 1, 0, 21),   -- 服装
                                                                                                                   ('牛仔裤', 199.99, 169.99, 150, '/images/jeans.jpg', 0, 0, 21),   -- 服装
                                                                                                                   ('玫瑰花', 59.99, 49.99, 100, '/images/rose.jpg', 1, 1, 22),  -- 鲜花
                                                                                                                   ('郁金香', 89.99, 69.99, 80, '/images/tulip.jpg', 0, 0, 22),  -- 鲜花
                                                                                                                   ('手工焚烧木', 199.99, 179.99, 50, '/images/woodburning.jpg', 1, 1, 30),  -- 焚决
                                                                                                                   ('龙形雕塑', 999.99, 899.99, 20, '/images/dragon.jpg', 1, 0, 34),  -- 龙
                                                                                                                   ('功法书籍', 199.99, 179.99, 60, '/images/martialartbook.jpg', 1, 1, 35);  -- 功法
