
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;



import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class MessageTab extends SSSTab {
	
	private static final long serialVersionUID = 7021415739968091789L;

	
	private JPanel inboxArea = new JPanel();
	private JPanel comboArea = new JPanel();
	private JPanel viewMessage = new JPanel();
	private JTextArea viewMessageText = new JTextArea(18,172);
	private JScrollPane viewMessageScroll = new JScrollPane();
	private JPanel sendMessage = new JPanel();
	private JTextArea sendMessageText = new JTextArea(18,172);
	private JScrollPane sendMessageScroll = new JScrollPane(sendMessageText);
	
	
	private static final String [] meldinger ={"Kalle Kallesen - Årsfest brio", "James Bond - Nattåpent desember", "Dr. Dre - styremøte kommende torsdag"," Kari UtenTraaa - Åpningstider i julen", "Lols Mc. Lolsen - test blalalbv","Kaptein Sabeltann -test slutt"};

	
	// SuppressWarning added by eclipse to remove error message when running
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JList inboxList = new JList(meldinger);
	private JScrollPane scrollInbox = new JScrollPane(inboxList);
	
	
	

	
	public MessageTab() {
		
		setLayout(new BorderLayout());
		inboxArea.setBackground(Color.BLUE);
		comboArea.setBackground(Color.RED);
		add(inboxArea,BorderLayout.WEST);
		inboxArea.add(scrollInbox,BorderLayout.CENTER);
		inboxList.addListSelectionListener(new inboxListner());
		add(comboArea,BorderLayout.CENTER);
		
		comboArea.setLayout(new BorderLayout());
		comboArea.add(viewMessage, BorderLayout.NORTH);
		comboArea.add(sendMessage,BorderLayout.SOUTH);
		viewMessage.setBackground(Color.GREEN);
		sendMessage.setBackground(Color.GRAY);
		
		viewMessage.add(viewMessageText, BorderLayout.CENTER);
		viewMessageText.setEditable(false);
		viewMessage.add(viewMessageScroll);
		
		sendMessage.add(sendMessageText,BorderLayout.CENTER);
		sendMessageText.setEditable(true);
		comboArea.add(sendMessageScroll);
		sendMessageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		
		
		
	}
	
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
		
