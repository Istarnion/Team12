package no.hist.aitel.team12.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import no.hist.aitel.team12.app.Address;
import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.UserType;

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
	public ShoppingCentre[] getShoppingCentreData() {
		ShoppingCentre[] centres = null;
		ResultSet result = null;
		int rows;

		try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM supershoppingsurfer_bronze.centres_view")) {

			result = statement.executeQuery();
			result.last();
			rows = result.getRow();
			result.beforeFirst();
			centres = new ShoppingCentre[rows];

			for(int i=0; result.next(); i++) {
				centres[i] = new ShoppingCentre(
						result.getInt("business_id"), 
						result.getString("business_name"), 
						new Address(
							result.getString("address"),
							result.getInt("zipcode"),
							result.getString("municipality_name"),
							result.getString("county_name")),
						new EmailAddress(), 
						result.getInt("telephone"), 
						result.getInt("opening_hours"),
						result.getInt("centre_id"), 
						result.getInt("parking_spaces"), 
						result.getString("text_description")
						);
			}
			
			result.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		//getBuildingData(centres);

		return centres;
	}
	
	public Building[] getBuildingData(ShoppingCentre[] centres) {
		Building[] buildings = null;
		ResultSet result = null;
		int rows;
		
		try(PreparedStatement statement = connection.prepareStatement(
				"SELECT building_id, building_name, floors FROM building"
				)) {

			result = statement.executeQuery();
			result.last();
			rows = result.getRow();
			result.beforeFirst();
			buildings = new Building[rows];

			for(int i=0; result.next(); i++) {
				buildings[i] = new Building(
						result.getInt("building_id"),
						result.getString("building_name"),
						result.getInt("floors")
						);
			}
			
			result.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return buildings;
	}
	
	@Override
	public int getUserID(String username) {
		int output = -1;
		
		try(PreparedStatement statement = connection.prepareStatement("SELECT employee_number FROM person WHERE username = ?")) {
			
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				output = result.getInt(1);
			}
			result.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	@Override
	public UserType getUserType(int userId) {
		if(userId == 0) return UserType.SYS_ADMIN;
		
		try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM ? WHERE employee_number = ?")) {
			statement.setInt(2, userId);
			
			statement.setString(1, "centremanager");
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				result.close();
				return UserType.CENTRE_MANAGER;
			}
			result.close();
			
			statement.setString(1, "customerservice");
			result = statement.executeQuery();
			if(result.next()) {
				result.close();
				return UserType.CENTRE_MANAGER;
			}
			result.close();
			
			statement.setString(1, "shopowner");
			result = statement.executeQuery();
			if(result.next()) {
				result.close();
				return UserType.CENTRE_MANAGER;
			}
			result.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String executeQuery(String sql) {
		String out = null;
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet result = statement.executeQuery();
			int columns = result.getMetaData().getColumnCount();
			
			StringBuilder sBuilder = new StringBuilder();
			
			while(result.next()) {
				for(int i=0; i<columns; i++) {
					sBuilder.append(result.getString(i+1));
					sBuilder.append("\t");
				}
				sBuilder.append("\n");
			}
			out = sBuilder.toString();
			
			result.close();
		}
		catch(SQLException e) {
			out = "SQL expression raised an exception:\n"+e.getMessage();
		}
		
		return out;
	}

	@Override
	public String executeUpdate(String sql) {
		String out = null;
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			int linesAffected = statement.executeUpdate();
			
			out = String.format("Update successful. %d lines affected", linesAffected);
		}
		catch(SQLException e) {
			out = "SQL expression raised an exception: "+e.getMessage();
		}
		
		return out;
	}
}
