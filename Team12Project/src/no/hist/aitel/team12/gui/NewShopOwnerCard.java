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

import no.hist.aitel.team12.app.DataBuffer;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.Personnel;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.User;
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
		firstName, lastName, username, email, personalAddress, personalZip, telephone, salary, title;
	
	private JComboBox<ShoppingCentre> shoppingCenter;
	
	private ShoppingCentre[] businessArray;
	
	private User user;
	
	private UserTab userTab;
	
	public NewShopOwnerCard(int centreID) {
		saveButton = new JButton(Text.getString("save"));
		cancelButton = new JButton(Text.getString("cancel"));
		buttonPanel = new JPanel(new GridLayout(1, 2, 25, 15));
		fieldPanel = new JPanel(new GridLayout(12, 1, 5, 15));
		labelPanel = new JPanel(new GridLayout(12, 1, 5, 15));
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
		title			= new JTextField();
		
		businessArray = ShoppingCentre.getPopulatedShoppingCentres();
		
		shoppingCenter = new JComboBox<ShoppingCentre>(businessArray);
		
		labelPanel.add(new JLabel(Text.getString("firstname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("lastname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("usr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("email")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("tel")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("sal")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("title")+ ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("shce") + ": ", SwingConstants.RIGHT));
		
		fieldPanel.add(firstName);
		fieldPanel.add(lastName);
		fieldPanel.add(username);
		fieldPanel.add(email);
		fieldPanel.add(personalAddress);
		fieldPanel.add(personalZip);
		fieldPanel.add(telephone);
		fieldPanel.add(salary);
		fieldPanel.add(title);
		fieldPanel.add(shoppingCenter);
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
					errMsg.append(Text.getString("frnamelong"));
				}
				
				if(lastName.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("lsnamelong"));
				}
				
				if(personalAddress.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("adrlong"));
				}
				
				try {
					Integer.parseInt(personalZip.getText());
					if(personalZip.getText().length() > 4) {
						errCount++;
						errMsg.append(Text.getString("zipfour"));
					}
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append(Text.getString("zipnr"));
				}
				
				if(!EmailAddress.isValidEmailAddress(email.getText())) {
					errCount++;
					errMsg.append(Text.getString("emailinv"));
				}
				
				try {
					Integer.parseInt(telephone.getText());
					if(telephone.getText().length() > 8) {
						errCount++;
						errMsg.append(Text.getString("tlplong"));
					}
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append(Text.getString("tlpnr"));
				}
				
				try {
					Integer.parseInt(salary.getText());
				}
				catch(NumberFormatException e) {
					errCount++;
					errMsg.append(Text.getString("salnr"));
				}
				/* DONE CHECKING FIELDS */
				
				if(errCount > 0) {
					if(errCount == 1) {
						JOptionPane.showMessageDialog(
								null,
								Text.getString("inputerr")+errMsg.toString(),
								Text.getString("err"),
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(
								null,
								Text.getString("inputerr")+errMsg.toString(),
								Text.getString("err"),
								JOptionPane.ERROR_MESSAGE);
					}
					
					return;
				}
				if(Personnel.createPersonnel(
						firstName.getText(), lastName.getText(),
						personalAddress.getText(), Integer.parseInt(personalZip.getText()), 
						email.getText(), Integer.parseInt(telephone.getText()), 
						Integer.parseInt(salary.getText()),
						title.getText(), shoppingCenter.getSelectedIndex()))  {
					
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
		
		DefaultComboBoxModel<ShoppingCentre> model = new DefaultComboBoxModel<ShoppingCentre>(DataBuffer.getCentres());
		shoppingCenter.setModel(model);
	}
}
