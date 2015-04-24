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
}
