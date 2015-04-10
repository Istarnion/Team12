package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class OverviewTab extends JPanel {

	private static final long serialVersionUID = 7700803662193994900L;
	
	private JPanel leftPanel;
	
	private JPanel rightPanel;
	
	public OverviewTab() {
		
		this.setLayout(new BorderLayout());
		
		leftPanel = new JPanel(new BorderLayout());
		rightPanel = new JPanel(new BorderLayout());
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
	}
}
