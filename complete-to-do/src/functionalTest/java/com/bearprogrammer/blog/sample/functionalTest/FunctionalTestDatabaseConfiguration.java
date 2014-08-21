package com.bearprogrammer.blog.sample.functionalTest;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.bearprogrammer.blog.sample.config.DataSourceFromEnvironmentConfiguration;
import com.bearprogrammer.blog.sample.test.DatabaseCleaner;

@Configuration
@PropertySource("classpath:functionalTestDatabase.properties")
public class FunctionalTestDatabaseConfiguration extends DataSourceFromEnvironmentConfiguration {
	
	@Bean(initMethod="migrate")
    public Flyway flyway(DatabaseCleaner cleaner, DataSource dataSource) throws Exception {
		cleaner.cleanDatabase();
		
		Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        return flyway;
    }
	
}