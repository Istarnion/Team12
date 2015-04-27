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
 * PasswordManagerTest.java Team 12, 27. apr. 2015
 *******************************************************************************/
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
