package no.hist.aitel.team12.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.util.Text;

public class CentreCard extends JPanel {

	private static final long serialVersionUID = -511628429934079700L;

	private JTextField businessName, email, telephone, openingHours, address, area;

	private JLabel businessNameLabel, emailLabel, telephoneLabel, openingHrsLabel, addressLabel, textDescrLabel, areaLabel;

	private JButton businessButton, emailButton, telephoneButton, openingHrsButton, addressButton, textDescrButton, areaButton;

	private JTextArea textDescription;

	private ButtonListener buttonListener;

	public CentreCard(int userID) {

		this.setLayout(new GridLayout(7,3));

		buttonListener 		= new ButtonListener();

		businessNameLabel	= new JLabel(Text.getString("businessName") + ": ");
		addressLabel		= new JLabel(Text.getString("adr") + ": ");
		emailLabel			= new JLabel(Text.getString("email") + ": ");
		telephoneLabel		= new JLabel(Text.getString("tel") + ": ");
		openingHrsLabel		= new JLabel(Text.getString("openingHrs") + ": ");
		textDescrLabel		= new JLabel(Text.getString("textDescription") + ": ");
		areaLabel			= new JLabel(Text.getString("area") + ": ");

		businessName		= new JTextField("");
		address				= new JTextField("");
		email				= new JTextField("");
		telephone			= new JTextField("");
		openingHours		= new JTextField("");
		area				= new JTextField("");
		textDescription		= new JTextArea("");
		textDescription.setPreferredSize(new Dimension(100,200));

		
		businessButton		= new JButton(Text.getString("edit"));
		addressButton		= new JButton(Text.getString("edit"));
		emailButton			= new JButton(Text.getString("edit"));
		telephoneButton		= new JButton(Text.getString("edit"));
		openingHrsButton	= new JButton(Text.getString("edit"));
		textDescrButton		= new JButton(Text.getString("edit"));
		areaButton			= new JButton(Text.getString("edit"));

		businessName.setEditable(false);
		address.setEditable(false);
		email.setEditable(false);
		telephone.setEditable(false);
		openingHours.setEditable(false);
		textDescription.setEditable(false);
		area.setEditable(false);

		businessName.setBorder(null);
		address.setBorder(null);
		email.setBorder(null);
		telephone.setBorder(null);
		openingHours.setBorder(null);
		textDescription.setBorder(null);
		area.setBorder(null);
		
		businessButton.addActionListener(buttonListener);
		addressButton.addActionListener(buttonListener);
		emailButton.addActionListener(buttonListener);
		telephoneButton.addActionListener(buttonListener);
		openingHrsButton.addActionListener(buttonListener);
		textDescrButton.addActionListener(buttonListener);
		areaButton.addActionListener(buttonListener);

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
		
		this.add(areaLabel);
		this.add(area);
		this.add(areaButton);

		this.add(textDescrLabel);
		this.add(textDescription);
		this.add(textDescrButton);


	}

	public void updateCard(ShoppingCentre centre) {
		businessName.setText(centre.getBusinessName());
		address.setText(centre.getAddress().getAdress());
		email.setText(centre.getEmail().getEmailAddress());
		telephone.setText(String.valueOf(centre.getTelephone()));
		openingHours.setText(String.valueOf(centre.getOpeningHours()));
		textDescription.setText(centre.getDescription());
		area.setText(String.valueOf(centre.getArea()));

		businessName.setEditable(false);
		address.setEditable(false);
		email.setEditable(false);
		telephone.setEditable(false);
		openingHours.setEditable(false);
		textDescription.setEditable(false);
		area.setEditable(false);

		businessButton.setText(Text.getString("edit"));
		addressButton.setText(Text.getString("edit"));
		emailButton.setText(Text.getString("edit"));
		telephoneButton.setText(Text.getString("edit"));
		openingHrsButton.setText(Text.getString("edit"));
		textDescrButton.setText(Text.getString("edit"));
		areaButton.setText(Text.getString("edit"));
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
			else if(pressedButton.equals(areaButton)) {
				if(areaButton.getText() == Text.getString("edit")) {
					area.setEditable(true);
					areaButton.setText(Text.getString("save"));				
				}
				else {
					area.setEditable(false);
					areaButton.setText(Text.getString("edit"));
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

