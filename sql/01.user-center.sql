/*
Navicat MySQL Data Transfer

Source Server         : 47.94.252.160
Source Server Version : 50722
Source Host           : 47.94.252.160:3306
Source Database       : user-center

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-22 22:38:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
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

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '-1', '用户中心', 'javascript:;', null, 'layui-icon-set', '0', '2018-08-16 17:03:04', '2018-08-20 10:20:42', '1', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '#!user', 'system/user.html', '', '2', '2017-11-17 16:56:59', '2018-08-20 10:03:26', '1', '0');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '#!role', 'system/role.html', '', '3', '2017-11-17 16:56:59', '2018-08-20 10:03:35', '1', '0');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '#!menus', 'system/menus.html', '', '4', '2017-11-17 16:56:59', '2018-08-20 10:03:40', '1', '0');
INSERT INTO `sys_menu` VALUES ('5', '1', '权限管理', '#!permissions', 'system/permissions.html', '', '5', '2018-08-19 22:12:49', '2018-08-20 10:03:46', '1', '0');
INSERT INTO `sys_menu` VALUES ('7', '-1', '注册中心', '#!register', 'http://127.0.0.1:1111', 'layui-icon-engine', '7', '2018-08-20 11:50:29', '2018-08-20 11:50:29', '1', '0');
INSERT INTO `sys_menu` VALUES ('8', '-1', '监控中心', '#!monitor', 'http://127.0.0.1:9001/#/wallboard', 'layui-icon-util\r\n', '8', '2017-11-17 16:56:59', '2018-08-20 10:43:13', '1', '0');
INSERT INTO `sys_menu` VALUES ('9', '-1', '文件中心', '#!files', 'files/files.html', 'layui-icon-file', '10', '2018-08-20 15:59:56', '2018-08-21 11:57:04', '1', '0');
INSERT INTO `sys_menu` VALUES ('10', '-1', '文档中心', '#!swagger', 'http://127.0.0.1:9200/swagger-ui.html', 'layui-icon-app', '9', '2018-08-20 13:57:45', '2018-08-22 17:58:13', '1', '0');
INSERT INTO `sys_menu` VALUES ('11', '1', '我的信息', '#!myInfo', 'system/myInfo.html', '', '10', '2018-08-20 13:39:23', '2018-08-21 11:56:12', '1', '1');
INSERT INTO `sys_menu` VALUES ('12', '-1', '认证中心', 'javascript:;', '', 'layui-icon-set', '1', '2018-08-21 12:48:13', '2018-08-21 12:48:13', '1', '0');
INSERT INTO `sys_menu` VALUES ('13', '12', 'token管理', '#!token', 'attestation/token.html', null, '10', '2018-08-16 17:03:04', '2018-08-16 17:03:04', '1', '0');
INSERT INTO `sys_menu` VALUES ('34', '12', '应用管理', '#!app', 'attestation/app.html', 'layui-icon-app', '11', '2018-08-22 16:05:24', '2018-08-22 16:05:24', '1', null);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
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

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'user:get/users', '用户查询', '2018-08-22 20:42:20', '2018-08-22 20:42:23');
INSERT INTO `sys_permission` VALUES ('2', 'user:post/users/saveOrUpdate', '用户-新增or更新', '2018-08-22 20:44:19', '2018-08-22 20:44:21');
INSERT INTO `sys_permission` VALUES ('3', 'user:post/users/exportUser', '用户导出数据', '2018-08-22 20:45:33', '2018-08-22 20:45:36');
INSERT INTO `sys_permission` VALUES ('4', 'user:post/users/{id}/resetPassword', '用户重置密码', '2018-08-22 20:46:25', '2018-08-22 20:46:27');
INSERT INTO `sys_permission` VALUES ('5', 'user:get/users/updateEnabled', '用户修改状态', '2018-08-22 20:47:36', '2018-08-22 20:47:38');
INSERT INTO `sys_permission` VALUES ('6', 'user:put/users/password', '用户修改密码', '2018-08-22 21:04:37', '2018-08-22 21:04:39');
INSERT INTO `sys_permission` VALUES ('7', 'user:put//users/me', '用户修改个人信息', '2018-08-22 21:07:04', '2018-08-22 21:07:08');
INSERT INTO `sys_permission` VALUES ('8', 'role:get/roles', '角色查询', '2018-08-22 21:20:42', '2018-08-22 21:20:45');
INSERT INTO `sys_permission` VALUES ('9', 'role:post/roles/saveOrUpdate', '角色-新增or更新', '2018-08-22 21:22:23', '2018-08-22 21:22:25');
INSERT INTO `sys_permission` VALUES ('10', 'role:delete/roles/{id}', '角色删除', '2018-08-22 21:28:00', '2018-08-22 21:28:02');
INSERT INTO `sys_permission` VALUES ('11', 'menu:get/menus/findAlls', '菜单查询', '2018-08-22 21:31:47', '2018-08-22 21:31:49');
INSERT INTO `sys_permission` VALUES ('12', 'menu:get/menus/findOnes', '获取一级菜单', '2018-08-22 21:32:39', '2018-08-22 21:32:42');
INSERT INTO `sys_permission` VALUES ('13', 'menu:post/menus/saveOrUpdate', '菜单-新增or更新', '2018-08-22 21:33:32', '2018-08-22 21:33:36');
INSERT INTO `sys_permission` VALUES ('14', 'menu:post/menus/granted', '给角色分配菜单', '2018-08-22 21:34:24', '2018-08-22 21:34:25');
INSERT INTO `sys_permission` VALUES ('15', 'menu:get/menus/{roleId}/menus', '根据roleId获取对应的菜单', '2018-08-22 21:35:36', '2018-08-22 21:35:37');
INSERT INTO `sys_permission` VALUES ('16', 'menu:delete/menus/{id}', '菜单删除', '2018-08-22 21:36:45', '2018-08-22 21:36:47');
INSERT INTO `sys_permission` VALUES ('17', 'menu:get/menus/current', '获取当前用户的菜单', '2018-08-22 21:38:35', '2018-08-22 21:38:37');
INSERT INTO `sys_permission` VALUES ('18', 'permission:delete/permissions/{id}', '权限删除', '2018-08-22 21:42:28', '2018-08-22 21:42:30');
INSERT INTO `sys_permission` VALUES ('19', 'permission:get/permissions', '权限查询', '2018-08-22 21:43:13', '2018-08-22 21:43:14');
INSERT INTO `sys_permission` VALUES ('20', 'permission:post/permissions/saveOrUpdate', '权限-新增or更新', '2018-08-22 21:44:08', '2018-08-22 21:44:09');
INSERT INTO `sys_permission` VALUES ('21', 'permission:get/permissions/{roleId}/permissions', '根据roleId获取对象的权限', '2018-08-22 21:45:01', '2018-08-22 21:45:02');
INSERT INTO `sys_permission` VALUES ('22', 'permission:post/permissions/granted', '给角色分配权限', '2018-08-22 21:46:01', '2018-08-22 21:46:03');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
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

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2018-08-10 21:45:29', '2018-08-10 21:45:32');
INSERT INTO `sys_role` VALUES ('2', 'test', 'asfsfdf', '2018-08-14 10:53:19', '2018-08-14 10:53:19');
INSERT INTO `sys_role` VALUES ('3', 'qqweqw', 'qqwe', '2018-08-14 11:03:36', '2018-08-14 11:03:36');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('1', '34');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('1', '2');
INSERT INTO `sys_role_user` VALUES ('9', '1');
INSERT INTO `sys_role_user` VALUES ('9', '2');
INSERT INTO `sys_role_user` VALUES ('9', '3');
INSERT INTO `sys_role_user` VALUES ('10', '1');
INSERT INTO `sys_role_user` VALUES ('10', '2');
INSERT INTO `sys_role_user` VALUES ('10', '3');
INSERT INTO `sys_role_user` VALUES ('11', '1');
INSERT INTO `sys_role_user` VALUES ('11', '2');
INSERT INTO `sys_role_user` VALUES ('11', '3');
INSERT INTO `sys_role_user` VALUES ('12', '1');
INSERT INTO `sys_role_user` VALUES ('12', '2');
INSERT INTO `sys_role_user` VALUES ('12', '3');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
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

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$Wtw81uu43fGKw9lkOr1RAOTNWxQIZBsB3YDwc/5yDnr/yeG5x92EG', '管理员', 'http://payo7kq4i.bkt.clouddn.com/QQ图片20170601094602.jpg', null, '0', '1', 'BACKEND', '2017-11-17 16:56:59', '2018-08-22 22:06:40');
INSERT INTO `sys_user` VALUES ('2', 'owen', '$2a$10$BD8FQRSJpg9DfU97Ct0jlea7WeFfHZjTDk/iNKSIaRkYchxzTM2au', '欧文', 'http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg', null, '1', '1', 'APP', '2017-11-17 16:56:59', '2017-11-17 16:56:59');
INSERT INTO `sys_user` VALUES ('3', 'user', '$2a$10$LUiAPxTB1m2pmYYUA1CskOWX2HAtEJeTC7jeOD6aU0BnU9ozH36.G', '体验用户', 'http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg', null, '1', '1', 'APP', '2017-11-17 16:56:59', '2018-08-22 21:06:06');
INSERT INTO `sys_user` VALUES ('4', 'test', '$2a$10$mKL47TyAli2Q1VP5UR3ZjOGLOQ0aIKA64yJGDceSiOX.QHnY2gvVG', '测试账户', 'http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg', null, '1', '1', 'APP', '2017-11-17 16:56:59', '2017-11-17 16:56:59');
INSERT INTO `sys_user` VALUES ('5', 'nancy', '$2a$10$cVhKfRxUHeBD.UgZ8F5lzu23USgWqZu0S.rMehjYT87rtBjbRwSvS', 'Nancy', 'http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg', null, '1', '1', 'APP', '2017-11-17 16:56:59', '2017-11-17 16:56:59');
INSERT INTO `sys_user` VALUES ('6', 'beckham', '$2a$10$w0QW4oJL6dgeubBb/LwT9.dcueqoccpnu/jxDpXODkP8dgkhO1Cu2', '贝克汉姆', 'http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg', null, '1', '1', 'APP', '2017-11-17 16:56:59', '2018-08-09 22:45:01');
