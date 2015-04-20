package no.hist.aitel.team12.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.util.Text;

public class EstablishmentCard extends JPanel {

	private static final long serialVersionUID = 9040445246335124938L;
	
	private JLabel businessNameLabel, emailLabel, telephoneLabel, openingHrsLabel, addressLabel, textDescrLabel;

	private JTextField businessName, email, telephone, openingHours, address;
	
	private JButton businessButton, emailButton, telephoneButton, openingHrsButton, addressButton, textDescrButton, tradeButton;
	
	private JTextArea textDescription;
	
	private ButtonListener buttonListener;
	
	private JScrollPane textDescriptionScroll;
	
	public EstablishmentCard(int userID) {
		
		this.setLayout(new GridBagLayout());
		
		buttonListener 		= new ButtonListener();
		
		businessNameLabel	= new JLabel(Text.getString("name") + ": ", SwingConstants.RIGHT);
		addressLabel		= new JLabel(Text.getString("adr") + ": ", SwingConstants.RIGHT);
		emailLabel			= new JLabel(Text.getString("email") + ": ", SwingConstants.RIGHT);
		telephoneLabel		= new JLabel(Text.getString("tel") + ": ", SwingConstants.RIGHT);
		openingHrsLabel		= new JLabel(Text.getString("openingHrs") + ": ", SwingConstants.RIGHT);
		textDescrLabel		= new JLabel(Text.getString("textDescription") + ": ", SwingConstants.RIGHT);
		
		
		businessName		= new JTextField("");
		address				= new JTextField("");
		email				= new JTextField("");
		telephone			= new JTextField("");
		openingHours		= new JTextField("");
		textDescription		= new JTextArea("", 2,2);
		textDescription.setLineWrap(true);
		
		
		businessButton		= new JButton(Text.getString("edit"));
		addressButton		= new JButton(Text.getString("edit"));
		emailButton			= new JButton(Text.getString("edit"));
		telephoneButton		= new JButton(Text.getString("edit"));
		openingHrsButton	= new JButton(Text.getString("edit"));
		textDescrButton		= new JButton(Text.getString("edit"));
		tradeButton			= new JButton(Text.getString("trades"));
		
		textDescriptionScroll = new JScrollPane(textDescription, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textDescriptionScroll.setPreferredSize(new Dimension(0, 200));
		
		businessButton.addActionListener(buttonListener);
		addressButton.addActionListener(buttonListener);
		emailButton.addActionListener(buttonListener);
		telephoneButton.addActionListener(buttonListener);
		openingHrsButton.addActionListener(buttonListener);
		textDescrButton.addActionListener(buttonListener);
		tradeButton.addActionListener(buttonListener);

		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets.bottom = 5;
		constraints.insets.top = 5;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(businessNameLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(businessName, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(businessButton, constraints);
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(addressLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(address, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(addressButton, constraints);
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(emailLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(email, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(emailButton, constraints);
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(telephoneLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(telephone, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(telephoneButton, constraints);
		
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(openingHrsLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(openingHours, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(openingHrsButton, constraints);
		
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		this.add(textDescrLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(textDescriptionScroll, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		this.add(textDescrButton, constraints);
		
		
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.01;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		this.add(tradeButton, constraints);
	}
	
	public void updateCard(Establishment establishment) {
		businessName.setText(establishment.getBusinessName());
		address.setText(establishment.getAddress().getAdress());
		email.setText(establishment.getEmail().getEmailAddress());
		telephone.setText(String.valueOf(establishment.getTelephone()));
		openingHours.setText(String.valueOf(establishment.getOpeningHours()));
		textDescription.setText(establishment.getDescription());

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

		public void newTrade() {
			JOptionPane.showMessageDialog(null, "button pressed");
		}
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
			else if(pressedButton.equals(tradeButton)) {
				newTrade();
			}
			
		}
	
	}
}

