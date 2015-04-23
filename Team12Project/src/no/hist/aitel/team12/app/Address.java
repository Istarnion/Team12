package no.hist.aitel.team12.app;


/**
 * 
 * @author Ole J. Skogstad
 *
 */
public class Address {

	private String address;
	
	private int zipcode;
	
	private String municipality;
	
	private String county;

	public Address(String address, int zipcode, String municipality, String county) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.municipality = municipality;
		this.county = county;
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

	@Override
	public String toString() {
		return address+", "+zipcode+", "+municipality+", "+county;
	}
}
