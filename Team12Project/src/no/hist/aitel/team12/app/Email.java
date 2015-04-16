package no.hist.aitel.team12.app;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.*;

import no.hist.aitel.team12.util.Text;

/*
 * This class takes use of the Java Mail library to send mails from in
 *  this instance a gmail account.
 * 
 * @ Andreas
 * @ Version 1.0
 * 
 */


public class Email {
	
	public static final String FROM = "supershoppingsurfer@gmail.com";
	public static final String PASSWORD = "teamadmin12";
	public static final String SUBJECT = Text.getString("regsub");
	public static final String HOST = "smtp.gmail.com";
	
	public static void main(String[]args){
		try{
			sendEmail("Dette er en test","andreasborsheim91@gmail.com");
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void sendEmail(String message, String toAddress) throws Exception{
		//String toAaddress = "andreasborsheim@gmail.com";
		//String to = toAddress.getEmailAddress();  
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable","true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", HOST);
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port",587);
		props.setProperty("mail.user",FROM);
		props.setProperty("mail.password",PASSWORD);
		
		//props.put("mail.smtp.starttls.enable","true");
		//props.put("mail.smtp.host", host);
		
		//props.put("mail.smtp.user", from);
		//props.put("mail.smtp.password", password);
		
		
		
		Session session = Session.getDefaultInstance(props,null);
		session.setDebug(true);
		
		Transport transport = session.getTransport("smtp");
		
	    MimeMessage msg = new MimeMessage(session);
	    msg.setSentDate(new Date());
	    msg.setSubject(SUBJECT);
	    msg.setFrom(new InternetAddress(FROM));
	    msg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toAddress));
	    msg.setText(message);
	    
	    
	    transport.connect(HOST,FROM,PASSWORD);
	    transport.sendMessage(msg, msg.getRecipients(javax.mail.Message.RecipientType.TO));
	    transport.close();    
	}
}
	    //transport.connect(host,from,password);
	        //transport.sendMessage(msg, msg.getAllRecipients());
	        //transport.close();
	       
	        
      //  } catch (Exception e) {
	        //    e.printStackTrace();
	//	}
		
	



  
