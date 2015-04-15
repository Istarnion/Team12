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
 * TextResources.java Team 12, 19 Feb 2015
 *******************************************************************************/

package no.hist.aitel.team12.resources;

import java.util.ListResourceBundle;

/**
 * This class contains the default English version of all string resources to be used.
 * Note that version number is not updated for each addition to the list.
 * 
 * @author Hallgeir
 * @version 1.0
 */
public class TextResources extends ListResourceBundle {

	public TextResources() {
	}

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{"login", "Log in"},
				{"cancel", "Cancel"},
				{"y", "Yes"},
				{"n", "No"},
				{"sss", "Super Shopping Surfer"},
				{"pwd", "Password"},
				{"usr", "Username"},
				{"loginfail", "Login failed.\nInvalid username/password."},
				{"err", "Error"},
				{"send", "Send"},
				{"reply", "Reply"},
				{"from", "From"},
				{"to", "To"},
				{"sub", "Subject"},
				{"exe", "Execute"},
				{"overview", "Overview"},
				{"msgs", "Messages"},
				{"usrs", "Users"},
				{"sql", "SQL"},
				{"rport", "Report"},
				{"tckts", "Tickets"},
				{"finance", "Finance"},
				{"reg","Register"},
				{"storeTxt", "Finance report for: "},
				{"cntrTxt", "On: "},
				{"fDate", "From date: "},
				{"tDate", "To date: "},
				{"spdf", "Show PDF"},
				{"svpdf", "Save PDF"}
		};
	}
}
