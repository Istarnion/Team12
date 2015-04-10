package no.hist.aitel.team12.database;


public interface Database {
	public String getPasswordHash(int user);
	
	public boolean testConnection();
	
	public void teardown();
	
	public String[][] getShoppingCentreData();
}
