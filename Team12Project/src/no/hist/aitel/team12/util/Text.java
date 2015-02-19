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

import java.util.ResourceBundle;

import no.hist.aitel.team12.resources.TextResources;

/**
 * A simple utility class to shorten the amount of code needed to get a String from the resource bundles.
 * 
 * @author Hallgeir
 * @version 1.0
 */
public class Text {

	private Text() {}

	/**
	 * This methods simply calls ResourceBundle.getBundle(TextResources.class.getName()).getString(key) internally.
	 * 
	 * @param key The key to the string resource needed.
	 * @return The string requested.
	 */
	public static String getString(String key) {
		return ResourceBundle.getBundle(TextResources.class.getName()).getString(key);
	}
}
