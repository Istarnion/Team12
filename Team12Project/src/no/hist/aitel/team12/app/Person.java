package no.hist.aitel.team12.app;

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
}
