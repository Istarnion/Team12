package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
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
				// Do checking here
				
				if(person.updateData(
						firstName.getText(), lastName.getText(),
						address.getText(), Integer.parseInt(zipcode.getText()),
						new EmailAddress(email.getText()), Integer.parseInt(telephone.getText()),
						Integer.parseInt(salary.getText()))) {
					
					updateCard(person);
				}
				else {
					// Report error
					System.out.println("You did a mistake.");
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				updateCard(person);
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
