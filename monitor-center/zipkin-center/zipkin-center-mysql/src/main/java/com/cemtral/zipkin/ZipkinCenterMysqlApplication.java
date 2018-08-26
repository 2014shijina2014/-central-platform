package com.cemtral.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zipkin.server.internal.EnableZipkinServer;
import zipkin2.storage.mysql.v1.MySQLStorage;

import javax.sql.DataSource;
//import zipkin.server.internal.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer        //默认采用HTTP通信方式启动ZipkinServer
public class ZipkinCenterMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinCenterMysqlApplication.class, args);
	}

	@Bean
	public MySQLStorage mySQLStorage(DataSource datasource) {
		return MySQLStorage.newBuilder().datasource(datasource).executor(Runnable::run).build();
	}

}
