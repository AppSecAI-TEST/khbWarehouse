/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : investtest

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-04-01 02:59:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `AVAILABLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', 'asd', '1');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `C_ID` int(11) DEFAULT NULL,
  `CLASS_NAME` varchar(255) DEFAULT NULL,
  `AVAILABLE` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'as', '1', 'yininji', '1');
INSERT INTO `student` VALUES ('2', 'zhetian', '1', null, null);
INSERT INTO `student` VALUES ('3', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('4', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('5', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('6', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('7', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('8', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('9', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('10', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('11', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('12', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('13', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('14', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('15', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('16', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('17', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('18', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('19', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('20', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('21', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('22', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('23', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('24', 'zhetian', '1', null, null);
INSERT INTO `student` VALUES ('25', 'zhetian', '1', null, null);
INSERT INTO `student` VALUES ('26', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('27', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('28', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('29', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('34', 'zhetian', '1', null, '1');
INSERT INTO `student` VALUES ('35', 'zhetian', '1', null, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` char(32) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
