package no.hist.aitel.team12.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import no.hist.aitel.team12.app.Address;
import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.IntHashMap;
import no.hist.aitel.team12.app.Message;
import no.hist.aitel.team12.app.Person;
import no.hist.aitel.team12.app.Personnel;
import no.hist.aitel.team12.app.Revenue;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.Trade;
import no.hist.aitel.team12.app.User;
import no.hist.aitel.team12.app.UserType;
import no.hist.aitel.team12.util.DoubleMetaphoneUtils;
import bak.pcj.list.IntArrayList;


public class DatabaseConnection implements Database {

	private Connection connection;

	boolean connect() {
		boolean ok = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost/supershoppingsurfer?"
					+ "user=root&password=Yko7p5si");

			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

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
		synchronized(connection) {
			try {
				connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
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

		synchronized (connection) {

			try {
				if(connection.isClosed()) return null;
			} catch (SQLException e1) {
				return null;
			}

			IntHashMap<ShoppingCentre>	centres = null;

			IntHashMap<ArrayList<Trade>> trades = null;
			
			String	centreQuery = null,
					buildingQuery = null,
					establishmentQuery = null,
					tradeQuery = "SELECT * FROM establishmenttrade JOIN trade USING (trade_id)";
			UserType userType = getUserType(userID);
			switch(userType) {
			case SYS_ADMIN:
			{
				centreQuery = "SELECT * FROM centres_view";
				buildingQuery = "SELECT centre_id, building_id, building_name, floors, area FROM building";
				establishmentQuery = "SELECT * FROM establishment_view";
			} break;
			case CENTRE_MANAGER:
			case CUSTOMER_SERVICE:
			{
				int centreId = getCentreID(userID);
				centreQuery = "SELECT * FROM centres_view WHERE centre_id = "+centreId;
				buildingQuery = "SELECT centre_id, building_id, building_name, floors, area FROM building WHERE centre_id = "+centreId;
				establishmentQuery = "SELECT * FROM establishment_view";
			} break;
			case SHOP_OWNER:
			{
				int centreId = getCentreID(userID);
				centreQuery = "SELECT * FROM centres_view WHERE centre_id = "+centreId;
				buildingQuery = "SELECT centre_id, building_id, building_name, floors, area FROM building WHERE centre_id = "+centreId;
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
									result.getString("county_name")
									),
									new EmailAddress(result.getString("email")),
									result.getInt("telephone"),
									result.getString("opening_hours"),
									result.getInt("centre_id"),
									result.getInt("parking_spaces"),
									result.getString("text_description"),
									new ArrayList<Revenue>(),
									new Personnel[] {
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
								new Personnel(0, "Test", result.getString("business_name"), new Address("address", 1337, "munici", "county"), new EmailAddress("email@test.personnel"), 99887766, 6000000, "Tester and chief of Test", 1),
							}
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
							result.getInt("floors"),
							result.getInt("area")
							);
					centres.get(result.getInt("centre_id")).addBuilding(building);
				}

				result.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}

			try (PreparedStatement statement = connection.prepareStatement(tradeQuery)) {
				trades = new IntHashMap<ArrayList<Trade>>();
				
				ResultSet result = statement.executeQuery();
				ArrayList<Trade> tradeList;
				while(result.next()) {
					tradeList = trades.get(result.getInt("establishment_id"));
					if(tradeList == null) {
						tradeList = new ArrayList<Trade>();
						trades.put(result.getInt("establishment_id"), tradeList);
					}
					
					tradeList.add(new Trade(result.getInt("trade_id"), result.getString("trade_name")));
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
							result.getString("opening_hours"),
							result.getInt("floor_number"),
							result.getInt("establishment_id"),
							result.getString("text_description"),
							new Address(
									result.getString("address"), 
									result.getInt("zipcode"), 
									result.getString("municipality_name"), 
									result.getString("county_name")
							),
							trades.get(result.getInt("establishment_id")), 
							new ArrayList<Revenue>()
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
	public String getUsername(int userID) {
		String output = null;

		try(PreparedStatement statement = connection.prepareStatement("SELECT username FROM user WHERE employee_number = ?")) {

			statement.setInt(1, userID);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				output = result.getString(1);
			}
			result.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}

	@Override
	public boolean isUserInDb(String username) {
		boolean found = false;

		try(PreparedStatement statement = connection.prepareStatement("SELECT employee_number FROM user WHERE username = ?")) {

			statement.setString(1, username);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				found = true;
			}
			result.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return found;
	}

	@Override
	public Person[] getPersons(int userID) {
		synchronized(connection) {
			ResultSet result = null;
			Person[] persons = null;

			User[] users = null;
			Personnel[] personnel = null;

			try {
				if(connection.isClosed()) {
					return null;
				}
			}
			catch(SQLException e) {
				return null;
			}

			try(
					PreparedStatement userQuery = connection.prepareStatement("SELECT * FROM user_view");
					PreparedStatement personellQuery = connection.prepareStatement("SELECT * FROM personnel_view");
					) {

				result = userQuery.executeQuery();
				result.last();
				int numUsers = result.getRow();
				result.beforeFirst();

				users = new User[numUsers];
				int index = 0;
				while(result.next()) {
					users[index++] = new User(
							result.getInt("employee_number"),
							result.getString("first_name"),
							result.getString("last_name"),
							new Address(
									result.getString("address"),
									result.getInt("zipcode"),
									"temp",
									"temp"),
									new EmailAddress(result.getString("email")),
									result.getInt("telephone"),
									result.getInt("salary"),
									result.getString("username")
							);
				}
				result.close();

				result = personellQuery.executeQuery();
				result.last();
				int numPersonnel = result.getRow();
				result.beforeFirst();

				personnel = new Personnel[numPersonnel];
				index = 0;
				while(result.next()) {
					personnel[index++] = new Personnel (
							result.getInt("employee_number"),
							result.getString("first_name"),
							result.getString("last_name"),
							new Address(
									result.getString("address"),
									result.getInt("zipcode"),
									"temp",
									"temp"),
									new EmailAddress(result.getString("email")),
									result.getInt("telephone"),
									result.getInt("salary"),
									result.getString("title"),
									result.getInt("centre_id")
							);
				}
				result.close();
			}
			catch(SQLException e) {
				e.printStackTrace();

			}
			catch(NullPointerException e) {
				System.out.println(e.getMessage());
				users = null;
				personnel = null;
			}
			finally {
				if(result != null) {
					try {
						result.close();
					}
					catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}

			if(users != null && personnel != null) {
				persons = new Person[users.length + personnel.length];

				int index = 0;
				for(User u : users) {
					persons[index++] = u;
				}
				for(Personnel p : personnel) {
					persons[index++] = p;
				}
			}

			return persons;
		}
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
	public boolean executePreparedStatement(String statement, Object... args) {
		boolean ok = false;

		try(PreparedStatement prepStatement = connection.prepareStatement(statement)) {
			for(int i=0; i<args.length; i++) {
				if(args[i] instanceof String) {
					prepStatement.setString(i+1, (String)args[i]);
				}
				else if(args[i] instanceof Integer) {
					prepStatement.setInt(i+1, (Integer)args[i]);
				}
				else {
					return false;
				}
			}

			prepStatement.executeUpdate();
			ok = true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			ok = false;
		}

		return ok;
	}


	@Override
	public Message[] getMessages(String username) {
		synchronized(connection) {

			Message[] messages = null;
			ResultSet result = null;
			int rows;

			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM message_view WHERE sender = ? OR reciever = ?")) {

				if(connection.isClosed()) return null;

				statement.setString(1, username);
				statement.setString(2, username);
				result = statement.executeQuery();

				result.last();
				rows = result.getRow();
				result.beforeFirst();

				messages = new Message[rows];

				for(int i=0; result.next(); i++) {
					messages[i] = new Message(
							result.getInt("message_id"),
							result.getString("Reciever"),
							result.getString("Sender"),
							result.getString("subject"),
							result.getString("content"),
							result.getTimestamp("timestamp"),
							result.getBoolean("trashed")
							);
				}
			} 

			catch (SQLException e) {
				e.printStackTrace();
			}

			return messages;
		}
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

	public String getUserStatement(int userID){

		UserType userType = getUserType(userID);

		String query;
		switch(userType) {
		case SYS_ADMIN:
			query = ""
					+ "SELECT * "
					+ "from person;";
		case CUSTOMER_SERVICE:
			query = ""
					+ "Select employee_number, first_name, last_name, email, telephone "
					+ "from person;";
			break;
		case CENTRE_MANAGER:
			query = ""
					+ "Select employee_number, first_name, last_name, zipcode, address, email, telephone, salary "
					+ "from person;";
			break;
		case SHOP_OWNER:
			query = ""
					+ "Select employee_number, first_name, last_name, email, telephone "
					+ "from person;";
			break;

		default:
			return null;
		}

		return query;




	}

	@Override
	public boolean sendMessage(String sender, String[] recievers, String content, String subject) {

		try(
				PreparedStatement msg  = connection.prepareStatement("INSERT INTO message (content, subject, timestamp) VALUES (?, ?, NOW())");
				PreparedStatement sndr = connection.prepareStatement("INSERT INTO messageSender (message_id, username) VALUES (LAST_INSERT_ID(), ?)");
				PreparedStatement rcvr = connection.prepareStatement("INSERT INTO messageReciever (message_id, username) VALUES (LAST_INSERT_ID(), ?)");
				) {

			connection.setAutoCommit(false);

			msg.setString(1, content);
			msg.setString(2, subject);
			msg.executeUpdate();

			sndr.setString(1, sender);
			sndr.executeUpdate();

			for(String s : recievers) {
				rcvr.setString(1, s);
				rcvr.executeUpdate();
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
	public Trade[] getSelectedTrades(int establishmentId) {
		Trade[] selectedTrades;
		ResultSet result = null;
		int rows = 0;
		try(PreparedStatement statement = connection.prepareStatement("SELECT trade_id, trade_name FROM establishmenttrade LEFT JOIN trade USING(trade_id) WHERE establishment_id = ?")) {
			connection.setAutoCommit(false);
			statement.setInt(1, establishmentId);
			result = statement.executeQuery();
			connection.commit();
			connection.setAutoCommit(true);

			result.last();
			rows = result.getRow();
			result.beforeFirst();

			selectedTrades = new Trade[rows];

			for(int i = 0; result.next(); i++) {
				selectedTrades[i] = new Trade(result.getInt("trade_id"),result.getString("trade_name"));
			}
			
			return selectedTrades;
		} catch (SQLException e) {
			e.printStackTrace();
			selectedTrades = new Trade[0];
			return selectedTrades;
		}
	}

	@Override
	public ArrayList<Trade> getAllTrades() {
		ArrayList<Trade> allTrades = new ArrayList<Trade>();
		ResultSet result = null;
		try(PreparedStatement statement = connection.prepareStatement("SELECT trade_id, trade_name FROM trade")) {
			connection.setAutoCommit(false);
			result = statement.executeQuery();
			connection.commit();
			connection.setAutoCommit(true);

			for(int i = 0; result.next(); i++) {
				allTrades.add(new Trade(result.getInt("trade_id"),result.getString("trade_name")));
			}
			return allTrades;
		} catch (SQLException e) {
			e.printStackTrace();
			return allTrades;
		}
	}
}
