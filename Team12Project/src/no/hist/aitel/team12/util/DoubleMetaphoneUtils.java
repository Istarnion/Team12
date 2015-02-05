package no.hist.aitel.team12.util;
import org.apache.commons.codec.language.DoubleMetaphone;
/**
 * 
 * @author Ole Jorgen
 *
 */
public class DoubleMetaphoneUtils {
	
	// Creates a DoubleMetaphone object
	private static DoubleMetaphone metaphone = new DoubleMetaphone();
	
	// No object should be initiated 
	private DoubleMetaphoneUtils() {}
	
	/**
	 * 
	 * @param st Input string that is being converted.
	 * @return Return the encoded string.
	 */
	public static String encodeString(String st) {
		return metaphone.encode(st);
	}
	
	/**
	 * 
	 * @param st1 One of the two strings that is being compared
	 * @param st2 The other of the two strings that is being compared
	 * @return Returns boolean value for the comparison 
	 */
	public static boolean isMetaphoneValueEqual(String st1, String st2) {
		return metaphone.isDoubleMetaphoneEqual(st1, st2);
	}
}

