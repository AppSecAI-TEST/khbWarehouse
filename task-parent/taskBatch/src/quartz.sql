/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : quartz-demo

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-08-09 20:58:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for task_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `task_schedule_job`;
CREATE TABLE `task_schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `job_name` varchar(255) DEFAULT NULL,
  `job_group` varchar(255) DEFAULT NULL,
  `job_status` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `bean_class` varchar(255) DEFAULT NULL,
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '1',
  `spring_id` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) NOT NULL,
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `name_group` (`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_schedule_job
-- ----------------------------
INSERT INTO `task_schedule_job` VALUES ('1', null, '2017-08-09 20:13:54', 'test', 'test', '0', '0/1 * * * * ?', 'test', 'com.xinnet.task.TaskTest', '0', null, 'run1');
INSERT INTO `task_schedule_job` VALUES ('2', null, '2017-08-09 20:15:27', 'test1', 'test', '0', '0/5 * * * * ?', 'test2', 'com.xinnet.task.TaskTest', '1', 'taskTest', 'run');
INSERT INTO `task_schedule_job` VALUES ('10', '2014-04-25 16:52:17', '2017-08-09 20:14:01', '中间', '1111', '0', '0/1 * * * * ?', 'xxx', 'com.xinnet.task.TaskTest', '1', '', 'run');
