package com.bearprogrammer.blog.spring.cache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Example service implementation. Loads and insert data in the database.
 * 
 * @author Vinicius Isola (viniciusisola@gmail.com)
 */
public class ExampleServiceImpl implements ExampleService {
	
	@Autowired
	private DataSource dataSource;
	
	public void initialize () throws Exception {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			StringBuilder createTable = new StringBuilder();
			createTable.append("create table properties (");
			createTable.append(" name varchar(200) primary key");
			createTable.append(" , value varchar(200)");
			createTable.append(")");
			
			conn.createStatement().executeUpdate(createTable.toString());
			
			String insert = "insert into properties values ('test', 'Some value')";
			conn.createStatement().executeUpdate(insert);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * Loads all data from the database.
	 * 
	 * @throws Exception
	 *             If any problem happens while reading the file.
	 */
	public Map<String, String> getAll() throws Exception {
		System.out.println("Service: Loading data from database...");
		Map<String, String> result = new HashMap<String, String>();
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String query = "Select name, value from properties";
			ResultSet rs = conn.createStatement().executeQuery(query);
			while (rs.next()) {
				result.put(rs.getString("name"), rs.getString("value"));
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		// Add a performance hit here, memory database is too fast
		Thread.sleep(200);
		
		System.out.println("Service: Finished loading data.");
		return result;
	}

	public void save(String name, String value) throws Exception {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			String checkExists = "select 1 from properties where name = ?";
			PreparedStatement ps = conn.prepareStatement(checkExists);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			String update;
			if (rs.next()) { // If record exist
				update = "update properties set value = ? where name = ?";
				
			} else { // If no record
				update = "insert into properties (value, name) values (?, ?)";
			}
			
			ps = conn.prepareStatement(update);
			ps.setString(1, value);
			ps.setString(2, name);
			ps.executeUpdate();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
