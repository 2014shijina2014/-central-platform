# central-platform

#### 项目介绍
从无到有搭建企业级微服务框架，为企业级项目提供一套完善的，运行稳定，多种分布式问题的解决方案;

central-platform简称CP，基于Spring Cloud(Finchley.RELEASE) 、Spring Boot(2.0.1)、Spring Security jwt开发
基于layui前后分离的开发平台,其中包括Gateway网关、Oauth认证服务、User用户服务、
Eureka注册中心等多个服务, 为微服务开发所需配置管理、服务发现、断路器、智能路由、
微代理等,努力为企业级打造最全面的微服务开发解决方案;


# 组织结构
central-platform

| 名称      | 项目名称            | 说明                                       |
| ------- | --------------- | ---------------------------------------- |
| API工具包  | api-commons     | 存放Model层，和部分工具类                          |
| Cloud网关 | api-gateway     | 基于Spring Cloud构建gateway网关服务，采用OAuth2.0认证体系，管理所有服务的负载，可以集群部署； |
| 业务中心    | business-center | 主要包括前端项目，用户中心，文件中心等服务                    |
| 配置中心    | config-center   | 配置中心，管理整个微服务的配置;                         |
| 数据封装    | db-core         | 数据库逻辑封装                                  |
| 任务中心    | job-center      | 基于xxl-job实现的Demo，可以直接使用                  |
| 监控中心    | monitor-center  | 基于Spring Boot Admin集成Turbine,Hystrix，对应用状态进行监控，对服务调用进行追踪和对熔断进行监测;集成zipkin链式追踪服务 |
| 认证中心    | oauth-center    | 基于SpringSecurity进行安全认证，采用OAuth2.0认证体系，对客户端、用户进行认证及授权，支持多种模式； |
| 注册中心    | register-center | 采用Euraka构建服务注册中心，负责服务注册于发现               |
| 脚本模块    | sql             | 存放sql脚本                                  |



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