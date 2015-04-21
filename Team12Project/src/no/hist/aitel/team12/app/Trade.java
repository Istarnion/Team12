package no.hist.aitel.team12.app;

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
		return new Trade[] {
				new Trade(1, "trade1"),
				new Trade(2, "trade2"),
				new Trade(3, "trade3"),
				new Trade(4, "trade4")
		};
	}
	
	public static Trade[] getAvailableTrades(int establishmentId) {
		return new Trade[] {
				new Trade(5, "trade5"),
				new Trade(6, "trade6"),
				new Trade(7, "trade7"),
				new Trade(8, "trade8"),
				new Trade(9, "trade9"),
				new Trade(10, "trade10"),
				new Trade(11, "trade11"),
				new Trade(12, "trade12")
		};
	
	}
}
