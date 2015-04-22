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
				{"svpdf", "Save PDF"},
				{"newuser", "New User"},
				{"edituser", "Edit User"},
				{"regsub", "SuperShoppingSurfer - New user created"},
				{"msgConfirmation", "The message has been sent"},
				{"businessName", "Business name"},
				{"email", "Email"},
				{"openingHrs", "Opening Hours"},
				{"textDescription", "Description"},
				{"edit", "Edit"},
				{"save", "Save"},
				{"nrOfFloors", "Number of floors"},
				{"area", "Areal"},
				{"firstname","First name"},
				{"lastname","Last name"},
				{"adr","Address"},
				{"zip","Zip code"},
				{"tel","Phone"},
				{"sal","Salary"},
				{"reverr", "Revenue field is empty"},
				{"rev", "Revenue"},
				{"revdaterr", "Could not register revenue.\nDate from/to is missing."},
				{"pdferr", " Cannont show finance report.\nDate from/to is missing."},	
				{"daterr", "From date must be before to date"},
				{"createuser","Save user"},
				{"generating", "Generating ..." },
				{"newTrade", "Add trade"},
				{"trades", "Trades"},
				{"personnel", "Personnel"},
				{"name", "Name"},
				{"title", "Title"},				
				{"centre", "Centre"},
				{"building", "Building"},
				{"shop", "Shop"},
				{"title", "Title"},
				{"cmp","Company"},
				{"pos","Position"},
				{"invalidEmail", "Invalid email address, try again"},
				{"invalidTel", "Invalid telephone number, type in 8 digits"},
				{"delMsg", "Trash message"},
				{"dbErr", "Something went wrong with the Database connection."},
				{"sysadmin", "System Administrator"},
				{"cntrmanager", "Centre Manager"},
				{"shopowner", "Establishment Owner"},
				{"customerservice", "Customer Service"},
				{"personnel", "Personnel"},
				{"invalidZip", "Invalid zipcode"},
				{"usertypeTry", "Trying to create usertype: "},
				{"whatUsertype", "What kind of user should be created?"},
				{"usertype", "User type"},
				{"invalidHrs","Invalid format for opening hours\nYou need two numbers in each of the 4 fields\nand you have to open before you close"},
				{"invalidInt", "Invalid, please input integer"},
				{"shce","Shopping Centre"},
				{"store","Store"},
				{"frnamelong","-First name is too long. Max 30 characters.\n"},
				{"lsnamelong","-Last name is too long. Max 30 characters.\n"},
				{"adrlong","-Address field is too long. Max 30 characters.\n"},
				{"zipfour","-Zipcode must be four digits long.\n"},
				{"zipnr","-Zip code can only be numbers, and four digits long.\n"},
				{"emailinv","-Email address is invalid.\n"},
				{"tlplong","-Telephone number must be eight digits long.\n"},
				{"tlpnr","-Telephone number must be all numbers, and eight digits long.\n"},
				{"salnr","-Salary must be all numbers.\n"},
				{"inputerr","There was an error in your input:\n"},
				{"centerLong", "-Center name is too long. Max 30 characters.\n"}
		};
	}
}
