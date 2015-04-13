package no.hist.aitel.team12.app;

import java.sql.Date;

public class Revenue {
	
	private Date month;
	
	private int turnover;

	public Revenue(Date month, int turnover) {
		this.month = month;
		this.turnover = turnover;
	}
}
