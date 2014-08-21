package com.bearprogrammer.blog.sample.integrationTest.glue;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.bearprogrammer.blog.sample.integrationTest.IntegrationTestConfiguration;
import com.bearprogrammer.blog.sample.test.DatabaseCleaner;

import cucumber.api.java.Before;

@ContextConfiguration(classes={IntegrationTestConfiguration.class})
public class DatabaseCleanerHook {
	
	@Autowired
	DatabaseCleaner databaseCleaner;
	
	@Autowired
	Flyway flyway;
	
	@Before(order=1000)
	public void cleanDatabase() throws Exception {
		databaseCleaner.cleanDatabase();
		
		flyway.migrate();
	}

}
