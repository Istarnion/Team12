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
	
	
	public EmailAddress(String address) {
		emailAddress = address;
	}
	
	public static boolean isValidEmailAddress(String emailAddress){
		boolean result = true;
		try {
			if (emailAddress.contains(".com") || emailAddress.contains(".no") || emailAddress.contains(".as")) {
				InternetAddress emailAddr = new InternetAddress(emailAddress);
				emailAddr.validate();
			}else {
				result = false;
				System.out.println("Invalid E-Mail");
			}
			
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
