package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

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
