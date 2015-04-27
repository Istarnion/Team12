package no.hist.aitel.team12.datagen;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;

public class PasswordReset {

	public static void main(String[] args) {
		DatabaseFactory.setup();
		Database db = DatabaseFactory.getDatabase();
		if(db == null) {
			System.out.println("Failed connectiong to the database");
			System.exit(0);
		}
		System.out.println("Connected to the database.");
		
		String asdfHash = PasswordManager.generatePasswordHash("pass");
		
		System.out.println("Resetting passwords..");
		db.executePreparedStatement(
				"UPDATE user SET password_hash = ? WHERE employee_number > 0", asdfHash);
		
		db.teardown();
		System.out.println("All passwords successfully reset.");
	}
}
