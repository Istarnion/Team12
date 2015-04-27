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
 * EmailAddressTest.java Team 12, 27. apr. 2015
 *******************************************************************************/
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
