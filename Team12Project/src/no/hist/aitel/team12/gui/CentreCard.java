package no.hist.aitel.team12.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.hist.aitel.team12.app.Personnel;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.util.Text;

public class CentreCard extends JPanel {

	private static final long serialVersionUID = -511628429934079700L;

	private JTextField businessName, email, telephone, openingHours, address, area;

	private JLabel businessNameLabel, emailLabel, telephoneLabel, openingHrsLabel, addressLabel, textDescrLabel, areaLabel, personnelLabel;
	
	private JLabel persNameLabel, persTelephoneLabel, persTitleLabel, persEmailLabel;
	
	private JPanel personnelPanel;
	
	private JButton businessButton, emailButton, telephoneButton, openingHrsButton, addressButton, textDescrButton, areaButton;

	private JTextArea textDescription;

	private JList<Personnel> personnelList;

	private ButtonListener buttonListener;

	private PersonnelListener personnelListener;

	private JScrollPane personnelScrollPane;

	private JScrollPane textDescriptionScroll;
	
	private ShoppingCentre centre;

	public CentreCard(int userID) {

		this.setLayout(new GridBagLayout());

		buttonListener 		= new ButtonListener();
		personnelListener	= new PersonnelListener();
		
		businessNameLabel	= new JLabel(Text.getString("name") + ": ", SwingConstants.RIGHT);
		addressLabel		= new JLabel(Text.getString("adr") + ": ", SwingConstants.RIGHT);
		emailLabel			= new JLabel(Text.getString("email") + ": ", SwingConstants.RIGHT);
		telephoneLabel		= new JLabel(Text.getString("tel") + ": ", SwingConstants.RIGHT);
		openingHrsLabel		= new JLabel(Text.getString("openingHrs") + ": ", SwingConstants.RIGHT);
		textDescrLabel		= new JLabel(Text.getString("textDescription") + ": ", SwingConstants.RIGHT);
		areaLabel			= new JLabel(Text.getString("area") + ": ", SwingConstants.RIGHT);
		personnelLabel		= new JLabel(Text.getString("personnel") + ": ", SwingConstants.RIGHT);
		persNameLabel		= new JLabel("");
		persTelephoneLabel	= new JLabel("");
		persTitleLabel 		= new JLabel("");
		persEmailLabel		= new JLabel("");
		personnelPanel		= new JPanel(new FlowLayout());
		
		businessName		= new JTextField("");
		address				= new JTextField("");
		email				= new JTextField("");
		telephone			= new JTextField("");
		openingHours		= new JTextField("");
		area				= new JTextField("");
		textDescription		= new JTextArea("", 2,2);
		textDescription.setLineWrap(true);
		personnelList		= new JList<Personnel>();

		personnelList.addListSelectionListener(personnelListener);
		personnelScrollPane	= new JScrollPane(personnelList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


		businessButton		= new JButton(Text.getString("edit"));
		addressButton		= new JButton(Text.getString("edit"));
		emailButton			= new JButton(Text.getString("edit"));
		telephoneButton		= new JButton(Text.getString("edit"));
		openingHrsButton	= new JButton(Text.getString("edit"));
		textDescrButton		= new JButton(Text.getString("edit"));
		areaButton			= new JButton(Text.getString("edit"));
		
		textDescriptionScroll = new JScrollPane(textDescription, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textDescriptionScroll.setPreferredSize(new Dimension(0, 200));

		businessButton.addActionListener(buttonListener);
		addressButton.addActionListener(buttonListener);
		emailButton.addActionListener(buttonListener);
		telephoneButton.addActionListener(buttonListener);
		openingHrsButton.addActionListener(buttonListener);
		textDescrButton.addActionListener(buttonListener);
		areaButton.addActionListener(buttonListener);

		personnelPanel.add(persNameLabel);
		personnelPanel.add(persTitleLabel);
		personnelPanel.add(persTelephoneLabel);
		personnelPanel.add(persEmailLabel);


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
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.001;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(personnelLabel, constraints);


	}

	public void updateCard(ShoppingCentre centre) {
		this.centre = centre;
		businessName.setText(centre.getBusinessName());
		address.setText(centre.getAddress().getAdress());
		email.setText(centre.getEmail().getEmailAddress());
		telephone.setText(String.valueOf(centre.getTelephone()));
		openingHours.setText(String.valueOf(centre.getOpeningHours()));
		textDescription.setText(centre.getDescription());
		area.setText(String.valueOf(centre.getArea()));

		DefaultListModel<Personnel> listModel = new DefaultListModel<Personnel>();
		for(Personnel p : centre.getPersonnel()) {
			listModel.addElement(p);
		}
		personnelList.setModel(listModel);

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

				if(businessButton.getText().equals(Text.getString("edit"))) {
					businessName.setEditable(true);
					businessButton.setText(Text.getString("save"));
				}
				else {
					if(!centre.setBusinessName(businessName.getText())) {
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
					if(!centre.setAddress(address.getText())) {
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
					if(!centre.setEmail(email.getText())) {
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
					if(!centre.setTelephone(telephone.getText())) {
						return;
					}
					telephone.setEditable(false);
					telephoneButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(openingHrsButton)) {
				if(openingHrsButton.getText().equals(Text.getString("edit"))) {
					openingHours.setEditable(true);
					openingHrsButton.setText(Text.getString("save"));
				}
				else {
					if(!centre.setOpeningHours(openingHours.getText())) {
						return;
					}
					openingHours.setEditable(false);
					openingHrsButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(areaButton)) {
				if(areaButton.getText().equals(Text.getString("edit"))) {
					area.setEditable(true);
					areaButton.setText(Text.getString("save"));				
				}
				else {
					area.setEditable(false);
					areaButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(textDescrButton)) {
				if(textDescrButton.getText().equals(Text.getString("edit"))) {
					textDescription.setEditable(true);
					textDescrButton.setText(Text.getString("save"));
				}
				else {
					if(!centre.setDescription(textDescription.getText())) {
						return;
					}
					textDescription.setEditable(false);
					textDescrButton.setText(Text.getString("edit"));
				}
			}
		}

	}

	private class PersonnelListener implements ListSelectionListener {


		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (!event.getValueIsAdjusting()) {
				Personnel selectedPers = personnelList.getSelectedValue();
				persNameLabel.setText(Text.getString("name") + ": " + selectedPers);
				persTitleLabel.setText(Text.getString("title") + ": " + selectedPers.getTitle());
				persTelephoneLabel.setText(Text.getString("tel") + ": " + String.valueOf(selectedPers.getTelephone()));
				persEmailLabel.setText(Text.getString("email") + ": " + selectedPers.getEmail());
			}
		}
	}
}



