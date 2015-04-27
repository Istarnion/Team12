/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * PasswordReset.java Team 12, 27. apr. 2015
 *******************************************************************************/
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
