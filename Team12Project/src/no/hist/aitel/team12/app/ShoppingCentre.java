package no.hist.aitel.team12.app;

import java.util.ArrayList;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

public class ShoppingCentre extends Business {

	public final int centreId;

	private int parkingSpaces;

	private String description;
	
	private ArrayList<Building> buildings;

	private Address address;
	
	public ShoppingCentre(
			int businessId, String businessName, Address address,
			EmailAddress email, int telephone, int openingHours,
			int centreId, int parkingSpaces, String description) {

		super(businessId, businessName, email, telephone, openingHours);

		this.centreId = centreId; 
		this.parkingSpaces = parkingSpaces;
		this.description = description;
		this.address = address;
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

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static ShoppingCentre[] getPopulatedShoppingCentres() {
		Database db = DatabaseFactory.getDatabase();
		ShoppingCentre[] centres = db.getShoppingCentreData();
		
		db.getBuildingData(centres);
		
		return centres;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
