package com.bearprogrammer.blog.sample.repository;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bearprogrammer.blog.sample.config.ApplicationConfiguration;
import com.bearprogrammer.blog.sample.test.DatabaseCleaner;
import com.bearprogrammer.blog.sample.test.FunctionalTestDatabaseConfiguration;

@ComponentScan("com.bearprogrammer.blog.sample.test")
@ContextConfiguration(classes={ApplicationConfiguration.class, FunctionalTestDatabaseConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class BaseFunctionalTest {
	
	@Autowired
	DatabaseCleaner cleaner;
	
	@Autowired
	Flyway flyway;

	@Before
	public void baseSetup() throws Exception {
		cleaner.cleanDatabase();
		flyway.migrate();
	}

}
