package no.hist.aitel.team12.util.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import no.hist.aitel.team12.util.PasswordManager;

import org.junit.Test;

public class PasswordManagerTest {

	@Test
	public void test() {
		String pwd1 = "password123";
		String pwd2 = "AnotherPwd";
		
		String hash1 = PasswordManager.generatePasswordHash(pwd1);
		assertTrue(PasswordManager.validatePasswordMatch(pwd1, hash1));
		
		assertFalse(PasswordManager.validatePasswordMatch(pwd2, hash1));
		
		String hash2 = PasswordManager.generatePasswordHash(pwd2);
		assertTrue(PasswordManager.validatePasswordMatch(pwd2, hash2));
		
		assertFalse(PasswordManager.validatePasswordMatch(pwd1, hash2));
	}

}
