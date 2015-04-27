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
 * EmailAddress.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;


/**
 * This class holds an email address, and provides methods for validating addresses
 * 
 * @author Roger
 *
 */
public class EmailAddress {
	private String emailAddress;
	
	
	public EmailAddress(String address) {
		emailAddress = address;
	}
	
	/**
	 * Validates an email address. Note that we don't check if the address exists, merely if it has right form.
	 * This means that something like fgdsfgdsfg@dfgdfgdsfgsdg.dfgsdfgsd is correct, but
	 * team12@hist,no is not, because of the comma used between hist and no.
	 * The required form i [something]@[something].[something]
	 * @param emailAddress The email address to verify
	 * @return True if the email address is on a valid form
	 */
	public static boolean isValidEmailAddress(String emailAddress){
		return emailAddress.matches("(.*)@(.*)\\.(.*)");
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return emailAddress;
	}
}
