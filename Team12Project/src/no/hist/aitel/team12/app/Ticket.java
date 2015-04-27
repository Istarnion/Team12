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
 * Ticket.java Team 12, 27. apr. 2015
 *******************************************************************************/
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
			boolean resolvedStatus, int centreID
			) {
		this.ticketID = ticketID;
		this.content = content;
		this.customerEmail = customerEmail;
		this.resolvedStatus = resolvedStatus;
		this.centreID = centreID;
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
