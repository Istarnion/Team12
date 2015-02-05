/**
 * 
 */
package no.hist.aitel.team12.util.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import no.hist.aitel.team12.util.DoubleMetaphoneUtils;

import org.junit.Test;

/**
 * @author Ole Jorgen
 *
 */
public class DoubleMetaphoneUtilsJUnitTest {

	@Test
	public void encodeStringTest() {
		String s1 = DoubleMetaphoneUtils.encodeString("Trondheim");
		String s2 = DoubleMetaphoneUtils.encodeString("Merqr");
		String s3 = DoubleMetaphoneUtils.encodeString("Troondhm");
		String s4 = DoubleMetaphoneUtils.encodeString("merkur");
		
		assertTrue(s2.equals(s4));
		assertFalse(s4.equals(s3));
		assertTrue(s1.equals(s3));
		assertFalse(s1.equals(s2));
	}
	
	@Test
	public void isMetaphoneValueEqualTest() {
		String s1 = "Trondheim";
		String s2 = "Merqr";
		String s3 = "Troondhm";
		String s4 = "merkur";
		
		assertTrue(DoubleMetaphoneUtils.isMetaphoneValueEqual(s1, s3));
		assertFalse(DoubleMetaphoneUtils.isMetaphoneValueEqual(s1, s2));
		assertTrue(DoubleMetaphoneUtils.isMetaphoneValueEqual(s2, s4));
		assertFalse(DoubleMetaphoneUtils.isMetaphoneValueEqual(s2, s1));
		
	}

}
