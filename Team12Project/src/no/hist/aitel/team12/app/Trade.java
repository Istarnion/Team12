package no.hist.aitel.team12.app;

public class Trade {
	
	private String tradeName;
	public Trade(String tradeName) {
		this.tradeName = tradeName;
	}
	
	@Override
	public String toString() {
		return tradeName;
	}
}
