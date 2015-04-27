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
 * Address.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;


/**
 * This data class contain address information, and provides various methods for
 * outputting the info in various formats, such as HTML
 * 
 * @author Ole J. Skogstad
 *
 */
public class Address {

	private String address;
	
	private int zipcode;
	
	private String municipality;
	
	private String district;
	
	private String county;

	/**
	 * The constructor simply sets all datapoints
	 * 
	 * @param address		The street address
	 * @param zipcode		The zipcode
	 * @param municipality	The municipality
	 * @param county		The county
	 * @param district		The name of the district connected to the zipcode
	 */
	public Address(String address, int zipcode, String municipality, String county, String district) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.municipality = municipality;
		this.county = county;
		this.district = district;
	}

	/**
	 * Gets the street address
	 */
	public String getAdress() {
		return address;
	}

	/**
	 * Sets the street address
	 * @param county The new street address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the zip code
	 */
	public int getZipcode() {
		return zipcode;
	}

	/**
	 * Sets the zipcode
	 * @param county The new zipcode
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Gets the municipality
	 */
	public String getMunicipality() {
		return municipality;
	}

	/**
	 * Sets the municipality
	 * @param county The new municipality name
	 */
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	/**
	 * Gets the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * Sets the county
	 * @param county The new county name
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	
	/**
	 * Gets the district
	 */
	public String getDistrict() {
		return district;
	}

	@Override
	public String toString() {
		return address + ", " + zipcode + ", " + district + ", " + municipality + ", " + county;
	}
	
	/**
	 * @return A shorter version of the address, containing the street address, zipcode and district, but not municipality nor county
	 */
	public String toShortString() {
		return address + " " + zipcode + " " + district;
	}
	
	/**
	 * This method looks into the db if this zipcode exists
	 * 
	 * @param zipCode The zipcode to check
	 * @return True if it does
	 */
	public static boolean isValidZip(String zipCode) {
		int parsedZip = 0;
		try {
			parsedZip = Integer.parseInt(zipCode);
		}
		catch(NumberFormatException e) {
			return false;
		}
		Database db = DatabaseFactory.getDatabase();
		if(db.zipExists(parsedZip)) {
			return true;
		}
		return false;
	}
}
