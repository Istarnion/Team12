package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sun.org.apache.xpath.internal.operations.String;

import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.Personnel;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.util.Text;
/**
 * 
 * @author Roger
 *
 */

public class NewPersonnelCard extends JPanel{
	private static final long serialVersionUID = -8666310208825761834L;
	

	private JButton saveButton, cancelButton;
	private JPanel buttonPanel, fieldPanel, labelPanel;
	
	private Personnel personnel;
	
	private JTextField
		firstName, lastName, email, address, zipCode, telephone, salary, title;
	
	private JComboBox<String> store;
	private JComboBox<ShoppingCentre> shoppingCenter;
	
	private ShoppingCentre[] businessArray;
	
	private UserTab userTab;
	
	public NewPersonnelCard() {
		saveButton = new JButton(Text.getString("save"));
		cancelButton = new JButton(Text.getString("cancel"));
		buttonPanel = new JPanel(new GridLayout(1, 2, 25, 15));
		fieldPanel = new JPanel(new GridLayout(12, 1, 5, 15));
		labelPanel = new JPanel(new GridLayout(12, 1, 5, 15));
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		firstName		= new JTextField();
		lastName		= new JTextField();
		email			= new JTextField();
		address	= new JTextField();
		zipCode		= new JTextField();
		telephone		= new JTextField();
		salary			= new JTextField();
		title			= new JTextField();
		
		
		
		store = new JComboBox<String>(new String[] {/*getStores*/});
		businessArray = ShoppingCentre.getPopulatedShoppingCentres();	
		shoppingCenter = new JComboBox<ShoppingCentre>(businessArray);
		
		labelPanel.add(new JLabel(Text.getString("firstname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("lastname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("email")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("tel")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("sal")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("title")+ ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("store") + ": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("shce") + ": ", SwingConstants.RIGHT));
		
		fieldPanel.add(firstName);
		fieldPanel.add(lastName);
		fieldPanel.add(email);
		fieldPanel.add(address);
		fieldPanel.add(zipCode);
		fieldPanel.add(telephone);
		fieldPanel.add(salary);
		fieldPanel.add(title);
		fieldPanel.add(store);
		fieldPanel.add(shoppingCenter);
		fieldPanel.add(buttonPanel);
		
		
		super.setLayout(new BorderLayout());
		super.add(labelPanel, BorderLayout.WEST);
		super.add(fieldPanel, BorderLayout.CENTER);
		
		/*
		shoppingCenter.addItemListener(
				event -> {
					JComboBox<?> cb = (JComboBox<?>)event.getSource();
					
					if()) {
						
					}
					else {
					}
				}
			);
			*/
		
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
				
				if(address.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("adrlong"));
				}
				
				try {
					Integer.parseInt(zipCode.getText());
					if(zipCode.getText().length() > 4) {
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
						address.getText(), Integer.parseInt(zipCode.getText()), 
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
				if(personnel != null) updateCard(personnel);
			}
		});
	}
	
	public void prepareCard() {
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		address.setText("");
		zipCode.setText("");
		telephone.setText("");
		salary.setText("");
	}
	
	public void updateCard(Personnel p) {
		personnel = p;
		
		if(p == null) return;
		
		firstName.setText(p.getFirstName()!=null?p.getFirstName():"");
		lastName.setText(p.getLastName()!=null?p.getLastName():"");
		email.setText(p.getEmail().getEmailAddress()!=null?p.getEmail().getEmailAddress():"");
		address.setText(p.getAddress().getAdress()!=null?p.getAddress().getAdress():"");
		zipCode.setText(p.getAddress().getZipcode()+""!=null?p.getAddress().getZipcode()+"":"");
		telephone.setText(p.getTelephone()+""!=null?p.getTelephone()+"":"");
		salary.setText(p.getSalary()+""!=null?p.getSalary()+"":"");
		
	}

}
