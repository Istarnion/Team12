/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * DatabaseConnection.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

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
import no.hist.aitel.team12.app.Ticket;
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

			String url = JOptionPane.showInputDialog(null, "Please input database URL:", "localhost");
			if(url == null) System.exit(0);
			String username = JOptionPane.showInputDialog("Please input username");
			if(username == null) System.exit(0);
			String password = JOptionPane.showInputDialog("Please input password for user "+username);
			if(password == null) System.exit(0);
			
			connection = DriverManager.getConnection("jdbc:mysql://"+url+"/supershoppingsurfer?"
					+ "user="+username+"&password="+password);

			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			ok = testConnection();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
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
			connection.setReadOnly(true);
			statement.setInt(1, user);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				passwordHash = result.getString(1);
			}
			result.close();
			connection.setReadOnly(false);
		}
		catch (SQLException e) {
			e.printStackTrace();			
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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
			System.out.println(e.getMessage());
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

			IntHashMap<ArrayList<Personnel>> personnel = null;

			String	centreQuery = null,
					buildingQuery = null,
					establishmentQuery = null,
					tradeQuery =
					"SELECT * FROM establishmenttrade JOIN trade USING (trade_id)",
					personnelQuery =
					"SELECT * FROM personnel "
							+"LEFT JOIN person USING (employee_number) "
							+"LEFT JOIN zipcode USING (zipcode) "
							+"LEFT JOIN municipality USING(municipality_id) "
							+"LEFT JOIN county USING (county_id)";
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

			try (PreparedStatement statement = connection.prepareStatement(personnelQuery)) {
				connection.setReadOnly(true);
				personnel = new IntHashMap<ArrayList<Personnel>>();
				ResultSet result = statement.executeQuery();
				ArrayList<Personnel> personnelList;
				while(result.next()) {
					personnelList = personnel.get(result.getInt("centre_id"));
					if(personnelList == null) {
						personnelList = new ArrayList<Personnel>();
						personnel.put(result.getInt("centre_id"), personnelList);
					}

					personnelList.add(
							new Personnel(
									result.getInt("employee_number"),
									result.getString("first_name"),
									result.getString("last_name"),
									new Address(
											result.getString("address"),
											result.getInt("zipcode"),
											result.getString("municipality_name"),
											result.getString("county_name"),
											result.getString("district")
											),
											new EmailAddress(result.getString("email")),
											result.getInt("telephone"),
											result.getInt("salary"),
											result.getString("title"),
											result.getInt("centre_id")
									)
							);
				}
				result.close();
				connection.setReadOnly(false);


			}
			catch(SQLException e) {
				e.printStackTrace();
				try {
					connection.setReadOnly(false);
				} catch (SQLException e1) {
					System.out.println("Something went wrong while setting ReadOnly");
					e1.printStackTrace();
				}

			}

			try(PreparedStatement statement = connection.prepareStatement(centreQuery)) {
				centres = new IntHashMap<ShoppingCentre>();
				connection.setReadOnly(true);

				ResultSet result = statement.executeQuery();
				while(result.next()) {
					centres.put(result.getInt("centre_id"), new ShoppingCentre(
							result.getInt("business_id"),
							result.getString("business_name"),
							new Address(
									result.getString("address"),
									result.getInt("zipcode"),
									result.getString("municipality_name"),
									result.getString("county_name"),
									result.getString("district")
									),
									new EmailAddress(result.getString("email")),
									result.getInt("telephone"),
									result.getString("opening_hours"),
									result.getInt("centre_id"),
									result.getInt("parking_spaces"),
									result.getString("text_description"),
									new ArrayList<Revenue>(),
									personnel.get(result.getInt("centre_id")),
									result.getString("first_name") + " " + result.getString("last_name")
							));
				}

				result.close();
				connection.setReadOnly(false);

			}
			catch(SQLException e) {
				e.printStackTrace();
				try {
					connection.setReadOnly(false);
				} catch (SQLException e1) {
					System.out.println("Something went wrong while setting ReadOnly");
					e1.printStackTrace();
				}

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
				try {
					connection.setReadOnly(false);
				} catch (SQLException e1) {
					System.out.println("Something went wrong while setting ReadOnly");
					e1.printStackTrace();
				}

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
				try {
					connection.setReadOnly(false);
				} catch (SQLException e1) {
					System.out.println("Something went wrong while setting ReadOnly");
					e1.printStackTrace();
				}

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
									result.getString("county_name"),
									result.getString("district")
									),
									trades.get(result.getInt("establishment_id")), 
									new ArrayList<Revenue>(),
									result.getString("first_name") + " " + result.getString("last_name")
							);
					if(estab != null) {
						ShoppingCentre centre = centres.get(result.getInt("centre_id"));
						if(centre != null) {
							centre.findBuilding(result.getInt("building_id")).addEstablishment(estab);
						}
					}
				}

				result.close();

			}
			catch(SQLException e) {

				e.printStackTrace();
				try {
					connection.setReadOnly(false);
				} catch (SQLException e1) {
					System.out.println("Something went wrong while setting ReadOnly");
					e1.printStackTrace();
				}

			}

			try {
				connection.setReadOnly(false);
			} catch (SQLException e) {
				System.out.println("Something went wrong while setting ReadOnly");
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
	public boolean createShoppingCentre(
			String firstname, 		String lastname,
			String username, 		String passwordHash,
			String email,			String personalAddress,
			int personalZip,		int telephone,
			int salary,				String centreName,
			String centreAddress,	int centreZip) {

		boolean ok = false;

		try(
				PreparedStatement businessStatement = connection.prepareStatement(
						"INSERT INTO business (business_name, address, zipcode, email, telephone, opening_hours, text_description) VALUES(?, ?, ?, ?, ?, '09150915', '...')");
				PreparedStatement centreStatement	= connection.prepareStatement(
						"INSERT INTO shoppingcentre (business_id, parking_spaces) VALUES (LAST_INSERT_ID(), 10)");
				PreparedStatement personStatement	= connection.prepareStatement(
						"INSERT INTO person (first_name, last_name, address, zipcode, email, telephone, salary) values(?, ?, ?, ?, ?, ?, ?)");
				PreparedStatement userStatement		= connection.prepareStatement(
						"INSERT INTO user (employee_number, username, password_hash) VALUES(LAST_INSERT_ID(), ?, ?)");
				PreparedStatement mangerStatement	= connection.prepareStatement(
						"INSERT INTO centremanager (employee_number, centre_id) VALUES ("
								+ "(SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'person')-1, "
								+ "(SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'shoppingcentre')-1)");
				) {
			connection.setAutoCommit(false);

			businessStatement.setString(1, centreName);
			businessStatement.setString(2, centreAddress);
			businessStatement.setInt(3, centreZip);
			businessStatement.setString(4, email);
			businessStatement.setInt(5, telephone);

			businessStatement.executeUpdate();

			centreStatement.executeUpdate();

			personStatement.setString(1, firstname);
			personStatement.setString(2, lastname);
			personStatement.setString(3, personalAddress);
			personStatement.setInt(4, personalZip);
			personStatement.setString(5, email);
			personStatement.setInt(6, telephone);
			personStatement.setInt(7, salary);

			personStatement.executeUpdate();

			userStatement.setString(1, username);
			userStatement.setString(2, passwordHash);

			userStatement.executeUpdate();

			mangerStatement.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);
			ok = true;
		}
		catch(SQLException e) {
			try {
				connection.rollback();
				System.out.println(e.getMessage());
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		finally {
			try {
				connection.setAutoCommit(true);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}


		return ok;
	};

	@Override
	public int getUserID(String username) {
		int output = -1;

		try(PreparedStatement statement = connection.prepareStatement("SELECT employee_number FROM user WHERE username = ?")) {
			connection.setReadOnly(true);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				output = result.getInt(1);
			}
			result.close();
			connection.setReadOnly(false);
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				System.out.println("Something went wrong while setting ReadOnly in DatabaseConnection.getUserID()");
				e1.printStackTrace();
			}
		}

		return output;
	}

	@Override
	public String getUsername(int userID) {
		String output = null;

		try(PreparedStatement statement = connection.prepareStatement("SELECT username FROM user WHERE employee_number = ?")) {
			connection.setReadOnly(true);
			statement.setInt(1, userID);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				output = result.getString(1);
			}
			result.close();
			connection.setReadOnly(false);
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				System.out.println("Something went wrong while setting ReadOnly in DatabaseConnection.getUsername()");
				e1.printStackTrace();
			}
		}

		return output;
	}

	@Override
	public boolean isUserInDb(String username) {
		boolean found = false;

		try(PreparedStatement statement = connection.prepareStatement("SELECT employee_number FROM user WHERE username = ?")) {
			connection.setReadOnly(true);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				found = true;
			}
			result.close();
			connection.setReadOnly(false);
		}
		catch (SQLException e) {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				System.out.println("Something went wrong while setting ReadOnly in DatabaseConnection.isUserInDb()");
				e1.printStackTrace();
			}
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

			String userStatement = null;
			String personnelStatement = null;

			int centreID = 0;

			if(userID == 1) {	// Sys admin
				userStatement = "SELECT * FROM user_view";
				personnelStatement = "SELECT * FROM personnel_view";
			}
			else {
				UserType type = getUserType(userID);
				if(type == UserType.CENTRE_MANAGER) {
					centreID = getCentreID(userID);
					userStatement =
							"SELECT uv.employee_number, uv.first_name, uv.last_name, uv.address, uv.zipcode, uv.email, uv.telephone, uv.salary, "
									+"uv.username, uv.district, uv.municipality_name, uv.county_name "
									+"FROM user_view uv "
									+"LEFT JOIN centremanager cm USING (employee_number) "
									+"LEFT JOIN establishmentowner USING (employee_number) "
									+"LEFT JOIN customerservice cs USING (employee_number) "
									+"LEFT JOIN establishment_view ev USING (establishment_id) "
									+"WHERE cm.centre_id = ? OR cs.centre_id = ? OR ev.centre_id = ?";

					personnelStatement = "SELECT * FROM personnel_view WHERE centre_id = ?";
				}
				else {
					System.out.println("Invalid usertype in getPersons()! Usertype: "+type);
					return new Person[0];
				}
			}

			try(
					PreparedStatement userQuery = connection.prepareStatement(userStatement);
					PreparedStatement personnelQuery = connection.prepareStatement(personnelStatement);
					) {

				if(centreID > 0) {
					userQuery.setInt(1, centreID);
					userQuery.setInt(2, centreID);
					userQuery.setInt(3, centreID);

					personnelQuery.setInt(1, centreID);
				}

				connection.setReadOnly(true);
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
									result.getString("municipality_name"),
									result.getString("county_name"),
									result.getString("district")
									),
									new EmailAddress(result.getString("email")),
									result.getInt("telephone"),
									result.getInt("salary"),
									result.getString("username")
							);
				}
				result.close();

				result = personnelQuery.executeQuery();
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
									result.getString("municipality_name"),
									result.getString("county_name"),
									result.getString("district")),
									new EmailAddress(result.getString("email")),
									result.getInt("telephone"),
									result.getInt("salary"),
									result.getString("title"),
									result.getInt("centre_id")
							);
				}
				result.close();
				connection.setReadOnly(false);
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
						connection.setReadOnly(false);
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

		try(
				PreparedStatement manager	= connection.prepareStatement("SELECT * FROM centremanager WHERE employee_number = ?");
				PreparedStatement shopowner	= connection.prepareStatement("SELECT * FROM establishmentowner WHERE employee_number = ?");
				PreparedStatement cservice	= connection.prepareStatement("SELECT * FROM customerservice WHERE employee_number = ?");
				) {
			connection.setReadOnly(true);
			manager.setInt(1, userId);
			ResultSet result = manager.executeQuery();
			if(result.next()) {
				result.close();
				return UserType.CENTRE_MANAGER;
			}
			result.close();

			shopowner.setInt(1, userId);
			result = shopowner.executeQuery();
			if(result.next()) {
				result.close();
				return UserType.SHOP_OWNER;
			}
			result.close();

			cservice.setInt(1, userId);
			result = cservice.executeQuery();
			if(result.next()) {
				result.close();
				return UserType.CUSTOMER_SERVICE;
			}
			result.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
			connection.setReadOnly(true);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return result.getInt("centre_id");
			}
			result.close();
		}
		catch(SQLException e) {

			e.printStackTrace();
		}
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return -1;
	}

	@Override
	public int getEstablishmentID(int userID) {

		try(PreparedStatement statement = connection.prepareStatement("SELECT establishment_id FROM establishmentowner WHERE employee_number = "+userID)) {
			connection.setReadOnly(true);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return result.getInt("establishment_id");
			}
			result.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return -1;
	}

	@Override
	public int getBusinessID(int userID) {
		int output = -1;

		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT GET_NOT_NULL_ID(cm.employee_number, eo.employee_number) AS employee_number "
						+"FROM business LEFT JOIN shoppingcentre USING (business_id) "
						+"LEFT JOIN establishment USING (business_id) "
						+"LEFT JOIN centremanager cm USING (centre_id) "
						+"LEFT JOIN establishmentowner eo USING(establishment_id) "
						+"WHERE business_id = ?"
				)) {
			connection.setReadOnly(true);

			statement.setInt(1, userID);

			ResultSet result = statement.executeQuery();

			if(result.next()) {
				output = result.getInt("employee_number");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.setReadOnly(false);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return output;
	}

	@Override
	public String[][] executeQuery(String sql) {
		String[][] out = null;

		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			connection.setReadOnly(true);
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
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return out;
	}

	@Override
	public String executeUpdate(String sql) {
		String out = null;

		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			connection.setAutoCommit(false);
			int linesAffected = statement.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
			out = String.format("Update successful. %d lines affected", linesAffected);
		}
		catch(SQLException e) {
			out = "SQL expression raised an exception: "+e.getMessage();
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return out;
	}

	@Override
	public boolean executePreparedStatement(String statement, Object... args) {
		boolean ok = false;

		try(PreparedStatement prepStatement = connection.prepareStatement(statement)) {
			connection.setAutoCommit(false);
			for(int i=0; i<args.length; i++) {
				if(args[i] instanceof String) {
					prepStatement.setString(i+1, (String)args[i]);
				}
				else if(args[i] instanceof Integer) {
					prepStatement.setInt(i+1, (Integer)args[i]);
				}
				else if(args[i] instanceof Boolean) {
					prepStatement.setBoolean(i+1, (Boolean)args[i]);
				}
				else if(args[i] instanceof Long) {
					prepStatement.setLong(i+1, (Long)args[i]);
				}
				else if(args[i] instanceof Date) {
					prepStatement.setDate(i+1, (Date)args[i]);
				}
				else {
					System.out.println("Invalid argument type: "+args[i].getClass().getSimpleName());
					return false;
				}
			}

			prepStatement.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
			ok = true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			ok = false;
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

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
				connection.setReadOnly(true);
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
				result.close();
			} 

			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.setReadOnly(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return false;
		}
		return true;
	}

	@Override
	public boolean createPersonnel	(String firstName, String lastName, String address, int zipCode, 
			String email, int telephone, int salary, String title, int centreID) {

		try(PreparedStatement personStatement = connection.prepareStatement("INSERT INTO person(first_name, last_name, address, zipcode, email, telephone, salary) VALUES(?, ?, ?, ?, ?, ?, ?)")) {
			connection.setAutoCommit(false);
			personStatement.setString(1, firstName);
			personStatement.setString(2, lastName);
			personStatement.setString(3, address);
			personStatement.setInt(4, zipCode);
			personStatement.setString(5, email);
			personStatement.setInt(6, telephone);
			personStatement.setInt(7, salary);

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
			connection.setReadOnly(true);
			statement.setInt(1, establishmentId);
			result = statement.executeQuery();


			result.last();
			rows = result.getRow();
			result.beforeFirst();

			selectedTrades = new Trade[rows];

			for(int i = 0; result.next(); i++) {
				selectedTrades[i] = new Trade(result.getInt("trade_id"),result.getString("trade_name"));
			}

			result.close();
			return selectedTrades;
		} catch (SQLException e) {
			e.printStackTrace();

			selectedTrades = new Trade[0];
			return selectedTrades;

		}
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Trade> getAllTrades() {
		ArrayList<Trade> allTrades = new ArrayList<Trade>();
		ResultSet result = null;
		try(PreparedStatement statement = connection.prepareStatement("SELECT trade_id, trade_name FROM trade")) {
			connection.setReadOnly(true);
			result = statement.executeQuery();
			while(result.next()) {
				allTrades.add(new Trade(result.getInt("trade_id"),result.getString("trade_name")));
			}
			result.close();

			return allTrades;
		} catch (SQLException e) {
			e.printStackTrace();
			return allTrades;
		}
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public boolean zipExists(int zipCode) {
		ResultSet result = null;
		try(PreparedStatement statement = connection.prepareStatement("SELECT zipcode FROM zipcode WHERE zipcode = ?")) {
			connection.setReadOnly(true);
			statement.setInt(1, zipCode);
			result = statement.executeQuery();

			if(result.next()) {
				return true;
			}
			result.close();
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getNumberOfShoppingCentres() {
		ResultSet result = null;
		try(PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM shoppingcentre")) {
			connection.setReadOnly(true);
			result = statement.executeQuery();
			if(result.next()) {
				return result.getInt("COUNT(*)");
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int getNumberOfEstablishment() {
		ResultSet result = null;
		try(PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM establishment")) {
			connection.setReadOnly(true);
			result = statement.executeQuery();
			if(result.next()) {
				return result.getInt("COUNT(*)");
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		finally {
			try {
				connection.setReadOnly(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public Ticket[] getTickets(int centreID) {
		synchronized(connection) {
			Ticket[] output = null;

			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket WHERE centre_id = ?")) {
				connection.setReadOnly(true);

				statement.setInt(1, centreID);

				ResultSet result = statement.executeQuery();

				result.last();
				output = new Ticket[result.getRow()];
				result.beforeFirst();

				for(int i=0; result.next(); i++) {
					output[i] = new Ticket(
							result.getInt("ticket_id"),
							result.getString("content"),
							new EmailAddress(
									result.getString("customer_email")
									),
									result.getBoolean("resolvedStatus"),
									centreID
							);
				}
				result.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.setReadOnly(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			return output;
		}
	}

	@Override
	public Revenue[] getRevenue(int businessID, Date from, Date to) {
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM revenue WHERE business_id=? AND month BETWEEN ? AND ?")) {
			
			connection.setReadOnly(true);
			
			statement.setInt(1, businessID);
			statement.setDate(2, from);
			statement.setDate(3, to);
			ResultSet result = statement.executeQuery();
			
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.setTime(from);
			
			Revenue[] revArray = new Revenue[differenceInMonths(from, to)+3];
			for(int i=0; i<revArray.length; i++) {
				revArray[i] = new Revenue(new Date(cal.getTime().getTime()), 0);
				cal.add(Calendar.MONTH, 1);
			}
			
			while(result.next()) {
				for(Revenue rev : revArray) {
					if(equalEnough(rev.getMonth(), result.getDate("month"))) {
						rev.setTurnover(result.getLong("turnover_month"));
					}
				}
			}
			
			return revArray;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.setReadOnly(false);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	private static boolean equalEnough(Date da, Date db) {
		Calendar c1 = Calendar.getInstance();
		c1.clear();
		c1.setTime(da);
		Calendar c2 = Calendar.getInstance();
		c2.clear();
		c2.setTime(db);
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH));
	}
	
	private static int differenceInMonths(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		int diff = 0;
		if (c2.after(c1)) {
			while (c2.after(c1)) {
				c1.add(Calendar.MONTH, 1);
				if (c2.after(c1)) {
					diff++;
				}
			}
		} else if (c2.before(c1)) {
			while (c2.before(c1)) {
				c1.add(Calendar.MONTH, -1);
				if (c1.before(c2)) {
					diff--;
				}
			}
		}
		return diff;
	}
}
