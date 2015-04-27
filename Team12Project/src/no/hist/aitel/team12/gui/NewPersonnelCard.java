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
 * NewPersonnelCard.java Team 12, 27. apr. 2015
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

import no.hist.aitel.team12.app.Address;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.Personnel;
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

	public NewPersonnelCard(UserTab userTab, int centreID) {
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
		address			= new JTextField();
		zipCode			= new JTextField();
		telephone		= new JTextField();
		salary			= new JTextField();
		title			= new JTextField();

		labelPanel.add(new JLabel(Text.getString("firstname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("lastname")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("email")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("adr")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("zip")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("tel")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("sal")+": ", SwingConstants.RIGHT));
		labelPanel.add(new JLabel(Text.getString("title")+ ": ", SwingConstants.RIGHT));

		fieldPanel.add(firstName);
		fieldPanel.add(lastName);
		fieldPanel.add(email);
		fieldPanel.add(address);
		fieldPanel.add(zipCode);
		fieldPanel.add(telephone);
		fieldPanel.add(salary);
		fieldPanel.add(title);
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

				if(!EmailAddress.isValidEmailAddress(email.getText())) {
					errCount++;
					errMsg.append(Text.getString("emailinv") + "\n");
				}

				if(address.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("adrlong") + "\n");
				}

				if(address.getText().length() == 0) {
					errCount++;
					errMsg.append(Text.getString("adrMissing") + "\n");
				}


				if(!Address.isValidZip(zipCode.getText())) {
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

				if(title.getText().length() > 30) {
					errCount++;
					errMsg.append(Text.getString("titleLong") + "\n");
				}

				if(title.getText().length() == 0) {
					errCount++;
					errMsg.append(Text.getString("titleMissing") + "\n");
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

				if(Personnel.createPersonnel(
						firstName.getText(), lastName.getText(),
						address.getText(), Integer.parseInt(zipCode.getText()), 
						email.getText(), Integer.parseInt(telephone.getText()), 
						Integer.parseInt(salary.getText()),
						title.getText(), centreID))  {
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
		title.setText("");

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
