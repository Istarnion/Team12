package no.hist.aitel.team12.app;

public class Ticket {
	
	private int ticketID;

	private String content;
	
	private EmailAddress customerEmail;
	
	private boolean resolvedStatus;
	
	private int employeeNumber;
	
	private int businessID;
	
	private int followUpID;
	
	public Ticket(int ticketID, String content, EmailAddress customerEmail,
			boolean resolvedStatus, int employeeNumber, int businessID,
			int followUpID) {
		super();
		this.ticketID = ticketID;
		this.content = content;
		this.customerEmail = customerEmail;
		this.resolvedStatus = resolvedStatus;
		this.employeeNumber = employeeNumber;
		this.businessID = businessID;
		this.followUpID = followUpID;
	}

	public boolean isResolvedStatus() {
		return resolvedStatus;
	}

	public void setResolvedStatus(boolean resolvedStatus) {
		this.resolvedStatus = resolvedStatus;
	}

	public int getTicketID() {
		return ticketID;
	}

	public String getContent() {
		return content;
	}

	public EmailAddress getCustomerEmail() {
		return customerEmail;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public int getBusinessID() {
		return businessID;
	}

	public int getFollowUpID() {
		return followUpID;
	}
}
