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
 * DatabaseFactory.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.database;

public class DatabaseFactory {
	
	private static DatabaseConnection db;
	
	private static boolean ready = false;
	
	/**
	 * @return A handle to the database
	 */
	public static Database getDatabase() {
		if(ready) {
			return db;
		}
		return null;
	}
	
	/**
	 * This method sets up the connection to the database
	 * 
	 * @return True if we successfully connected to the database
	 */
	public static boolean setup() {
		db = new DatabaseConnection();
		ready = db.connect();
		return ready;
	}
}
