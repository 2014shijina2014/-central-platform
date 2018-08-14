package com.central.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/** 
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年12月8日 上午9:03:32 
* 类说明 
*/
@EnableAdminServer
@EnableDiscoveryClient 
@SpringBootApplication  
public class MonitorApp {
	public static void main(String[] args) {
		SpringApplication.run(MonitorApp.class, args);
	}

}
