package no.hist.aitel.team12.app;

public class Personnel extends Person {

	private String title;
	
	public Personnel(int employeeNumber, String firstName, String lastName,
			Address address, EmailAddress email, int telephone, int salary, String title) {
		super(employeeNumber, firstName, lastName, address, email, telephone, salary);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
