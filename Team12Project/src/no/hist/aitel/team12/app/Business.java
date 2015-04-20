package no.hist.aitel.team12.app;

import java.sql.PreparedStatement;
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

	private int openingHours;

	private ArrayList<Revenue> revenue;

	private Database db;


	public Business(
			int businessId, String businessName,
			EmailAddress email, int telephone,
			int openingHours, String description, 
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
		if(db.executePreparedStatement("UPDATE business SET business_name = ? WHERE business_id = " + this.businessId)) {
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

	public int getOpeningHours() {
		return openingHours;
	}

	public boolean setOpeningHours(String openingHours) {
		return true;
	}

	public int getBusinessId() {
		return businessId;
	}

	public String getDescription() {
		return description;
	}

	public boolean setDescription(String description) {
		this.description = description;
		return true;
	}

	public Address getAddress() {
		return address;
	}

	public boolean setAddress(String address) {
		this.address.setAddress(address);
		return true;
	}

	public boolean setZipcode(String zipcode) {
		return true;
	}

	public ArrayList<Revenue> getRevenue() {
		return revenue;
	}
	@Override
	public String toString() {
		return businessName;
	}
}
