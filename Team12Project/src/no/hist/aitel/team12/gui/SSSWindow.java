package no.hist.aitel.team12.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SSSWindow extends JFrame {

	private static final long serialVersionUID = -6075431975477953906L;

	private JTabbedPane tabbedPane;
	
	public SSSWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tabbedPane = new JTabbedPane();
		
		add(tabbedPane);
	}
	
	public void addTab(String tabName, JPanel tab) {
		tabbedPane.add(tabName, tab);
	}
	
	public void showWindow() {
		pack();
		setVisible(true);
	}
}
