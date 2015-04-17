package no.hist.aitel.team12.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import no.hist.aitel.team12.database.*;

//import javax.swing.JList;
/**
 * 
 * @author Roger
 *
 */

public class UserTab extends SSSTab {

	private static final long serialVersionUID = -445246322816259272L;
	
	private JButton newUser = new JButton("New User");	
	private JButton editUser = new JButton("Edit User");
	//private JList<?> centerList;

	private JPanel buttonPanel;

	private JTable resultTable;

	public UserTab() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		buttonPanel = new JPanel();
		
		resultTable = new JTable();
		JScrollPane resultTablePane = new JScrollPane(resultTable);
		resultTablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.9;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(resultTablePane, gbc);
		
		buttonPanel.setLayout(new GridLayout(3,3));
		buttonPanel.add(newUser);		
		buttonPanel.add(editUser);
		gbc.weightx = 0.4;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(buttonPanel, gbc);
		
		ButtonListener buttonListener = new ButtonListener();
		editUser.addActionListener(buttonListener);
		newUser.addActionListener(buttonListener);
		
	}
	
	public void setUserTable(DatabaseConnection databaseConnection, int userID){
		
		//int userID = 
		
		String statement = databaseConnection.getUserStatement(userID);
		String[][] output = DatabaseFactory.getDatabase().executeQuery(statement);
		String[][] content = new String[output.length-1][output[0].length];		
		for(int row=0; row<content.length; row++) {			
			for(int col=0; col<content[0].length; col++) {
				content[row][col] = output[row+1][col];
			}
	
		
		DefaultTableModel tableModel = new DefaultTableModel(content, output[0]);
		resultTable.setModel(tableModel);
		}

		
		
		
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent event) {
			
			JButton button = new JButton();
			
			if (button == newUser) {
		/* ------------------------------Create new user------------------------------------ */ 
				
				
				
			}else if (button == editUser) {
		/* ------------------------------Edit existing user--------------------------------- */ 
				
			}
			
		}
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}