package com.central.model.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
* url白名单处理 
* application.yml中配置需要放权的url白名单
 */
@ConfigurationProperties(prefix = "permit")
public class PermitUrlProperties {
	
	 /**
     * 监控中心和swagger需要访问的url
     */
    private static final String[] ENDPOINTS = {"/**/actuator/health", "/**/actuator/env", "/**/actuator/metrics/**", "/**/actuator/trace", "/**/actuator/dump",
            "/**/actuator/jolokia", "/**/actuator/info", "/**/actuator/logfile", "/**/actuator/refresh", "/**/actuator/flyway", "/**/actuator/liquibase",
            "/**/actuator/heapdump", "/**/actuator/loggers", "/**/actuator/auditevents", "/**/actuator/env/PID", "/**/actuator/jolokia/**",
            "/**/v2/api-docs/**", "/**/swagger-ui.html", "/**/swagger-resources/**", "/**/webjars/**" ,"/**/druid/**" };
    
	private String[]  http_urls;
	
	public String[] getHttp_urls() {
		if (http_urls == null || http_urls.length == 0) {
            return ENDPOINTS;
        }

        List<String> list = new ArrayList<>();
        for (String url : ENDPOINTS) {
            list.add(url);
        }
        for (String url : http_urls) {
            list.add(url);
        }

        return list.toArray(new String[list.size()]);
	}


	public void setHttp_urls(String[] http_urls) {
		this.http_urls = http_urls;
	}
	 

	private String[]  oauth_urls;
	
	 public String[] getOauth_urls() {
		 if (oauth_urls == null || oauth_urls.length == 0) {
	            return ENDPOINTS;
	        }

	        List<String> list = new ArrayList<>();
	        for (String url : ENDPOINTS) {
	            list.add(url);
	        }
	        for (String url : oauth_urls) {
	            list.add(url);
	        }

	        return list.toArray(new String[list.size()]);
	}


	/**
     * 需要放开权限的url
     *
     * @param urls 自定义的url
     * @return 自定义的url和监控中心需要访问的url集合
     */
	

	public void setOauth_urls(String[] oauth_urls) {
		this.oauth_urls = oauth_urls;
	}
     
	 
}
