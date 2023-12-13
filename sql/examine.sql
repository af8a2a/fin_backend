/*
Navicat MySQL Data Transfer

Source Server         : wsl
Source Server Version : 80035
Source Host           : 127.0.0.1:3306
Source Database       : fin

Target Server Type    : MYSQL
Target Server Version : 80035
File Encoding         : 65001

Date: 2023-12-13 21:34:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for examine
-- ----------------------------
DROP TABLE IF EXISTS `examine`;
CREATE TABLE `examine` (
  `examine_id` int NOT NULL AUTO_INCREMENT,
  `commit_id` int NOT NULL,
  `examine_company` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `examine_time` datetime DEFAULT NULL,
  `pass_time` datetime DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`examine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of examine
-- ----------------------------
INSERT INTO `examine` VALUES ('1', '3', 'A', '2023-12-13 12:59:55', null, '营业审核', '待审核', '1234');
INSERT INTO `examine` VALUES ('2', '4', 'A', '2023-12-13 12:59:55', null, '营业审核', '待审核', '5678');
INSERT INTO `examine` VALUES ('3', '5', 'B', '2023-12-13 21:32:53', null, '营业审核', '待审核', '4444');
