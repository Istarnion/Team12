package no.hist.aitel.team12.app;

import no.hist.aitel.team12.util.DoubleMetaphoneUtils;

public class Business {

	public final int businessId;
	
	private String businessName;
	
	private Adress adress;
	
	private EmailAdress email;
	
	private String telephone;
	
	private String openingHours;

	public Business(
			int businessId, String businessName, Adress adress,
			EmailAdress email, String telephone, String openingHours) {
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

	public EmailAdress getEmail() {
		return email;
	}

	public void setEmail(EmailAdress email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
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
