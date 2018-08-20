# 导出 user-center 的数据库结构
CREATE DATABASE IF NOT EXISTS `user-center` DEFAULT CHARACTER SET = utf8mb4;
Use `user-center`;


#
# Structure for table "sys_menu"
#

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `path` varchar(1024) DEFAULT NULL,
  `css` varchar(32) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `isMenu` int(11) DEFAULT NULL COMMENT '是否菜单 1 是 2 不是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_menu"
#

INSERT INTO `sys_menu` VALUES (1,-1,'用户中心','javascript:;',NULL,'layui-icon-set',0,'2018-08-16 17:03:04','2018-08-20 10:20:42',1),(2,1,'用户管理','#!user','system/user.html','',2,'2017-11-17 16:56:59','2018-08-20 10:03:26',1),(3,1,'角色管理','#!role','system/role.html','',3,'2017-11-17 16:56:59','2018-08-20 10:03:35',1),(4,1,'菜单管理','#!menus','system/menus.html','',4,'2017-11-17 16:56:59','2018-08-20 10:03:40',1),(5,1,'权限管理','#!permissions','system/permissions.html','',5,'2018-08-19 22:12:49','2018-08-20 10:03:46',1),(6,-1,'监控中心','#!monitor','http://127.0.0.1:9001/#/wallboard','',8,'2017-11-17 16:56:59','2018-08-20 10:43:13',1),(7,-1,'swagger文档','#!swagger','http://127.0.0.1:9200/swagger-ui.html','',7,'2017-11-17 16:56:59','2018-08-20 10:43:05',1);

#
# Structure for table "sys_permission"
#

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_permission"
#

INSERT INTO `sys_permission` VALUES (1,'back:permission:save','保存权限标识','2017-11-17 16:56:59','2017-11-17 16:56:59'),(2,'back:permission:update','修改权限标识','2017-11-17 16:56:59','2017-11-17 16:56:59'),(3,'back:permission:delete','删除权限标识','2017-11-17 16:56:59','2017-11-17 16:56:59'),(4,'back:permission:query','查询权限标识','2017-11-17 16:56:59','2017-11-17 16:56:59'),(5,'back:role:save','添加角色','2017-11-17 16:56:59','2017-11-17 16:56:59'),(6,'back:role:update','修改角色','2017-11-17 16:56:59','2017-11-17 16:56:59'),(7,'back:role:delete','删除角色','2017-11-17 16:56:59','2017-11-17 16:56:59'),(8,'back:role:permission:grant','给角色分配权限','2017-11-17 16:56:59','2017-11-17 16:56:59'),(9,'back:user:query','用户查询','2017-11-17 16:56:59','2017-11-17 16:56:59'),(10,'back:user:update','修改用户','2017-11-17 16:56:59','2017-11-17 16:56:59'),(11,'back:user:role:grant','给用户分配角色','2017-11-17 16:56:59','2017-11-17 16:56:59'),(12,'back:user:password','用户重置密码','2017-11-17 16:56:59','2017-11-17 16:56:59'),(13,'back:menu:save','添加菜单','2017-11-17 16:56:59','2017-11-17 16:56:59'),(14,'back:menu:update','修改菜单','2017-11-17 16:56:59','2017-11-17 16:56:59'),(15,'back:menu:delete','删除菜单','2017-11-17 16:56:59','2017-11-17 16:56:59'),(16,'back:menu:query','查询菜单','2017-11-17 16:56:59','2017-11-17 16:56:59'),(17,'back:menu:granted','给角色分配菜单','2017-11-17 16:56:59','2017-11-17 16:56:59'),(18,'back:role:query','查询角色','2017-11-17 16:56:59','2017-11-17 16:56:59'),(19,'user:role:byuid','获取用户的角色','2017-11-17 16:56:59','2017-11-17 16:56:59'),(20,'role:permission:byroleid','获取角色的权限','2017-11-17 16:56:59','2017-11-17 16:56:59'),(21,'menu:byroleid','获取角色的菜单','2017-11-17 16:56:59','2017-11-17 16:56:59'),(22,'ip:black:query','查询黑名单ip','2017-11-17 16:56:59','2017-11-17 16:56:59'),(23,'ip:black:save','添加黑名单ip','2017-11-17 16:56:59','2017-11-17 16:56:59'),(24,'ip:black:delete','删除黑名单ip','2017-11-17 16:56:59','2017-11-17 16:56:59'),(25,'log:query','日志查询','2017-11-17 16:56:59','2017-11-17 16:56:59'),(26,'file:query','文件查询','2017-11-17 16:56:59','2017-11-17 16:56:59'),(27,'file:del','文件删除','2017-11-17 16:56:59','2017-11-17 16:56:59'),(28,'mail:save','保存邮件','2017-11-17 16:56:59','2017-11-17 16:56:59'),(29,'mail:update','修改邮件','2017-11-17 16:56:59','2017-11-17 16:56:59'),(30,'mail:query','邮件查询','2017-11-17 16:56:59','2017-11-17 16:56:59'),(31,'sms:query','短信发送记录查询','2017-11-17 16:56:59','2017-11-17 16:56:59');

#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role"
#

INSERT INTO `sys_role` VALUES (1,'ADMIN','管理员','2018-08-10 21:45:29','2018-08-10 21:45:32'),(2,'test','asfsfdf','2018-08-14 10:53:19','2018-08-14 10:53:19'),(3,'qqweqw','qqwe','2018-08-14 11:03:36','2018-08-14 11:03:36');

#
# Structure for table "sys_role_menu"
#

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_menu"
#

INSERT INTO `sys_role_menu` VALUES (1,-1),(1,0),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7);

#
# Structure for table "sys_role_permission"
#

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_permission"
#


#
# Structure for table "sys_role_user"
#

DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_user"
#

INSERT INTO `sys_role_user` VALUES (1,1),(1,2);

#
# Structure for table "sys_user"
#

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `headImgUrl` varchar(1024) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `type` varchar(16) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_user"
#

INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$aqI3ScbMZtCqgs0HOjbWMexhOiPuoR9PNiBomSVQypdBVHk5sZmIW','管理员','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg','',1,1,'BACKEND','2017-11-17 16:56:59','2018-08-20 10:41:50'),(2,'owen','$2a$10$BD8FQRSJpg9DfU97Ct0jlea7WeFfHZjTDk/iNKSIaRkYchxzTM2au','欧文','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2017-11-17 16:56:59'),(3,'user','$2a$10$fBP0JTEvo553CrngJFRthODuykCLfgcIe8HiRmQEuv0/XkOrD4IXC','体验用户','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2017-11-17 16:56:59'),(4,'test','$2a$10$mKL47TyAli2Q1VP5UR3ZjOGLOQ0aIKA64yJGDceSiOX.QHnY2gvVG','测试账户','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2017-11-17 16:56:59'),(5,'nancy','$2a$10$cVhKfRxUHeBD.UgZ8F5lzu23USgWqZu0S.rMehjYT87rtBjbRwSvS','Nancy','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2017-11-17 16:56:59'),(6,'beckham','$2a$10$w0QW4oJL6dgeubBb/LwT9.dcueqoccpnu/jxDpXODkP8dgkhO1Cu2','贝克汉姆','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2018-08-09 22:45:01'),(7,'werwerwerw','$2a$10$ZrB.mw1LLifZM9pN1zAdkeFW3qv2mYS.84kYAF2Cpc31uKFKYUHtq','werwer','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg','18579068166',0,1,'APP','2018-08-14 11:02:31','2018-08-14 11:02:31'),(8,'aasfasdf','$2a$10$NVHmrW.84haZNH4Qd0oHkOKEOBHEXRWlmKzsGOzzdY0GnN3OWrFcC','aasfasdf','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg','18579068166',0,1,'APP','2018-08-14 11:03:21','2018-08-14 11:03:21');
