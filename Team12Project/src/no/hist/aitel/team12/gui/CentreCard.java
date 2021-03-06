/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * CentreCard.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.Personnel;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.UserType;
import no.hist.aitel.team12.util.Text;

public class CentreCard extends BusinessCard {

	private static final long serialVersionUID = -511628429934079700L;

	private JLabel parkingSpaceLabel,areaLabel, personnelLabel, persNameLabel, persTelephoneLabel, persTitleLabel, persEmailLabel;
	private JPanel newBuildingPanel, personnelPanel;
	private JButton parkingSpaceButton, saveBuildingButton, newBuildingButton, areaButton;
	private JTextField parkingSpace;
	private JList<Personnel> personnelList;
	private ButtonListener buttonListener;
	private PersonnelListener personnelListener;
	private JScrollPane personnelScrollPane;
	private ShoppingCentre centre;
	private InputField newBuildingNameInput, newBuildingAreaInput, newBuildingFloorsInput;
	private JDialog newBuildingDialog;
	private UserType type;
	public CentreCard(int userID, UserType type) {

		this.type = type;
		buttonListener 		= new ButtonListener();
		personnelListener	= new PersonnelListener();
		areaLabel			= new JLabel(Text.getString("area") + ": ", SwingConstants.RIGHT);
		personnelLabel		= new JLabel(Text.getString("personnel") + ": ", SwingConstants.RIGHT);
		persNameLabel		= new JLabel(Text.getString("name") + ": ");
		persTelephoneLabel	= new JLabel(Text.getString("tel") + ": ");
		persTitleLabel 		= new JLabel(Text.getString("title") + ": ");
		persEmailLabel		= new JLabel(Text.getString("email") + ": ");
		parkingSpaceLabel	= new JLabel(Text.getString("park") + ": ", SwingConstants.RIGHT);
		personnelPanel		= new JPanel(new GridLayout(4,1));
		newBuildingPanel	= new JPanel();

		parkingSpaceButton = new JButton(Text.getString("edit"));

		parkingSpace = new JTextField("");

		personnelPanel.setPreferredSize(personnelPanel.getSize());
		newBuildingDialog = new JDialog();		
		newBuildingPanel.setLayout(new BoxLayout(newBuildingPanel, BoxLayout.Y_AXIS));
		newBuildingDialog.setMinimumSize(new Dimension(200,200));
		newBuildingDialog.setResizable(false);
		newBuildingNameInput = new InputField(Text.getString("name"), 20);
		newBuildingAreaInput = new InputField(Text.getString("area"), 20);
		newBuildingFloorsInput = new InputField(Text.getString("nrOfFloors"), 20);
		saveBuildingButton = new JButton(Text.getString("save"));
		saveBuildingButton.setAlignmentX(CENTER_ALIGNMENT);
		saveBuildingButton.addActionListener(buttonListener);
		newBuildingPanel.setPreferredSize(new Dimension(200,200));
		newBuildingPanel.setBorder(new EmptyBorder(10,10,10,10));
		newBuildingDialog.setLocationRelativeTo(null);
		newBuildingPanel.add(newBuildingNameInput);
		newBuildingPanel.add(newBuildingAreaInput);
		newBuildingPanel.add(newBuildingFloorsInput);
		newBuildingPanel.add(saveBuildingButton);
		newBuildingDialog.add(newBuildingPanel);	
		personnelList		= new JList<Personnel>();
		personnelList.addListSelectionListener(personnelListener);
		personnelScrollPane	= new JScrollPane(personnelList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaButton			= new JButton(Text.getString("edit"));
		newBuildingButton	= new JButton(Text.getString("newBuilding"));			
		areaButton.setPreferredSize(new Dimension(60, 23));			
		parkingSpaceButton.setPreferredSize(openingHrsButton.getPreferredSize());			
		textDescriptionScroll = new JScrollPane(textDescription, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textDescriptionScroll.setPreferredSize(new Dimension(0, 200));
		businessButton.addActionListener(buttonListener);
		addressButton.addActionListener(buttonListener);
		emailButton.addActionListener(buttonListener);
		telephoneButton.addActionListener(buttonListener);
		openingHrsButton.addActionListener(buttonListener);
		textDescrButton.addActionListener(buttonListener);
		areaButton.addActionListener(buttonListener);
		zipButton.addActionListener(buttonListener);
		newBuildingButton.addActionListener(buttonListener);
		parkingSpaceButton.addActionListener(buttonListener);
		personnelPanel.add(persNameLabel);
		personnelPanel.add(persTitleLabel);
		personnelPanel.add(persTelephoneLabel);
		personnelPanel.add(persEmailLabel);

		// Grid bag layout
		GridBagConstraints constraints = new GridBagConstraints();

		// Setting some margins for a bit of space between each field/button/label
		constraints.insets.bottom 	= 5;
		constraints.insets.top 		= 5;


		// Grid bag for parking spaces 
		// Grid bag for business name
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(parkingSpaceLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(parkingSpace, constraints);

		constraints.gridx = 4;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(parkingSpaceButton, constraints);

		// Grid bag for area 
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		this.add(areaLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(area, constraints);

		// Description grid bag setup
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		this.add(textDescrLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(textDescriptionScroll, constraints);

		constraints.gridx = 4;
		constraints.gridy = 8;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		this.add(textDescrButton, constraints);

		// Grid bag for personnel
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(personnelLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.4;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(personnelScrollPane, constraints);

		constraints.insets.left = 10;
		constraints.gridx = 2;
		constraints.gridy = 9;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.4;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(personnelPanel, constraints);

		// New building button grid bag set up
		constraints.insets.left = 0;
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.001;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.NONE;
		this.add(newBuildingButton, constraints);
	}

	public void updateCard(ShoppingCentre centre) {
		this.centre = centre;

		businessName.setText(centre.getBusinessName());
		address.setText(centre.getAddress().getAdress());
		email.setText(centre.getEmail().getEmailAddress());
		telephone.setText(String.valueOf(centre.getTelephone()));
		String openingHrs = centre.getOpeningHours();
		openingTextField1.setText(openingHrs.substring(0, 2));
		openingTextField2.setText(openingHrs.substring(2, 4));
		openingTextField3.setText(openingHrs.substring(4, 6));
		openingTextField4.setText(openingHrs.substring(6, 8));
		textDescription.setText(centre.getDescription());
		area.setText(String.valueOf(centre.getArea()));
		zip.setText(String.valueOf(centre.getAddress().getZipcode()));
		parkingSpace.setText(String.valueOf(centre.getParkingSpaces()));

		DefaultListModel<Personnel> listModel = new DefaultListModel<Personnel>();
		for(Personnel p : centre.getPersonnel()) {
			listModel.addElement(p);
		}
		personnelList.setModel(listModel);

		businessName.setEditable(false);
		address.setEditable(false);
		email.setEditable(false);
		telephone.setEditable(false);
		openingTextField1.setEditable(false);
		openingTextField2.setEditable(false);
		openingTextField3.setEditable(false);
		openingTextField4.setEditable(false);
		textDescription.setEditable(false);
		area.setEditable(false);
		zip.setEditable(false);
		parkingSpace.setEditable(false);

		businessButton.setText(Text.getString("edit"));
		addressButton.setText(Text.getString("edit"));
		emailButton.setText(Text.getString("edit"));
		telephoneButton.setText(Text.getString("edit"));
		openingHrsButton.setText(Text.getString("edit"));
		textDescrButton.setText(Text.getString("edit"));
		areaButton.setText(Text.getString("edit"));
		zipButton.setText(Text.getString("edit"));
		parkingSpaceButton.setText(Text.getString("edit"));
		
		if(type == UserType.SHOP_OWNER) {
			businessButton.setEnabled(false);
			addressButton.setEnabled(false);
			emailButton.setEnabled(false);
			telephoneButton.setEnabled(false);
			openingHrsButton.setEnabled(false);
			textDescrButton.setEnabled(false);
			areaButton.setEnabled(false);
			zipButton.setEnabled(false);
			parkingSpaceButton.setEnabled(false);
			newBuildingButton.setEnabled(false);
		}
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
					if(!centre.setOpeningHours(newValue)) {
						return;
					}
					openingTextField1.setEditable(false);
					openingTextField2.setEditable(false);
					openingTextField3.setEditable(false);
					openingTextField4.setEditable(false);
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
			else if(pressedButton.equals(zipButton)) {
				if(zipButton.getText().equals(Text.getString("edit"))) {
					zip.setEditable(true);
					zipButton.setText(Text.getString("save"));
				}
				else {
					if(!centre.setZipcode(zip.getText())) {
						return;
					}
					zip.setEditable(false);
					zipButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(newBuildingButton)) {
				newBuildingDialog.setVisible(true);
				newBuildingNameInput.setText("");
				newBuildingAreaInput.setText("");
				newBuildingFloorsInput.setText("");
			}
			else if(pressedButton.equals(saveBuildingButton)) {
				int parsedArea = 0;
				int parsedFloors = 0;
				String newBuildingName = "";
				try {
					parsedArea = Integer.parseInt(newBuildingAreaInput.getText());
					parsedFloors = Integer.parseInt(newBuildingFloorsInput.getText());
					newBuildingName = newBuildingNameInput.getText();
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, Text.getString("invalidInt"));
					return;
				}

				if(!Building.newBuilding(centre.getCentreId(), newBuildingName , parsedFloors, parsedArea)) {
					System.out.println("db save failed");
					return;
				}
				newBuildingDialog.setVisible(false);
				JOptionPane.showMessageDialog(null, Text.getString("buildingCreated"));
			}
			else if(pressedButton.equals(parkingSpaceButton)) {
				if(parkingSpaceButton.getText().equals(Text.getString("edit"))) {
					parkingSpaceButton.setText(Text.getString("save"));
					parkingSpace.setEditable(true);
				}
				else {
					if(!centre.setParkingSpaces(parkingSpace.getText())) {
						return;
					}
					parkingSpaceButton.setText(Text.getString("edit"));
					parkingSpace.setEditable(false);
				}
			}
		}
	}

	private class PersonnelListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (!event.getValueIsAdjusting()) {
				Personnel selectedPers = personnelList.getSelectedValue();
				if(selectedPers != null) {
					persNameLabel.setText(Text.getString("name") + ": " + selectedPers);
					persTitleLabel.setText(Text.getString("title") + ": " + selectedPers.getTitle());
					persTelephoneLabel.setText(Text.getString("tel") + ": " + String.valueOf(selectedPers.getTelephone()));
					persEmailLabel.setText(Text.getString("email") + ": " + selectedPers.getEmail());
				}

			}
		}
	}
}