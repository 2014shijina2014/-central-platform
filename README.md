# central-platform

#### 项目介绍
从无到有搭建企业级微服务框架，为企业级项目提供一套完善的，运行稳定，多种分布式问题的解决方案;

central-platform简称CP，基于Spring Cloud(Finchley.RELEASE) 、Spring Security jwt开发
基于layui前后分离的开发平台,其中包括Gateway网关、Oauth认证服务、User用户服务、
Eureka注册中心等多个服务; 为微服务开发所需配置管理、服务发现、断路器、智能路由、
微代理等；

### 组织结构

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
├		├── eureka-server	--服务注册中心			 [1111]  
└		└── open-eureka-client    --注册服务样例工程    [7768] 
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




#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)