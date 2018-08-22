package com.central.client.oauth2.config;

import org.springframework.context.annotation.Configuration;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2018年4月5日 下午19:52:21 类说明
 * swagger 聚合文档配置
 * zuul routers 映射具体服务的/v2/api-docs swagger 
 */
@Configuration
@EnableSwaggerButler
public class SwaggerConfig {

}
