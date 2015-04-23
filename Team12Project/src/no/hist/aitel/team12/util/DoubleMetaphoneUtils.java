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
 * DoubleMetaphoneUtils.java Team 12, 18 Feb 2015
 *******************************************************************************/

package no.hist.aitel.team12.util;
import org.apache.commons.codec.language.DoubleMetaphone;
/**
 * This is a thin wrapper around org.apache.commons.codec.language.DoubleMetaphone.<br />
 * Read more about the Double Metaphone algorithm on <a href="">Wikipedia</a>.<br />
 * 
 * @author Ole Jorgen
 * @version 1.0
 */
public class DoubleMetaphoneUtils {
	
	// Creates a DoubleMetaphone object
	private static DoubleMetaphone metaphone = new DoubleMetaphone();
	
	// Private constructor, no object should be initiated 
	private DoubleMetaphoneUtils() {}
	
	/**
	 * Converts the input string to a Double Metaphone value.
	 * @param st Input string that is being converted.
	 * @return Return the encoded string.
	 */
	public static String encodeString(String st) {
		return metaphone.encode(st);
	}
	
	/**
	 * Compares two strings to check if the Double Metaphone values are equal. 
	 * @param st1 One of the two strings that is being compared
	 * @param st2 The other of the two strings that is being compared
	 * @return Returns boolean value for the comparison 
	 */
	public static boolean isMetaphoneValueEqual(String st1, String st2) {
		return metaphone.isDoubleMetaphoneEqual(st1, st2);
	}
	
	/**
	 * Checks if the first string matches the beginning of the second string.
	 * Think of it almost like a wildcard-test.<br>
	 * Example: 'str' would match 'string'
	 * @param st1
	 * @param st2
	 * @return True if st1 is a valid prefix of st2
	 */
	public static boolean isBeginningMetaphoneEqual(String st1, String st2) {
		String mp1 = metaphone.encode(st1);
		String mp2 = metaphone.encode(st2);
		
		return mp2.startsWith(mp1);
	}
}

