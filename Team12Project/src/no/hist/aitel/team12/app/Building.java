package no.hist.aitel.team12.app;

import java.util.Arrays;

import javax.swing.JOptionPane;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.Text;


public class Building {

	private int buildingId;
	
	private String buildingName;
	
	private int area;
	
	private int floors;
	
	private Establishment[] establishments;

	private int numEstablishments;
	
	private Database db;

	
	public Building(int buildingId, String buildingName, int floors, int area) {
		super();
		this.buildingId		= buildingId;
		this.buildingName 	= buildingName;
		this.floors 		= floors;
		this.area 			= area;
		
		db = DatabaseFactory.getDatabase();
	}

	public String getBuildingName() {
		return buildingName;
	}

	public boolean setBuildingName(String buildingName) {
		if(db.executePreparedStatement("UPDATE building SET building_name = ? WHERE building_id = " + this.buildingId, buildingName)) {
			this.buildingName = buildingName;
			return true;
		}
		return false;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public int getFloors() {
		return floors;
	}
	
	public boolean setNrOfFloors(String floors) {
		int parsedInt;
		try {
			parsedInt = Integer.parseInt(floors);
			if(db.executePreparedStatement("UPDATE building SET floors = ? WHERE building_id = " + this.buildingId, parsedInt)) {
				this.floors = parsedInt;
				return true;
			}
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, Text.getString("invalidInt"));
			return false;
		}
		return false;
	}
	
	public void setEstablishments(Establishment[] establishments) {
		this.establishments = establishments;
		numEstablishments = establishments.length;
	}
	
	public Establishment[] getEstablishments() {
		return establishments;
	}
	
	public void addEstablishment(Establishment e) {
		if(establishments == null) establishments = new Establishment[2];
		if(numEstablishments == establishments.length) {
			establishments = Arrays.copyOf(establishments, establishments.length*2);
			establishments[numEstablishments++] = e;
		}
		else {
			establishments[numEstablishments++] = e;
		}
	}
	
	@Override
	public String toString() {
		return buildingName;
	}

	public int getArea() {
		return area;
	}
	
	public boolean setArea(String area) {
		int parsedInt;
		try {
			parsedInt = Integer.parseInt(area);
			if(db.executePreparedStatement("UPDATE building SET area = ? WHERE building_id = " + this.buildingId, parsedInt)) {
				this.area = parsedInt;
				return true;
			}
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, Text.getString("invalidInt"));
			return false;
		}
		return false;
	}
}
