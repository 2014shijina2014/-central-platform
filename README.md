# central-platform

#### 项目介绍
从无到有搭建企业级微服务框架，为企业级项目提供一套完善的，运行稳定，多种分布式问题的解决方案;

central-platform简称CP，基于Spring Cloud(Finchley.RELEASE) 、Spring Boot(2.0.1)、Spring Security jwt开发
基于layui前后分离的开发平台,其中包括Gateway网关、Oauth认证服务、User用户服务、
Eureka注册中心等多个服务, 为微服务开发所需配置管理、服务发现、断路器、智能路由、
微代理等,努力为企业级打造最全面的微服务开发解决方案;


#### 组织结构
central-platform

```
├    api-commons  -- 封装基本的数据结构与Model层    
├    api-gateway    -- SpringCloud网关	 [9200]
├    business-center   -- 逻辑业务层		   
├         ├  	back-center  -- 后台项目(前端项目)   [8066]
├         ├     file-center  -- 文件中心  			[5000]
├         └     user-center  -- 用户中心 			[7000]  
├    db-core 	-- 数据库逻辑封装       
├    job-center -- 分布式调度任务
├       ├── job-core  --核心库
├       ├── job-admin   --job管理器    
├       └── job-demo    --job执行器
├     monitor-center  -- 监控中心
├       ├── admin-server  -- spring boot admin server [9001]  
├     oauth-center		--oauth2套件 认证服务
├       └── oauth-server    --oauth认证中心			   [8000] 
├     register-center	--eureka服务注册发现套件 
├		├── eureka-server	--服务注册中心			  [1111]  
├		└── open-eureka-client    --注册服务样例工程    [7768] 
└	  sql				--存放sql脚本

```



#### 开发工具:

- MySql: 数据库
- Tomcat: 应用服务器
- SVN|Git: 版本管理
- Nginx: 反向代理服务器
- IntelliJ IDEA/STS-3.8.0.RELEASE : 开发IDE/STS
- Navicat for MySQL: 数据库客户端



### 开发环境

JDK8+

READIS 3.X

MySQL 5.6 +

MAVEN 3.3.9


#### 技术介绍 




#### 安装教程

1. ​

#### 使用说明

1. ​

#### 截图预览 

#### 参与贡献

1. ​


#### 码云特技

1. ​