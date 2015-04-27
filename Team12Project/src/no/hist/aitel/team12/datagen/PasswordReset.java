package no.hist.aitel.team12.datagen;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.PasswordManager;

public class PasswordReset {

	public static void main(String[] args) {
		DatabaseFactory.setup();
		Database db = DatabaseFactory.getDatabase();
		
		String asdfHash = PasswordManager.generatePasswordHash("asdf");
		
		db.executePreparedStatement(
				"UPDATE user SET password_hash = ? WHERE employee_number > 0", asdfHash);
		
		db.teardown();
	}
}
