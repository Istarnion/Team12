package no.hist.aitel.team12.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.hist.aitel.team12.util.Text;

public class EditUserCard extends JPanel {

	private static final long serialVersionUID = -8466460979393419014L;
	
	private InputField firstName, lastName, address, zipcode, email, telephone, username, company, position, salary, password;
	
	private JButton saveButton, cancelButton;
	
	private JPanel buttonPanel;

	public EditUserCard() {
		
		super.setLayout(new GridLayout(11,1));
		
		
		firstName = new InputField(Text.getString("firstname"), 20);
		super.add(firstName);
		
		lastName = new InputField(Text.getString("lastname"), 20);
		super.add(lastName);
		
		address = new InputField(Text.getString("adr"), 20);
		super.add(address);
		
		zipcode = new InputField(Text.getString("zip"), 20);
		super.add(zipcode);
		
		email = new InputField(Text.getString("email"), 20);
		super.add(email);
		
		telephone = new InputField(Text.getString("tel"), 20);
		super.add(telephone);
		
		company = new InputField(Text.getString("cmp"), 20);
		super.add(company);
		
		position = new InputField(Text.getString("pos"), 20);
		super.add(position);
		
		salary = new InputField(Text.getString("sal"), 20);
		super.add(salary);
		
		username = new InputField(Text.getString("usr"), 20);
		super.add(username);
		
		password = new InputField(Text.getString("pwd"), 20);
		super.add(password);
		
		
		saveButton = new JButton(Text.getString("save"));
		cancelButton = new JButton(Text.getString("cancel"));
		buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		super.add(buttonPanel);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
	}

}
