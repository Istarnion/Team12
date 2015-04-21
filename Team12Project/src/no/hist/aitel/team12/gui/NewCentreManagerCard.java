package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import no.hist.aitel.team12.util.Text;

public class NewCentreManagerCard extends JPanel {
	private static final long serialVersionUID = 4688863130267581267L;
	
	private JButton saveButton, cancelButton;
	private JPanel buttonPanel, fieldPanel, labelPanel;
	
	private JTextField
		firstName, lastName, username, email, personalAddress, personalZip, telephone, salary, centreName, centreAddress, centreZip;
	
	public NewCentreManagerCard() {
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
		centreName		= new JTextField();
		centreAddress	= new JTextField();
		centreZip		= new JTextField();
		
		labelPanel.add(new JLabel(Text.getString("firstname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("lastname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("usr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("email")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("tel")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("sal")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("businessName")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+": ", SwingConstants.RIGHT));
		
		fieldPanel.add(firstName);
		fieldPanel.add(lastName);
		fieldPanel.add(username);
		fieldPanel.add(email);
		fieldPanel.add(personalAddress);
		fieldPanel.add(personalZip);
		fieldPanel.add(telephone);
		fieldPanel.add(salary);
		fieldPanel.add(centreName);
		fieldPanel.add(centreAddress);
		fieldPanel.add(centreZip);
		fieldPanel.add(buttonPanel);
		
		super.setLayout(new BorderLayout());
		super.add(labelPanel, BorderLayout.WEST);
		super.add(fieldPanel, BorderLayout.CENTER);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
			}
		});
	}
	
	public void prepareCard() {
		
	}
}
