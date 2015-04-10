package no.hist.aitel.team12.database;


public interface Database {
	
	public int getUserID(String username);
	
	public String getPasswordHash(int user);
	
	public boolean testConnection();
	
	public void teardown();
	
	public String[][] getShoppingCentreData();
}
