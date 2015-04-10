package no.hist.aitel.team12.app;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * This is the EmailAdress class.
 * 
 * The method validateEmailAddress checks if the selected email-address is valid on the format xx@yy.com or xx.yy@xx.com
 * 
 * 
 * @author Roger
 *
 */

public class EmailAddress {
	private String emailAddress;
	
	
	public static boolean isValidEmailAddress(String emailAddress){
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(emailAddress);
			emailAddr.validate();
			
		} catch (AddressException e) {
			// TODO: handle exception
			result = false;
			System.out.println("IllegalArgumentException");
		} 
		return result;
		
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	

}
