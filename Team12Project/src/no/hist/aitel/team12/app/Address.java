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
 * Address.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;


/**
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

	public Address(String address, int zipcode, String municipality, String county, String district) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.municipality = municipality;
		this.county = county;
		this.district = district;
	}

	public String getAdress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	public String getDistrict() {
		return district;
	}

	@Override
	public String toString() {
		return address + ", " + zipcode + ", " + district + ", " + municipality + ", " + county;
	}
	
	public String toShortString() {
		return address + " " + zipcode + " " + district;
	}
	
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
