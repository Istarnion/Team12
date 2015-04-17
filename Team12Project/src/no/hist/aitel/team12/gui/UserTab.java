package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import no.hist.aitel.team12.database.*;
import no.hist.aitel.team12.util.Text;

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
	JLabel header;
	
	InputField firstName = new InputField(Text.getString("firstname" + ": "), 20);
	InputField lastName = new InputField(Text.getString("lastname"+ ": "), 20);
	InputField address = new InputField(Text.getString("adr"+ ": "), 20);
	InputField zipCode = new InputField(Text.getString("zip"+ ": "), 4);
	InputField electronicmail = new InputField(Text.getString("email"+ ": "), 30);
	InputField telephone = new InputField(Text.getString("tel"+ ": "), 12);
	InputField salary = new InputField(Text.getString("sal"+ ": "), 20);
	InputField username = new InputField(Text.getString("usr"+ ": "), 20);
	InputField password = new InputField(Text.getString("pwd"+ ": "), 20);

	private JPanel buttonPanel;

	private JTable resultTable;

	public UserTab() {
		this.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		//header = new JLabel(databaseConnection.getUserID());
		header.setText(Text.getString("usr"));
		topPanel.add(header);
		this.add(topPanel, BorderLayout.NORTH);
		

		JPanel mainPanel = new JPanel();
		  mainPanel.add(firstName);
		  mainPanel.add(lastName);
		  mainPanel.add(address);
		  mainPanel.add(zipCode);
		  mainPanel.add(electronicmail);
		  mainPanel.add(telephone);
		  mainPanel.add(salary);
		  mainPanel.add(username);
		  mainPanel.add(password);

		this.add(mainPanel, BorderLayout.CENTER);

		

		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BorderLayout());
		resultTable = new JTable();
		JScrollPane resultTablePane = new JScrollPane(resultTable);
		resultTablePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		userPanel.add(resultTablePane, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(newUser);		
		buttonPanel.add(editUser);
		ButtonListener buttonListener = new ButtonListener();
		editUser.addActionListener(buttonListener);
		newUser.addActionListener(buttonListener);
		userPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(userPanel, BorderLayout.WEST);
	}
	
	public void setUserTable(DatabaseConnection databaseConnection, int userID){
		
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
			
			@SuppressWarnings("unused")
			Database db;
			
			JButton button = new JButton();
			
			if (button == newUser) {
		/* ------------------------------Create new user------------------------------------ */
				/**
				 * 
				 * String firstName;
				 * String lastName;
				 * String address;
				 * int zipCode;
				 * String email;
				 * int telephone;
				 * int salary;
				 * String username;
				 * String password;
				*/
				
				DatabaseFactory.setup();
				db = DatabaseFactory.getDatabase();
				try {
					/*
					 Object newUser = db.createUser(
							firstName.getText(), 
							lastName.getText(), 
							address.getText(), 
							Integer.parseInt(zipCode.getText()), 
							electronicmail.getText(), 
							Integer.parseInt(telephone.getText()), 
							Integer.parseInt(salary.getText()), 
							username.getText(), 
							password.getText());
					 
					 User.setUser(newUser);
					 */
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
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