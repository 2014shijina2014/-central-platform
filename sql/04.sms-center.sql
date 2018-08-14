###导出 sms-center 的数据库结构
CREATE DATABASE IF NOT EXISTS `sms-center` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `sms-center`;


SET FOREIGN_KEY_CHECKS=0;



#
# Structure for table "sys_sms"
#

DROP TABLE IF EXISTS `sys_sms`;
CREATE TABLE `sys_sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(16) NOT NULL COMMENT '手机号码',
  `signName` varchar(128) DEFAULT NULL,
  `templateCode` varchar(128) DEFAULT NULL,
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `bizId` varchar(128) DEFAULT NULL COMMENT '阿里云返回的',
  `code` varchar(64) DEFAULT NULL COMMENT '阿里云返回的code',
  `message` varchar(128) DEFAULT NULL COMMENT '阿里云返回的',
  `day` date NOT NULL COMMENT '日期',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `phone` (`phone`),
  KEY `day` (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发短信记录';

#
# Data for table "sys_sms"
#
