package no.hist.aitel.team12.database.test;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;

public class Test {
	
	private Database db;
	
	public Test() {
		DatabaseFactory.setup();
		db = DatabaseFactory.getDatabase();
		boolean testUser = db.createUser("Ole", "Hansen", "Storgata 1", 7018, "OleHansen@Storgata.no", 90909090, 1500000, "OHANSEN2", PasswordManager.generatePasswordHash("Hansen123"));
		System.out.println(testUser);		
		
		db.teardown();
	}
	
	public static void main(String[] test) {
		new Test();
	}
}
