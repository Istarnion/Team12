package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.util.Text;

public class NewUserCard extends UserCard {
	private static final long serialVersionUID = 4688863130267581267L;
	
	private JButton saveButton, cancelButton;
	private JPanel buttonPanel;
	
	public NewUserCard(InputField firstName, InputField lastName, InputField address, InputField zipcode, 
			InputField email, InputField telephone, InputField username, InputField company, InputField position, 
			InputField salary, PasswordInputField password, JLabel emptyLabel, JLabel logoLabel, JPanel westPanel, 
			JPanel eastPanel, JPanel eastBottom, BufferedImage logo) {
		
		super(firstName, lastName, address, zipcode, email, telephone, username, company, position, salary, password, emptyLabel, 
				logoLabel, westPanel, eastPanel, eastBottom, logo);
		
		saveButton = new JButton(Text.getString("save"));
		cancelButton = new JButton(Text.getString("cancel"));
		buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		super.add(buttonPanel, BorderLayout.SOUTH);
		
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
