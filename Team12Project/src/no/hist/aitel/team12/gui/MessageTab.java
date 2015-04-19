
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.hist.aitel.team12.app.DataBuffer;
import no.hist.aitel.team12.app.Message;
import no.hist.aitel.team12.database.Database;
import no.hist.aitel.team12.database.DatabaseFactory;
import no.hist.aitel.team12.util.Text;



public class MessageTab extends SSSTab {

	private static final long serialVersionUID = 7021415739968091789L;

	private JTabbedPane inboxArea = new JTabbedPane();

	private JPanel inboxPanel, outboxPanel, trashPanel;

	private JPanel comboArea = new JPanel();

	private JPanel viewMessagePanel = new JPanel();

	private JTextArea viewMessageText;

	private JScrollPane viewMessageScroll;

	private JPanel sendMessagePanel = new JPanel();

	private JTextArea sendMessageText;

	private JScrollPane sendMessageScroll;

	private JTextField
	subject, subjectto,
	from, to;

	private Message[] messages;

	private ArrayList<Message> outbox;

	private ArrayList<Message> inbox;

	private ArrayList<Message> trash;

	private JList<Message> inboxList, outboxList, trashList;

	private JScrollPane scrollInbox, scrollOutbox, scrollTrash;

	private String username;

	public MessageTab(String username) {
		this.username = username;

		outbox	= new ArrayList<Message>();
		inbox	= new ArrayList<Message>();
		trash	= new ArrayList<Message>();

		messages = DataBuffer.getMessages();
		for(Message m : messages) {
			if(m.isDeleted()) {
				trash.add(m);
			}
			else if(m.getSender().equals(username)) {
				outbox.add(m);
			}
			else {
				inbox.add(m);
			}
		}

		setupGUI();

		Timer timer = new Timer(5000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}

		});
		timer.start();
	}

	private void setupGUI() {
		inboxArea.setTabPlacement(JTabbedPane.BOTTOM);

		inboxList = new JList<Message>(inbox.toArray(new Message[outbox.size()]));
		inboxList.addListSelectionListener(new inboxListner());

		scrollInbox = new JScrollPane(inboxList);

		inboxPanel = new JPanel();
		inboxPanel.setLayout(new BorderLayout());
		inboxPanel.add(scrollInbox,BorderLayout.CENTER);

		outboxList = new JList<Message> (outbox.toArray(new Message[outbox.size()]));
		outboxList.addListSelectionListener(new inboxListner());

		scrollOutbox = new JScrollPane(outboxList);

		outboxPanel = new JPanel();
		outboxPanel.setLayout(new BorderLayout());
		outboxPanel.add(scrollOutbox, BorderLayout.CENTER);

		trashList = new JList<Message> (trash.toArray(new Message[outbox.size()]));
		trashList.addListSelectionListener(new inboxListner());

		scrollTrash = new JScrollPane(trashList);

		trashPanel = new JPanel();
		trashPanel.setLayout(new BorderLayout());
		trashPanel.add(scrollTrash, BorderLayout.CENTER);

		inboxArea.add(inboxPanel, "inbox");
		inboxArea.add(outboxPanel, "outbox");
		inboxArea.add(trashPanel, "trash");

		setLayout(new BorderLayout());
		add(inboxArea,BorderLayout.WEST);

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
		from = new JTextField();
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
		reply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!from.getText().isEmpty() && !subject.getText().isEmpty()) {
					to.setText(from.getText());
					subjectto.setText("RE: "+subject.getText());
				}
			}

		});
		constraintsViewMsg.gridx = 1;
		constraintsViewMsg.gridy = 0;
		constraintsViewMsg.gridwidth = 1;
		constraintsViewMsg.gridheight = 1;
		constraintsViewMsg.weightx = 0.05;
		constraintsViewMsg.weighty = .025;
		viewMessagePanel.add(reply, constraintsViewMsg);

		// Field for message subject
		subject = new JTextField();
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
		to = new JTextField();
		constraintsSendMsg.gridx = 0;
		constraintsSendMsg.gridy = 0;
		constraintsSendMsg.gridwidth = 1;
		constraintsSendMsg.gridheight = 1;
		constraintsSendMsg.weightx = 0.95;
		constraintsSendMsg.weighty = .035;
		constraintsSendMsg.fill = GridBagConstraints.HORIZONTAL;
		sendMessagePanel.add(to,constraintsSendMsg);

		// Button for sending to message
		JButton send = new JButton(Text.getString("send"));
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					boolean messageSent;
					@Override
					protected Void doInBackground() throws Exception {

						Database db = DatabaseFactory.getDatabase();

						if(db.isUserInDb(to.getText()) && Message.sendMessage(username, to.getText().split(","), sendMessageText.getText(), subjectto.getText())){
							JOptionPane.showMessageDialog(null, Text.getString("msgConfirmation"));
							messageSent = true;
						}
						else {
							JOptionPane.showMessageDialog(null, Text.getString("err"));
							messageSent = false;
						}
						return null;
					}

					@Override
					protected void done() {
						if(messageSent) {
							to.setText("");
							sendMessageText.setText("");
							subjectto.setText("");
							refresh();
						}
						send.setEnabled(true);
						send.setText(Text.getString("send"));
					}

				};


				send.setEnabled(false);
				send.setText("sending...");
				worker.execute();


			}
		});

		constraintsSendMsg.gridx = 1;
		constraintsSendMsg.gridy = 0;
		constraintsSendMsg.gridwidth = 1;
		constraintsSendMsg.gridheight = 1;
		constraintsSendMsg.weightx = 0.05;
		constraintsSendMsg.weighty = .025;
		sendMessagePanel.add(send, constraintsSendMsg);

		// Field for message subject
		subjectto = new JTextField();
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
		public void messageSelected(Message m){
			viewMessageText.setText(m.getContent());
			from.setText(m.getSender());
			subject.setText(m.getSubject());
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			JList<?> source = (JList<?>) e.getSource();
			Message msg = (Message)source.getSelectedValue();
			if(msg != null) messageSelected(msg);
		}
	}

	@Override
	public void refresh() {
		DefaultListModel<Message> inboxModel = new DefaultListModel<Message>();
		DefaultListModel<Message> outboxModel = new DefaultListModel<Message>();
		DefaultListModel<Message> trashModel = new DefaultListModel<Message>();

		messages = DataBuffer.getMessages();
		for(Message m : messages) {
			if(m.isDeleted()) {
				trashModel.addElement(m);
			}
			else if(m.getSender().equals(username)) {
				outboxModel.addElement(m);
			}
			else {
				inboxModel.addElement(m);
			}
		}


		inboxList.setModel(inboxModel);
		outboxList.setModel(outboxModel);
		trashList.setModel(trashModel);
	}
}

