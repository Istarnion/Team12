package no.hist.aitel.team12.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection implements Database {

	private Connection connection;
	
	boolean connect() {
		boolean ok = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://hist.tilfeldig.info/supershoppingsurfer_bronze?"
					+ "user=team12&password=teamadmin12");
			
			ok = testConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
			ok = false;
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			ok = false;
		}
		
		
		
		return ok;
	}
	
	@Override
	public String getPasswordHash(String user) {
		return null;
	}
	
	@Override
	public boolean testConnection() {
		boolean ok;
		try(PreparedStatement statement = connection.prepareStatement("SHOW TABLES")) {
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				System.out.println(result.getString(1));
			}
			result.close();
			
			ok = true;
		}
		catch (SQLException e) {
			ok = false;
		}
		
		return ok;
	}
	
	@Override
	public void teardown() {
		try {
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
