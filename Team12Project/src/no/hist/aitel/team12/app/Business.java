package no.hist.aitel.team12.app;

import java.util.ArrayList;

import no.hist.aitel.team12.util.DoubleMetaphoneUtils;

public class Business {

	public final int businessId;
	
	private String businessName;
	
	private String description;
	
	private EmailAddress email;
	
	private Address address;
	
	private int telephone;
	
	private int openingHours;
	
	private ArrayList<Revenue> revenue;

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
		}

	public String getBusinessnameDmp() {
		return DoubleMetaphoneUtils.encodeString(businessName);
	}
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public int getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(int openingHours) {
		this.openingHours = openingHours;
	}

	public int getBusinessId() {
		return businessId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<Revenue> getRevenue() {
		return revenue;
	}
	@Override
	public String toString() {
		return businessName;
	}
}
