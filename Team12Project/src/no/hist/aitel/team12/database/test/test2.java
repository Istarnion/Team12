package no.hist.aitel.team12.database.test;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;

public class test2 {

	private Database db;
	public test2() {
		DatabaseFactory.setup();
		db = DatabaseFactory.getDatabase();
		
		
		boolean testPersonnel = db.createPersonnel("testname", "lastname", "gatenavn 1", 7227, "asd@asd.no", 9, 10, "sjef", 2);
		
		
		db.teardown();
	}
	
	public static void main(String[] args) {
		new test2();
	}
}
