package no.hist.aitel.team12.app;

import java.sql.Date;

public class Revenue {
	
	private Date month;
	
	private int turnover;

	public Revenue(Date month, int turnover) {
		this.month = month;
		this.turnover = turnover;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public int getTurnover() {
		return turnover;
	}

	public void setTurnover(int turnover) {
		this.turnover = turnover;
	}
}
