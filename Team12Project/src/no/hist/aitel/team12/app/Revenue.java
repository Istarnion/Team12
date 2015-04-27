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
 * Revenue.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.sql.Date;
import java.util.Calendar;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

/**
 * Class containing info on a a single registered revenue
 * 
 * @author Team12
 */
public class Revenue {
	
	private Date month;
	
	private long turnover;

	public Revenue(Date month, long turnover) {
		this.month = month;
		this.turnover = turnover;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public long getTurnover() {
		return turnover;
	}

	public void setTurnover(long turnover) {
		this.turnover = turnover;
	}
	
	/**
	 * Registers a turnover row in the db.
	 * 
	 * @param date
	 * @param turnover
	 * @param business_id
	 * @return	True if the upload was successful
	 */
	public static boolean registerTurnover(java.util.Date date, long turnover, int business_id) {
		Database db = DatabaseFactory.getDatabase();
		
		if(db == null) return false;
		
		Calendar c = Calendar.getInstance();   // this takes current date
		c.clear();
		c.setTime(date);
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    Date sqlDate = new Date(c.getTime().getTime());
	    
		return db.executePreparedStatement(
				"INSERT INTO revenue (business_id, month, turnover_month) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE turnover_month = ?",
				business_id, sqlDate,
				new Long(turnover),
				new Long(turnover));
	}
	
	@Override
	public String toString() {
		return "Revenue: "+turnover+"\t"+month;
	}
}
