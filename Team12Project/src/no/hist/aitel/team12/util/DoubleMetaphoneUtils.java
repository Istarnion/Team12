/*
 * DoubleMetaphoneUtils
 * 
 * v1.0
 * 
 * 2015.02.05
 * 
 * <Licence info goes here>
 * 
 */

package no.hist.aitel.team12.util;
import org.apache.commons.codec.language.DoubleMetaphone;
/**
 * This is a thin wrapper around org.apache.commons.codec.language.DoubleMetaphone.<br />
 * Read more about the Double Metaphone algorithm on <a href="">Wikipedia</a>.<br />
 * 
 * @author Ole Jorgen
 * 
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
}

