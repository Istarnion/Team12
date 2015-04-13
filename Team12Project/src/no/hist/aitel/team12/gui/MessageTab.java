package no.hist.aitel.team12.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextArea;



public class MessageTab extends SSSTab {
	
	private static final long serialVersionUID = 7021415739968091789L;

	
	private JTextArea inboxArea;
	
	private JTextArea viewMessageArea;
	
	private JTextArea sendMessageArea;
	
	private int rows, cols;
	
	public MessageTab() {
		rows = 20;
		cols = 50;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.BOTH;
		
		inboxArea = new JTextArea(rows, cols);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 10;
		this.add(inboxArea, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0.1;
		gbc.weightx = 0.9;
		viewMessageArea = new JTextArea(1, cols*2);
		viewMessageArea.setEditable(false);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		this.add(viewMessageArea, gbc);
		
		gbc.weightx = 0.1;
		gbc.fill = GridBagConstraints.NONE;
		sendMessageArea = new JTextArea();
		sendMessageArea.setEditable(true);
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(sendMessageArea, gbc);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}


}
		
