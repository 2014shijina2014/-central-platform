package com.cemtral.zipkin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.storage.mysql.v1.MySQLStorage;

import javax.sql.DataSource;

/**
 * @Author: [zhangzhiguang]
 * @Date: [2018-08-26 19:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */
@Configuration
public class ZipkinConfig {

    @Bean
    public MySQLStorage mySQLStorage(DataSource datasource) {
        return MySQLStorage.newBuilder().datasource(datasource).executor(Runnable::run).build();
    }

}
