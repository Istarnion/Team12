package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import no.hist.aitel.team12.util.SqlTextArea;

public class SqlTab extends JPanel {

	private static final long serialVersionUID = 6417537010099126929L;

	private SqlTextArea sqlArea;
	
	private JTextArea resultArea;
	
	private JTextArea outputArea;
	
	private JButton executeButton;
	
	private JPanel mainPanel;
	private JPanel bottomPanel;
	
	private int rows, cols;
	
	public SqlTab() {
		rows = 20;
		cols = 50;
		
		this.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		bottomPanel = new JPanel();
		
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		sqlArea = new SqlTextArea(rows, cols);
		mainPanel.add(sqlArea.generateScrollPane());
		
		resultArea = new JTextArea(rows, cols);
		mainPanel.add(resultArea);
	}

}
