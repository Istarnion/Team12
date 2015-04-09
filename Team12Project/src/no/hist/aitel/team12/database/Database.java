package no.hist.aitel.team12.database;


public interface Database {
	public String getPasswordHash(String user);
	
	public boolean testConnection();
	
	public void teardown();
}
