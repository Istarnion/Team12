package no.hist.aitel.team12.app;

import java.util.ArrayList;

public class ShoppingCentre extends Business {

	public final int centreId;

	private int parkingSpaces;

	private String description;
	
	private ArrayList<Building> buildings;

	public ShoppingCentre(
			int businessId, String businessName, Address adress,
			EmailAddress email, int telephone, int openingHours,
			int centreId, int parkingSpaces, String description) {

		super(businessId, businessName, adress, email, telephone, openingHours);

		this.centreId = centreId; 
		this.parkingSpaces = parkingSpaces;
		this.description = description;
	}

	public int getParkingSpaces() {
		return parkingSpaces;
	}

	public void setParkingSpaces(int parkingSpaces) {
		this.parkingSpaces = parkingSpaces;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCentreId() {
		return centreId;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
