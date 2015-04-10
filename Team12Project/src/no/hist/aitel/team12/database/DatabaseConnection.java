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
	public void teardown() {
		try {
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getPasswordHash(int user) {
		String passwordHash = null;
		try(PreparedStatement statement = connection.prepareStatement("SELECT password_hash FROM person WHERE employee_number = ?")) {
			statement.setInt(1, user);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				passwordHash = result.getString(1);
			}
			result.close();
		}
		catch (SQLException e) {
			e.printStackTrace();																										
		}
		return passwordHash;																											
	}

	@Override
	public boolean testConnection() {
		boolean ok;
		try(PreparedStatement statement = connection.prepareStatement("SHOW TABLES")) {

			ResultSet result = statement.executeQuery();

			while(result.next()) {

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
	public String[][] getShoppingCentreData() {
		String[][] data = null;

		int rows, cols;

		try(PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM shoppingcentre LEFT JOIN business USING (business_id) LEFT JOIN zipcode USING (zipcode) LEFT JOIN municipality	USING (municipality_id) LEFT JOIN county USING (county_id)"
				)) {

			ResultSet result = statement.executeQuery();
			result.last();
			rows = result.getRow();
			result.beforeFirst();
			cols = result.getMetaData().getColumnCount();

			data = new String[rows][cols];


			for(int i=0; i<rows; i++) {
				result.next();
				for(int j=0; j<cols; j++) {
					data[i][j] = result.getString(j+1);
				}
			}
			result.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}
}
