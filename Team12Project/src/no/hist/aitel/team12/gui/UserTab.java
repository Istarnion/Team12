package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UserTab extends SSSTab {

	private static final long serialVersionUID = -445246322816259272L;
	
	private JButton newUser;
	
	private JButton editUser;
	
	private JList<?> centerList;

	private JPanel buttonPanel;

	private JPanel mainPanel;
	
	private JTable userTable;

	public UserTab() {
		this.setLayout(new BorderLayout());
		
		buttonPanel = new JPanel();
		
		userTable = new JTable();
		JScrollPane userTablePane = new JScrollPane(userTable);
		userTablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.add(userTablePane, BorderLayout.WEST);
		this.add(buttonPanel, BorderLayout.EAST);
		
		
	}
	
	public void showUserTab(){
		
		
		
		buttonPanel = new JPanel();
		
		buttonPanel.add(newUser);		
		buttonPanel.add(editUser);
		mainPanel.add(centerList);
		
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
