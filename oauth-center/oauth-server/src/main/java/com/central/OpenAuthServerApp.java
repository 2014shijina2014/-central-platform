/**
 * 
 */
package com.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.central.annotation.EnableLogging;

/** 
* @author owen 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
* 类说明 
*/
@EnableLogging
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OpenAuthServerApp {
	
	public static void main(String[] args) {
		SpringApplication.run(OpenAuthServerApp.class, args);
	}

}
