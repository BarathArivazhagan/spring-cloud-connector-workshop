package com.barath.app;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

@Configuration
public class CloudEnvironmentInspector {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ConfigurableEnvironment env;
	
	
	@PostConstruct
	public void check(){
		//System.out.println("Datasource "+dataSource);
		
		String appName=env.getProperty("spring.cloud.appId");
		System.out.println("App name "+appName);
		
		MutablePropertySources propertySources = env.getPropertySources();
		PropertySource source=propertySources.get("vcap");
		System.out.println("SOURCE "+source);
		propertySources.forEach( prop -> {
			System.out.println(prop.getSource());
		});
		 propertySources.forEach(propertySource -> {
	            if (propertySource.getSource() instanceof Map) {
	                // it will print systemProperties, systemEnvironment, application.properties and other overrides of
	                // application.properties
	               // System.out.println("#######" + propertySource.getName() + "#######");

	                final Map<String, String> properties = mapValueAsString((Map<String, Object>) propertySource.getSource());
	               // System.out.println(properties);
	            }
	        });
	    }

	    private Map<String, String> mapValueAsString(
	        final Map<String, Object> map) {

	        return map.entrySet().stream()
	            .collect(Collectors.toMap(entry -> entry.getKey(), entry -> toString(entry.getValue())));
	    }
	    
	    private String toString(
	            final Object object) {

	            return Optional.ofNullable(object).map(value -> value.toString()).orElse(null);
	        }

}
