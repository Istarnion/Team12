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
import no.hist.aitel.team12.app.Person;
import no.hist.aitel.team12.util.Text;

public class EditUserCard extends JPanel {
	private static final long serialVersionUID = -8466460979393419014L;
	
	private JButton saveButton, cancelButton;
	
	private JTextField firstName, lastName, address, zipcode, email, telephone, salary;
	
	private Person person;
	
	private JPanel labelPanel, fieldPanel, buttonPanel;
	
	public EditUserCard() {
		super.setLayout(new BorderLayout());
		
		firstName = new JTextField();
		lastName = new JTextField();
		address = new JTextField();
		zipcode = new JTextField();
		email = new JTextField();
		telephone = new JTextField();
		salary = new JTextField();
		
		saveButton = new JButton(Text.getString("save"));
		cancelButton = new JButton(Text.getString("cancel"));
		
		setEditable(false);
		
		labelPanel = new JPanel(new GridLayout(8, 1, 5, 25));
		fieldPanel = new JPanel(new GridLayout(8, 1, 5, 25));
		buttonPanel = new JPanel(new GridLayout(1, 2, 25, 25));
		
		labelPanel.add(new JLabel(Text.getString("firstname")+":", SwingConstants.RIGHT));
		fieldPanel.add(firstName);
		labelPanel.add(new JLabel(Text.getString("lastname")+":", SwingConstants.RIGHT));
		fieldPanel.add(lastName);
		labelPanel.add(new JLabel(Text.getString("adr")+":", SwingConstants.RIGHT));
		fieldPanel.add(address);
		labelPanel.add(new JLabel(Text.getString("zip")+":", SwingConstants.RIGHT));
		fieldPanel.add(zipcode);
		labelPanel.add(new JLabel(Text.getString("email")+":", SwingConstants.RIGHT));
		fieldPanel.add(email);
		labelPanel.add(new JLabel(Text.getString("tel")+":", SwingConstants.RIGHT));
		fieldPanel.add(telephone);
		labelPanel.add(new JLabel(Text.getString("sal")+":", SwingConstants.RIGHT));
		fieldPanel.add(salary);
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		fieldPanel.add(buttonPanel);
		
		add(labelPanel, BorderLayout.WEST);
		add(fieldPanel, BorderLayout.CENTER);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				StringBuilder errMsg = new StringBuilder();
				int errCount = 0;
				
				/* CHECKING FIELDS */
				if(firstName.getText().length() > 10) {
					errCount++;
					errMsg.append("-First name is too long. Max ten characters.\n");
				}
				
				if(lastName.getText().length() > 10) {
					errCount++;
					errMsg.append("-Last name is too long. Max ten characters.\n");
				}
				
				if(address.getText().length() > 30) {
					errCount++;
					errMsg.append("-Address field is too long. Max 30 characters.\n");
				}
				
				try {
					Integer.parseInt(zipcode.getText());
					if(zipcode.getText().length() > 4) {
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
				
				if(person.updateData(
						firstName.getText(), lastName.getText(),
						address.getText(), Integer.parseInt(zipcode.getText()),
						new EmailAddress(email.getText()), Integer.parseInt(telephone.getText()),
						Integer.parseInt(salary.getText()))) {
					
					updateCard(person);
				}
				else {
					JOptionPane.showMessageDialog(null, Text.getString("dbErr"), Text.getString("err"), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(person != null) updateCard(person);
			}
		});
	}
	
	public void setEditable(boolean editable){
		firstName.setEditable(editable);
		lastName.setEditable(editable);
		address.setEditable(editable);
		zipcode.setEditable(editable);
		email.setEditable(editable);
		telephone.setEditable(editable);
		salary.setEditable(editable);
		saveButton.setEnabled(editable);
		cancelButton.setEnabled(editable);
	}
	
	public void updateCard(Person p) {
		person = p;
		
		firstName.setText(p.getFirstName());
		lastName.setText(p.getLastName());
		address.setText(p.getAddress().getAdress());
		zipcode.setText(p.getAddress().getZipcode()+"");
		email.setText(p.getEmail().getEmailAddress());
		telephone.setText(p.getTelephone()+"");
		salary.setText(p.getSalary()+"");
		
		setEditable(false);
	}

}
