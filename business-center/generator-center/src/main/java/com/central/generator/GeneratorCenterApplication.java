package com.central.generator;

import com.central.annotation.EnableLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableLogging
@EnableDiscoveryClient
@SpringBootApplication
public class GeneratorCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorCenterApplication.class, args);
	}
}
