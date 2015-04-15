package no.hist.aitel.team12.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
//import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import no.hist.aitel.team12.database.*;

public class UserTab extends SSSTab {

	private static final long serialVersionUID = -445246322816259272L;
	
	private JButton newUser = new JButton("New User");	
	private JButton editUser = new JButton("Edit User");
	//private JList<?> centerList;

	private JPanel buttonPanel;
	
	private JTextArea userTable;

	public UserTab() {
		this.setLayout(new GridLayout(1,2));
		buttonPanel = new JPanel();
		
		userTable = new JTextArea(1,100);
		userTable.setEditable(false);
		
		buttonPanel.setLayout(new GridLayout(3,1));
		buttonPanel.add(newUser);		
		buttonPanel.add(editUser);
		
		this.add(userTable);
		this.add(buttonPanel);
		
	}
	
	public void setUserTable(DatabaseConnection databaseConnection, int userID){
		userTable.setText(databaseConnection.getUserTable(userID)[0][0]);
	}
	
	
	public void showUserTab(){
		
		ButtonListener buttonListener = new ButtonListener();
		editUser.addActionListener(buttonListener);
		newUser.addActionListener(buttonListener);
		
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
