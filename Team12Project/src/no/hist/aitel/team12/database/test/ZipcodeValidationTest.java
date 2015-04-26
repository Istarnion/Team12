package no.hist.aitel.team12.database.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import no.hist.aitel.team12.app.Address;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZipcodeValidationTest {

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
		String validZip = "7227";
		String invalidZip = "9090";
		
		assertTrue(Address.isValidZip(validZip));
		assertFalse(Address.isValidZip(invalidZip));
	}
}
