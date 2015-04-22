package no.hist.aitel.team12.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.Trade;
import no.hist.aitel.team12.util.Text;

public class EstablishmentCard extends JPanel {

	private static final long serialVersionUID = 9040445246335124938L;
	private JLabel openingLabel1, openingLabel2,openingLabel3,openingLabel4,openingLabel5,zipLabel, businessNameLabel, emailLabel, telephoneLabel, openingHrsLabel, addressLabel, textDescrLabel;
	private JTextField zip, businessName, email, telephone, address;
	private JButton tradesButtonRight, tradesButtonLeft, zipButton, businessButton, emailButton, telephoneButton, openingHrsButton, addressButton, textDescrButton, tradeButton;
	private JTextArea textDescription;
	private JPanel openingHours, tradesPanel, tradesMidPanel;
	private ButtonListener buttonListener;
	private JScrollPane textDescriptionScroll, tradesLeftScroll, tradesRightScroll;
	private Establishment establishment;
	private JList<Trade> tradesLeftList, tradesRightList;
	private ArrayList<Trade> allTrades;
	private JTextFieldLimit openingTextField1, openingTextField2,openingTextField3, openingTextField4;

	public EstablishmentCard(int userID) {

		this.setLayout(new GridBagLayout());
		allTrades = Trade.getAllTrades();
		buttonListener 		= new ButtonListener();

		businessNameLabel	= new JLabel(Text.getString("name") + ": ", SwingConstants.RIGHT);
		addressLabel		= new JLabel(Text.getString("adr") + ": ", SwingConstants.RIGHT);
		emailLabel			= new JLabel(Text.getString("email") + ": ", SwingConstants.RIGHT);
		telephoneLabel		= new JLabel(Text.getString("tel") + ": ", SwingConstants.RIGHT);
		openingHrsLabel		= new JLabel(Text.getString("openingHrs") + ": ", SwingConstants.RIGHT);
		textDescrLabel		= new JLabel(Text.getString("textDescription") + ": ", SwingConstants.RIGHT);
		zipLabel 			= new JLabel(Text.getString("zip") + ": ", SwingConstants.RIGHT);
		
		businessName		= new JTextField("");
		address				= new JTextField("");
		email				= new JTextField("");
		telephone			= new JTextField("");
		zip					= new JTextField("");
		textDescription		= new JTextArea("", 2,2);
		textDescription.setLineWrap(true);
		

		tradesPanel			= new JPanel(new GridBagLayout());
		tradesPanel.setPreferredSize(new Dimension(1,200));
		openingHours		= new JPanel(new FlowLayout(FlowLayout.LEFT));
		openingHours.setBorder(new LineBorder(Color.LIGHT_GRAY	));

		openingLabel1 = new JLabel("("); 
		openingTextField1 = new JTextFieldLimit(2);
		openingTextField1.setBorder(null);
		openingLabel2 = new JLabel("-");
		openingTextField2 = new JTextFieldLimit(2);
		openingTextField2.setBorder(null);
		openingLabel3 = new JLabel("(");
		openingTextField3 = new JTextFieldLimit(2); 
		openingTextField3.setBorder(null);
		openingLabel4 = new JLabel("-");
		openingTextField4 = new JTextFieldLimit(2);
		openingTextField4.setBorder(null);
		openingLabel5 = new JLabel("))");
		
		openingTextField1.setPreferredSize(new Dimension(15,14));
		openingTextField2.setPreferredSize(new Dimension(15,14));
		openingTextField3.setPreferredSize(new Dimension(15,14));
		openingTextField4.setPreferredSize(new Dimension(15,14));
		
		openingHours.add(openingLabel1);
		openingHours.add(openingTextField1);
		openingHours.add(openingLabel2);
		openingHours.add(openingTextField2);
		openingHours.add(openingLabel3);
		openingHours.add(openingTextField3);
		openingHours.add(openingLabel4);
		openingHours.add(openingTextField4);
		openingHours.add(openingLabel5);

		businessButton		= new JButton(Text.getString("edit"));
		addressButton		= new JButton(Text.getString("edit"));
		emailButton			= new JButton(Text.getString("edit"));
		telephoneButton		= new JButton(Text.getString("edit"));
		openingHrsButton	= new JButton(Text.getString("edit"));
		textDescrButton		= new JButton(Text.getString("edit"));
		zipButton			= new JButton(Text.getString("edit"));
		tradeButton			= new JButton(Text.getString("trades"));
		tradesButtonLeft	= new JButton("<--");
		tradesButtonRight	= new JButton("-->");
		
		businessButton.setPreferredSize(new Dimension(60, 23));		
		addressButton.setPreferredSize(new Dimension(60, 23));		
		emailButton.setPreferredSize(new Dimension(60, 23));			
		telephoneButton.setPreferredSize(new Dimension(60, 23));		
		openingHrsButton.setPreferredSize(new Dimension(60, 23));
		textDescrButton.setPreferredSize(new Dimension(60, 23));	
		zipButton.setPreferredSize(new Dimension(60, 23));
		tradesButtonLeft.setPreferredSize(new Dimension(60, 23));
		tradesButtonRight.setPreferredSize(new Dimension(60, 23));
		
		tradesLeftList			= new JList<Trade>();
		tradesRightList			= new JList<Trade>();
		tradesLeftScroll	 	= new JScrollPane(tradesLeftList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tradesRightScroll 		= new JScrollPane(tradesRightList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tradesLeftScroll.setPreferredSize(new Dimension(225, tradesLeftScroll.getPreferredSize().height));
		tradesRightScroll.setPreferredSize(new Dimension(225, tradesRightScroll.getPreferredSize().height));
		
		textDescriptionScroll	= new JScrollPane(textDescription, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textDescriptionScroll.setPreferredSize(new Dimension(0, 200));
			
		
		businessButton.addActionListener(buttonListener);
		addressButton.addActionListener(buttonListener);
		emailButton.addActionListener(buttonListener);
		telephoneButton.addActionListener(buttonListener);
		openingHrsButton.addActionListener(buttonListener);
		textDescrButton.addActionListener(buttonListener);
		tradeButton.addActionListener(buttonListener);
		zipButton.addActionListener(buttonListener);

		// Grid bag layout
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Setting some margins for a bit of space between each field/button/label
		constraints.insets.bottom = 5;
		constraints.insets.top = 5;

		// Business name grid bag setup
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

		// Address grid bag setup
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

		// zipcode grid bag setup
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(zipLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(zip, constraints);

		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(zipButton, constraints);

		// email grid bag setup
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(emailLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(email, constraints);

		constraints.gridx = 4;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(emailButton, constraints);

		// telephone grid bag setup
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(telephoneLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(telephone, constraints);

		constraints.gridx = 4;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(telephoneButton, constraints);

		// openinghours grid bag setup
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(openingHrsLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(openingHours, constraints);

		constraints.gridx = 4;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(openingHrsButton, constraints);

		// description grid bag setup
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		this.add(textDescrLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(textDescriptionScroll, constraints);

		constraints.gridx = 4;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		this.add(textDescrButton, constraints);
		
		// trades grid bag setup 
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.01;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(tradesPanel, constraints);
		
		// tradesPanel internal grid bag setup
		constraints = new GridBagConstraints();
		
		int margin = 10;
		Insets noInsets		= new Insets(0, 0, 0, 0);
		Insets leftInsets	= new Insets(0, 0, 0, margin);
		Insets rightInsets	= new Insets(0, margin, 0, 0);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.weightx = 0.20;
		constraints.weighty = 1;
		constraints.insets = leftInsets;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		constraints.fill = GridBagConstraints.VERTICAL;
		tradesPanel.add(tradesLeftScroll, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.20;
		constraints.weighty = 0.001;
		constraints.insets = noInsets;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.NONE;
		tradesPanel.add(tradesButtonLeft, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.20;
		constraints.weighty = 0.001;
		constraints.anchor = GridBagConstraints.NORTH;
		tradesPanel.add(tradesButtonRight, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.weightx = 0.20;
		constraints.weighty = 1;
		constraints.insets = rightInsets;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.VERTICAL;
		tradesPanel.add(tradesRightScroll, constraints);
	}

	public void updateCard(Establishment establishment) {
		this.establishment = establishment;

		DefaultListModel<Trade> selectedListModel = new DefaultListModel<Trade>();
		
		tradesLeftList.setModel(selectedListModel);
	
		DefaultListModel<Trade> availableListModel = new DefaultListModel<Trade>();
		boolean foundMatch = false;
		for(Trade aT : allTrades) {
			foundMatch = false;
			for(Trade sT : establishment.getSelectedTrades()) {
				
				if(sT.equals(aT)) {
					foundMatch = true;
					selectedListModel.addElement(sT);
					break;
				}
			}
			if(!foundMatch) {
				availableListModel.addElement(aT);
			}
			
		}
		tradesRightList.setModel(availableListModel);
		
		
		businessName.setText(establishment.getBusinessName());
		address.setText(establishment.getAddress().getAdress());
		email.setText(establishment.getEmail().getEmailAddress());
		telephone.setText(String.valueOf(establishment.getTelephone()));
		String openingHrs = establishment.getOpeningHours();
		openingTextField1.setText(openingHrs.substring(0, 2));
		openingTextField2.setText(openingHrs.substring(2, 4));
		openingTextField3.setText(openingHrs.substring(4, 6));
		openingTextField4.setText(openingHrs.substring(6, 8));
		textDescription.setText(establishment.getDescription());
		zip.setText(String.valueOf(establishment.getAddress().getZipcode()));

		businessName.setEditable(false);
		address.setEditable(false);
		email.setEditable(false);
		telephone.setEditable(false);
		openingTextField1.setEditable(false);
		openingTextField2.setEditable(false);
		openingTextField3.setEditable(false);
		openingTextField4.setEditable(false);
		textDescription.setEditable(false);
		zip.setEditable(false);

		businessButton.setText(Text.getString("edit"));
		addressButton.setText(Text.getString("edit"));
		emailButton.setText(Text.getString("edit"));
		telephoneButton.setText(Text.getString("edit"));
		openingHrsButton.setText(Text.getString("edit"));
		textDescrButton.setText(Text.getString("edit"));
		zipButton.setText(Text.getString("edit"));
	}

	private class ButtonListener implements ActionListener {

		public void newTrade() {
			JOptionPane.showMessageDialog(null, "button pressed");
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton pressedButton = (JButton) event.getSource();
			if(pressedButton.equals(businessButton)) {

				if(businessButton.getText().equals(Text.getString("edit"))) {
					businessName.setEditable(true);
					businessButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setBusinessName(businessName.getText())) {
						return;
					}
					businessName.setEditable(false);
					businessButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(addressButton)) {
				if(addressButton.getText().equals(Text.getString("edit"))) {
					address.setEditable(true);
					addressButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setAddress(address.getText())) {
						return;
					}
					address.setEditable(false);
					addressButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(emailButton)) {
				if(emailButton.getText().equals(Text.getString("edit"))) {
					email.setEditable(true);
					emailButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setEmail(email.getText())) {
						return;
					}
					email.setEditable(false);
					emailButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(telephoneButton)) {
				if(telephoneButton.getText().equals(Text.getString("edit"))) {
					telephone.setEditable(true);
					telephoneButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setTelephone(telephone.getText())) {
						JOptionPane.showMessageDialog(null, Text.getString("invalidTel"));
						return;
					}
					telephone.setEditable(false);
					telephoneButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(openingHrsButton)) {
				if(openingHrsButton.getText().equals(Text.getString("edit"))) {
					openingTextField1.setEditable(true);
					openingTextField2.setEditable(true);
					openingTextField3.setEditable(true);
					openingTextField4.setEditable(true);
					openingHrsButton.setText(Text.getString("save"));
				}
				else {
					String newValue = 
							openingTextField1.getText() + 
							openingTextField2.getText() + 
							openingTextField3.getText() + 
							openingTextField4.getText();
					if(newValue.length() != 8) {
						JOptionPane.showMessageDialog(null, Text.getString("invalidHrs"));
						return;
					}
					if(!establishment.setOpeningHours(newValue)) {
						return;
					}
					openingTextField1.setEditable(false);
					openingTextField2.setEditable(false);
					openingTextField3.setEditable(false);
					openingTextField4.setEditable(false);
					openingHrsButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(textDescrButton)) {
				if(textDescrButton.getText().equals(Text.getString("edit"))) {
					textDescription.setEditable(true);
					textDescrButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setDescription(textDescription.getText())) {
						return;
					}
					textDescription.setEditable(false);
					textDescrButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(zipButton)) {
				if(zipButton.getText().equals(Text.getString("edit"))) {
					zip.setEditable(true);
					zipButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setZipcode(zip.getText())) {
						return;
					}
					zip.setEditable(false);
					zipButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(tradeButton)) {
				newTrade();
			}
		}
	}
}

