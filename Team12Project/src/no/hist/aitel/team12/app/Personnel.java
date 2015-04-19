package no.hist.aitel.team12.app;

public class Personnel extends Person {

	private String title;
	
	private int centreID;
	
	public Personnel(int employeeNumber, String firstName, String lastName,Address address, 
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
	
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

}
