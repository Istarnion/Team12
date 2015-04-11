package no.hist.aitel.team12.app;

/**
 * 
 * @author Ole J. Skogstad
 *
 */
public class Adress {

	private String adress;
	
	private int zipcode;
	
	private String municipality;
	
	private String county;

	public Adress(String adress, int zipcode, String municipality, String county) {
		super();
		this.adress = adress;
		this.zipcode = zipcode;
		this.municipality = municipality;
		this.county = county;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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


}
