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
 * Personnel.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

/**
 * Extension of the Person class
 * 
 * @author Team12
 */
public class Personnel extends Person {

	private String title;
	
	private int centreID;
	
	public Personnel(int employeeNumber, String firstName, String lastName, Address address, 
			EmailAddress email, int telephone, int salary, String title, int centreID) {
		super(employeeNumber, firstName, lastName, address, email, telephone, salary);
		this.title 		= title;
		this.centreID 	= centreID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCentreID() {
		return centreID;
	}
	
	/**
	 * This method pushes a new Personell to the db.
	 * 
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param zipCode
	 * @param email
	 * @param telephone
	 * @param salary
	 * @param title
	 * @param centreID
	 * @return	True if we managed to push all the info to the db
	 */
	public static boolean createPersonnel(
			String firstname, 		String lastname,
			String address,			int zipCode,			
			String email,			int telephone,			
			int salary,				String title,			
			int centreID) {
		
		Database db = DatabaseFactory.getDatabase();
		return db.createPersonnel(
				firstname,			lastname,
				address,			zipCode,
				email,				telephone,
				salary,				title,
				centreID);
	}
	
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

}
