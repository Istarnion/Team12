package no.hist.aitel.team12.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import no.hist.aitel.team12.app.Address;
import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.UserType;

public class DatabaseConnection implements Database {

	private Connection connection;

	boolean connect() {
		boolean ok = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://hist.tilfeldig.info/supershoppingsurfer_silver?"
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

		try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM centres_view")) {

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
						new EmailAddress(result.getString("email")), 
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
	
	@Override
	public void getBuildingData(ShoppingCentre[] centres) {
		ResultSet result = null;
		
		try(PreparedStatement statement = connection.prepareStatement(
				"SELECT building_id, building_name, floors FROM building WHERE centre_id = ?"
				)) {

			Building[] buildings;
			Building building;
			for(ShoppingCentre centre : centres) {
				statement.setInt(1, centre.getCentreId());
				
				result = statement.executeQuery();
				result.last();
				int rows = result.getRow();
				result.beforeFirst();
				
				buildings = new Building[rows];
				for(int i=0; result.next(); i++) {
					building = new Building(
							result.getInt("building_id"),
							result.getString("building_name"),
							result.getInt("floors")
							);
					
					building.setEstablishments(
							getEstablishmentsInBuilding(building.getBuilding_id())
							);
					
					buildings[i] = building;
					
				}
				
				result.close();
				centre.setBuildings(buildings);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Establishment[] getEstablishmentsInBuilding(int buildingID) {
		Establishment[] establishments = null;
		
		try(PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM establishment LEFT JOIN business USING (business_id) WHERE building_id = ?"
				)) {
			
			statement.setInt(1, buildingID);
			ResultSet result = statement.executeQuery();
			
			result.last();
			int rows = result.getRow();
			result.beforeFirst();	
			
			establishments = new Establishment[rows];
			for(int i=0; result.next(); i++) {
				establishments[i] = new Establishment(
						result.getInt("business_id"),
						result.getString("business_name"),
						new EmailAddress(result.getString("email")),
						result.getInt("telephone"),
						result.getInt("opening_hours"),
						result.getInt("floor_number"),
						result.getInt("establishment_id")
						);
			}
			result.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return establishments;
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
	public String[][] executeQuery(String sql) {
		String[][] out = null;
		
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet result = statement.executeQuery();
			int columns = result.getMetaData().getColumnCount();
			int rows;
			
			result.last();
			rows = result.getRow();
			result.beforeFirst();
			
			out = new String[rows+1][columns];
			for(int i=0; i<result.getMetaData().getColumnCount(); i++) {
				out[0][i] = result.getMetaData().getColumnLabel(i+1);
			}
			
			for(int row=1; result.next(); row++) {  
				for(int col=0; col<columns; col++) {
					out[row][col] = result.getString(col+1);
				}
			}
			
			result.close();
		}
		catch(SQLException e) {
			out = new String[1][1];
			out[0][0] = "SQL expression raised an exception: "+e.getMessage();
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
