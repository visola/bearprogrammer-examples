package com.bearprogrammer.blog.sample.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner {
	
	@Autowired
	DataSource dataSource;
	
	public void cleanDatabase() throws Exception {
		/* 
		 * For this example, cleaning the database is simple, just delete all tables
		 * This depends on the application and how the data is structured. 
		 */
		try (Connection conn = dataSource.getConnection()) {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet tables = meta.getTables(null, null, "%", null);
			while (tables.next()) {
				String dropTable = String.format("drop table %s", tables.getString("TABLE_NAME"));
				conn.prepareStatement(dropTable).execute();
			}
		}
	}

}
