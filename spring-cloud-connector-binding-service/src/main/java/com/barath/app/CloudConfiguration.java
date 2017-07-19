package com.barath.app;


import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudConfiguration extends AbstractCloudConfig {
	
	@Bean
	public DataSource getDataSource() {
		DataSource dataSource= this.connectionFactory().dataSource();
		System.out.println("DataSOURCE =============>"+dataSource);
		return dataSource;
	}

}
