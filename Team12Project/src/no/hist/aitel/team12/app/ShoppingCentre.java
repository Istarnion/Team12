package no.hist.aitel.team12.app;

import java.util.Arrays;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

public class ShoppingCentre extends Business {

	public final int centreId;

	private int parkingSpaces;
	
	private Building[] buildings;

	private int numBuildings;
	
	
	public ShoppingCentre(
			int businessId, String businessName, Address address,
			EmailAddress email, int telephone, int openingHours,
			int centreId, int parkingSpaces, String description) {

		super(businessId, businessName, email, telephone, openingHours, description, address);

		this.centreId = centreId; 
		this.parkingSpaces = parkingSpaces;
	}

	public int getParkingSpaces() {
		return parkingSpaces;
	}

	public void setParkingSpaces(int parkingSpaces) {
		this.parkingSpaces = parkingSpaces;
	}


	public int getCentreId() {
		return centreId;
	}

	public Building[] getBuildings() {
		return buildings;
	}

	public void setBuildings(Building[] buildings) {
		this.buildings = buildings;
		numBuildings = buildings.length;
	}

	public void addBuilding(Building b) {
		if(buildings == null) buildings = new Building[2];
		if(numBuildings == buildings.length) {
			buildings = Arrays.copyOf(buildings, buildings.length*2);
			buildings[numBuildings++] = b;
		}
		else {
			buildings[numBuildings++] = b;
		}
	}
	
	public Building findBuilding(int buildingId) {
		for(Building b : buildings) {
			if(b.getBuildingId() == buildingId) return b;
		}
		return null;
	}
	

	public static ShoppingCentre[] getPopulatedShoppingCentres() {
		long timestamp = System.currentTimeMillis();
		Database db = DatabaseFactory.getDatabase();
		ShoppingCentre[] centres = db.getShoppingCentres(1);
		
		System.out.println(((double)(System.currentTimeMillis()-timestamp)/1000)+" seconds to complete.");
		
		return centres;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
