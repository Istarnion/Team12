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
 * Trade.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.app;

import java.util.ArrayList;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.Text;


public class Trade {
	private int tradeId;
	private String tradeName;

	public Trade(int tradeId, String tradeName) {
		this.tradeName = tradeName;
		this.tradeId = tradeId;
	}

	@Override
	public String toString() {
		return Text.getString(tradeName);
	}

	public int getTradeId() {
		return tradeId;
	}

	public String getTradeName() {
		return tradeName;
	}

	public static Trade[] getSelectedTrades(int establishmentId) {
		Database db = DatabaseFactory.getDatabase();
		return db.getSelectedTrades(establishmentId);	
	}

	public static ArrayList<Trade> getAllTrades() {
		Database db = DatabaseFactory.getDatabase();
		return db.getAllTrades();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o instanceof Trade) {
			return this.tradeId == ((Trade)o).tradeId;
		}
		return false;
	}
}
