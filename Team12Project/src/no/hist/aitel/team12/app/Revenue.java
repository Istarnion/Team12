package no.hist.aitel.team12.app;

import java.sql.Date;
import java.util.Calendar;

import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;

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

	public void setTurnover(int turnover) {
		this.turnover = turnover;
	}
	
	public static boolean registerTurnover(java.util.Date date, long turnover, int business_id) {
		Database db = DatabaseFactory.getDatabase();
		
		if(db == null) return false;
		
		Calendar c = Calendar.getInstance();   // this takes current date
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    Date sqlDate = new Date(c.getTime().getTime());
		
		return db.executePreparedStatement("INSERT INTO revenue (business_id, month, turnover_month) VALUES (?, ?, ?)", business_id, sqlDate, turnover);
	}
}
