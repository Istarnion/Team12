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
 * NewCentreManagerCard.java Team 12, 27 Apr 2015
 *******************************************************************************/
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
import javax.swing.SwingWorker;

import no.hist.aitel.team12.app.Address;
import no.hist.aitel.team12.app.Email;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.app.User;
import no.hist.aitel.team12.util.PasswordManager;
import no.hist.aitel.team12.util.Text;

/**
 * 
 * @author Roger
 *
 */

public class NewCentreManagerCard extends JPanel {
	private static final long serialVersionUID = 4688863130267581267L;

	private JButton saveButton, cancelButton;
	private JPanel buttonPanel, fieldPanel, labelPanel;

	private JTextField
	firstName, lastName, username, email, personalAddress, personalZip, telephone, salary, centreName, centreAddress, centreZip;

	private User user;

	private ShoppingCentre shoppingCentre;

	public NewCentreManagerCard(UserTab userTab) {

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

				if(personalAddress.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("adrlong") + "\n");
				}


				if(!Address.isValidZip(personalZip.getText())) {
					errCount++;
					errMsg.append(Text.getString("invalidZip") + "\n");
				}

				if(!EmailAddress.isValidEmailAddress(email.getText())) {
					errCount++;
					errMsg.append(Text.getString("emailinv") + "\n");
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



				if(centreName.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("centerlong") + "\n");
				}
				
				if(centreName.getText().equals("")) {
					errCount++;
					errMsg.append(Text.getString("noCentreName") + "\n");
				}

				if(centreAddress.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("adrlong") + "\n");
				}


				if(!Address.isValidZip(centreZip.getText())) {
					errCount++;
					errMsg.append(Text.getString("invalidZip") + "\n");
				}
				
				

				/* DONE CHECKING FIELDS */

				if(errCount > 0) {
					if(errCount == 1) {
						JOptionPane.showMessageDialog(
								null,
								Text.getString("inputerr") + "\n" +errMsg.toString(),
								Text.getString("err"),
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(
								null,
								Text.getString("inputerr")  + "\n" +errMsg.toString(),
								Text.getString("err"),
								JOptionPane.ERROR_MESSAGE);
					}

					return;
				}

				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					boolean OK = false;
					String password = PasswordManager.generatePassword(username.getText());

					@Override
					protected Void doInBackground() throws Exception {
						
						
						if(ShoppingCentre.createCentre(
								firstName.getText(), lastName.getText(),
								username.getText(), password, email.getText(),
								personalAddress.getText(), Integer.parseInt(personalZip.getText()),
								Integer.parseInt(telephone.getText()), Integer.parseInt(salary.getText()),
								centreName.getText(), centreAddress.getText(),
								Integer.parseInt(centreZip.getText()))) {
							OK = true;
						}
						return null;
					}

					@Override
					protected void done() {
						if(OK) {
							Thread t = new Thread() {
								@Override
								public void run() {
									Email.sendEmail("Dear "+firstName.getText()+" "+lastName.getText()
											+",\nYou have been created as a Centre Manager for "+centreName.getText()
											+".\nYour username is: "+username.getText()+"\nYour password is: "+password
											+"\n\nPlease change your password at your earliest oppurtunity.\n Regards, System Administrator for the SSS system,\nTeam12",
											new EmailAddress(email.getText()));
								}
							};
							t.start();
							JOptionPane.showMessageDialog(null, Text.getString("usrCreated"));

							userTab.showLogoCard();
							saveButton.setEnabled(true);
							saveButton.setText(Text.getString("save"));
						}
						else{
							JOptionPane.showMessageDialog(null, Text.getString("dbErr"), Text.getString("err"), JOptionPane.ERROR_MESSAGE);
						}

					}

				};
				saveButton.setEnabled(false);
				saveButton.setText(Text.getString("saving"));
				worker.execute();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(user != null) updateCard(user, shoppingCentre);
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
		centreName.setText("");
		centreAddress.setText("");
		centreZip.setText("");
	}

	public void updateCard(User u, ShoppingCentre s) {
		user = u;
		shoppingCentre = s;

		if(u == null) return;

		firstName.setText(u.getFirstName()!=null?u.getFirstName():"");
		lastName.setText(u.getLastName()!=null?u.getLastName():"");
		username.setText(u.getUsername()!=null?u.getUsername():"");
		email.setText(u.getEmail().getEmailAddress()!=null?u.getEmail().getEmailAddress():"");
		personalAddress.setText(u.getAddress().getAdress()!=null?u.getAddress().getAdress():"");
		personalZip.setText(u.getAddress().getZipcode()+""!=null?u.getAddress().getZipcode()+"":"");
		telephone.setText(u.getTelephone()+""!=null?u.getTelephone()+"":"");
		salary.setText(u.getSalary()+""!=null?u.getSalary()+"":"");

		centreName.setText(s.getBusinessName()!=null?s.getBusinessName():"");
		centreAddress.setText(s.getAddress().getAdress()!=null?s.getAddress().getAdress():"");
		centreZip.setText(s.getAddress().getZipcode()+""!=null?s.getAddress().getZipcode()+"":"");

	}
}
