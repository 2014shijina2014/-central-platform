package com.open.capacity.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.open.capacity.common.Constants;

@Component
public class InitializeServletListener implements ServletContextListener {

	private Logger LOGGER = LoggerFactory.getLogger(InitializeServletListener.class);
	private String appName = null;
	
	
	public InitializeServletListener() {
//		System.setProperty(Constants.DEPLOY_ENVIRONMENT, "test");
//		System.setProperty(Constants.DEPLOYMENT_APPLICATION_ID, "mobile_zuul");
		System.setProperty(Constants.DEPLOY_CONFIG_URL, "http://127.0.0.1:8080/configfiles/zuulservice/default/application");			
		String applicationID = ConfigurationManager.getConfigInstance().getString(Constants.DEPLOYMENT_APPLICATION_ID);
		if (StringUtils.isEmpty(applicationID)) {
			LOGGER.warn("Using default config!");
			ConfigurationManager.getConfigInstance().setProperty(Constants.DEPLOYMENT_APPLICATION_ID, "zuulservice");
		}
		
		System.setProperty(DynamicPropertyFactory.ENABLE_JMX, "true");
		
        loadConfiguration();
//        configLog();
//        registerEureka();
	}
	
	private void loadConfiguration() {
		appName = ConfigurationManager.getDeploymentContext().getApplicationId();

		// Loading properties via archaius.
		if (null != appName) {
			try {
				LOGGER.info(String.format("Loading application properties with app id: %s and environment: %s", appName,
						ConfigurationManager.getDeploymentContext().getDeploymentEnvironment()));
				ConfigurationManager.loadCascadedPropertiesFromResources(appName);
			} catch (IOException e) {
				LOGGER.error(String.format(
						"Failed to load properties for application id: %s and environment: %s. This is ok, if you do not have application level properties.",
						appName, ConfigurationManager.getDeploymentContext().getDeploymentEnvironment()), e);
			}
		} else {
			LOGGER.warn(
					"Application identifier not defined, skipping application level properties loading. You must set a property 'archaius.deployment.applicationId' to be able to load application level properties.");
		}

	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	 
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
}