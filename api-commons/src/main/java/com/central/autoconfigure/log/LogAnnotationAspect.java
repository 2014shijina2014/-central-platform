package com.central.autoconfigure.log;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import com.alibaba.fastjson.JSONObject;
import com.central.annotation.log.LogAnnotation;
import com.central.log.service.LogService;
import com.central.log.service.impl.LogServiceImpl;
import com.central.log.util.SpringUtils;
import com.central.model.common.utils.SysUserUtil;
import com.central.model.log.SysLog;
import com.central.model.user.LoginAppUser;

 
/**
 * 保存日志
 * @author owen
 * @create 2017年7月2日
 */
@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
public class LogAnnotationAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAnnotationAspect.class);

    @Around("@annotation(ds)")
    public Object logSave (ProceedingJoinPoint joinPoint, LogAnnotation ds) throws Throwable {
    	
    	
    	 SysLog log = new SysLog();
         log.setCreateTime(new Date());
         LoginAppUser loginAppUser = SysUserUtil.getLoginAppUser();
         if (loginAppUser != null) {
             log.setUsername(loginAppUser.getUsername());
         }

         MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
         LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
         log.setModule(logAnnotation.module());

         if (logAnnotation.recordRequestParam()) {
             String[] paramNames = methodSignature.getParameterNames();// 参数名
             if (paramNames != null && paramNames.length > 0) {
                 Object[] args = joinPoint.getArgs();// 参数值

                 Map<String, Object> params = new HashMap<>();
                 for (int i = 0; i < paramNames.length; i++) {
                	 
                	 if(paramNames[i] instanceof Serializable){
                		 params.put(paramNames[i], args[i]);
                	 }
                	 
                     
                 }

                 try {
                     log.setParams(JSONObject.toJSONString(params));
                     
                     
                 } catch (Exception e) {
                     logger.error("记录参数失败：{}", e.getMessage());
                 }
             }
         }
         
         try {
        	// 调用原来的方法
        	 Object object = joinPoint.proceed();
             log.setFlag(Boolean.TRUE);

             return object;
         } catch (Exception e) {
             log.setFlag(Boolean.FALSE);
             log.setRemark(e.getMessage());
             
             
             
             throw e;
         } finally {
             
             CompletableFuture.runAsync(() -> {
            	 try {
     				LogService logService = SpringUtils.getBean(LogServiceImpl.class);
     				 logService.save(log);
     			} catch (Exception e) {
     				 logger.error("记录参数失败：{}", e.getMessage());
     			}
            	 
             });

         }

         
    }
   
    	
      
}