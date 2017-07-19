package com.barath.app;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CloudEnvironmentInspector {
	
	//@Autowired
	private DataSource dataSource;
	
	@Autowired
	private Environment env;
	
	
	@PostConstruct
	public void check(){
		//System.out.println("Datasource "+dataSource);
		
		String appName=env.getProperty("spring.cloud.appId");
		System.out.println("App name "+appName);
	}

}
