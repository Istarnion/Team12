package no.hist.aitel.team12.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import no.hist.aitel.team12.database.DatabaseFactory;

public class SqlTab extends JPanel {

	private static final long serialVersionUID = 6417537010099126929L;

	private SqlTextArea sqlArea;
	
	private JTable resultTable;
	
	private JTextArea outputArea;
	
	private JButton executeButton;
	
	private int rows, cols;
	
	private ListenerGroup listener;
	
	private final static String
		EXECUTE_CMD = "EXECUTE",
		EXECUTE_ALL = "EXECUTEALL";
	
	public SqlTab() {
		rows = 20;
		cols = 50;
		
		listener = new ListenerGroup();
		
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
		
		resultTable = new JTable();
		JScrollPane resultTablePane = new JScrollPane(resultTable);
		resultTablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		this.add(resultTablePane, gbc);
		
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

	void executeBulk() {
		String[] statements = sqlArea.getText().split(";");
		
		for(String statement : statements) {
			executeStatement(statement.trim());
		}
	}
	
	void executeSingleStatement() {
		System.out.println("Not yet implemented");
	}
	
	private void executeStatement(String statement) {
		if (statement == null) return;
		
		if(statement.toLowerCase().startsWith("select")) {
			String[][] result = DatabaseFactory.getDatabase().executeQuery(statement);
			if(result.length == 1) {
				outputArea.setText(result[0][0]);
			}
			else {
				outputArea.setText("");
		
				String[][] content = new String[result.length-1][result[0].length];
				for(int row=0; row<content.length; row++) {
					for(int col=0; col<content[0].length; col++) {
						content[row][col] = result[row+1][col];
					}
				}
				
				DefaultTableModel tableModel = new DefaultTableModel(content, result[0]);
				resultTable.setModel(tableModel);
			}
		}
		else {
			String result = DatabaseFactory.getDatabase().executeUpdate(statement);
			
			outputArea.setText(result);
		}
	}
	
	private class ListenerGroup implements ActionListener, KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.isControlDown()) {
				if(e.isShiftDown()) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						actionPerformed(new ActionEvent(executeButton, ActionEvent.ACTION_PERFORMED, EXECUTE_ALL));
					}
				}
				else {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						actionPerformed(new ActionEvent(executeButton, ActionEvent.ACTION_PERFORMED, EXECUTE_CMD));
					}
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
		public void actionPerformed(ActionEvent ae) {
			switch(ae.getActionCommand()) {
				case EXECUTE_ALL:
				{
					executeBulk();
				} break;
				
				case EXECUTE_CMD:
				{
					executeSingleStatement();
				} break;
				
				default:
					System.out.println("Invalid action command given in the sql tab.");
					break;
			}
		}
	}
}
