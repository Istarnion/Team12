package no.hist.aitel.team12.app;

import java.util.ArrayList;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;


public class Trade {
	private int tradeId;
	private String tradeName;

	public Trade(int tradeId, String tradeName) {
		this.tradeName = tradeName;
		this.tradeId = tradeId;
	}

	@Override
	public String toString() {
		return tradeName;
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
