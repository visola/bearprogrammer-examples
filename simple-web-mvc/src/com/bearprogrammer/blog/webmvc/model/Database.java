package com.bearprogrammer.blog.webmvc.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * Handles all database connections.
 * 
 * @author Vinicius Isola (viniciusisola@gmail.com)
 */
public final class Database {
	
	/**
	 * A single instance of the connection pool will
	 * exist for the whole application.
	 */
	private static BasicDataSource dataSource;
	
	/**
	 * Private constructor, cannot be instantiated.
	 */
	private Database () {
		throw new UnsupportedOperationException("This class should not be instantiated.");
	}
	
	/**
	 * Handles closing a connection.
	 * 
	 * @param conn
	 *            The connection that should be closed.
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqle) {
			// Do nothing
		}
	}
	
	/**
	 * Get the instance of the connection pool. This is synchronized because we
	 * are working on the servlet world and it can be a highly concurrent.
	 * 
	 * @return The unique instance of the connection pool.
	 */
	public synchronized static DataSource getDataSource() {
		if (dataSource == null) {
				// This normally is read from a properties file
				dataSource = new BasicDataSource();
				dataSource.setPassword("sa");
				dataSource.setUsername("sa");
				dataSource.setUrl("jdbc:h2:mem:test");
				dataSource.setDriverClassName("org.h2.Driver");
		}
		
		return dataSource;
	}
	
	/**
	 * Get a new connection from the pool.
	 * 
	 * @return New connection from the pool.
	 * @throws SQLException
	 *             If any problem happens while getting the connection.
	 */
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

}
