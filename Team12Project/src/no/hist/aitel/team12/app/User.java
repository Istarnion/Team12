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
 * User.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;


public class User extends Person {

	private String username;

	public User(int employeeNumber, String firstName, 
			String lastName, Address address, 
			EmailAddress email, int telephone,
			int salary, String username) {

		super(employeeNumber, firstName, lastName, address, email, telephone, salary);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * Creates a new Customer Service employee row in the db
	 * 
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param zipcode
	 * @param email
	 * @param telephone
	 * @param salary
	 * @param centreID
	 * @param username
	 * @param password
	 * @return True if successful in creating the row
	 */
	public static boolean createCustomerServiceEmployee(
			String firstname, String lastname,
			String address, int zipcode,
			String email, int telephone,
			int salary, int centreID,
			String username, String password) {

		Database db = DatabaseFactory.getDatabase();
		if(db == null) return false;

		boolean ok = db.executePreparedStatement(
				"INSERT INTO person (first_name, last_name, address, zipcode, email, telephone, salary) values(?, ?, ?, ?, ?, ?, ?)",
				firstname, lastname, address, zipcode, email, telephone, salary);
		if(!ok) return false;
		ok = db.executePreparedStatement(
				"INSERT INTO user (employee_number, username, password_hash) VALUES(LAST_INSERT_ID(), ?, ?)",
				username, PasswordManager.generatePasswordHash(password));
		if(!ok) return false;
		ok = db.executePreparedStatement(
				"INSERT INTO customerservice (employee_number, centre_id) values(LAST_INSERT_ID(), ?)",
				centreID);

		return ok;
	}
	
	/**
	 * Checks if a certain user exists in the db, based on the username
	 * 
	 * @param userName
	 * @return True if the user exists
	 */
	public static boolean userExists(String userName) {
		Database db = DatabaseFactory.getDatabase();
		int id = db.getUserID(userName);
		if(id==-1) {
			return false;
		}
		return true;
	}
}
