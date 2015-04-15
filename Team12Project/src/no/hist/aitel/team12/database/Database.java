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

import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.Message;
import no.hist.aitel.team12.app.ShoppingCentre;
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
	 * Method for retrieving the user ID through the username.
	 * Although the usernames are unique, comparisons between ints are more reliable than Strings,
	 * and therefore using the user IDs should be preferred over using the user name in the application logic.
	 * 
	 * @param username The username
	 * @return	The unique ID for this user
	 */
	public int getUserID(String username);
	
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
	 * Retrieves an array of all shopping centres in the database. <br>
	 * Note that they are not initialized with buildings (and thus neither with shops).
	 * This can be done using the getBuildingData() method.
	 * 
	 * @return An array of shoppingCentres, containing every centre in the database
	 */
	public ShoppingCentre[] getShoppingCentreData();
	
	/**
	 * Fills the given centres with building data. The buildings are populated with associated establishments.
	 * 
	 * @param centres The centres that needs to be populated with data.
	 */
	public void getBuildingData(ShoppingCentre[] centres);
	
	/**
	 * Retrieves all establishment in a certain building
	 * 
	 * @param buildingID The ID of the building in question
	 * @return	The array
	 */
	public Establishment[] getEstablishmentsInBuilding(int buildingID);
	
	/**
	 * Finds the UserType of the user.
	 * 
	 * @param userId	The user in question
	 * @return			The type of the user
	 */
	public UserType getUserType(int userId);

	/**
	 * 
	 * @param username	The user in question
	 * @return			A list of all messages, sent and recieved
	 */
	public Message[] getMessages(String username);
}
