package no.hist.aitel.team12.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.util.Text;

public class EstablishmentCard extends JPanel {

	private static final long serialVersionUID = 9040445246335124938L;
	
	private JTextField businessName, email, telephone, openingHours, address;
	
	private JLabel businessNameLabel, emailLabel, telephoneLabel, openingHrsLabel, addressLabel, textDescrLabel;
	
	private JButton businessButton, emailButton, telephoneButton, openingHrsButton, addressButton, textDescrButton;
	
	private JTextArea textDescription;
	
	private ButtonListener buttonListener;
	
	public EstablishmentCard(int userID) {
		
		this.setLayout(new GridLayout(6,3));
		
		buttonListener 		= new ButtonListener();
		
		businessNameLabel	= new JLabel(Text.getString("businessName") + ": ");
		addressLabel		= new JLabel(Text.getString("address") + ": ");
		emailLabel			= new JLabel(Text.getString("email") + ": ");
		telephoneLabel		= new JLabel(Text.getString("telephone") + ": ");
		openingHrsLabel		= new JLabel(Text.getString("openingHrs") + ": ");
		textDescrLabel		= new JLabel(Text.getString("textDescription") + ": ");
		
		businessName		= new JTextField("");
		address				= new JTextField("");
		email				= new JTextField("");
		telephone			= new JTextField("");
		openingHours		= new JTextField("");
		textDescription		= new JTextArea("");
		
		businessButton		= new JButton(Text.getString("edit"));
		addressButton		= new JButton(Text.getString("edit"));
		emailButton			= new JButton(Text.getString("edit"));
		telephoneButton		= new JButton(Text.getString("edit"));
		openingHrsButton	= new JButton(Text.getString("edit"));
		textDescrButton		= new JButton(Text.getString("edit"));
		
		businessName.setEditable(false);
		address.setEditable(false);
		email.setEditable(false);
		telephone.setEditable(false);
		openingHours.setEditable(false);
		textDescription.setEditable(false);
		
		businessName.setBorder(null);
		address.setBorder(null);
		email.setBorder(null);
		telephone.setBorder(null);
		openingHours.setBorder(null);
		textDescription.setBorder(null);
		
		businessButton.addActionListener(buttonListener);
		addressButton.addActionListener(buttonListener);
		emailButton.addActionListener(buttonListener);
		telephoneButton.addActionListener(buttonListener);
		openingHrsButton.addActionListener(buttonListener);
		textDescrButton.addActionListener(buttonListener);
		
		this.add(businessNameLabel);
		this.add(businessName);
		this.add(businessButton);
		
		this.add(addressLabel);
		this.add(address);		
		this.add(addressButton);
		
		this.add(emailLabel);
		this.add(email);
		this.add(emailButton);
		
		this.add(telephoneLabel);
		this.add(telephone);
		this.add(telephoneButton);
		
		this.add(openingHrsLabel);
		this.add(openingHours);
		this.add(openingHrsButton);
		
		this.add(textDescrLabel);
		this.add(textDescription);
		this.add(textDescrButton);
		
		
	}
	
	public void updateCard(Establishment establishment) {
		businessName.setText(establishment.getBusinessName());
		address.setText("Adress needs to be moved to business class");
		email.setText(establishment.getEmail().getEmailAddress());
		telephone.setText(String.valueOf(establishment.getTelephone()));
		openingHours.setText(String.valueOf(establishment.getOpeningHours()));
		textDescription.setText("description needs to be moved to business class");
		
		businessName.setEditable(false);
		address.setEditable(false);
		email.setEditable(false);
		telephone.setEditable(false);
		openingHours.setEditable(false);
		textDescription.setEditable(false);
		
		businessButton.setText(Text.getString("edit"));
		addressButton.setText(Text.getString("edit"));
		emailButton.setText(Text.getString("edit"));
		telephoneButton.setText(Text.getString("edit"));
		openingHrsButton.setText(Text.getString("edit"));
		textDescrButton.setText(Text.getString("edit"));
		}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton pressedButton = (JButton) event.getSource();
			
			if(pressedButton.equals(businessButton)) {
				
				if(businessButton.getText() == Text.getString("edit")) {
					businessName.setEditable(true);
					businessButton.setText(Text.getString("save"));
				}
				else {
					businessName.setEditable(false);
					businessButton.setText(Text.getString("edit"));
				}
				
			}
			else if(pressedButton.equals(addressButton)) {
				if(addressButton.getText() == Text.getString("edit")) {
					address.setEditable(true);
					addressButton.setText(Text.getString("save"));
				}
				else {
					address.setEditable(false);
					addressButton.setText(Text.getString("edit"));
				}
			}
			
			else if(pressedButton.equals(emailButton)) {
				if(emailButton.getText() == Text.getString("edit")) {
					email.setEditable(true);
					emailButton.setText(Text.getString("save"));
				}
				else {
					email.setEditable(false);
					emailButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(telephoneButton)) {
				if(telephoneButton.getText() == Text.getString("edit")) {
					telephone.setEditable(true);
					telephoneButton.setText(Text.getString("save"));
				}
				else {
					telephone.setEditable(false);
					telephoneButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(openingHrsButton)) {
				if(openingHrsButton.getText() == Text.getString("edit")) {
					openingHours.setEditable(true);
					openingHrsButton.setText(Text.getString("save"));
				}
				else {
					openingHours.setEditable(false);
					openingHrsButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(textDescrButton)) {
				if(textDescrButton.getText() == Text.getString("edit")) {
					textDescription.setEditable(true);
					textDescrButton.setText(Text.getString("save"));
				}
				else {
					textDescription.setEditable(false);
					textDescrButton.setText(Text.getString("edit"));
				}
			}
		}
	
	}
}

