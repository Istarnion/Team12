package no.hist.aitel.team12.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SqlTab extends JPanel {

	private static final long serialVersionUID = 6417537010099126929L;

	private SqlTextArea sqlArea;
	
	private JTextArea resultArea;
	
	private JTextArea outputArea;
	
	private JButton executeButton;
	
	private int rows, cols;
	
	private ListenerGroup listener;
	
	private final static String EXECUTE_CMD = "EXECUTE";
	
	public SqlTab() {
		rows = 20;
		cols = 50;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.9;
		sqlArea = new SqlTextArea(rows, cols);
		sqlArea.addKeyListener(listener);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		this.add(sqlArea.generateScrollPane(), gbc);
		
		resultArea = new JTextArea(rows, cols);
		resultArea.setEditable(false);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		this.add(resultArea, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0.1;
		gbc.weightx = 0.9;
		outputArea = new JTextArea(1, cols*2);
		outputArea.setEditable(false);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		this.add(outputArea, gbc);
		
		gbc.weightx = 0.1;
		gbc.fill = GridBagConstraints.NONE;
		executeButton = new JButton("Execute");
		executeButton.addActionListener(listener);
		executeButton.setActionCommand(EXECUTE_CMD);
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(executeButton, gbc);
	}

	private class ListenerGroup implements ActionListener, KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.isControlDown()) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					actionPerformed(new ActionEvent(executeButton, ActionEvent.ACTION_PERFORMED, EXECUTE_CMD));
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
		}
	}
}
