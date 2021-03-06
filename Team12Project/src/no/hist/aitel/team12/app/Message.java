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
 * Message.java Team 12, 13 Apr 2015
 *******************************************************************************/

package no.hist.aitel.team12.app;

import java.sql.Timestamp;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;


/**
 * This class holds the data for a message in the db
 * 
 * @author Gjermund
 * @version 1.0
 * 
 */
public class Message {
	
	private final int messageId;
	
	private final String reciever;

	private final String sender;
	
	private final String subject;
	
	private final String content;
	
	private boolean deleted;
	
	private final Timestamp timestamp;
	
	/**
	 * Constructor fills all fields
	 * 
	 * @param id			The ID of the message
	 * @param reciever		The reciever of the message
	 * @param sender		The sender
	 * @param subject		The subject
	 * @param content		The content
	 * @param timestamp		The timestamp of when the message was sent
	 * @param deleted		a flag indicating if this message is deleted
	 */
	public Message(int id, String reciever, String sender, String subject, String content, Timestamp timestamp, boolean deleted) {
		this.messageId		= id;
		this.reciever 		= reciever;
		this.sender 		= sender;
		this.subject 		= subject;
		this.content		= content;
		this.timestamp 		= timestamp;
		this.deleted 		= deleted;
	}
	
	public String getReciever() {
		return reciever;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets the deleted status of this message.
	 * The object is not altered if the db push fails.
	 * 
	 * @param sender	If the message should be deleted for the sender or the reciever
	 * @param username	The username of the 'owner' (either sender or reciever) of this message
	 */
	public void setDeleted(boolean sender, String username) {
		Database db = DatabaseFactory.getDatabase();
		String query = "UPDATE "+(sender?"messageSender":"messageReciever")+" SET trashed = True WHERE username = ? AND message_id = "+messageId;
		if (db.executePreparedStatement(query, username)) {
			deleted = true;
		}
	}
	
	/**
	 * Method for retrieving messages relevant for a specific user.
	 * 
	 * @param username The user in question
	 * @return The messages relevant for this user
	 */
	
	public static Message[] getUserMessages(String username) {
		Database db = DatabaseFactory.getDatabase();
		Message[] messages = db.getMessages(username);
		return messages;
	}
	
	@Override
	public String toString() {
		return subject;
	}

	/**
	 * Uploads a new message to the database
	 * 
	 * @param sender	The sender, must be a valid username
	 * @param recievers	The reciever, must be a valid username
	 * @param content	The content
	 * @param subject	The subject
	 * @return
	 */
	public static boolean sendMessage(String sender, String[] recievers, String content,
			String subject) {
		Database db = DatabaseFactory.getDatabase();
		return db.sendMessage(sender, recievers, content, subject);
	}
}
