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
 * EmailAddress.java Team 12, 27. apr. 2015
 *******************************************************************************/
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

	@Override
	public String toString() {
		return emailAddress;
	}
}
