package com.bearprogrammer.blog.webmvc.model;

import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This will create the required table for this example. You probably don't need
 * this.
 * 
 * @author Vinicius Isola (viniciusisola@gmail.com)
 */
@WebListener
public class CreateDatabaseListener implements ServletContextListener {
	
	private final static String [] INITIALIZE_DATABASE = {
		"create table Contact (id INTEGER PRIMARY KEY AUTO_INCREMENT, name varchar(200), email varchar(200))"
	};

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Create the database schema
		
		Connection conn = null;
		try {
			conn = Database.getConnection();
			
			System.out.println("Initializing database...");
			
			for (String query : INITIALIZE_DATABASE) {
				System.out.println("Executing query: " + query);
				conn.createStatement().executeUpdate(query);			
			}
			
			System.out.println("Database ready.");
		} catch (Exception e) {
			throw new RuntimeException("Error while creating database schema.", e);
		} finally {
			Database.closeConnection(conn);
		}
	}

}
