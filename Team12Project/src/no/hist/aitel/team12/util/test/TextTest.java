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
 * TextTest.java Team 12, 19 Feb 2015
 *******************************************************************************/

package no.hist.aitel.team12.util.test;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import no.hist.aitel.team12.util.Text;

import org.junit.Test;

/**
 * JUnit test for the Text utility class
 * 
 * @author Hallgeir
 */
public class TextTest {

	@Test
	public void test() {
		Locale[] lacales = Locale.getAvailableLocales();
		Locale l = null;
		for (Locale locale : lacales) {
			if(locale.toString().equals("no")) {
				l = locale;
				break;
			}
		}
		
		Locale.setDefault(Locale.ENGLISH);
		String y = Text.getString("y");
		assertTrue(y.equals("Yes"));
		
		Locale.setDefault(l);
		y = Text.getString("y");
		assertTrue(y.equals("Ja"));
	}
}
