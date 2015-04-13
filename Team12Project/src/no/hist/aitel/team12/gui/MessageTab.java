
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class MessageTab extends SSSTab {
	
	private static final long serialVersionUID = 7021415739968091789L;

	
	private JPanel inboxArea = new JPanel();
	private JPanel comboArea = new JPanel();
	private JPanel viewMessagePanel = new JPanel();
	private JTextArea viewMessageText;
	private JScrollPane viewMessageScroll;
	private JPanel sendMessagePanel = new JPanel();
	private JTextArea sendMessageText;
	private JScrollPane sendMessageScroll;
	
	
	private static final String [] meldinger ={"Kalle Kallesen - Årsfest brio", "James Bond - Nattåpent desember", "Dr. Dre - styremøte kommende torsdag"," Kari UtenTraaa - Åpningstider i julen", "Lols Mc. Lolsen - test blalalbv","Kaptein Sabeltann -test slutt"};

	
	// SuppressWarning added by eclipse to remove error message when running
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JList inboxList = new JList(meldinger);
	private JScrollPane scrollInbox = new JScrollPane(inboxList);
	
	
	

	
	public MessageTab() {
		
		setLayout(new BorderLayout());
		
		add(inboxArea,BorderLayout.WEST);
		inboxArea.setLayout(new BorderLayout());
		inboxArea.add(scrollInbox,BorderLayout.CENTER);
		inboxList.addListSelectionListener(new inboxListner());
		add(comboArea,BorderLayout.CENTER);
		
		// Colors for debugging
		inboxArea.setBackground(Color.BLUE);
		comboArea.setBackground(Color.RED);
		viewMessagePanel.setBackground(Color.pink);
		sendMessagePanel.setBackground(Color.CYAN);
		
		comboArea.setLayout(new GridLayout(2, 1));
		comboArea.add(viewMessagePanel);
		comboArea.add(sendMessagePanel);
		
		// VIEWMESSAGE AREA
		
		viewMessageText = new JTextArea();
		viewMessageScroll = new JScrollPane(viewMessageText);
		viewMessageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		viewMessagePanel.setLayout(new GridBagLayout());
		GridBagConstraints constraintsViewMsg = new GridBagConstraints();
		
		// Field for from
		JTextField from = new JTextField();
		constraintsViewMsg.gridx = 0;
		constraintsViewMsg.gridy = 0;
		constraintsViewMsg.gridwidth = 6;
		constraintsViewMsg.anchor = GridBagConstraints.WEST;
		constraintsViewMsg.fill = GridBagConstraints.HORIZONTAL;
		viewMessagePanel.add(from,constraintsViewMsg);
		
		// Button for replying to message
		JButton reply = new JButton("Reply");
		constraintsViewMsg.gridx = 6;
		constraintsViewMsg.gridy = 0;
		constraintsViewMsg.anchor = GridBagConstraints.EAST;
		viewMessagePanel.add(reply, constraintsViewMsg);
		
		// Field for message subject
		JTextField subject = new JTextField();
		constraintsViewMsg.gridx = 0;
		constraintsViewMsg.gridy = 1;
		constraintsViewMsg.anchor = GridBagConstraints.WEST;
		constraintsViewMsg.fill = GridBagConstraints.HORIZONTAL;
		viewMessagePanel.add(subject, constraintsViewMsg);
		
		// Field for viewing selected message
		constraintsViewMsg.gridx = 0;
		constraintsViewMsg.gridy = 2;
		constraintsViewMsg.anchor = GridBagConstraints.WEST;
		constraintsViewMsg.gridheight = 1;
		constraintsViewMsg.gridwidth = 6;
		constraintsViewMsg.weightx = 1;
		constraintsViewMsg.fill = GridBagConstraints.BOTH;
		constraintsViewMsg.weightx = 1.0;
		constraintsViewMsg.weighty = 1.0;
		viewMessagePanel.add(viewMessageScroll, constraintsViewMsg);
		viewMessageText.setEditable(false);
		
		
		// SENDMESSAGE AREA
		
		sendMessageText = new JTextArea();
		sendMessageScroll = new JScrollPane(sendMessageText);
		sendMessageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		sendMessagePanel.setLayout(new GridBagLayout());
		GridBagConstraints constraintsSendMsg = new GridBagConstraints();
		
		// Field for from
		JTextField to = new JTextField();
		constraintsViewMsg.gridx = 0;
		constraintsViewMsg.gridy = 0;
		constraintsViewMsg.gridwidth = 6;
		constraintsViewMsg.anchor = GridBagConstraints.WEST;
		constraintsViewMsg.fill = GridBagConstraints.HORIZONTAL;
		sendMessagePanel.add(to,constraintsViewMsg);
		
		// Button for replying to message
		JButton send = new JButton("Send");
		constraintsViewMsg.gridx = 6;
		constraintsViewMsg.gridy = 0;
		constraintsViewMsg.anchor = GridBagConstraints.EAST;
		sendMessagePanel.add(send, constraintsViewMsg);
		
		// Field for message subject
		JTextField subjectto = new JTextField();
		constraintsViewMsg.gridx = 0;
		constraintsViewMsg.gridy = 1;
		constraintsViewMsg.anchor = GridBagConstraints.WEST;
		constraintsViewMsg.fill = GridBagConstraints.HORIZONTAL;
		sendMessagePanel.add(subjectto, constraintsViewMsg);
		
		// Field for viewing selected message
		constraintsViewMsg.gridx = 0;
		constraintsViewMsg.gridy = 2;
		constraintsViewMsg.anchor = GridBagConstraints.WEST;
		constraintsViewMsg.gridheight = 1;
		constraintsViewMsg.gridwidth = 6;
		constraintsViewMsg.weightx = 1;
		constraintsViewMsg.fill = GridBagConstraints.BOTH;
		constraintsViewMsg.weightx = 1.0;
		constraintsViewMsg.weighty = 1.0;

		sendMessagePanel.add(sendMessageScroll, constraintsSendMsg);
		sendMessageText.setEditable(true);
		
	}
	
	// LISTNERS
	
	private class inboxListner implements ListSelectionListener{
		public void messageSelected(ListSelectionEvent e){
			int select = inboxList.getSelectedIndex();
			if(select >=0){
				String message = (String)inboxList.getSelectedValue();
				viewMessageText.setText(message);
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			messageSelected(e);
			
		}

	}
		
		


	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
		

		
	}


	}
		
