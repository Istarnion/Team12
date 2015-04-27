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
 * UserType.java Team 12, 9 Apr 2015
 *******************************************************************************/

package no.hist.aitel.team12.app;

import no.hist.aitel.team12.util.Text;

/**
 * And enum type for handling the different usertypes
 * 
 * @author Team12
 *
 */
public enum UserType {
	SYS_ADMIN, 
	CENTRE_MANAGER, 
	SHOP_OWNER, 
	CUSTOMER_SERVICE,
	PERSONNEL;
	
	@Override
	public String toString() {
		switch(this) {
		case SYS_ADMIN:
			return Text.getString("sysadmin");
		case CENTRE_MANAGER:
			return Text.getString("cntrmanager");
		case SHOP_OWNER:
			return Text.getString("shopowner");
		case CUSTOMER_SERVICE:
			return Text.getString("customerservice");
		case PERSONNEL:
			return Text.getString("personnel");
		default:
			return "Invalid UserType";
		}
	}
}
