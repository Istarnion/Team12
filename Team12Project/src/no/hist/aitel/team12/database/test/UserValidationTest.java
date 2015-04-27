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
 * UserValidationTest.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.database.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserValidationTest {

	Database db;
	
	@Before
	public void setUp() throws Exception {
		DatabaseFactory.setup();
		db = DatabaseFactory.getDatabase();
	}

	@After
	public void tearDown() throws Exception {
		db.teardown();
	}

	@Test
	public void test() {
		String validUsername = "admin";
		String invalidUsername = "ikkeenbruker";
		
		assertTrue(db.isUserInDb(validUsername));
		assertFalse(db.isUserInDb(invalidUsername));
	}
}
