/*
 Navicat Premium Dump SQL

 Source Server         : fosu_web
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : ch9

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 27/12/2024 22:43:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ausertable
-- ----------------------------
DROP TABLE IF EXISTS `ausertable`;
CREATE TABLE `ausertable`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `aname` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `apwd` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ausertable
-- ----------------------------
INSERT INTO `ausertable` VALUES (1, 'admin', '123');

-- ----------------------------
-- Table structure for busertable
-- ----------------------------
DROP TABLE IF EXISTS `busertable`;
CREATE TABLE `busertable`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `bemail` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `bpwd` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `rebpwd` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busertable
-- ----------------------------
INSERT INTO `busertable` VALUES (9, 'lisi@1', '123', '123');
INSERT INTO `busertable` VALUES (13, 'wilson10@yahoo.com', 'eIx8UaX978', 'uUZXIXM0Pb');
INSERT INTO `busertable` VALUES (21, 'kiflore1951@mail.com', 'qAj1EDMFOl', 'NQ2mtKPbLz');
INSERT INTO `busertable` VALUES (22, 'fujiy822@hotmail.com', '43AiagGAA1', 'zgpqzCkEbI');
INSERT INTO `busertable` VALUES (23, 'clifford1228@hotmail.com', 'veSn28GzdP', 'cJ4oOGRfmO');
INSERT INTO `busertable` VALUES (25, 'riceant2@gmail.com', 'yqVMEPbUJ1', '6O5mwQCNAF');
INSERT INTO `busertable` VALUES (26, 'daichiyoko@outlook.com', 'or4YDGc533', 'TDj3vXIxMM');
INSERT INTO `busertable` VALUES (29, 'ogarin@yahoo.com', 'alfZIGrWvy', '4EqQLLsAjg');
INSERT INTO `busertable` VALUES (30, 'lichongming@qq.com', '123', '123456');

-- ----------------------------
-- Table structure for carttable
-- ----------------------------
DROP TABLE IF EXISTS `carttable`;
CREATE TABLE `carttable`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `busertable_id` int NOT NULL,
  `goodstable_id` int NOT NULL,
  `shoppingnum` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bid`(`busertable_id` ASC) USING BTREE,
  INDEX `gno`(`goodstable_id` ASC) USING BTREE,
  CONSTRAINT `bid` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `gno` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carttable
-- ----------------------------

-- ----------------------------
-- Table structure for focustable
-- ----------------------------
DROP TABLE IF EXISTS `focustable`;
CREATE TABLE `focustable`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `goodstable_id` int NOT NULL,
  `busertable_id` int NOT NULL,
  `focustime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gno1`(`goodstable_id` ASC) USING BTREE,
  INDEX `bid1`(`busertable_id` ASC) USING BTREE,
  CONSTRAINT `bid1` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `gno1` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of focustable
-- ----------------------------
INSERT INTO `focustable` VALUES (6, 47, 9, '2021-01-04 05:09:35');
INSERT INTO `focustable` VALUES (7, 36, 9, '2021-01-04 05:09:55');
INSERT INTO `focustable` VALUES (12, 38, 9, '2024-12-26 15:27:11');
INSERT INTO `focustable` VALUES (13, 38, 9, '2024-12-26 15:37:56');
INSERT INTO `focustable` VALUES (14, 54, 9, '2024-12-26 15:38:00');
INSERT INTO `focustable` VALUES (15, 36, 9, '2024-12-26 15:38:16');
INSERT INTO `focustable` VALUES (16, 40, 9, '2024-12-26 20:14:24');
INSERT INTO `focustable` VALUES (17, 59, 30, '2024-12-27 22:11:08');
INSERT INTO `focustable` VALUES (18, 38, 30, '2024-12-27 22:22:59');
INSERT INTO `focustable` VALUES (19, 39, 30, '2024-12-27 22:23:02');
INSERT INTO `focustable` VALUES (20, 40, 30, '2024-12-27 22:23:04');
INSERT INTO `focustable` VALUES (21, 41, 30, '2024-12-27 22:23:05');
INSERT INTO `focustable` VALUES (22, 54, 30, '2024-12-27 22:23:07');

-- ----------------------------
-- Table structure for goodstable
-- ----------------------------
DROP TABLE IF EXISTS `goodstable`;
CREATE TABLE `goodstable`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `gname` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `goprice` double NULL DEFAULT NULL,
  `grprice` double NULL DEFAULT NULL,
  `gstore` int NULL DEFAULT NULL,
  `gpicture` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `isRecommend` tinyint NULL DEFAULT NULL,
  `isAdvertisement` tinyint NULL DEFAULT NULL,
  `goodstype_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `typeid`(`goodstype_id` ASC) USING BTREE,
  CONSTRAINT `typeid` FOREIGN KEY (`goodstype_id`) REFERENCES `goodstype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstable
-- ----------------------------
INSERT INTO `goodstable` VALUES (36, '苹果1', 10, 8, 70, '202111311142932272.jpg', 1, 0, 19);
INSERT INTO `goodstable` VALUES (37, '衣服1', 20, 10, 100, '202111309210632700.jpg', 1, 1, 21);
INSERT INTO `goodstable` VALUES (38, '鲜花1', 20, 10, 200, '202111309210632700.jpg', 1, 1, 22);
INSERT INTO `goodstable` VALUES (39, '鲜花2', 30, 20, 300, '202111309210632700.jpg', 1, 1, 22);
INSERT INTO `goodstable` VALUES (40, '鲜花3', 50, 30, 400, '202111309210632700.jpg', 1, 1, 22);
INSERT INTO `goodstable` VALUES (41, '鲜花4', 50, 40, 300, '202111309210632700.jpg', 0, 1, 22);
INSERT INTO `goodstable` VALUES (42, '衣服11', 30, 20, 300, '202111309210632700.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (43, '衣服22', 50, 40, 600, '202111309210632700.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (44, '衣服33', 50, 40, 600, '202111309210632700.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (45, '衣服144', 50, 40, 400, '202111309210632700.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (46, '衣服155', 400, 300, 900, '202111309210632700.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (47, '衣服66', 80, 50, 750, '202111309210632700.jpg', 1, 0, 18);
INSERT INTO `goodstable` VALUES (48, '空调', 2999.99, 1000, 100, '202111309210632700.jpg', 1, 0, 18);
INSERT INTO `goodstable` VALUES (49, '洗衣机', 1499.99, 1000, 50, '202111309210632700.jpg', 0, 0, 18);
INSERT INTO `goodstable` VALUES (50, '苹果', 5.99, 5, 500, '202111309210632700.jpg', 1, 1, 19);
INSERT INTO `goodstable` VALUES (51, '香蕉', 3.99, 5, 300, '202111309210632700.jpg', 1, 0, 19);
INSERT INTO `goodstable` VALUES (52, 'T恤', 99.99, 100, 200, '202111309210632700.jpg', 1, 0, 21);
INSERT INTO `goodstable` VALUES (53, '牛仔裤', 199.99, 100, 150, '202111309210632700.jpg', 0, 0, 21);
INSERT INTO `goodstable` VALUES (54, '玫瑰花', 59.99, 100, 100, '202111309210632700.jpg', 1, 1, 22);
INSERT INTO `goodstable` VALUES (55, '郁金香', 89.99, 100, 80, '202111309210632700.jpg', 0, 0, 22);
INSERT INTO `goodstable` VALUES (56, '手工焚烧木', 199.99, 500, 50, '202111309210632700.jpg', 1, 1, 30);
INSERT INTO `goodstable` VALUES (57, '龙形雕塑', 999.99, 5444, 20, '202111309210632700.jpg', 1, 0, 34);
INSERT INTO `goodstable` VALUES (58, '功法书籍', 199.99, 666, 60, '202111309210632700.jpg', 1, 1, 35);
INSERT INTO `goodstable` VALUES (59, '天神坠落——屠龙刀', 6666666, 888888, 12, '202111309210632700.jpg', 1, 1, 23);

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (18, '家电');
INSERT INTO `goodstype` VALUES (19, '水果');
INSERT INTO `goodstype` VALUES (20, '文具');
INSERT INTO `goodstype` VALUES (21, '服装');
INSERT INTO `goodstype` VALUES (22, '鲜花');
INSERT INTO `goodstype` VALUES (23, '神兵利器');

-- ----------------------------
-- Table structure for orderbasetable
-- ----------------------------
DROP TABLE IF EXISTS `orderbasetable`;
CREATE TABLE `orderbasetable`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `busertable_id` int NOT NULL,
  `amount` double NOT NULL,
  `status` tinyint NOT NULL,
  `orderdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bid2`(`busertable_id` ASC) USING BTREE,
  CONSTRAINT `bid2` FOREIGN KEY (`busertable_id`) REFERENCES `busertable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderbasetable
-- ----------------------------
INSERT INTO `orderbasetable` VALUES (16, 9, 4874548, 1, '2024-12-26 21:44:50');
INSERT INTO `orderbasetable` VALUES (17, 9, 207200, 1, '2024-12-26 22:19:38');
INSERT INTO `orderbasetable` VALUES (18, 9, 0, 1, '2024-12-26 22:20:17');
INSERT INTO `orderbasetable` VALUES (19, 9, 0, 1, '2024-12-26 22:20:22');
INSERT INTO `orderbasetable` VALUES (20, 9, 0, 1, '2024-12-26 22:21:13');
INSERT INTO `orderbasetable` VALUES (21, 9, 188.01, 14, '2018-05-07 18:09:20');
INSERT INTO `orderbasetable` VALUES (22, 9, 489.43, 16, '2003-10-10 02:58:20');
INSERT INTO `orderbasetable` VALUES (23, 9, 374.62, 75, '2017-01-30 22:24:06');
INSERT INTO `orderbasetable` VALUES (24, 9, 841.01, 113, '2023-02-11 10:22:36');
INSERT INTO `orderbasetable` VALUES (25, 9, 956.8, 7, '2011-12-08 00:28:42');
INSERT INTO `orderbasetable` VALUES (26, 9, 549.44, 57, '2002-12-07 11:47:59');
INSERT INTO `orderbasetable` VALUES (27, 9, 388.62, 104, '2016-02-06 14:14:02');
INSERT INTO `orderbasetable` VALUES (28, 9, 843.95, 103, '2019-05-07 17:10:40');
INSERT INTO `orderbasetable` VALUES (29, 9, 33.63, 44, '2021-05-09 20:37:06');
INSERT INTO `orderbasetable` VALUES (30, 9, 675.29, 16, '2003-04-24 18:12:14');
INSERT INTO `orderbasetable` VALUES (31, 30, 9286585, 1, '2024-12-27 22:17:52');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderbasetable_id` int NOT NULL,
  `goodstable_id` int NOT NULL,
  `shoppingnum` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `odsn`(`orderbasetable_id` ASC) USING BTREE,
  INDEX `gno3`(`goodstable_id` ASC) USING BTREE,
  CONSTRAINT `gno3` FOREIGN KEY (`goodstable_id`) REFERENCES `goodstable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `odsn` FOREIGN KEY (`orderbasetable_id`) REFERENCES `orderbasetable` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES (14, 16, 53, 474);
INSERT INTO `orderdetail` VALUES (15, 16, 52, 427);
INSERT INTO `orderdetail` VALUES (16, 16, 50, 212);
INSERT INTO `orderdetail` VALUES (17, 16, 56, 233);
INSERT INTO `orderdetail` VALUES (18, 16, 42, 887);
INSERT INTO `orderdetail` VALUES (19, 16, 44, 515);
INSERT INTO `orderdetail` VALUES (20, 16, 47, 729);
INSERT INTO `orderdetail` VALUES (21, 16, 57, 782);
INSERT INTO `orderdetail` VALUES (22, 16, 51, 778);
INSERT INTO `orderdetail` VALUES (23, 16, 49, 331);
INSERT INTO `orderdetail` VALUES (24, 17, 52, 794);
INSERT INTO `orderdetail` VALUES (25, 17, 54, 335);
INSERT INTO `orderdetail` VALUES (26, 17, 55, 943);
INSERT INTO `orderdetail` VALUES (27, 31, 56, 706);
INSERT INTO `orderdetail` VALUES (28, 31, 51, 461);
INSERT INTO `orderdetail` VALUES (29, 31, 40, 782);
INSERT INTO `orderdetail` VALUES (30, 31, 42, 25);
INSERT INTO `orderdetail` VALUES (31, 31, 41, 461);
INSERT INTO `orderdetail` VALUES (32, 31, 59, 10);

SET FOREIGN_KEY_CHECKS = 1;
