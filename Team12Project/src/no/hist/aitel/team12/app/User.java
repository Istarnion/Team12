package no.hist.aitel.team12.app;


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
	
	
}
