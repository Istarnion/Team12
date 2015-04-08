package no.hist.aitel.team12.database;

public class DatabaseFactory {
	
	private static Database db;
	
	private static boolean ready = false;
	
	/**
	 * @return A handle to the database
	 */
	public static Database getDatabase() {
		if(ready) {
			return db;
		}
		return null;
	}
	
	/**
	 * This method sets up the connection to the database
	 * 
	 * @return True if we successfully connected to the database
	 */
	public static boolean setup() {
		return false;
	}
}
