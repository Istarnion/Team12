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
 * Email.java Team 12, 17 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import no.hist.aitel.team12.util.Text;


/*
 * This class takes use of the Java Mail library to send mails from in
 *  this instance a gmail account.
 * 
 * @ Andreas
 * @ Version 1.0
 */
public class Email {

	private static final String FROM = "supershoppingsurfer@gmail.com";
	private static final String PASSWORD = "teamadmin12";
	private static final String SUBJECT = Text.getString("regsub");
	private static final String HOST = "smtp.gmail.com";

	/**
	 * Sends a mail to a new user. The subject line is constant, but the content can be whatever is sent in here.
	 * 
	 * @param message	A string containing the message to be sent.
	 * @param toAddress	The target email address.
	 */
	public static void sendEmail(String message, EmailAddress toAddress) { 
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable","true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", HOST);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port",587);
		props.setProperty("mail.user",FROM);
		props.setProperty("mail.password",PASSWORD);

		Session session = Session.getDefaultInstance(props,null);
		session.setDebug(true);

		Transport transport = null;
		try {
			transport = session.getTransport("smtp");

			MimeMessage msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			msg.setSubject(SUBJECT);
			msg.setFrom(new InternetAddress(FROM));
			msg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toAddress.getEmailAddress()));
			msg.setText(message);


			transport.connect(HOST,FROM,PASSWORD);
			transport.sendMessage(msg, msg.getRecipients(javax.mail.Message.RecipientType.TO));
			transport.close();
		}
		catch(MessagingException e) {
			e.printStackTrace();
		}
		finally {
			if(transport != null) {
				try {
					transport.close();
				}
				catch(MessagingException e) {
					System.err.println("Failed to close the mail Transport.");
					e.printStackTrace();
				}
			}
		}
	}
}
