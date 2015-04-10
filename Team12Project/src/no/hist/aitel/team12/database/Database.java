package no.hist.aitel.team12.database;

import java.sql.ResultSet;

import no.hist.aitel.team12.app.Business;
import no.hist.aitel.team12.app.UserType;


public interface Database {
	
	public int getUserID(String username);
	
	public String getPasswordHash(int user);
	
	public boolean testConnection();
	
	public void teardown();
	
	public Business[] getShoppingCentreData();
	
	public UserType getUserType(int userId);
}
