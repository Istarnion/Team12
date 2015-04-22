package no.hist.aitel.team12.app;

import java.util.ArrayList;

public class Establishment extends Business {
	
	private final int establishmentId;
	
	private ArrayList<Trade> selectedTrades;
	
	private ArrayList<Revenue> revenue;
	
	private int floorNumber;

	public Establishment(
			int businessId,	String businessName,	EmailAddress email,
			int telephone,	String openingHours,		int floorNumber,
			int establishmentId, String description, Address address,
			ArrayList<Trade> selectedTrades, ArrayList<Revenue> revenue
			) {
		
		super(businessId, businessName, email, telephone, openingHours, description, address, revenue);
		
		this.establishmentId = establishmentId;
		this.floorNumber = floorNumber;
		this.selectedTrades = selectedTrades;
	}

	public ArrayList<Trade> getSelectedTrades() {
		return selectedTrades;
	}
	
	@Override
	public ArrayList<Revenue> getRevenue() {
		return revenue;
	}

	public void setRevenue(ArrayList<Revenue> revenue) {
		this.revenue = revenue;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getEstablishmentId() {
		return establishmentId;
	}
}
