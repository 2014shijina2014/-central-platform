
package com.central.client.oauth2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.central.model.properties.PermitUrlProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2018年4月5日 下午19:52:21
 */
@Component
@Configuration
@EnableResourceServer
//开启spring security 注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(PermitUrlProperties.class)
public class OAuth2ClientConfig extends ResourceServerConfigurerAdapter{

 
		//对应oauth_client_details的 resource_ids字段 如果表中有数据 client_id只能访问响应resource_ids的资源服务器
		private static final String DEMO_RESOURCE_ID = "test";
		
		@Resource 
		private ObjectMapper objectMapper ; //springmvc启动时自动装配json处理类

		@Autowired(required = false)
		private TokenStore redisTokenStore;

		@Autowired(required = false)
		private JwtTokenStore jwtTokenStore;
		@Autowired(required = false)
		private JwtAccessTokenConverter jwtAccessTokenConverter;
		
		
		@Autowired
		private AuthenticationEntryPoint authenticationEntryPoint;
		
		@Autowired
		private OAuth2WebSecurityExpressionHandler expressionHandler;
		@Autowired
		private OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler;
		@Autowired
		private PermitUrlProperties permitUrlProperties ;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

			if (jwtTokenStore != null) {
				resources.tokenStore(jwtTokenStore);
			} else if (redisTokenStore != null) {
				resources.tokenStore(redisTokenStore);
			}
			resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
			
			resources.authenticationEntryPoint(authenticationEntryPoint) ;
			
			resources.expressionHandler(expressionHandler);
			resources.accessDeniedHandler(oAuth2AccessDeniedHandler);
		}
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/doc.html");
		}
		
		@Override
		public void configure(HttpSecurity http) throws Exception {

			http.csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().formLogin()
			// 对请求授权
			.and().authorizeRequests()
			.antMatchers(permitUrlProperties.getHttp_urls()).permitAll()
			.and().requestMatchers()
			.and().authorizeRequests().anyRequest().authenticated().and().httpBasic();
			
			 

			
		}
		
		
}
