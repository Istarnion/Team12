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
 * MapGson.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapGson {

	@SerializedName("place_id")
	@Expose
	private String placeId;
	@Expose
	private String licence;
	@SerializedName("osm_type")
	@Expose
	private String osmType;
	@SerializedName("osm_id")
	@Expose
	private String osmId;
	@Expose
	private List<String> boundingbox = new ArrayList<String>();
	@Expose
	private String lat;
	@Expose
	private String lon;
	@SerializedName("display_name")
	@Expose
	private String displayName;
	@SerializedName("class")
	@Expose
	private String _class;
	@Expose
	private String type;
	@Expose
	private Double importance;
	@Expose
	private Mapaddress mapaddress;

	/**
	 * 
	 * @return
	 * The placeId
	 */
	public String getPlaceId() {
		return placeId;
	}

	/**
	 * 
	 * @param placeId
	 * The place_id
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	/**
	 * 
	 * @return
	 * The licence
	 */
	public String getLicence() {
		return licence;
	}

	/**
	 * 
	 * @param licence
	 * The licence
	 */
	public void setLicence(String licence) {
		this.licence = licence;
	}

	/**
	 * 
	 * @return
	 * The osmType
	 */
	public String getOsmType() {
		return osmType;
	}

	/**
	 * 
	 * @param osmType
	 * The osm_type
	 */
	public void setOsmType(String osmType) {
		this.osmType = osmType;
	}

	/**
	 * 
	 * @return
	 * The osmId
	 */
	public String getOsmId() {
		return osmId;
	}

	/**
	 * 
	 * @param osmId
	 * The osm_id
	 */
	public void setOsmId(String osmId) {
		this.osmId = osmId;
	}

	/**
	 * 
	 * @return
	 * The boundingbox
	 */
	public List<String> getBoundingbox() {
		return boundingbox;
	}

	/**
	 * 
	 * @param boundingbox
	 * The boundingbox
	 */
	public void setBoundingbox(List<String> boundingbox) {
		this.boundingbox = boundingbox;
	}

	/**
	 * 
	 * @return
	 * The lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * 
	 * @param lat
	 * The lat
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * 
	 * @return
	 * The lon
	 */
	public String getLon() {
		return lon;
	}

	/**
	 * 
	 * @param lon
	 * The lon
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 * 
	 * @return
	 * The displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 
	 * @param displayName
	 * The display_name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 
	 * @return
	 * The _class
	 */
	public String getClass_() {
		return _class;
	}

	/**
	 * 
	 * @param _class
	 * The class
	 */
	public void setClass_(String _class) {
		this._class = _class;
	}

	/**
	 * 
	 * @return
	 * The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 * The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return
	 * The importance
	 */
	public Double getImportance() {
		return importance;
	}

	/**
	 * 
	 * @param importance
	 * The importance
	 */
	public void setImportance(Double importance) {
		this.importance = importance;
	}

	/**
	 * 
	 * @return
	 * The mapaddress
	 */
	public Mapaddress getMapaddress() {
		return mapaddress;
	}

	/**
	 * 
	 * @param mapaddress
	 * The mapaddress
	 */
	public void setMapaddress(Mapaddress mapaddress) {
		this.mapaddress = mapaddress;
	}

}

