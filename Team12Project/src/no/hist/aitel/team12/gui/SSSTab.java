package no.hist.aitel.team12.gui;

import javax.swing.JPanel;

public abstract class SSSTab extends JPanel {

	private static final long serialVersionUID = 1819301448571520953L;

	protected boolean active = false;
	
	public abstract void refresh();
	
	void setActive(boolean active) {
		this.active = active;
	}
}
