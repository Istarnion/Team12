package no.hist.aitel.team12.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import no.hist.aitel.team12.util.Text;

public class BusinessCard extends JPanel {
	
	private static final long serialVersionUID = 6218656828930977854L;
	protected JTextField businessName, address, email, telephone, area, zip;
	protected JTextFieldLimit openingTextField1, openingTextField2, openingTextField3, openingTextField4;
	protected JTextArea textDescription;
	protected JLabel openingLabel1, openingLabel2, openingLabel3, openingLabel4, openingLabel5;
	protected JPanel openingHours;
	protected JScrollPane textDescriptionScroll;
	protected JLabel businessNameLabel, addressLabel, emailLabel, telephoneLabel, openingHrsLabel, textDescrLabel, zipLabel;
	protected JButton businessButton, addressButton, emailButton, telephoneButton, openingHrsButton, textDescrButton, zipButton;

	public BusinessCard() {
		this.setLayout(new GridBagLayout());
		
		businessName		= new JTextField("");
		address				= new JTextField("");
		email				= new JTextField("");
		telephone			= new JTextField("");
		area				= new JTextField("");
		zip					= new JTextField("");
		
		businessNameLabel	= new JLabel(Text.getString("name") + ": ", SwingConstants.RIGHT);
		addressLabel		= new JLabel(Text.getString("adr") + ": ", SwingConstants.RIGHT);
		emailLabel			= new JLabel(Text.getString("email") + ": ", SwingConstants.RIGHT);
		telephoneLabel		= new JLabel(Text.getString("tel") + ": ", SwingConstants.RIGHT);
		openingHrsLabel		= new JLabel(Text.getString("openingHrs") + ": ", SwingConstants.RIGHT);
		textDescrLabel		= new JLabel(Text.getString("textDescription") + ": ", SwingConstants.RIGHT);
		zipLabel			= new JLabel(Text.getString("zip") + ": ", SwingConstants.RIGHT);

		businessButton		= new JButton(Text.getString("edit"));
		addressButton		= new JButton(Text.getString("edit"));
		emailButton			= new JButton(Text.getString("edit"));
		telephoneButton		= new JButton(Text.getString("edit"));
		openingHrsButton	= new JButton(Text.getString("edit"));
		textDescrButton		= new JButton(Text.getString("edit"));
		zipButton			= new JButton(Text.getString("edit"));
		
		businessButton.setPreferredSize(new Dimension(72, 23));		
		addressButton.setPreferredSize(new Dimension(72, 23));		
		emailButton.setPreferredSize(new Dimension(72, 23));			
		telephoneButton.setPreferredSize(new Dimension(72, 23));		
		openingHrsButton.setPreferredSize(new Dimension(72, 23));
		textDescrButton.setPreferredSize(new Dimension(72, 23));	
		zipButton.setPreferredSize(new Dimension(72, 23));
		
		textDescription		= new JTextArea("", 2,2);
		textDescription.setLineWrap(true);
		
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

		openingHours		= new JPanel(new FlowLayout(FlowLayout.LEFT));
		openingHours.setBorder(zip.getBorder());
		openingHours.setPreferredSize(new Dimension(0,20));
		
		openingHours.add(openingLabel1);
		openingHours.add(openingTextField1);
		openingHours.add(openingLabel2);
		openingHours.add(openingTextField2);
		openingHours.add(openingLabel3);
		openingHours.add(openingTextField3);
		openingHours.add(openingLabel4);
		openingHours.add(openingTextField4);
		openingHours.add(openingLabel5);
		
		// Grid bag layout
		GridBagConstraints constraints = new GridBagConstraints();

		// Setting some margins for a bit of space between each field/button/label
		constraints.insets.bottom 	= 5;
		constraints.insets.top 		= 5;

		// Grid bag for business name
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

		// Grid bag for address
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

		// Grid bag for zip
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

		// Grid bag for email
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

		// Grid bag for telephone
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

		// Grid bag for opening hours
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
	}
}
