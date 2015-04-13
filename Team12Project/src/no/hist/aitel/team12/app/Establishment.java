package no.hist.aitel.team12.app;

import java.util.ArrayList;

public class Establishment extends Business {
	
	private final int establishmentId;
	
	private ArrayList<String> trades;
	
	private ArrayList<Revenue> revenue;
	
	private int floorNumber;

	public Establishment(
			int businessId,	String businessName,	EmailAddress email,
			int telephone,	int openingHours,		int floorNumber,
			int establishmentId
			) {
		
		super(businessId, businessName, email, telephone, openingHours);
		
		this.establishmentId = establishmentId;
		this.floorNumber = floorNumber;
	}

	public ArrayList<String> getTrades() {
		return trades;
	}

	public void setTrades(ArrayList<String> trades) {
		this.trades = trades;
	}

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
}
