package no.hist.aitel.team12.app.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import no.hist.aitel.team12.app.EmailAddress;

import org.junit.Test;

public class EmailAddressTest {

	@Test
	public void test() {
		String invalidEmailAddress = "dfgsdg@dfgdfg,sdf";
		String validEmailAddress = "asdasd@dfsdf.vrg";
		
		assertFalse(EmailAddress.isValidEmailAddress(invalidEmailAddress));
		assertTrue(EmailAddress.isValidEmailAddress(validEmailAddress));
	}
}
