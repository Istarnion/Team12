package no.hist.aitel.team12.app.test;

import javax.swing.JOptionPane;

import no.hist.aitel.team12.app.EmailAddress;


/**
 * This is a class for testing the EmailAdress validation
 * 
 * @author Roger
 *
 */

public class EmailAddressTest {
	private static String emailAddress;
	
	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			emailAddress = JOptionPane.showInputDialog("Write your email");
			System.out.println("Your emailadress : " + emailAddress + " is " + EmailAddress.isValidEmailAddress(emailAddress));
			i++;
		}
	}
}
