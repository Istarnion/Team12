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
 * Database.java Team 12, 13 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.database;

import java.sql.Date;
import java.util.ArrayList;

import no.hist.aitel.team12.app.Message;
import no.hist.aitel.team12.app.Person;
import no.hist.aitel.team12.app.Revenue;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.Ticket;
import no.hist.aitel.team12.app.Trade;
import no.hist.aitel.team12.app.UserType;
import no.hist.aitel.team12.util.PasswordManager;

/**
 * This interface specifies all relevant methods to be used against the database, to allow for easy swapping of
 * database systems.
 * <br>
 * Some coupling has been allowed between the application layer and the database layer through this interface in
 * order to make code shorter and more readable
 * 
 * @author Hallgeir
 * @version 1.0
 */
public interface Database {
	
	/**
	 * This is a general purpose sql query, only to be used from the SqlTab by the system administrator
	 * 
	 * @param sql The statement to be run.
	 * @return	The result of the query.
	 */
	public String[][] executeQuery(String sql);
	
	/**
	 * This is a general purpose sql update, only to be used from the SqlTab by the system administrator
	 * 
	 * @param sql The statement to be run.
	 * @return	The result. Can be error, or lines affected
	 */
	public String executeUpdate(String sql);
	
	/**
	 * Executes the given prepared statement with the provided arguments
	 * 
	 * @param statement A string with a syntacticly correct SQL statement
	 * @param args	A list of arguments, in the correct order.
	 * @return True if the update was successful, false if not.
	 */
	public boolean executePreparedStatement(String statement, Object... args);
	
	/**
	 * Method for retrieving the user ID through the username.
	 * Although the usernames are unique, comparisons between ints are more reliable than Strings,
	 * and therefore using the user IDs should be preferred over using the user name in the application logic.
	 * 
	 * @param username The username
	 * @return	The unique ID for this user
	 */
	public int getUserID(String username);
	
	/**
	 * Method for retrieving the username through the userID. Using ints for comparisons are more reliable than Strings.
	 * 
	 * @param userID	The ID of the user
	 * @return			The username corresponding to the ID
	 */
	public String getUsername(int userID);
	
	/**
	 * Checks if a username is currently in the db
	 * 
	 * @param username The username to check
	 * @return 
	 */
	public boolean isUserInDb(String username);
	
	/**
	 * Retrieves an array of all persons in the db.
	 * Note that the objects in this array is never simply of the base type Person, but either User or Personnel
	 * 
	 * @param userID The ID of the user the returned users are related to. This must be either the system admin (return all users) or a centre manager
	 * @return An array of needed users
	 */
	public Person[] getPersons(int userID); 
	
	/**
	 * Finds the hashed and salted password for the user indicated by the user ID.
	 * Use the PasswordManager class to validate passwords.
	 * 
	 * @see PasswordManager
	 * @param user The ID of the user
	 * @return	The hashed and salted password
	 */
	
	public String getPasswordHash(int user);
	

	/**
	 * This method test the database connection by retrieving the table names, thus validating that we can execute queries.
	 * 
	 * @return	True if the query is successful, False if not
	 */
	public boolean testConnection();
	
	/**
	 * This method cleans up the database connection, closing it.<br>
	 * It needs to be called before the application is shut down.
	 */
	public void teardown();
	
	/**
	 * Finds all shopping centres in the database related to the user in question.
	 * For the SysAdmin (UserID = 1) this means every shopping centre.
	 * 
	 * These shopping centre objects is fully set up with buildings and establishments
	 * 
	 * @param userID	The user ID of the user
	 * @return			The related shopping centres
	 */
	public ShoppingCentre[] getShoppingCentres(int userID);
	
	/**
	 * Creates a ShoppingCentre in the database, together with the centreManager.
	 * 
	 * @param firstname			The first name of the manager
	 * @param lastname			The last name of the manager
	 * @param username			The username of the manager
	 * @param password			The password. Should be hashed and salted.
	 * @param email				the email of the manager
	 * @param personalAddress	The personal address of the manager
	 * @param personalZip		The personal zip of the manager
	 * @param telephone			The managers telephone number
	 * @param salary			The managers salary
	 * @param centreName		The name of the new Centre
	 * @param centreAddress		The address of the centre
	 * @param centreZip			The zipcode of the centre
	 * @return True if we succeeded creating the new data.
	 */
	public boolean createShoppingCentre(
			String firstname, 		String lastname,
			String username, 		String passwordHash,
			String email,			String personalAddress,
			int personalZip,		int telephone,
			int salary,				String centreName,
			String centreAddress,	int centreZip);
	
	/**
	 * Finds the UserType of the user.
	 * 
	 * @param userId	The user in question
	 * @return			The type of the user
	 */
	public UserType getUserType(int userId);

	/**
	 * Retrieves the corresponding centre ID to a user
	 * 
	 * @param userID	The user ID
	 * @return			The centreID related to a user. 0 in the case of the system admin, -1 if invalid user
	 */
	public int getCentreID(int userID);
	
	/**
	 * Retrieves the establishment ID for a shop owner.
	 * 
	 * Returns -1 if an invalid user ID is supplied.
	 * 
	 * @param userID	The ID of a shop owner
	 * @return	The establishment ID
	 */
	public int getEstablishmentID(int userID);
	
	/**
	 * Gets the businessID for the business a certain user belongs to.
	 * For a centre manager, this would be the centre, and for an establishment owner, this would
	 * be the id of the establishment.
	 * 
	 * @param userID The userID of an establishment owner of a centre manager
	 * @return An int containing the businessID, or -1 if an invalid userID was provided
	 */
	public int getBusinessID(int userID);
	
	/**
	 * Gets all messages from the db concerning a certain user
	 * 
	 * @param username	The user in question
	 * @return			A list of all messages, sent and recieved
	 */
	public Message[] getMessages(String username);

	/**
	 * Creates a new user in the db
	 * 
	 * @param firstName		The first name of the new user
	 * @param lastName		The last name of the new user
	 * @param address		The address of the new user
	 * @param zipCode		The zipcode of the new user
	 * @param email			The email address of the new user
	 * @param telephone		The telephone number of the new user
	 * @param salary		The salary of the new user
	 * @param username		The new username
	 * @param passwordHash	The hashed and salted password
	 * @return	True if we managed to push the data to the db
	 */
	public boolean createUser	(String firstName, String lastName, String address, int zipCode, 
			String email, int telephone, int salary, String username, String passwordHash);
	
	

	/**
	 * 
	 * Creates a new personell in the db
	 * 
	 * @param firstName	First name of the user
	 * @param lastName	Last name of the user
	 * @param address	The users street address
	 * @param zipCode	The users zipcode
	 * @param email		The users email address
	 * @param telephone	The users telephone number
	 * @param sallary	The users salary
	 * @param title		The users title
	 * @param centreID	The centre ID of wich the user belongs
	 * @return True if we managed to push the data to the db
	 */
	public boolean createPersonnel	(String firstName, String lastName, String address, int zipCode, 
			String email, int telephone, int sallary, String title, int centreID);
	
	/**
	 * 
	 * Uploads a message to the db
	 * 
	 * @param sender		The username of the sender
	 * @param recievers		An array containing the usernames of the recievers
	 * @param content		The content of the message
	 * @param subject		The subject of the message
	 * @return				True if the message was successfully uploaded to the db
	 */
	public boolean sendMessage (String sender, String[] recievers, String content, String subject);


	/**
	 * Retrieves all trades registered in the DB
	 * 
	 * @return	Returns an <code>ArrayList&ltTrade&gt</code> of all Trade object that the establishment has not registered
	 */
	public ArrayList<Trade> getAllTrades();

	/**
	 * Retrieves all selected trades of an establishments
	 * 
	 * @param establishmentId The ID of the establishment in question
	 * @return An array containing the trades
	 */
	public Trade[] getSelectedTrades(int establishmentId);

	/**
	 * Checks if a zipcode exists in the db
	 * 
	 * @param zipCode	The zipcode to check
	 * @return			True if the zipcode exits
	 */
	public boolean zipExists(int zipCode);

	/**
	 * Retrieves the total number of shopping centres in the db
	 * @return An integer containing the number of shopping centres in the db
	 */
	public int getNumberOfShoppingCentres();
	
	/**
	 * Retrieves the total number of establishments in the db
	 * @return An integer containing the number of establishments in the db
	 */
	public int getNumberOfEstablishment();
	
	/**
	 * Gets all tickets related to a certain shopping centre
	 * 
	 * @param centreID	The ID of the centre in question
	 * @return			An array containing all tickets related to the centre in question
	 */
	public Ticket[] getTickets(int centreID);
	
	/**
	 * Retrieves an array of revenue objects for a specified business from the db.
	 * 
	 * @param businessID	The business to find the revenues from
	 * @param from			The date from
	 * @param to			The date to
	 * @return An array of revenue objects. The length of this array will be equal to the number of months between to and from
	 */
	public Revenue[] getRevenue(int businessID, Date from, Date to);
}
