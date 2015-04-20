package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.util.Text;

public class EditUserCard extends UserCard {
	private static final long serialVersionUID = -8466460979393419014L;
	


	private JButton saveButton, cancelButton;
	private JPanel buttonPanel;
	
	public EditUserCard(InputField firstName, InputField lastName, InputField address, InputField zipcode, 
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
	public boolean setEditable(){
		/* --------------- this method set the inputfields editable, in order to edit values ---------------- */
			return false;
	}

}
