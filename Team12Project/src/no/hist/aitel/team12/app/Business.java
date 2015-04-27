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
 * Business.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.DoubleMetaphoneUtils;
import no.hist.aitel.team12.util.Text;

/**
 * This is the super class for both Establishment and ShoppingCentre.
 * It holds all the data that is common for those two classes, and mirrors the Business table in the db
 * 
 * @author Hallgeir
 */
public class Business {

	public final int businessId;

	private String businessName;

	private String description;

	private EmailAddress email;

	private Address address;

	private int telephone;

	private String openingHours;

	private ArrayList<Revenue> revenue;
	
	private String ownerName;

	protected Database db;

	/**
	 * The constructor simply sets all datapoints
	 * 
	 * @param businessId	The ID of this business
	 * @param businessName	The name of this business
	 * @param email			The email address to this business
	 * @param telephone		The telephone number of this business
	 * @param openingHours	The opening hours of this business
	 * @param description	The textual description of this business
	 * @param address		The street address of this business
	 * @param revenue		An ArrayList containing earlier revenues of this business
	 * @param ownerName		The name of the owner of this business
	 */
	public Business(
			int businessId, String businessName,
			EmailAddress email, int telephone,
			String openingHours, String description, 
			Address address, ArrayList<Revenue> revenue, String ownerName) {

		this.businessId 	= businessId;
		this.businessName 	= businessName;
		this.email 			= email;
		this.telephone 		= telephone;
		this.openingHours 	= openingHours;
		this.description 	= description;
		this.address 		= address;
		this.revenue 		= revenue;
		this.ownerName 		= ownerName;
		
		db = DatabaseFactory.getDatabase();
	}

	public String getBusinessnameDmp() {
		return DoubleMetaphoneUtils.encodeString(businessName);
	}

	public String getBusinessName() {
		return businessName;
	}

	public boolean setBusinessName(String businessName) {
		if(db.executePreparedStatement("UPDATE business SET business_name = ? WHERE business_id = " + this.businessId, businessName)) {
			this.businessName = businessName;
			return true;
		}
		return false;
	}

	public EmailAddress getEmail() {
		return email;
	}

	/**
	 * Sets the email address of this business, updating the info in the db.
	 * Note that the info in the object will not be changed unless the update in the db succeeds.
	 * 
	 * @param email	The new email address
	 * @return	True if the update succeeded
	 */
	public boolean setEmail(String email) {
		if(EmailAddress.isValidEmailAddress(email)) {
			if(db.executePreparedStatement("UPDATE business SET email = ? WHERE business_id = " + this.businessId, email)) {
				this.email = new EmailAddress(email);
				return true;
			}
			return false;
		}
		else{
			JOptionPane.showMessageDialog(null, Text.getString("invalidEmail"));
			return false;
		}
	}

	public int getTelephone() {
		return telephone;
	}

	/**
	 * Updates the telephone number both in the object and the db.
	 * Neither will be altered if we fail pushing the data to the db
	 * 
	 * @param telephone The new telephone number
	 * @return True if the update succeeded
	 */
	public boolean setTelephone(String telephone) {
		try {
			int parsedTlf = Integer.parseInt(telephone);
			if(String.valueOf(parsedTlf).length() != 8){
				return false;
			}
			if(db.executePreparedStatement("UPDATE business SET telephone = ? WHERE business_id = " + this.businessId, parsedTlf)){
				this.telephone = parsedTlf;
				return true;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
		return false;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	/**
	 * Sets the opening hours of the business both in this object and in the db.
	 * The object is not updated unless the db update is successful
	 * 
	 * @param openingHours The new opening hours
	 * @return True if the db update succeeded
	 */
	public boolean setOpeningHours(String openingHours) {
		int hour1, hour2, hour3, hour4;
		try {
			hour1 = Integer.parseInt(openingHours.substring(0, 2));
			hour2 = Integer.parseInt(openingHours.substring(2, 4));
			hour3 = Integer.parseInt(openingHours.substring(4, 6));
			hour4 = Integer.parseInt(openingHours.substring(6, 8));
			
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, Text.getString("invalidHrs"));
			return false;
		}

		if(hour1 >= hour2 || hour3 >= hour4) {
			JOptionPane.showMessageDialog(null, Text.getString("invalidHrs"));
			return false;
		}
		if(db.executePreparedStatement("UPDATE business SET opening_hours = ? WHERE business_id = " + this.businessId, openingHours)) {
			this.openingHours = openingHours;
			return true;
		}
		return false;
	}

	public int getBusinessId() {
		return businessId;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description both in this object and in the database.
	 * The objects state is not altered unless the db update is successful 
	 * @param description The new textual description. Max 1000 characters
	 * @return True if the db update was successful
	 */
	public boolean setDescription(String description) {
		if(db.executePreparedStatement("UPDATE business SET text_description = ? WHERE business_id = " + this.businessId, description)) {
			this.description = description;
			return true;
		}
		return false;
	}

	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address of this object, and updates the db.
	 * The state of this object is not altered unless the db update is successful
	 * 
	 * @param address The new Address
	 * @return True if the update is successful
	 */
	public boolean setAddress(String address) {
		if(db.executePreparedStatement("UPDATE business SET address = ? WHERE business_id = " + this.businessId, address)) {
			this.address.setAddress(address);
			return true;
		}
		return false;
	}

	/**
	 * Updates the zipcode in this object and the db.
	 * The state of this object is not altered unless the db update is successful.
	 * This method also checks if the zipcode is valid
	 * 
	 * @param zipcode The new zipcode
	 * @return True if the update was successful
	 */
	public boolean setZipcode(String zipcode) {
		int parsedZip = 0;
		try {
			parsedZip = Integer.parseInt(zipcode);
			if(String.valueOf(parsedZip).length() != 4) {
				JOptionPane.showMessageDialog(null, Text.getString("invalidZip"));
				return false;
			}
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, Text.getString("invalidZip"));
			return false;
		}
		if(db.executePreparedStatement("UPDATE business SET zipcode = ? WHERE business_id = " + this.businessId, parsedZip)) {
			this.address.setZipcode(parsedZip);
			return true;
		}
		JOptionPane.showMessageDialog(null, Text.getString("invalidZip"));
		return false;
	}

	public ArrayList<Revenue> getRevenue() {
		return revenue;
	}
	@Override
	public String toString() {
		return businessName;
	}

	public String getOwnerName() {
		return ownerName;
	}

}
