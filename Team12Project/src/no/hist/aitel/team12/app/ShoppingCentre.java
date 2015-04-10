package no.hist.aitel.team12.app;

import no.hist.aitel.team12.database.Database;

public class ShoppingCentre extends Business {

	public final int centreId;
	
	private int parkingSpaces;
	
	private String description;
	
	public ShoppingCentre(
			int businessId, String businessName, Adress adress,
			EmailAdress email, String telephone, String openingHours,
			int centreId, int parkingSpaces, String description) {
		
		super(businessId, businessName, adress, email, telephone, openingHours);
		
		this.centreId = centreId;
		this.parkingSpaces = parkingSpaces;
		this.description = description;
	}
	
	public int getParkingSpaces() {
		return parkingSpaces;
	}

	public void setParkingSpaces(int parkingSpaces) {
		this.parkingSpaces = parkingSpaces;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCentreId() {
		return centreId;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public static ShoppingCentre[] getShoppingCentres(Database db) {
		String[][] data = db.getShoppingCentreData();
		
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<data[0].length; j++) {
				System.out.print(data[i][j]+"\t");
			}
			System.out.println();
		}
		
		ShoppingCentre[] output = new ShoppingCentre[data.length];
		
		for(int i=0; i<data.length; i++) {
			output[i] = new ShoppingCentre(
					Integer.parseInt(data[i][3]), data[i][7], new Adress(),
					new EmailAdress(), data[i][11], data[i][12],
					Integer.parseInt(data[i][4]), Integer.parseInt(data[i][5]), data[i][6]
					);
		}
		
		return output;
	}
}
