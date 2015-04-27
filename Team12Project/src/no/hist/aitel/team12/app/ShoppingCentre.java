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
 * ShoppingCentre.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;
import no.hist.aitel.team12.util.Text;

/**
 * 
 * Entity class for the shoppingcentre table in the db
 * 
 * @author Hallgeir
 */
public class ShoppingCentre extends Business {

	public final int centreId;

	private int parkingSpaces;
	
	private Building[] buildings;
	
	private Personnel[] personnel;

	private int numBuildings;
		
	public ShoppingCentre(
			int businessId, String businessName, Address address,
			EmailAddress email, int telephone, String openingHours,
			int centreId, int parkingSpaces, String description, 
			ArrayList<Revenue> revenue, ArrayList<Personnel> personnel,
			String ownerName
			) {

		super(businessId, businessName, email, telephone, openingHours, description, address, revenue, ownerName);

		this.centreId = centreId; 
		this.parkingSpaces = parkingSpaces;
		if(personnel != null) {
			this.personnel = new Personnel[personnel.size()];
			personnel.toArray(this.personnel);
		}
		else {
			this.personnel = new Personnel[0];
		}
		
		buildings = new Building[1];
	}

	public int getParkingSpaces() {
		return parkingSpaces;
	}

	/**
	 * Updates the number of parking spaces for this centre both for this object, and in the db.
	 * If the db push fails, the object is not altered
	 * @param parkingSpaces
	 * @return True if the update succeeds
	 */
	public boolean setParkingSpaces(String parkingSpaces) {
		int parsedInt = 0;
		try {
			parsedInt = Integer.parseInt(parkingSpaces);
			if(db.executePreparedStatement("UPDATE shoppingcentre SET parking_spaces = ? WHERE centre_id = ?", parsedInt, this.centreId)) {
				this.parkingSpaces = parsedInt;
				return true;
			}
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, Text.getString("invalidInt"));
			return false;
		}
		return false;
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

	/**
	 * Adds a building, and grows the array of buildings as needed. The array doubles in size every time
	 * @param b
	 */
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
	

	/**
	 * Retrieves all shopping centres, fully populated from the database.
	 * This method simply calls DataBuffer.getCentres() internally
	 * @return A <code>ShoppingCentre[]</code> containin all centres
	 */
	public static ShoppingCentre[] getPopulatedShoppingCentres() {
		ShoppingCentre[] centres = DataBuffer.getCentres();		
		return centres;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * Gets the area of this shopping centre, which is equal to the sum of all its buildings
	 * @return
	 */
	public int getArea() {
		int areaSum = 0;
		if(buildings == null) return areaSum;
		for(Building b : buildings) {
			if(b != null) areaSum += b.getArea();
		}
		return areaSum;
	}

	public Personnel[] getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel[] personnel) {
		this.personnel = personnel;
	}
	
	/**
	 * Creates a centre and pushes the data to the db
	 * 
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param email
	 * @param personalAddress
	 * @param personalZip
	 * @param telephone
	 * @param salary
	 * @param centreName
	 * @param centreAddress
	 * @param centreZip
	 * @return True if the db insert succeeds
	 */
	public static boolean createCentre(
			String firstname, 		String lastname,
			String username, 		String password,
			String email,			String personalAddress,
			int personalZip,		int telephone,
			int salary,				String centreName, 
			String centreAddress,	int centreZip) {
		
		Database db = DatabaseFactory.getDatabase();
		return db.createShoppingCentre(
				firstname,			lastname,
				username,			PasswordManager.generatePasswordHash(password),
				email,				personalAddress,
				personalZip,		telephone,
				salary,				centreName,
				centreAddress,		centreZip);
	}
	
	/**
	 * @return The number of shopping centres in the db
	 */
	public static int getNumberOfShoppingCentres() {
		Database db = DatabaseFactory.getDatabase();
		return db.getNumberOfShoppingCentres();
	}
	
	/**
	 * 
	 * @return A string containing map info about the location of this centre based on its address
	 */
	public String getMapString() {
		return super.getBusinessName().replaceAll(" ", "%20") 
		+ "%20" + super.getAddress().getDistrict().replaceAll(" ", "%20");
	}
}
