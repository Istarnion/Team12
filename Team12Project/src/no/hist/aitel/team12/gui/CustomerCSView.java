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
 * CustomerCSView.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.Ticket;
import no.hist.aitel.team12.util.Text;

public class CustomerCSView extends JPanel {

	private JLabel centreHeader;
	
	private InputField email;
	
	private JScrollPane messageScroll;
	
	private JTextArea supportMessage = new JTextArea();
	
	private JButton sendButton, cancelButton;
	
	private int centreID = 0;
	
	private String centreName = null;
	
	private static final long serialVersionUID = 242612470705756493L;

	private CustomerView cv;
	
	public CustomerCSView(CustomerView cv) {
		
		this.cv = cv;
		
		//this.setLayout(new BorderLayout());
		
		Box mainBox = Box.createVerticalBox();
		
		centreHeader = new JLabel();
		mainBox.add(centreHeader);
		mainBox.add(Box.createVerticalStrut(5));
		
		email = new InputField(Text.getString("email"), 20);
		mainBox.add(email);
		
		supportMessage.setLineWrap(true);
		supportMessage.setWrapStyleWord(true);
		messageScroll = new JScrollPane(supportMessage);
		messageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageScroll.setPreferredSize(new Dimension(600, 750));
		mainBox.add(messageScroll);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		
		sendButton = new JButton(Text.getString("send"));
		
		buttonPanel.add(sendButton);
		
		cancelButton = new JButton(Text.getString("cancel"));
		
		buttonPanel.add(cancelButton);
		
		mainBox.add(buttonPanel);
		mainBox.add(Box.createVerticalStrut(50));
		
		this.add(mainBox);
		
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(EmailAddress.isValidEmailAddress(email.getText())) {
					if(!supportMessage.getText().isEmpty()) {
						if(Ticket.sendSupportTicket(supportMessage.getText(), new EmailAddress(email.getText()), centreID)) {
							JOptionPane.showMessageDialog(null, Text.getString("msgConfirmation"));
							cv.gotoMainView();
						}
						else {
							JOptionPane.showMessageDialog(null, Text.getString("dbErr"), Text.getString("err"), JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, Text.getString("noEmpty"), Text.getString("err"), JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, Text.getString("invalidEmail"), Text.getString("err"), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cv.gotoMainView();
			}
		});
	}
	
	public void updateView(int centreID, String centreName) {
		this.centreID = centreID;
		this.centreName = centreName;
		
		centreHeader.setText(centreName);
	}
}
