# 导出 oauth-center 的数据库结构
CREATE DATABASE IF NOT EXISTS `oauth-center` DEFAULT CHARACTER SET = utf8mb4;
Use `oauth-center`;
 



#
# Structure for table "oauth_client_details"
#

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(48) NOT NULL COMMENT '应用标识',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源限定串(逗号分割)',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
  `client_secret_str` varchar(256) DEFAULT NULL COMMENT '应用密钥(明文)',
  `scope` varchar(256) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '回调地址 ',
  `authorities` varchar(256) DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期',
  `additional_information` varchar(4096) DEFAULT '{}' COMMENT '{}',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '是否自动授权 是-true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "oauth_client_details"
#

INSERT INTO `oauth_client_details` VALUES (1,'app',NULL,'$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO','app','app','password,refresh_token',NULL,NULL,180000,NULL,'{}','true'),(2,'mobile','mobile,test','$2a$10$ULxRssv/4NWOc388lZFbyus3IFfsbcpG/BZOq4TRxDhsx5HHIR7Jm','mobile','all','password,refresh_token',NULL,NULL,180000,NULL,'{}','true'),(3,'test','test','$2a$10$dETnZPnzikOSdBS0wGqdqeDR4Av.V15/aUcW3qCMifNT/dRRJH4Wq','test','test','password,refresh_token',NULL,NULL,180000,NULL,'{}','true'),(4,'webApp',NULL,'$2a$10$06msMGYRH8nrm4iVnKFNKOoddB8wOwymVhbUzw/d3ZixD7Nq8ot72','webApp','app','authorization_code,password,refresh_token,client_credentials',NULL,NULL,180000,NULL,'{}','true'),(5,'beck','','$2a$10$56LGyH.2wOFNNp3ScUkspOMdyRnenYhnWEnfI0itIFfsXsd5ZhKh.','beck','all','authorization_code,password,refresh_token,client_credentials','http://www.baidu.com','',180000,NULL,'{}','true'),(6,'owen',NULL,'$2a$10$a1ZEXiZQr604LN.wVxet.etPm6RvDs.HIaXP48J2HKRaEnZORTVwe','owen','app','authorization_code,password,refresh_token,client_credentials','http://127.0.0.1:9997/clientOne/login',NULL,180000,NULL,'{}','true');

#
# Structure for table "sys_client_permission"
#

DROP TABLE IF EXISTS `sys_client_permission`;
CREATE TABLE `sys_client_permission` (
  `clientId` int(11) NOT NULL COMMENT '应用标识',
  `permissionId` int(11) NOT NULL COMMENT '服务权限标识',
  PRIMARY KEY (`clientId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_client_permission"
#

INSERT INTO `sys_client_permission` VALUES (1,71),(1,72),(1,73),(1,74),(1,75),(2,71),(2,72),(2,73),(2,74),(2,75),(3,71),(3,72),(3,73),(3,74),(3,75),(4,71),(4,72),(4,73),(4,74),(4,75),(5,71),(5,72),(5,73),(5,74),(5,75);

#
# Structure for table "sys_services"
#

DROP TABLE IF EXISTS `sys_services`;
CREATE TABLE `sys_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务ID',
  `parentId` int(11) NOT NULL COMMENT '父服务ID',
  `name` varchar(50) NOT NULL COMMENT '服务名称',
  `css` varchar(30) DEFAULT NULL COMMENT 'css',
  `href` varchar(1000) DEFAULT NULL COMMENT '服务地址',
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) DEFAULT NULL COMMENT '服务权限标识符',
  `sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_services"
#

INSERT INTO `sys_services` VALUES (71,0,'授权服务','','/auth',1,'',1),(72,71,'用户授权token','','/auth/user/attestation',1,'',100),(73,71,'应用申请token','','/auth/client/attestation',1,'',100),(74,0,'测试微服务','','/client',1,'',100),(75,74,'hello接口','','/client/hello',1,'',100);
