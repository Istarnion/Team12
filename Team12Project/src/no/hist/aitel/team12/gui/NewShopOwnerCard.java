package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import no.hist.aitel.team12.app.Address;
import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.app.Email;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.User;
import no.hist.aitel.team12.util.PasswordManager;
import no.hist.aitel.team12.util.Text;

/**
 * 
 * @author Roger
 *
 */

public class NewShopOwnerCard extends JPanel{
	private static final long serialVersionUID = -5354374021580408397L;
	private JButton saveButton, cancelButton;
	private JPanel buttonPanel, fieldPanel, labelPanel;
	private JTextField
	firstName, lastName, username, email, personalAddress, personalZip, telephone, salary,
	shopName, shopAddress, shopZip, shopEmail, shopTelephone, floorNumber;

	private JComboBox<Building> building;

	private Building[] buildings;


	public NewShopOwnerCard(UserTab userTab, int centreID) {
		saveButton = new JButton(Text.getString("save"));
		cancelButton = new JButton(Text.getString("cancel"));
		buttonPanel = new JPanel(new GridLayout(1, 2, 25, 15));
		fieldPanel = new JPanel(new GridLayout(16, 1, 5, 15));
		labelPanel = new JPanel(new GridLayout(16, 1, 5, 15));
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		firstName		= new JTextField();
		lastName		= new JTextField();
		username		= new JTextField();
		email			= new JTextField();
		personalAddress	= new JTextField();
		personalZip		= new JTextField();
		telephone		= new JTextField();
		salary			= new JTextField();
		shopName		= new JTextField();
		shopAddress		= new JTextField();
		shopZip			= new JTextField();
		shopEmail		= new JTextField();
		shopTelephone	= new JTextField();
		floorNumber		= new JTextField();

		buildings = new Building[0];
		ShoppingCentre[] centres = ShoppingCentre.getPopulatedShoppingCentres();
		if(centres != null && centres.length > 0) {
			buildings = ShoppingCentre.getPopulatedShoppingCentres()[0].getBuildings();
		}

		building = new JComboBox<Building>(buildings);

		labelPanel.add(new JLabel(Text.getString("firstname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("lastname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("usr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("email")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("tel")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("sal")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("businessName")+ ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+ ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+ ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("email")+ ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("tel")+ ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("floor")+ ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("building") + ": ", SwingConstants.RIGHT));

		fieldPanel.add(firstName);
		fieldPanel.add(lastName);
		fieldPanel.add(username);
		fieldPanel.add(email);
		fieldPanel.add(personalAddress);
		fieldPanel.add(personalZip);
		fieldPanel.add(telephone);
		fieldPanel.add(salary);
		fieldPanel.add(shopName);
		fieldPanel.add(shopAddress);
		fieldPanel.add(shopZip);
		fieldPanel.add(shopEmail);
		fieldPanel.add(shopTelephone);
		fieldPanel.add(floorNumber);
		fieldPanel.add(building);
		fieldPanel.add(buttonPanel);

		super.setLayout(new BorderLayout());
		super.add(labelPanel, BorderLayout.WEST);
		super.add(fieldPanel, BorderLayout.CENTER);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				StringBuilder errMsg = new StringBuilder();
				int errCount = 0;

				/* CHECKING FIELDS */
				if(firstName.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("frnamelong") + "\n");
				}

				if(firstName.getText().length() == 0) {
					errCount++;
					errMsg.append(Text.getString("frnameMissing") + "\n");
				}

				if(lastName.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("lsnamelong") + "\n");
				}

				if(firstName.getText().length() == 0) {
					errCount++;
					errMsg.append(Text.getString("lsnameMissing") + "\n");
				}

				if(username.getText().length() == 0) {
					errCount++;
					errMsg.append(Text.getString("usrNameMissing") + "\n");
				}

				if (username.getText().length() > 20) {
					errCount++;
					errMsg.append(Text.getString("userlong") + "\n");
				}

				if(User.userExists(username.getText())) {
					errCount++;
					errMsg.append(Text.getString("usrAllreadyExists") + "\n");
				}

				if(!EmailAddress.isValidEmailAddress(email.getText())) {
					errCount++;
					errMsg.append(Text.getString("emailinv") + "\n");
				}

				if(personalAddress.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("adrlong") + "\n");
				}

				if(personalAddress.getText().length() == 0) {
					errCount++;
					errMsg.append(Text.getString("adrMissing") + "\n");
				}

				if(!Address.isValidZip(personalZip.getText())) {
					errCount++;
					errMsg.append(Text.getString("invalidZip") + "\n");
				}

				try {
					Integer.parseInt(telephone.getText());
					if(telephone.getText().length() > 8) {
						errCount++;
						errMsg.append(Text.getString("tlplong") + "\n");
					}
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append(Text.getString("tlpnr") + "\n");
				}

				try {
					Integer.parseInt(salary.getText());
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append(Text.getString("salnr") + "\n");
				}

				if(shopName.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("shopNameLong") + "\n");
				}
				if(shopName.getText().length() == 0) {
					errCount++;
					errMsg.append(Text.getString("shopNameMissing") + "\n");
				}

				if(shopAddress.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("shopAdrLong") + "\n");
				}

				if(!Address.isValidZip(shopZip.getText())) {
					errCount++;
					errMsg.append(Text.getString("shopInvZip") + "\n");
				}

				if(!EmailAddress.isValidEmailAddress(shopEmail.getText())) {
					errCount++;
					errMsg.append(Text.getString("shopInvEmail") + "\n");
				}

				try {
					Integer.parseInt(shopTelephone.getText());
					if(shopTelephone.getText().length() != 8) {
						errCount++;
						errMsg.append(Text.getString("shoptlpnr") + "\n");
					}
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append(Text.getString("shoptlpnr") + "\n");
				}

				try {
					Integer.parseInt(floorNumber.getText());
				}

				catch(NumberFormatException e) {
					errCount++;
					errMsg.append(Text.getString("shopFloorError") + "\n");
				}

				if(building.getSelectedItem() == null) {
					errCount++;
					errMsg.append(Text.getString("selectBuildingErr") + "\n");
				}







				/* DONE CHECKING FIELDS */

				if(errCount > 0) {
					if(errCount == 1) {
						JOptionPane.showMessageDialog(
								null,
								Text.getString("inputerr") + "\n" + errMsg.toString(),
								Text.getString("err"),
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(
								null,
								Text.getString("inputerr") + "\n" + errMsg.toString(),
								Text.getString("err"),
								JOptionPane.ERROR_MESSAGE);
					}

					return;
				}

				String password = PasswordManager.generatePassword(username.getText());

				if(Establishment.createEstablishment(
						shopName.getText(), shopAddress.getText(),
						Integer.parseInt(shopZip.getText()), shopEmail.getText(),
						Integer.parseInt(shopTelephone.getText()),
						firstName.getText(), lastName.getText(),
						personalAddress.getText(), Integer.parseInt(personalZip.getText()), 
						email.getText(), Integer.parseInt(telephone.getText()), 
						Integer.parseInt(salary.getText()), username.getText(),
						building.getItemAt(building.getSelectedIndex()).getBuildingId(),
						Integer.parseInt(floorNumber.getText()), password))  {

					Thread t = new Thread() {
						@Override
						public void run() {
							Email.sendEmail(
									"Dear "+firstName.getText()+" "+lastName.getText()+
									",\n\nYou have been registered as an Establishment Owner for "+
									shopName.getText()+".\n\nYour login details are:\nusername:\t"+
									username.getText()+"\npassword:\t"+password+
									"\n\nPlease change your password at your earliest oppurtunity.\nRegards, System Administrator for the SSS system,\nTeam12",
									new EmailAddress(email.getText()));
						}
					};
					t.start();
					JOptionPane.showMessageDialog(null, Text.getString("usrCreated"));

					userTab.showLogoCard();
				}
				else {
					JOptionPane.showMessageDialog(null, Text.getString("dbErr"), Text.getString("err"), JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				prepareCard();
			}
		});

		prepareCard();
	}

	public void prepareCard() {
		firstName.setText("");
		lastName.setText("");
		username.setText("");
		email.setText("");
		personalAddress.setText("");
		personalZip.setText("");
		telephone.setText("");
		salary.setText("");
		shopName.setText("");		
		shopAddress.setText("");		
		shopZip.setText("");			
		shopEmail.setText("");		
		shopTelephone.setText("");	
		floorNumber.setText("");		

		buildings = new Building[0];
		ShoppingCentre[] centres = ShoppingCentre.getPopulatedShoppingCentres();
		if(centres != null && centres.length > 0) {
			buildings = ShoppingCentre.getPopulatedShoppingCentres()[0].getBuildings();
		}
		DefaultComboBoxModel<Building> buildingModel = new DefaultComboBoxModel<Building>();
		for(Building b : buildings) {
			if(b != null) buildingModel.addElement(b);
		}
		building.setModel(buildingModel);
		
		if(buildings.length>0) {
			building.setSelectedIndex(0);
		}
	}
}
