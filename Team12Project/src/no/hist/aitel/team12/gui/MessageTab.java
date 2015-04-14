
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.hist.aitel.team12.app.Message;
import no.hist.aitel.team12.util.Text;



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
	
	private Message[] messages;
	
	private ArrayList<Message> outbox;
	private ArrayList<Message> inbox;
	private ArrayList<Message> trash;

	//private static final String [] meldinger ={"Kalle Kallesen - Årsfest brio", "James Bond - Nattåpent desember", "Dr. Dre - styremøte kommende torsdag"," Kari UtenTraaa - Åpningstider i julen", "Lols Mc. Lolsen - test blalalbv","Kaptein Sabeltann -test slutt"};

	private JList<Message> inboxList;
	
	private JScrollPane scrollInbox = new JScrollPane(inboxList);

	
	public MessageTab(String username) {
		
		outbox = new ArrayList<Message>();
		inbox = new ArrayList<Message>();
		trash = new ArrayList<Message>();


		messages = Message.getUserMessages(username);
		for(Message m : messages) {
			if(m.isDeleted()) {
				trash.add(m);
			}
			else if(m.getSender() == username) {
				outbox.add(m);
			}
			else {
				inbox.add(m);
			}
		}
		inboxList = new JList<Message>(messages);
		
		setLayout(new BorderLayout());
		
		add(inboxArea,BorderLayout.WEST);
		inboxArea.setLayout(new BorderLayout());
		inboxArea.add(scrollInbox,BorderLayout.CENTER);
		inboxList.addListSelectionListener(new inboxListner());
		add(comboArea,BorderLayout.CENTER);

		// Colors for debugging
		//		inboxArea.setBackground(Color.BLUE);
		//		comboArea.setBackground(Color.RED);
		//		viewMessagePanel.setBackground(Color.PINK);
		//		sendMessagePanel.setBackground(Color.CYAN);

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
		constraintsViewMsg.gridwidth = 1;
		constraintsViewMsg.gridheight = 1;
		constraintsViewMsg.weightx = 0.95;
		constraintsViewMsg.weighty = .035;
		constraintsViewMsg.fill = GridBagConstraints.HORIZONTAL;
		viewMessagePanel.add(from,constraintsViewMsg);

		// Button for replying to message
		JButton reply = new JButton(Text.getString("reply"));
		constraintsViewMsg.gridx = 1;
		constraintsViewMsg.gridy = 0;
		constraintsViewMsg.gridwidth = 1;
		constraintsViewMsg.gridheight = 1;
		constraintsViewMsg.weightx = 0.05;
		constraintsViewMsg.weighty = .025;
		viewMessagePanel.add(reply, constraintsViewMsg);

		// Field for message subject
		JTextField subject = new JTextField();
		constraintsViewMsg.gridx = 0;
		constraintsViewMsg.gridy = 1;
		constraintsViewMsg.gridwidth = 2;
		constraintsViewMsg.gridheight = 1;
		constraintsViewMsg.weightx = 1;
		constraintsViewMsg.weighty = .025;
		constraintsViewMsg.fill = GridBagConstraints.HORIZONTAL;
		viewMessagePanel.add(subject, constraintsViewMsg);

		// Field for viewing selected message
		constraintsViewMsg.gridx = 0;
		constraintsViewMsg.gridy = 2;
		constraintsViewMsg.gridwidth = 2;
		constraintsViewMsg.gridheight = 1;
		constraintsViewMsg.weightx = 1;
		constraintsViewMsg.weighty = .95;
		constraintsViewMsg.fill = GridBagConstraints.BOTH;
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
		constraintsSendMsg.gridx = 0;
		constraintsSendMsg.gridy = 0;
		constraintsSendMsg.gridwidth = 1;
		constraintsSendMsg.gridheight = 1;
		constraintsSendMsg.weightx = 0.95;
		constraintsSendMsg.weighty = .035;
		constraintsSendMsg.fill = GridBagConstraints.HORIZONTAL;
		sendMessagePanel.add(to,constraintsSendMsg);

		// Button for replying to message
		JButton send = new JButton(Text.getString("send"));
		constraintsSendMsg.gridx = 1;
		constraintsSendMsg.gridy = 0;
		constraintsSendMsg.gridwidth = 1;
		constraintsSendMsg.gridheight = 1;
		constraintsSendMsg.weightx = 0.05;
		constraintsSendMsg.weighty = .025;
		sendMessagePanel.add(send, constraintsSendMsg);

		// Field for message subject
		JTextField subjectto = new JTextField();
		constraintsSendMsg.gridx = 0;
		constraintsSendMsg.gridy = 1;
		constraintsSendMsg.gridwidth = 2;
		constraintsSendMsg.gridheight = 1;
		constraintsSendMsg.weightx = 1;
		constraintsSendMsg.weighty = .025;
		constraintsSendMsg.fill = GridBagConstraints.HORIZONTAL;
		sendMessagePanel.add(subjectto, constraintsSendMsg);

		// Field for viewing selected message
		constraintsSendMsg.gridx = 0;
		constraintsSendMsg.gridy = 2;
		constraintsSendMsg.gridwidth = 2;
		constraintsSendMsg.gridheight = 1;
		constraintsSendMsg.weightx = 1;
		constraintsSendMsg.weighty = .95;
		constraintsSendMsg.fill = GridBagConstraints.BOTH;
		sendMessagePanel.add(sendMessageScroll, constraintsSendMsg);
		sendMessageText.setEditable(true);

	}

	// LISTNERS

	private class inboxListner implements ListSelectionListener{
		public void messageSelected(ListSelectionEvent e){
			int select = inboxList.getSelectedIndex();
			if(select >=0){
				Message message = inboxList.getSelectedValue();
				viewMessageText.setText(message.getContent());
			}
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			messageSelected(e);

		}

	}




	@Override
	public void refresh() {
	}



}

