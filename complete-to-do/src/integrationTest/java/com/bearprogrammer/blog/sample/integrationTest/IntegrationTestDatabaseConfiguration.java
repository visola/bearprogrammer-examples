package com.bearprogrammer.blog.sample.integrationTest;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.bearprogrammer.blog.sample.config.DataSourceFromEnvironmentConfiguration;

@Configuration
@Profile("test")
@PropertySource("classpath:integrationTestDatabase.properties")
public class IntegrationTestDatabaseConfiguration extends DataSourceFromEnvironmentConfiguration {
	
	@Bean(initMethod = "migrate", name="flyway")
    public Flyway getFlyway(DatabaseCleaner cleaner, DataSource dataSource) throws Exception {
		cleaner.cleanDatabase();
		
		Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations("classpath:db/migration","classpath:testdb/migration");
        return flyway;
    }
	
}