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
 * TicketTab.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.hist.aitel.team12.app.DataBuffer;
import no.hist.aitel.team12.app.Email;
import no.hist.aitel.team12.app.EmailAddress;
import no.hist.aitel.team12.app.Ticket;
import no.hist.aitel.team12.util.Text;

public class TicketTab extends SSSTab {

	private static final long serialVersionUID = -1027205615346780225L;

	private JPanel leftPanel;

	private JPanel mainPanel;

	private JTabbedPane ticketPane;

	private JScrollPane resolvedScroll, unresolvedScroll;

	private JList<Ticket> resolvedTickets, unresolvedTickets;

	private JScrollPane incomingScroll, replyScroll;

	private JTextArea incomingMessage, replyMessage;

	private JTextField customerEmail;

	private JButton refreshButton, replyButton;

	private Ticket selectedTicket = null;

	private String centreName = null;

	public TicketTab() {
		this.setLayout(new BorderLayout());

		leftPanel = new JPanel(new BorderLayout());
		refreshButton = new JButton("refresh");
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		leftPanel.add(refreshButton, BorderLayout.SOUTH);

		ticketPane = new JTabbedPane();
		ticketPane.setTabPlacement(JTabbedPane.BOTTOM);
		leftPanel.add(ticketPane, BorderLayout.CENTER);

		TicketSelectionListener tsl = new TicketSelectionListener();

		unresolvedTickets = new JList<Ticket>();
		unresolvedTickets.addListSelectionListener(tsl);
		unresolvedScroll = new JScrollPane(unresolvedTickets);
		unresolvedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		ticketPane.addTab("unresolved", unresolvedScroll);

		resolvedTickets = new JList<Ticket>();
		resolvedTickets.addListSelectionListener(tsl);
		resolvedScroll = new JScrollPane(resolvedTickets);
		resolvedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		ticketPane.add("resolved", resolvedScroll);

		leftPanel.setPreferredSize(new Dimension(200, (int)leftPanel.getPreferredSize().getHeight()));
		add(leftPanel, BorderLayout.WEST);

		mainPanel = new JPanel(new BorderLayout());

		Box mainBox = Box.createVerticalBox();

		incomingMessage = new JTextArea();
		incomingMessage.setLineWrap(true);
		incomingMessage.setWrapStyleWord(true);
		incomingMessage.setEditable(false);
		incomingScroll = new JScrollPane(incomingMessage);
		incomingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainBox.add(incomingScroll);

		mainBox.add(Box.createVerticalStrut(5));

		Box replyBox = Box.createHorizontalBox();

		customerEmail = new JTextField();
		Color c = customerEmail.getBackground();
		customerEmail.setEditable(false);
		customerEmail.setBackground(c);

		replyBox.add(customerEmail);

		replyButton = new JButton(Text.getString("reply"));
		replyBox.add(replyButton);
		replyBox.setMaximumSize(new Dimension((int)replyBox.getMaximumSize().getWidth(), (int)customerEmail.getPreferredSize().getHeight()));

		mainBox.add(replyBox);

		replyMessage = new JTextArea();
		replyMessage.setLineWrap(true);
		replyMessage.setWrapStyleWord(true);
		replyScroll = new JScrollPane(replyMessage);
		replyScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainBox.add(replyScroll);


		mainPanel.add(mainBox);

		add(mainPanel, BorderLayout.CENTER);

		replyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!customerEmail.getText().isEmpty() && EmailAddress.isValidEmailAddress(customerEmail.getText())) {

					while(centreName == null) {
						centreName = DataBuffer.getCentres()[0].getBusinessName();
					}

					if(selectedTicket.setResolvedStatus(true)) {
						Thread t = new Thread() {
							@Override
							public void run() {
								Email.sendEmail(
										replyMessage.getText()+"\n\nRegards,\n"+centreName+" customer service.",
										new EmailAddress(customerEmail.getText()),
										centreName+" - Customer Service - Ticket #"+selectedTicket.getTicketID());
							}
						};
						t.start();
						
						JOptionPane.showMessageDialog(null, Text.getString("msgConfirmation"));
					}
				}

				refresh();
			}
		});
	}

	private class TicketSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JList<?>){
				final JList<Ticket> sourceList = (JList<Ticket>) obj;

				Ticket t = sourceList.getSelectedValue();
				if(t != null) {
					selectedTicket = t;
					customerEmail.setText(t.getCustomerEmail().getEmailAddress());
					incomingMessage.setText(t.getContent());
				}
			}
		}
	}

	@Override
	public void refresh() {
		Ticket[] tarray = DataBuffer.getTickets();

		if(tarray != null) {
			DefaultListModel<Ticket> resModel = new DefaultListModel<Ticket>();
			DefaultListModel<Ticket> unresModel = new DefaultListModel<Ticket>();

			for(Ticket t : tarray) {
				if(t.isResolvedStatus()) {
					resModel.addElement(t);
				}
				else {
					unresModel.addElement(t);
				}
			}

			int prevResSel = resolvedTickets.getSelectedIndex();
			int prevUnresSel = unresolvedTickets.getSelectedIndex();

			resolvedTickets.setModel(resModel);
			unresolvedTickets.setModel(unresModel);

			resolvedTickets.setSelectedIndex(prevResSel);
			unresolvedTickets.setSelectedIndex(prevUnresSel);
		}
	}
}
