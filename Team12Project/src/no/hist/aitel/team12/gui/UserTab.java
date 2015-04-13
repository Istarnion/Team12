package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserTab extends SSSTab {

	private static final long serialVersionUID = -445246322816259272L;
	
	private JButton newUser;
	
	private JButton editUser;
	
	private JList<?> centerList;

	private JPanel buttonPanel;

	private JPanel mainPanel;
	
	private JTextArea outputArea = new JTextArea(20, 50);

	public UserTab() {
		this.setLayout(new BorderLayout());
		
		this.add(mainPanel, BorderLayout.WEST);
		
		this.add(buttonPanel, BorderLayout.EAST);
		
		
		
	}
	
	public void showUserTab(){
	
		mainPanel = new JPanel();
		mainPanel.add(centerList);
		mainPanel.add(outputArea);
		
		
		
		buttonPanel = new JPanel();
		
		buttonPanel.add(newUser);
		@SuppressWarnings("unused")
		NewUserListener newUserListener = new NewUserListener();
		
		buttonPanel.add(editUser);
		@SuppressWarnings("unused")
		EditUserListener editUserListener = new EditUserListener();
		
	}
	
	private class NewUserListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class EditUserListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
