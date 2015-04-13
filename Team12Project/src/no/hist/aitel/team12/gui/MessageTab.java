
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

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
		
		viewMessageText = new JTextArea();
		viewMessageScroll = new JScrollPane(viewMessageText);
		viewMessageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		viewMessagePanel.setLayout(new BorderLayout());
		viewMessagePanel.add(viewMessageScroll, BorderLayout.CENTER);
		viewMessageText.setEditable(false);
		
		sendMessageText = new JTextArea();
		sendMessageScroll = new JScrollPane(sendMessageText);
		sendMessageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		sendMessagePanel.setLayout(new BorderLayout());
		sendMessagePanel.add(sendMessageScroll, BorderLayout.CENTER);
		sendMessageText.setEditable(true);
		
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
		
