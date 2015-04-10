package no.hist.aitel.team12.app;

import no.hist.aitel.team12.util.DoubleMetaphoneUtils;

public class Business {

	public final int businessId;
	
	private String businessName;
	
	private Adress adress;
	
	private EmailAddress email;
	
	private int telephone;
	
	private int openingHours;

	public Business(
			int businessId, String businessName, Adress adress,
			EmailAddress email, int telephone, int openingHours) {
		this.businessId = businessId;
		this.businessName = businessName;
		this.adress = adress;
		this.email = email;
		this.telephone = telephone;
		this.openingHours = openingHours;
	}

	public String getBusinessnameDmp() {
		return DoubleMetaphoneUtils.encodeString(businessName);
	}
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public int getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(int openingHours) {
		this.openingHours = openingHours;
	}

	public int getBusinessId() {
		return businessId;
	}
	
	@Override
	public String toString() {
		return businessName;
	}
}
