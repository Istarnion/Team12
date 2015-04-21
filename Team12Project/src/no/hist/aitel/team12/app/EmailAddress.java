package no.hist.aitel.team12.app;


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
		return emailAddress.matches("(.*)@(.*)\\.(.*)");
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	

}
