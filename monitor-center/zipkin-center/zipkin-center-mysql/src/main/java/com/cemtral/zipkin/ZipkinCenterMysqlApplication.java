package com.cemtral.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;


//@EnableEurekaClient
@SpringBootApplication
@EnableZipkinServer        //默认采用HTTP通信方式启动ZipkinServer
public class ZipkinCenterMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinCenterMysqlApplication.class, args);
	}

}
