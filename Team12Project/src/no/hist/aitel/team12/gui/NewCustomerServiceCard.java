package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import no.hist.aitel.team12.app.Address;
import no.hist.aitel.team12.app.Email;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.User;
import no.hist.aitel.team12.util.PasswordManager;
import no.hist.aitel.team12.util.Text;

/**
 * 
 * @author Hallgeir
 *
 */
public class NewCustomerServiceCard extends JPanel{

	private static final long serialVersionUID = -1259252191776134538L;
	private JButton saveButton, cancelButton;
	private JPanel buttonPanel, fieldPanel, labelPanel;

	private User user;

	private JTextField
	firstName, lastName, username, email, personalAddress, personalZip, telephone, salary;

	private UserTab userTab;

	public NewCustomerServiceCard(UserTab userTab, int centreID) {
		this.userTab = userTab;

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

		labelPanel.add(new JLabel(Text.getString("firstname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("lastname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("usr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("email")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("tel")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("sal")+": ", SwingConstants.RIGHT));

		fieldPanel.add(firstName);
		fieldPanel.add(lastName);
		fieldPanel.add(username);
		fieldPanel.add(email);
		fieldPanel.add(personalAddress);
		fieldPanel.add(personalZip);
		fieldPanel.add(telephone);
		fieldPanel.add(salary);
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
				
				if(lastName.getText().length() == 0) {
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
					if(telephone.getText().length() != 8) {
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

				if(User.createCustomerServiceEmployee(
						firstName.getText(), lastName.getText(),
						personalAddress.getText(), Integer.parseInt(personalZip.getText()),
						email.getText(), Integer.parseInt(telephone.getText()),
						Integer.parseInt(salary.getText()), centreID,
						username.getText(), password)) {

					Thread t = new Thread() {
						@Override
						public void run() {
							Email.sendEmail("Dear "+firstName.getText()+" "+lastName.getText()
									+",\nYou have been created as a Customer Service emplotee for a Shopping Centre in the SSS system"
									+".\n\nYour username is:\t"+username.getText()+"\nYour password is:\t"+password
									+"\n\nPlease change your password at your earliest oppurtunity.\nRegards, System Administrator for the SSS system,\nTeam12",
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
				if(user != null) updateCard(user);
				userTab.showLogoCard();
			}
		});
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
	}

	public void updateCard(User u) {
		user = u;

		if(u == null) return;

		firstName.setText(u.getFirstName()!=null?u.getFirstName():"");
		lastName.setText(u.getLastName()!=null?u.getLastName():"");
		username.setText(u.getUsername()!=null?u.getUsername():"");
		email.setText(u.getEmail().getEmailAddress()!=null?u.getEmail().getEmailAddress():"");
		personalAddress.setText(u.getAddress().getAdress()!=null?u.getAddress().getAdress():"");
		personalZip.setText(u.getAddress().getZipcode()+""!=null?u.getAddress().getZipcode()+"":"");
		telephone.setText(u.getTelephone()+""!=null?u.getTelephone()+"":"");
		salary.setText(u.getSalary()+""!=null?u.getSalary()+"":"");

	}



}
