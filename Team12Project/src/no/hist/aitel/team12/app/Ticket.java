package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

public class Ticket {
	
	private int ticketID;

	private String content;
	
	private EmailAddress customerEmail;
	
	private boolean resolvedStatus;
	
	private int centreID;
	
	
	public Ticket(int ticketID, String content, EmailAddress customerEmail,
			boolean resolvedStatus, int businessID
			) {
		this.ticketID = ticketID;
		this.content = content;
		this.customerEmail = customerEmail;
		this.resolvedStatus = resolvedStatus;
		this.centreID = businessID;
	}

	public boolean isResolvedStatus() {
		return resolvedStatus;
	}

	public boolean setResolvedStatus(boolean resolvedStatus) {
		Database db = DatabaseFactory.getDatabase();
		boolean ok = db.executePreparedStatement("UPDATE ticket SET resolvedStatus = ? WHERE ticket_id = ?", resolvedStatus, ticketID);
		
		if(ok) {
			this.resolvedStatus = resolvedStatus;
		}
		return ok;
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

	public int getBusinessID() {
		return centreID;
	}
	
	public static boolean sendSupportTicket(String content, EmailAddress email, int centreID) {
		Database db = DatabaseFactory.getDatabase();
		
		if(db == null) return false;
		
		return db.executePreparedStatement(
				"INSERT INTO ticket (content, customer_email, centre_id) VALUES (?, ?, ?)",
				content, email.getEmailAddress(), centreID);
	}
	
	@Override
	public String toString() {
		return customerEmail+" #"+ticketID;
	}
}
