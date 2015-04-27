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
 * Person.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

public class Person {
	
	private int employeeNumber;
	
	private String firstName;
	
	private String lastName;
	
	private Address address;
	
	private EmailAddress email;
	
	private int telephone;
	
	private int salary;
	
	
	public Person(
			int employeeNumber, String firstName, 
			String lastName, Address address, 
			EmailAddress email, int telephone,
			int salary) {
		
		this.employeeNumber 	= employeeNumber;
		this.firstName 			= firstName;
		this.lastName			= lastName;
		this.address 			= address;
		this.email 				= email;
		this.telephone 			= telephone;
		this.salary 			= salary;
	}

	/**
	 * Updates the data both in this object, and in the db
	 * 
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param zipcode
	 * @param email
	 * @param telephone
	 * @param salary
	 * @return
	 */
	public boolean updateData(
			String firstname,	String lastname,
			String address,		int zipcode,
			EmailAddress email,	int telephone, int salary) {
		
		boolean ok = false;;
		
		String query =
				"UPDATE person SET first_name = ?, last_name = ?, address = ?, zipcode = ?, email = ?, telephone = ?, salary = ? WHERE employee_number =  "
				+employeeNumber;
		
		Database db = DatabaseFactory.getDatabase();
		if(db.executePreparedStatement(query, firstname, lastname, address, zipcode, email.getEmailAddress(), telephone, salary)) {
			ok = true;
			
			this.firstName 			= firstname;
			this.lastName			= lastname;
			this.email 				= email;
			this.telephone 			= telephone;
			this.salary 			= salary;
			
			this.address.setAddress(address);
			this.address.setZipcode(zipcode);
		}
		
		return ok;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public EmailAddress getEmail() {
		return email;
	}


	public void setEmail(EmailAddress email) {
		this.email = email;
	}


	public int getTelephone() {
		return telephone;
	}


	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public int getEmployeeNumber() {
		return employeeNumber;
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName; 
	}
}
