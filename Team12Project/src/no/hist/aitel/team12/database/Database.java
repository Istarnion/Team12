package no.hist.aitel.team12.database;

import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.UserType;


public interface Database {
	
	public int getUserID(String username);
	
	public String getPasswordHash(int user);
	
	public boolean testConnection();
	
	public void teardown();
	
	public ShoppingCentre[] getShoppingCentreData();
	
	public UserType getUserType(int userId);
}
