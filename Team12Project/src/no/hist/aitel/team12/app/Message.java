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

public class Message {
	
	private final String to;
	private final String from;
	private final String subject;
	private final String content;
	
	private Timestamp timestamp;
	
	
	public Message(String to, String from, String subject, String content, Timestamp timestamp) {
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	public String getTo() {
		return to;
	}
	
	public String getFrom() {
		return from;
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
	
	
}
