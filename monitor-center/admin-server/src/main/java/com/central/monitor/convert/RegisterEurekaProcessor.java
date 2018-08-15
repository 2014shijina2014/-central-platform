package com.central.monitor.convert;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import de.codecentric.boot.admin.server.cloud.discovery.EurekaServiceInstanceConverter;
@Component
public class RegisterEurekaProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// 置换代理对象
		if(bean instanceof EurekaServiceInstanceConverter){
			return new EurekaConverter();
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	
}
