
package com.central.server.oauth2;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.central.model.properties.PermitUrlProperties;
import com.central.server.oauth2.client.RedisClientDetailsService;
import com.central.server.oauth2.code.RedisAuthorizationCodeServices;
import com.central.server.oauth2.token.store.RedisTemplateTokenStore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author owen 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 */
@Configuration
public class OAuth2ServerConfig {

	private Logger logger = LoggerFactory.getLogger(OAuth2ServerConfig.class);

	@Resource
	private DataSource dataSource;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Bean // 声明 ClientDetails实现
	@ConditionalOnProperty(prefix = "security.oauth2.token.store", name = "type", havingValue = "redis", matchIfMissing = true)
	public RedisClientDetailsService clientDetailsService() {
		RedisClientDetailsService clientDetailsService = new RedisClientDetailsService(dataSource);
		clientDetailsService.setRedisTemplate(redisTemplate);
		return clientDetailsService;
	}

	// @Bean
	// public ApprovalStore approvalStore() {
	// return new JdbcApprovalStore(dataSource);
	// }

	@Bean
	public RandomValueAuthorizationCodeServices authorizationCodeServices() {
		RedisAuthorizationCodeServices redisAuthorizationCodeServices = new RedisAuthorizationCodeServices();
		redisAuthorizationCodeServices.setRedisTemplate(redisTemplate);
		return redisAuthorizationCodeServices;
	}

	/**
	 * @author owen 624191343@qq.com
	 * @version 创建时间：2017年11月12日 上午22:57:51 默认token存储在内存中
	 * DefaultTokenServices默认处理
	 */
	@Component
	@Configuration
	@EnableAuthorizationServer
	@AutoConfigureAfter(AuthorizationServerEndpointsConfigurer.class)
	public class UnieapAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
		/**
		 * 注入authenticationManager 来支持 password grant type
		 */
		@Autowired
		private AuthenticationManager authenticationManager;

		@Resource
		private ObjectMapper objectMapper; // springmvc启动时自动装配json处理类

		@Autowired
		private UserDetailsService userDetailsService;
		@Autowired(required = false)
		private RedisTemplateTokenStore redisTokenStore;

		@Autowired(required = false)
		private JwtTokenStore jwtTokenStore;
		@Autowired(required = false)
		private JwtAccessTokenConverter jwtAccessTokenConverter;

		@Autowired
		private WebResponseExceptionTranslator webResponseExceptionTranslator;

		@Autowired
		private RedisClientDetailsService clientDetailsService;

		@Autowired(required = false)
		private RandomValueAuthorizationCodeServices authorizationCodeServices;

		// 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

			if (jwtTokenStore != null) {
				endpoints.tokenStore(jwtTokenStore).authenticationManager(authenticationManager)
						.userDetailsService(userDetailsService); // 支持
				// password
				// grant
				// type;
			} else if (redisTokenStore != null) {
				endpoints.tokenStore(redisTokenStore).authenticationManager(authenticationManager)
						.userDetailsService(userDetailsService); // 支持
				// password
				// grant
				// type;
			}

			if (jwtAccessTokenConverter != null) {
				endpoints.accessTokenConverter(jwtAccessTokenConverter);
			}

			endpoints.authorizationCodeServices(authorizationCodeServices);

			endpoints.exceptionTranslator(webResponseExceptionTranslator);

		}

		// 配置应用名称 应用id
		// 配置OAuth2的客户端相关信息
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

			// if(clientDetailsService!=null){
			// clients.withClientDetails(clientDetailsService);
			// }else{
			// clients.inMemory().withClient("neusoft1").secret("neusoft1")
			// .authorizedGrantTypes("authorization_code", "password",
			// "refresh_token").scopes("all")
			// .resourceIds(SERVER_RESOURCE_ID).accessTokenValiditySeconds(1200)
			// .refreshTokenValiditySeconds(50000)
			// .and().withClient("neusoft2").secret("neusoft2")
			// .authorizedGrantTypes("authorization_code", "password",
			// "refresh_token").scopes("all")
			// .resourceIds(SERVER_RESOURCE_ID).accessTokenValiditySeconds(1200)
			// .refreshTokenValiditySeconds(50000)
			// ;
			// }
			clients.withClientDetails(clientDetailsService);
			clientDetailsService.loadAllClientToCache();
		}

		// 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

			security.tokenKeyAccess("permitAll()") /// url:/oauth/token_key,exposes
													/// public key for token
													/// verification if using
													/// JWT tokens
					.checkTokenAccess("isAuthenticated()") // url:/oauth/check_token
															// allow check token
					.allowFormAuthenticationForClients();

			// security.allowFormAuthenticationForClients();
			//// security.tokenKeyAccess("permitAll()");
			// security.tokenKeyAccess("isAuthenticated()");
		}

	}

	@Configuration
	@EnableResourceServer
	@EnableConfigurationProperties(PermitUrlProperties.class)
	public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

		private static final String DEMO_RESOURCE_ID = "api-auth";
		@Autowired
		private PermitUrlProperties permitUrlProperties;

		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/health");
			web.ignoring().antMatchers("/oauth/user/token");
			web.ignoring().antMatchers("/oauth/client/token");
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.requestMatcher(
				/**
				 * 判断来源请求是否包含oauth2授权信息
				*/	
				new RequestMatcher() {
					private AntPathMatcher antPathMatcher = new AntPathMatcher();
					@Override
					public boolean matches(HttpServletRequest request) {
						// 请求参数中包含access_token参数
						if (request.getParameter(OAuth2AccessToken.ACCESS_TOKEN) != null) {
							return true;
						}
	
						// 头部的Authorization值以Bearer开头
						String auth = request.getHeader("Authorization");
						if (auth != null) {
							if (auth.startsWith(OAuth2AccessToken.BEARER_TYPE)) {
								return true;
							}
						}
						
						
						String url = request.getRequestURI() ;
						
						if (antPathMatcher.match(request.getRequestURI(),  "/oauth/userinfo")) {
	                            return true;
	                    }
						if (antPathMatcher.match(request.getRequestURI(),  "/oauth/remove/token")) {
                            return true;
						}
						if (antPathMatcher.match(request.getRequestURI(),  "/oauth/get/token")) {
							return true;
						}
						if (antPathMatcher.match(request.getRequestURI(),  "/oauth/refresh/token")) {
							return true;
						}
						
						
						
						
						return false;
				}
			}

			).authorizeRequests().antMatchers(permitUrlProperties.getOauth_urls()).permitAll().anyRequest()
					.authenticated();
		}

	}

}
