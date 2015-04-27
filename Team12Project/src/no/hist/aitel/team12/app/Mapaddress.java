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
 * Mapaddress.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mapaddress {

	@Expose
	private String mall;
	@Expose
	private String road;
	@Expose
	private String neighbourhood;
	@Expose
	private String suburb;
	@Expose
	private String city;
	@Expose
	private String county;
	@Expose
	private String state;
	@Expose
	private String postcode;
	@Expose
	private String country;
	@SerializedName("country_code")
	@Expose
	private String countryCode;

	/**
	 * 
	 * @return
	 * The mall
	 */
	public String getMall() {
		return mall;
	}

	/**
	 * 
	 * @param mall
	 * The mall
	 */
	public void setMall(String mall) {
		this.mall = mall;
	}

	/**
	 * 
	 * @return
	 * The road
	 */
	public String getRoad() {
		return road;
	}

	/**
	 * 
	 * @param road
	 * The road
	 */
	public void setRoad(String road) {
		this.road = road;
	}

	/**
	 * 
	 * @return
	 * The neighbourhood
	 */
	public String getNeighbourhood() {
		return neighbourhood;
	}

	/**
	 * 
	 * @param neighbourhood
	 * The neighbourhood
	 */
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	/**
	 * 
	 * @return
	 * The suburb
	 */
	public String getSuburb() {
		return suburb;
	}

	/**
	 * 
	 * @param suburb
	 * The suburb
	 */
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	/**
	 * 
	 * @return
	 * The city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city
	 * The city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return
	 * The county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * 
	 * @param county
	 * The county
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * 
	 * @return
	 * The state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 * The state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @return
	 * The postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * 
	 * @param postcode
	 * The postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * 
	 * @return
	 * The country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @param country
	 * The country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 
	 * @return
	 * The countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * 
	 * @param countryCode
	 * The country_code
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}