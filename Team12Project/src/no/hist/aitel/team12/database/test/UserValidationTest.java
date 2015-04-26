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
