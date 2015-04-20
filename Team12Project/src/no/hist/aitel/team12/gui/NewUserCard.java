package no.hist.aitel.team12.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.hist.aitel.team12.util.Text;

public class NewUserCard extends JPanel {

	private static final long serialVersionUID = 4688863130267581267L;
	
	private InputField firstName, lastName, address, zipcode, email, telephone, salary, username;
	
	private JButton saveButton, cancelButton;
	
	private JPanel buttonPanel;
	
	public NewUserCard() {
		super.setLayout(new GridLayout(9, 1, 2, 2));
		
		firstName = new InputField(Text.getString("firstName"), 20);
		super.add(firstName);
		
		firstName = new InputField(Text.getString("firstName"), 20);
		super.add(firstName);
		
		firstName = new InputField(Text.getString("firstName"), 20);
		super.add(firstName);
		
		firstName = new InputField(Text.getString("firstName"), 20);
		super.add(firstName);
		
		firstName = new InputField(Text.getString("firstName"), 20);
		super.add(firstName);
		
		firstName = new InputField(Text.getString("firstName"), 20);
		super.add(firstName);
		
		firstName = new InputField(Text.getString("firstName"), 20);
		super.add(firstName);
		
		firstName = new InputField(Text.getString("firstName"), 20);
		super.add(firstName);
		
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
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
	}

}
