/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Building.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.util.Arrays;

import javax.swing.JOptionPane;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.Text;

/**
 * The building class mirror the building table in the db,
 * and is therefore  tightly coupled with it.
 * It holds info about the building, and an array of establishments located in this building
 * 
 * @author Hallgeir
 *
 */
public class Building {

	private int buildingId;
	
	private String buildingName;
	
	private int area;
	
	private int floors;
	
	private Establishment[] establishments;

	private int numEstablishments;
	
	private Database db;

	/**
	 * Constructs a new building, by setting all datapoints, except the <code>Establishment[]</code>,
	 * which is inited as an empty array of length 1.
	 * The establishments must be added later with the <code>addEstablishment</code> method.
	 * 
	 * @param buildingId	The ID of the building
	 * @param buildingName	The name of the building
	 * @param floors		The number of floors the building has
	 * @param area			The total area of the building
	 */
	public Building(int buildingId, String buildingName, int floors, int area) {
		super();
		this.buildingId		= buildingId;
		this.buildingName 	= buildingName;
		this.floors 		= floors;
		this.area 			= area;
		
		this.establishments = new Establishment[1];
		
		db = DatabaseFactory.getDatabase();
	}

	/**
	 * @return Gets the building name
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * Pushes a new name to the db
	 * The datafield in this object will not be altered unless the push to the db succeeds
	 * 
	 * @param buildingName	The new building name
	 * @return	True if we managed to push the new name to hthe db
	 */
	public boolean setBuildingName(String buildingName) {
		if(db.executePreparedStatement("UPDATE building SET building_name = ? WHERE building_id = " + this.buildingId, buildingName)) {
			this.buildingName = buildingName;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return Gets the building ID
	 */
	public int getBuildingId() {
		return buildingId;
	}

	/**
	 * 
	 * @return Gets the number of floors in the building
	 */
	public int getFloors() {
		return floors;
	}
	
	/**
	 * Sets a new number of floors in this building, pushing the info to the db
	 * The field in this object will not be updated unless the db update succeeds.
	 * @param floors The new number of floors
	 * @return True if the update was successful
	 */
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
	
	/**
	 * Replaces the old array of establishments (If there were one) with a new one.
	 * Note that this is not reflected in the db
	 * @param establishments The new Array of establishments
	 */
	public void setEstablishments(Establishment[] establishments) {
		this.establishments = establishments;
		numEstablishments = establishments.length;
	}
	
	/**
	 * Gets the array of establishments.
	 * Note that this array is not null-safe. Some indexes might be empty
	 * @return The array of establishments
	 */
	public Establishment[] getEstablishments() {
		return establishments;
	}
	
	/**
	 * Adds a new establishment to this building, growing the array as needed.
	 * The array will double every time it grows.
	 * Note that this is not reflected in the db
	 * @param e The new establishment to add
	 */
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

	/**
	 * Gets the area of the building
	 * @return The area (in square meters) of this buildings
	 */
	public int getArea() {
		return area;
	}
	
	/**
	 * Sets the area of this building, pushing the info to the db.
	 * The data is nto altered in the object if the db update fails
	 * @param area The new area (in square meters)
	 * @return True if the update succeeded
	 */
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
	
	/**
	 * Creates a new building in the db
	 * 
	 * @param centreId		The ID of the centre this building should belong to
	 * @param buildingName	The name of the new building
	 * @param floors		The number of floors the new building has
	 * @param area			The area of the new building
	 * @return True if we managed to push the data to the db
	 */
	public static boolean newBuilding(int centreId, String buildingName, int floors, int area) {
		Database db = DatabaseFactory.getDatabase();
		return db.executePreparedStatement("INSERT INTO building (centre_id, building_name, floors, area) VALUES (?,?,?,?)", centreId, buildingName, floors, area );
	}
}
