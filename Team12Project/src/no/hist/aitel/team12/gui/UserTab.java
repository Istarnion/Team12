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

/**
 * 
 * @author Roger
 *
 */

public class UserTab extends SSSTab {

	private static final long serialVersionUID = -445246322816259272L;
	
	JButton newUser = new JButton(Text.getString("newuser"));	
	JButton editUser = new JButton(Text.getString("edituser"));
	//private JList<?> centerList;
	String headertext = "Username";
	JLabel header = new JLabel(headertext);
	
	InputField firstName = new InputField(Text.getString("firstname"), 20);
	InputField lastName = new InputField(Text.getString("lastname"), 20);
	InputField address = new InputField(Text.getString("adr"), 20);
	InputField zipCode = new InputField(Text.getString("zip"), 4);
	InputField electronicmail = new InputField(Text.getString("email"), 30);
	InputField telephone = new InputField(Text.getString("tel"), 12);
	InputField salary = new InputField(Text.getString("sal"), 20);
	InputField username = new InputField(Text.getString("usr"), 20);
	PasswordInputField password = new PasswordInputField(Text.getString("pwd"), 20);
	JButton createUser = new JButton(Text.getString("createuser"));
	
	JButton editFirstName = new JButton(Text.getString("edit"));
	JButton editLastName = new JButton(Text.getString("edit"));
	JButton editAddress = new JButton(Text.getString("edit"));
	JButton editZipCode = new JButton(Text.getString("edit"));
	JButton editEmail = new JButton(Text.getString("edit"));
	JButton editTelephone = new JButton(Text.getString("edit"));
	JButton editSalary = new JButton(Text.getString("edit"));
	JButton editUsername = new JButton(Text.getString("edit"));
	JButton editPassword = new JButton(Text.getString("edit"));
	
	private JPanel buttonPanel;
	private JPanel mainPanel;
	JPanel editPanel;
	JPanel bottomPanel;
	
	private JTable resultTable;

	public UserTab() {
		this.setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.add(header);
		mainPanel.add(topPanel, BorderLayout.NORTH);

		JPanel uPanel = new JPanel();
		uPanel.setLayout(new GridLayout(11,3));
		uPanel.add(firstName);
		uPanel.add(lastName);
		uPanel.add(address);
		uPanel.add(zipCode);
		uPanel.add(electronicmail);
		uPanel.add(telephone);
		uPanel.add(salary);
		uPanel.add(username);
		uPanel.add(password);
		mainPanel.add(uPanel, BorderLayout.CENTER);

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
		
		userPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(userPanel, BorderLayout.WEST);
		
		ButtonListener buttonListener = new ButtonListener();
		editUser.addActionListener(buttonListener);
		newUser.addActionListener(buttonListener);
		editFirstName.addActionListener(buttonListener);
		editLastName.addActionListener(buttonListener);
		editAddress.addActionListener(buttonListener);
		editZipCode.addActionListener(buttonListener);
		editEmail.addActionListener(buttonListener);
		editTelephone.addActionListener(buttonListener);
		editSalary.addActionListener(buttonListener);
		editUsername.addActionListener(buttonListener);
		editPassword.addActionListener(buttonListener);	
		
	}
	/*
	public void updateFields(){
		newUser.setText(Text.getString("newuser"));
		editUser.setText(Text.getString("edituser"));
		
		header.setText("test");
		
		firstName.setDefaultText(Text.getString("firstname"));
		lastName.setDefaultText(Text.getString("lastname"));
		address.setDefaultText(Text.getString("adr"));
		zipCode.setDefaultText(Text.getString("zip"));
		electronicmail.setDefaultText(Text.getString("email"));
		telephone.setDefaultText(Text.getString("tel"));
		salary.setDefaultText(Text.getString("sal"));
		username.setDefaultText(Text.getString("usr"));
		password.setDefaultText(Text.getString("pwd"));
		
		createUser.setText("createuser");
		
		editFirstName.setText(Text.getString("edit"));
		editLastName.setText(Text.getString("edit"));
		editAddress.setText(Text.getString("edit"));
		editZipCode.setText(Text.getString("edit"));
		editEmail.setText(Text.getString("edit"));
		editTelephone.setText(Text.getString("edit"));
		editSalary.setText(Text.getString("edit"));
		editUsername.setText(Text.getString("edit"));
		editPassword.setText(Text.getString("edit"));
		
		newUser.repaint();
		editUser.repaint();
		header.repaint();
		firstName.repaint();
		lastName.repaint();
		address.repaint();
		zipCode.repaint();
		electronicmail.repaint();
		telephone.repaint();
		salary.repaint();
		username.repaint();
		password.repaint();
		
		createUser.repaint();
		
		editFirstName.repaint();
		editLastName.repaint();
		editAddress.repaint();
		editZipCode.repaint();
		editEmail.repaint();
		editTelephone.repaint();
		editSalary.repaint();
		editUser.repaint();
		editPassword.repaint();
		
	}
	
	*/
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
				
		/* ----------------------- Show the save-button for saving a new user ----------*/
				JPanel bottomPanel = new JPanel();
				bottomPanel.add(createUser);
				mainPanel.add(bottomPanel, BorderLayout.SOUTH);
				
				try {
					
					 @SuppressWarnings({ "unused", "deprecation" })
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
					 
					 //User.setUser(newUser);
					 
					
					 
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}else if (button == editUser) {
		/* ------------------------------Edit existing user--------------------------------- */
				
				JPanel editPanel = new JPanel();
				editPanel.setLayout(new GridLayout(11,1));
				editPanel.add(editFirstName);
				editPanel.add(editLastName);
				editPanel.add(editAddress);
				editPanel.add(editZipCode);
				editPanel.add(editEmail);
				editPanel.add(editTelephone);
				editPanel.add(editSalary);
				editPanel.add(editUsername);
				editPanel.add(editPassword);
				mainPanel.add(editPanel, BorderLayout.EAST);
				
				JButton b = new JButton();
				
				if (b == editFirstName) {
					/*		change text "edit to "save"		*/
					/*		Save new first name				*/
					editFirstName = new JButton(Text.getString("save"));
					//firstName = new firstName();
					
				}else if (b == editLastName) {
					/*		change text "edit to "save"		*/
					/*		Save new last name				*/
					editLastName = new JButton(Text.getString("save"));
				}else if (b == editAddress) {
					/*		change text "edit to "save"		*/
					/*		Save new address				*/
					editAddress = new JButton(Text.getString("save"));
				}else if (b == editZipCode) {
					/*		change text "edit to "save"		*/
					/*		Save new zip code				*/
					editZipCode = new JButton(Text.getString("save"));
				}else if (b == editEmail) {
					/*		change text "edit to "save"		*/
					/*		Save new email					*/
					editEmail = new JButton(Text.getString("save"));
				}else if (b == editTelephone) {
					/*		change text "edit to "save"		*/
					/*		Save new telephone				*/
					editTelephone = new JButton(Text.getString("save"));
				}else if (b == editSalary) {
					/*		change text "edit to "save"		*/
					/*		Save new salary					*/
					editSalary = new JButton(Text.getString("save"));
				}else if (b == editUsername) {
					/*		change text "edit to "save"		*/
					/*		Save new username				*/
					editUsername = new JButton(Text.getString("save"));
				}else if (b == editPassword) {
					/*		change text "edit to "save"		*/
					/*		Save new password				*/
					editPassword = new JButton(Text.getString("save"));
				}
				
			}
		}
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}

