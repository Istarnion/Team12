package no.hist.aitel.team12.app;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.DoubleMetaphoneUtils;
import no.hist.aitel.team12.util.Text;

public class Business {

	public final int businessId;

	private String businessName;

	private String description;

	private EmailAddress email;

	private Address address;

	private int telephone;

	private String openingHours;

	private ArrayList<Revenue> revenue;

	private Database db;


	public Business(
			int businessId, String businessName,
			EmailAddress email, int telephone,
			String openingHours, String description, 
			Address address, ArrayList<Revenue> revenue) {

		this.businessId 	= businessId;
		this.businessName 	= businessName;
		this.email 			= email;
		this.telephone 		= telephone;
		this.openingHours 	= openingHours;
		this.description 	= description;
		this.address 		= address;
		this.revenue 		= revenue;
		
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

	public boolean setAddress(String address) {
		if(db.executePreparedStatement("UPDATE business SET address = ? WHERE business_id = " + this.businessId, address)) {
			this.address.setAddress(address);
			return true;
		}
		return false;
	}

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
}
