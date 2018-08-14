package com.central.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.central.autoconfigure.LoggingConfigurationSelector;



/**
 * 启动日志框架支持
 * @author owen
 * @create 2017年7月2日
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//自动装配starter
@Import(LoggingConfigurationSelector.class)
public @interface EnableLogging{
//	String name() ;
}