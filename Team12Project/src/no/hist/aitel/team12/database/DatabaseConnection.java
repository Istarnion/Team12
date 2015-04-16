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
import no.hist.aitel.team12.app.IntHashMap;
import no.hist.aitel.team12.app.Message;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.UserType;
import no.hist.aitel.team12.util.DoubleMetaphoneUtils;
import bak.pcj.list.IntArrayList;


public class DatabaseConnection implements Database {

	private Connection connection;

	boolean connect() {
		boolean ok = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://hist.tilfeldig.info/supershoppingsurfer_silver?"
					+ "user=team12&password=teamadmin12");
			connection.setAutoCommit(false);

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
		try(PreparedStatement statement = connection.prepareStatement("SELECT password_hash FROM user WHERE employee_number = ?")) {
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
	public ShoppingCentre[] getShoppingCentres(int userID) {
		try {
			if(connection.isClosed()) return null;
		} catch (SQLException e1) {
			return null;
		}
		
		IntHashMap<ShoppingCentre>	centres = null;

		String	centreQuery = null,
				buildingQuery = null,
				establishmentQuery = null;
		UserType userType = getUserType(userID);
		switch(userType) {
		case SYS_ADMIN:
		{
			centreQuery = "SELECT * FROM centres_view";
			buildingQuery = "SELECT centre_id, building_id, building_name, floors FROM building";
			establishmentQuery = "SELECT * FROM establishment_view";
		} break;
		case CENTRE_MANAGER:
		case CUSTOMER_SERVICE:
		{
			int centreId = getCentreID(userID);
			centreQuery = "SELECT * FROM centres_view WHERE centre_id = "+centreId;
			buildingQuery = "SELECT centre_id, building_id, building_name, floors FROM building WHERE centre_id = "+centreId;
			establishmentQuery = "SELECT * FROM establishment_view";
		} break;
		case SHOP_OWNER:
		{
			int centreId = getCentreID(userID);
			centreQuery = "SELECT * FROM centres_view WHERE centre_id = "+centreId;
			buildingQuery = "SELECT centre_id, building_id, building_name, floors FROM building WHERE centre_id = "+centreId;
			establishmentQuery = "SELECT * FROM establishment_view WHERE establishment_id = "+getEstablishmentID(userID);
		} break;
		default:
			break;
		}

		try(PreparedStatement statement = connection.prepareStatement(centreQuery)) {
			centres = new IntHashMap<ShoppingCentre>();

			ResultSet result = statement.executeQuery();
			while(result.next()) {
				centres.put(result.getInt("centre_id"), new ShoppingCentre(
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
						));
			}

			result.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		try(PreparedStatement statement = connection.prepareStatement(buildingQuery)) {
			Building building;
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				building = new Building(
						result.getInt("building_id"),
						result.getString("building_name"),
						result.getInt("floors")
						);
				centres.get(result.getInt("centre_id")).addBuilding(building);
			}

			result.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		try(PreparedStatement statement = connection.prepareStatement(establishmentQuery)) {
			Establishment estab;
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				estab = new Establishment(
						result.getInt("business_id"),
						result.getString("business_name"),
						new EmailAddress(result.getString("email")),
						result.getInt("telephone"),
						result.getInt("opening_hours"),
						result.getInt("floor_number"),
						result.getInt("establishment_id")
						);
				centres.get(result.getInt("centre_id")).findBuilding(result.getInt("building_id")).addEstablishment(estab);
			}

			result.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		ShoppingCentre[] output = null;
		if(centres != null) {
			output = new ShoppingCentre[centres.size()];
			IntArrayList keys = centres.getKeys();
			for(int i=0; i<keys.size(); i++) {
				output[i] = centres.get(keys.get(i));
			}
		}
		return output;
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

		try(PreparedStatement statement = connection.prepareStatement("SELECT employee_number FROM user WHERE username = ?")) {

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
		if(userId == 1) return UserType.SYS_ADMIN;

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
	public int getCentreID(int userID) {
		UserType userType = getUserType(userID);

		if(userType == UserType.SYS_ADMIN) return 0;
		String query;
		switch(userType) {
			case CUSTOMER_SERVICE:
				query = "SELECT centre_id FROM customerservice WHERE employee_number = "+userID;
				break;
			case CENTRE_MANAGER:
				query = "SELECT centre_id FROM centremanager WHERE employee_number = "+userID;
				break;
			case SHOP_OWNER:
				query =
				"SELECT centre_id FROM establishmentowner e LEFT JOIN establishment USING (establishment_id) LEFT JOIN building USING (building_id) WHERE employee_number = "+userID;
				break;
			default:
				return -1;
		}

		try(PreparedStatement statement = connection.prepareStatement(query)) {

			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return result.getInt("centre_id");
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int getEstablishmentID(int userID) {

		try(PreparedStatement statement = connection.prepareStatement("SELECT establishment_id FROM establishmentowner WHERE employee_number = "+userID)) {

			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return result.getInt("establishment_id");
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return -1;
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


	@Override
	public Message[] getMessages(String username) {
		Message[] messages = null;
		ResultSet result = null;
		int rows;

		try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM message WHERE sender = ? OR reciever = ?")) {

			statement.setString(1, username);
			statement.setString(2, username);
			result = statement.executeQuery();

			result.last();
			rows = result.getRow();
			result.beforeFirst();

			messages = new Message[rows];

			for(int i=0; result.next(); i++) {
				messages[i] = new Message(
						result.getString("reciever"),
						result.getString("sender"),
						result.getString("subject"),
						result.getString("content"),
						result.getTimestamp("timestamp"),
						result.getBoolean("deleted")
						);
			}


		} 

		catch (SQLException e) {
			e.printStackTrace();
		}



		return messages;
	}

	@Override
	public boolean createUser	(String firstName, String lastName, String address, int zipCode, 
			String email, int telephone, int salary, String username, String passwordHash) {

		String firstNameDMP = DoubleMetaphoneUtils.encodeString(firstName);
		String lastNameDMP = DoubleMetaphoneUtils.encodeString(lastName);


		try(PreparedStatement personStatement = connection.prepareStatement("INSERT INTO person(first_name, first_name_dmp, last_name, last_name_dmp, address, zipcode, email, telephone, salary) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			connection.setAutoCommit(false);
			personStatement.setString(1, firstName);
			personStatement.setString(2, firstNameDMP);
			personStatement.setString(3, lastName);
			personStatement.setString(4, lastNameDMP);
			personStatement.setString(5, address);
			personStatement.setInt(6, zipCode);
			personStatement.setString(7, email);
			personStatement.setInt(8, telephone);
			personStatement.setInt(9, salary);

			personStatement.executeUpdate();


			try(PreparedStatement userStatement = connection.prepareStatement("INSERT INTO user(username, password_hash, employee_number) VALUES(?, ?, LAST_INSERT_ID())")) {
				userStatement.setString(1, username);
				userStatement.setString(2, passwordHash);

				userStatement.executeUpdate();
			}

			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

			connection.commit();
			connection.setAutoCommit(true);
		} 

		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean createPersonnel	(String firstName, String lastName, String address, int zipCode, 
			String email, int telephone, int salary, String title, int centreID) {

		String firstNameDMP = DoubleMetaphoneUtils.encodeString(firstName);
		String lastNameDMP = DoubleMetaphoneUtils.encodeString(lastName);

		try(PreparedStatement personStatement = connection.prepareStatement("INSERT INTO person(first_name, first_name_dmp, last_name, last_name_dmp, address, zipcode, email, telephone, salary) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			connection.setAutoCommit(false);
			personStatement.setString(1, firstName);
			personStatement.setString(2, firstNameDMP);
			personStatement.setString(3, lastName);
			personStatement.setString(4, lastNameDMP);
			personStatement.setString(5, address);
			personStatement.setInt(6, zipCode);
			personStatement.setString(7, email);
			personStatement.setInt(8, telephone);
			personStatement.setInt(9, salary);

			personStatement.executeUpdate();

			try(PreparedStatement personnelStatement = connection.prepareStatement("INSERT INTO personnel(title, centre_id, employee_number) VALUES(?, ?, LAST_INSERT_ID())")) {

				personnelStatement.setString(1, title);
				personnelStatement.setInt(2, centreID);

				personnelStatement.executeUpdate();
			} 

			catch (SQLException e) {
				e.printStackTrace();
			}

			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}



		return true;
	}
	
	public String getUserStatement(){
		
		int userID = getUserID(null);
		
		String statement;
		
		if (getUserType(userID) == UserType.SYS_ADMIN ){
			statement = "'SELECT * from person;'";
		}else if (getUserType(userID) == UserType.CENTRE_MANAGER) {
			statement = "'Select employee_number, first_name, last_name, zipcode, adress, email, telephone, salary from person;'";
		}else if (getUserType(userID) == UserType.CUSTOMER_SERVICE) {
			statement = "'Select employee_number, first_name, last_name, email, telephone from person;'";
		}else if (getUserType(userID) == UserType.SHOP_OWNER) {
			statement = "'Select employee_number, first_name, last_name, email, telephone from person;'";
		}else {
			statement = "'null'";
		}
		
		return statement;
		
	}

	@Override
	public boolean sendMessage(String sender, String reciever, String content,
			String subject) {

		try(PreparedStatement statement = connection.prepareStatement("INSERT INTO message (sender, reciever, content, subject, timestamp) VALUES (?, ?, ?, ?, NOW())")) {
		
			connection.setAutoCommit(false);
			
			statement.setString(1, sender);
			statement.setString(2, reciever);
			statement.setString(3, content);
			statement.setString(4, subject);
			
			statement.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
