/*
Navicat MySQL Data Transfer

Source Server         : wsl
Source Server Version : 80035
Source Host           : 127.0.0.1:3306
Source Database       : fin

Target Server Type    : MYSQL
Target Server Version : 80035
File Encoding         : 65001

Date: 2023-12-16 21:27:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `company_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of company
-- ----------------------------

-- ----------------------------
-- Table structure for examine
-- ----------------------------
DROP TABLE IF EXISTS `examine`;
CREATE TABLE `examine` (
  `examine_id` int NOT NULL AUTO_INCREMENT,
  `commit_id` int NOT NULL,
  `examine_company` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `examine_time` datetime DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`examine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of examine
-- ----------------------------
INSERT INTO `examine` VALUES ('13', '9', '123456', '2023-12-16 12:12:44', '营业审核', '54523');

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `incoming` decimal(10,0) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of transaction
-- ----------------------------
INSERT INTO `transaction` VALUES ('8', '1234', 'test', '2023-12-16 12:04:27', '收入', '123456', '已审核');
INSERT INTO `transaction` VALUES ('9', '54523', 'abcd', '2023-12-16 12:12:44', '收入', '123456', '待审批');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', 'admin');
INSERT INTO `user` VALUES ('2', '2', '2', '2', 'guest');
INSERT INTO `user` VALUES ('3', '1', '1', '1', 'guest');
INSERT INTO `user` VALUES ('4', '123456', '123456', '123456', 'guest');

-- ----------------------------
-- Table structure for wage
-- ----------------------------
DROP TABLE IF EXISTS `wage`;
CREATE TABLE `wage` (
  `wage_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of wage
-- ----------------------------
INSERT INTO `wage` VALUES ('14', 'test', '2023-12-16 12:04:06', '1234', '123456', '待审核');
