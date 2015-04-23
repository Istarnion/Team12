package no.hist.aitel.team12.app;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import no.hist.aitel.team12.util.Text;

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

	public boolean setFloorNumber(String floorNumber) {
		int parsedInt = 0;
		try {
			parsedInt = Integer.parseInt(floorNumber);
			if(db.executePreparedStatement("UPDATE establishment SET floor_number = ? WHERE establishment_id = ?", parsedInt, this.establishmentId)) {
				this.floorNumber = parsedInt;
				return true;
			}
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, Text.getString("invalidInt"));
		}
		return false;
	}

	public int getEstablishmentId() {
		return establishmentId;
	}
	
	public boolean addTrade(int tradeId) {
		if(db.executePreparedStatement("INSERT INTO establishmenttrade (establishment_id, trade_id) VALUES(?,?)", this.establishmentId, tradeId)) {
			return true;
		}
		return false;
	}
	
	public boolean removeTrade(int tradeId) {
		if(db.executePreparedStatement("DELETE FROM establishmenttrade WHERE establishment_id = ? AND trade_id = ?", this.establishmentId, tradeId)) {
			return true;
		}
		return false;
	}
}
