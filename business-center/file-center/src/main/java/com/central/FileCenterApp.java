package com.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

 
/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 文件中心
*/
@EnableDiscoveryClient
@SpringBootApplication
public class FileCenterApp {

	public static void main(String[] args) {
		SpringApplication.run(FileCenterApp.class, args);
	}

}