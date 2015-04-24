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
 * Text.java Team 12, 19 Feb 2015
 *******************************************************************************/

package no.hist.aitel.team12.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import no.hist.aitel.team12.resources.TextResources;

/**
 * A simple utility class to shorten the amount of code needed to get a String from the resource bundles.
 * 
 * @author Hallgeir
 * @version 1.0
 */
public class Text {

	public static Locale
		NORWEGIAN = findNorwegianLocale(),
		ENGLISH = Locale.ENGLISH;
	
	private Text() {}

	/**
	 * This method gets the text resources of the approprite language and concats them together, with a single space inbetween.
	 * 
	 * @param keys The keys to the string resources needed.
	 * @return The string requested.
	 */
	public static String getString(String... keys) {
		String output = "";
		String resource;
		for(String key : keys) {
			try {
				output += ResourceBundle.getBundle(TextResources.class.getName()).getString(key)+" ";
			}
			catch(MissingResourceException e) {
				output += " ";
			}
		}
		return output.trim();
	}
	
	/**
	 * This method does the same as <code>getText(String... keys)</code>, but places the given delimiter between the resoures.
	 * 
	 * @param delimiter	The delimiter to be used
	 * @param keys		The keys to the resources
	 * @return			A concated string of text resources
	 */
	public static String getCustomString(String delimiter, String... keys) {
		String output = "";
		for(String key : keys) {
			try {
				output += ResourceBundle.getBundle(TextResources.class.getName()).getString(key)+delimiter;
			}
			catch(MissingResourceException e) {
				output += " ";
			}
		}
		if(!output.isEmpty()) {
			output = output.substring(0, output.length()-delimiter.length());
		}
		
		return output;
	}
	
	public static void setLocale(Locale l) {
		Locale.setDefault(l);
	}
	
	private static Locale findNorwegianLocale() {
		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			if(locale.toString().equals("no")) {
				return locale;
			}
		}
		return null;
	}
}
