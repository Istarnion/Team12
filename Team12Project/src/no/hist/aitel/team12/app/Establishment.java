package no.hist.aitel.team12.app;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;
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
			ArrayList<Trade> selectedTrades, ArrayList<Revenue> revenue,
			String ownerName
			) {
		
		super(businessId, businessName, email, telephone, openingHours, description, address, revenue, ownerName);
		
		this.establishmentId = establishmentId;
		this.floorNumber = floorNumber;
		this.selectedTrades = selectedTrades;
		if(this.selectedTrades == null) {
			this.selectedTrades = new ArrayList<Trade>();
		}
	}

	/**
	 * business
	 * estab
	 * person
	 * owner
	 * 
	 * @return
	 */
	public static boolean createEstablishment(
			String businessName, String address,
			int zipcode, String email,
			int telephone, String firstName,
			String lastName, String personalAddress,
			int personalZip, String personalEmail,
			int personalTelephone, int salary,
			String username, int buildingID,
			int floorNumber, String password) {
		Database db = DatabaseFactory.getDatabase();
		boolean ok;
		ok = db.executePreparedStatement(
				"INSERT INTO business (business_name, address, zipcode, email, telephone, opening_hours, text_description) VALUES(?, ?, ?, ?, ?, '09150915', '...')",
				businessName, address, zipcode, email, telephone);
		if(!ok) return false;
		ok = db.executePreparedStatement(
				"INSERT INTO establishment (business_id, building_id, floor_number) VALUES (LAST_INSERT_ID(), ?, ?)",
				buildingID, floorNumber);
		if(!ok) return false;
		ok = db.executePreparedStatement(
				"INSERT INTO person (first_name, last_name, address, zipcode, email, telephone, salary) values(?, ?, ?, ?, ?, ?, ?)",
				firstName, lastName, personalAddress, personalZip, personalEmail, personalTelephone, salary);
		if(!ok) return false;
		ok = db.executePreparedStatement(
				"INSERT INTO user (employee_number, username, password_hash) VALUES(LAST_INSERT_ID(), ?, ?)",
				username, PasswordManager.generatePasswordHash(password));
		ok = db.executePreparedStatement(
				"INSERT INTO establishmentowner (employee_number, establishment_id) VALUES ("
						+ "(SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'person')-1, "
						+ "(SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'establishment')-1)"
				);
		
		return ok;
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
