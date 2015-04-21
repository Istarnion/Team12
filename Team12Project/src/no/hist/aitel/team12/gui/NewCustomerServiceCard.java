package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.User;
import no.hist.aitel.team12.util.Text;

public class NewCustomerServiceCard extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1259252191776134538L;
	private JButton saveButton, cancelButton;
	private JPanel buttonPanel, fieldPanel, labelPanel;
	
	private User user;
	
	private JTextField
		firstName, lastName, username, email, personalAddress, personalZip, telephone, salary;
	
	public NewCustomerServiceCard() {
		saveButton = new JButton(Text.getString("save"));
		cancelButton = new JButton(Text.getString("cancel"));
		buttonPanel = new JPanel(new GridLayout(1, 2, 25, 15));
		fieldPanel = new JPanel(new GridLayout(12, 1, 5, 15));
		labelPanel = new JPanel(new GridLayout(12, 1, 5, 15));
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		firstName		= new JTextField();
		lastName		= new JTextField();
		username		= new JTextField();
		email			= new JTextField();
		personalAddress	= new JTextField();
		personalZip		= new JTextField();
		telephone		= new JTextField();
		salary			= new JTextField();
		
		labelPanel.add(new JLabel(Text.getString("firstname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("lastname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("usr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("email")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("tel")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("sal")+": ", SwingConstants.RIGHT));
		
		fieldPanel.add(firstName);
		fieldPanel.add(lastName);
		fieldPanel.add(username);
		fieldPanel.add(email);
		fieldPanel.add(personalAddress);
		fieldPanel.add(personalZip);
		fieldPanel.add(telephone);
		fieldPanel.add(salary);
		
		
		super.setLayout(new BorderLayout());
		super.add(labelPanel, BorderLayout.WEST);
		super.add(fieldPanel, BorderLayout.CENTER);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				StringBuilder errMsg = new StringBuilder();
				int errCount = 0;
				
				/* CHECKING FIELDS */
				if(firstName.getText().length() > 30) {
					errCount++;
					errMsg.append("-First name is too long. Max thirty characters.\n");
				}
				
				if(lastName.getText().length() > 30) {
					errCount++;
					errMsg.append("-Last name is too long. Max thirty characters.\n");
				}
				
				if(personalAddress.getText().length() > 30) {
					errCount++;
					errMsg.append("-Address field is too long. Max 30 characters.\n");
				}
				
				try {
					Integer.parseInt(personalZip.getText());
					if(personalZip.getText().length() > 4) {
						errCount++;
						errMsg.append("-Zipcode must be four digits long.\n");
					}
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append("-Zip code can only be numbers, and four digits long.\n");
				}
				
				if(!EmailAddress.isValidEmailAddress(email.getText())) {
					errCount++;
					errMsg.append("-Email address is invalid.\n");
				}
				
				try {
					Integer.parseInt(telephone.getText());
					if(telephone.getText().length() > 8) {
						errCount++;
						errMsg.append("-Telephone number must be eight digits long.\n");
					}
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append("-Telephone number must be all numbers, and eight digits long.\n");
				}
				
				try {
					Integer.parseInt(salary.getText());
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append("-Salary must be all numbers.\n");
				}
				/* DONE CHECKING FIELDS */
				
				if(errCount > 0) {
					if(errCount == 1) {
						JOptionPane.showMessageDialog(
								null,
								"There was an error in your input:\n"+errMsg.toString(),
								Text.getString("err"),
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(
								null,
								"There was an error in your input:\n"+errMsg.toString(),
								Text.getString("err"),
								JOptionPane.ERROR_MESSAGE);
					}
					
					return;
				}
				/*
				if(user.updateData(
						firstName.getText(), lastName.getText(),
						personalAddress.getText(), Integer.parseInt(personalZip.getText()),
						new EmailAddress(email.getText()), Integer.parseInt(telephone.getText()),
						Integer.parseInt(salary.getText()))) {
					
					updateCard(user);
				}
				else {
					JOptionPane.showMessageDialog(null, Text.getString("dbErr"), Text.getString("err"), JOptionPane.ERROR_MESSAGE);
				}
				*/
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(user != null) updateCard(user);
			}
		});
	}
	
	public void prepareCard() {
		firstName.setText("");
		lastName.setText("");
		username.setText("");
		email.setText("");
		personalAddress.setText("");
		personalZip.setText("");
		telephone.setText("");
		salary.setText("");
	}
	
	public void updateCard(User u) {
		user = u;
		
		if(u == null) return;
		
		firstName.setText(u.getFirstName()!=null?u.getFirstName():"");
		lastName.setText(u.getLastName()!=null?u.getLastName():"");
		username.setText(u.getUsername()!=null?u.getUsername():"");
		email.setText(u.getEmail().getEmailAddress()!=null?u.getEmail().getEmailAddress():"");
		personalAddress.setText(u.getAddress().getAdress()!=null?u.getAddress().getAdress():"");
		personalZip.setText(u.getAddress().getZipcode()+""!=null?u.getAddress().getZipcode()+"":"");
		telephone.setText(u.getTelephone()+""!=null?u.getTelephone()+"":"");
		salary.setText(u.getSalary()+""!=null?u.getSalary()+"":"");
		
	}

	
	
}
