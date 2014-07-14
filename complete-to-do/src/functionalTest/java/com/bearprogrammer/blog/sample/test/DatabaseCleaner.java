package com.bearprogrammer.blog.sample.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner {
	
	/* 
	 * For this example, cleaning the database is simple, just delete all tables
	 * in a specific order to respect foreign key relationships.
	 * This depends on the application and how the data is structured. 
	 */
	private static final String [] TABLES_TO_DROP = {
		"schema_version",
		"ToDoHistory","ToDo",
		"User_Authority","Authority","User"
	};
	
	Logger logger = LoggerFactory.getLogger(DatabaseCleaner.class);
	
	@Autowired
	DataSource dataSource;
	
	public void cleanDatabase() throws Exception {
		logger.info("Cleaning database");
		try (Connection conn = dataSource.getConnection()) {
			for (String table : TABLES_TO_DROP) {
				logger.debug("Dropping table '{}'", table);
				conn.prepareStatement(String.format("drop table if exists %s", table)).execute();
			}
		}
	}

}
