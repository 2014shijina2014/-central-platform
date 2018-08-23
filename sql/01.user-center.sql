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
  `hidden` int(11) DEFAULT NULL COMMENT '是否隐藏,0 false 1 true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_menu"
#

INSERT INTO `sys_menu` VALUES (1,-1,'用户中心','javascript:;',NULL,'layui-icon-set',0,'2018-08-16 17:03:04','2018-08-20 10:20:42',1,0),(2,1,'用户管理','#!user','system/user.html','',2,'2017-11-17 16:56:59','2018-08-20 10:03:26',1,0),(3,1,'角色管理','#!role','system/role.html','',3,'2017-11-17 16:56:59','2018-08-20 10:03:35',1,0),(4,1,'菜单管理','#!menus','system/menus.html','',4,'2017-11-17 16:56:59','2018-08-20 10:03:40',1,0),(5,1,'权限管理','#!permissions','system/permissions.html','',5,'2018-08-19 22:12:49','2018-08-20 10:03:46',1,0),(7,-1,'注册中心','#!register','http://127.0.0.1:1111','layui-icon-engine',7,'2018-08-20 11:50:29','2018-08-20 11:50:29',1,0),(8,-1,'监控中心','#!monitor','http://127.0.0.1:9001/#/wallboard','layui-icon-util\r\n',8,'2017-11-17 16:56:59','2018-08-20 10:43:13',1,0),(9,-1,'文件中心','#!files','files/files.html','layui-icon-file',10,'2018-08-20 15:59:56','2018-08-21 11:57:04',1,0),(10,-1,'文档中心','#!swagger','http://127.0.0.1:9200/swagger-ui.html','layui-icon-app',9,'2018-08-20 13:57:45','2018-08-22 17:58:13',1,0),(11,1,'我的信息','#!myInfo','system/myInfo.html','',10,'2018-08-20 13:39:23','2018-08-21 11:56:12',1,1),(12,-1,'认证中心','javascript:;','','layui-icon-set',1,'2018-08-21 12:48:13','2018-08-21 12:48:13',1,0),(13,12,'token管理','#!token','attestation/token.html',NULL,10,'2018-08-16 17:03:04','2018-08-16 17:03:04',1,0),(34,12,'应用管理','#!app','attestation/app.html','layui-icon-app',11,'2018-08-22 16:05:24','2018-08-22 16:05:24',1,NULL);

#
# Structure for table "sys_permission"
#

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_permission"
#

INSERT INTO `sys_permission` VALUES (1,'permission:post/permissions','保存权限标识','2018-01-18 17:06:39','2018-01-18 17:06:42'),(2,'permission:put/permissions','修改权限标识','2018-01-18 17:06:39','2018-01-18 17:06:42'),(3,'permission:delete/permissions/{id}','删除权限标识','2018-01-18 17:06:39','2018-01-18 17:06:42'),(4,'permission:get/permissions','查询权限标识','2018-01-18 17:06:39','2018-01-18 17:06:42'),(5,'role:post/roles','添加角色','2018-01-18 17:06:39','2018-01-18 17:06:42'),(6,'role:put/roles','修改角色','2018-01-18 17:06:39','2018-01-18 17:06:42'),(7,'role:delete/roles/{id}','删除角色','2018-01-18 17:06:39','2018-01-18 17:06:42'),(8,'role:post/roles/{id}/permissions','给角色分配权限','2018-01-18 17:06:39','2018-01-18 17:06:42'),(9,'role:get/roles','查询角色','2018-01-18 17:06:39','2018-01-18 17:06:42'),(10,'role:get/roles/{id}/permissions','获取角色的权限','2018-01-18 17:06:39','2018-01-18 17:06:42'),(11,'user:post/users/{id}/roles','给用户分配角色','2018-01-18 17:06:39','2018-01-18 17:06:42'),(12,'user:post/users/{id}/resetPassword','用户重置密码','2018-01-18 17:06:39','2018-01-18 17:06:42'),(13,'user:get/users','用户查询','2018-01-18 17:12:00','2018-01-18 17:12:05'),(14,'user:put/users/me','修改用户','2018-01-18 17:06:39','2018-01-18 17:06:42'),(15,'user:get/users/{id}/roles','获取用户的角色','2018-01-18 17:06:39','2018-01-18 17:06:42'),(16,'user:post/users/saveOrUpdate','新增用户','2018-01-18 17:06:39','2018-01-18 17:06:42'),(17,'user:post/users/exportUser','导出用户','2018-01-18 17:06:39','2018-01-18 17:06:42'),(18,'user:get/users/updateEnabled','用户状态修改','2018-01-18 17:06:39','2018-01-18 17:06:42'),(19,'user:put/users/password','修改密码','2018-01-18 17:06:39','2018-01-18 17:06:39'),(20,'menu:get/menus/all','查询菜单','2018-01-18 17:06:39','2018-01-18 17:06:42'),(21,'menu:post/menus/granted','给角色分配菜单','2018-01-18 17:06:39','2018-01-18 17:06:42'),(22,'menu:get/menus/tree','树形显示','2018-01-18 17:06:39','2018-01-18 17:06:39'),(23,'menu:get/menus/{roleId}/menus','获取角色的菜单','2018-01-18 17:06:39','2018-01-18 17:06:42'),(24,'menu:post/menus','添加菜单','2018-01-18 17:06:39','2018-01-18 17:06:42'),(25,'menu:put/menus','修改菜单','2018-01-18 17:06:39','2018-01-18 17:06:42'),(26,'menu:delete/menus/{id}','删除菜单','2018-01-18 17:06:39','2018-01-18 17:06:42'),(27,'menu:get/menus/current','当前用户菜单','2018-01-18 17:06:39','2018-01-18 17:06:42'),(28,'menu:get/menus/findOnes','菜单树形列表','2018-01-18 17:06:39','2018-01-18 17:06:39'),(29,'menu:get/menus/findAlls','所有菜单','2018-01-18 17:06:39','2018-01-18 17:06:39'),(30,'file:del','文件删除','2018-01-18 17:06:39','2018-01-18 17:06:42'),(31,'file:query','文件查询','2018-01-18 17:06:39','2018-01-18 17:06:42');

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

INSERT INTO `sys_role_menu` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,34);

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

INSERT INTO `sys_role_permission` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(2,21),(2,22),(3,20),(3,22);

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

INSERT INTO `sys_role_user` VALUES (1,1),(1,2),(9,1),(9,2),(9,3),(10,1),(10,2),(10,3),(11,1),(11,2),(11,3),(12,1),(12,2),(12,3);

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_user"
#

INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG','管理员','http://payo7kq4i.bkt.clouddn.com/QQ图片20170601094602.jpg',NULL,0,1,'BACKEND','2017-11-17 16:56:59','2018-08-22 22:06:40'),(2,'owen','$2a$10$BD8FQRSJpg9DfU97Ct0jlea7WeFfHZjTDk/iNKSIaRkYchxzTM2au','欧文','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2017-11-17 16:56:59'),(3,'user','$2a$10$LUiAPxTB1m2pmYYUA1CskOWX2HAtEJeTC7jeOD6aU0BnU9ozH36.G','体验用户','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2018-08-22 21:06:06'),(4,'test','$2a$10$mKL47TyAli2Q1VP5UR3ZjOGLOQ0aIKA64yJGDceSiOX.QHnY2gvVG','测试账户','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2017-11-17 16:56:59'),(5,'nancy','$2a$10$cVhKfRxUHeBD.UgZ8F5lzu23USgWqZu0S.rMehjYT87rtBjbRwSvS','Nancy','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2017-11-17 16:56:59'),(6,'beckham','$2a$10$w0QW4oJL6dgeubBb/LwT9.dcueqoccpnu/jxDpXODkP8dgkhO1Cu2','贝克汉姆','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg',NULL,1,1,'APP','2017-11-17 16:56:59','2018-08-09 22:45:01');
