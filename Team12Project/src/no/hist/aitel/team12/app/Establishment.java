package no.hist.aitel.team12.app;

import java.util.ArrayList;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;

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
		return true;
	}

	public int getEstablishmentId() {
		return establishmentId;
	}
}
