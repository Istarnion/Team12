package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CustomerCSView extends SSSTab {
	
	private JPanel contactCs = new JPanel();
	private JPanel info = new JPanel();
	private JPanel message = new JPanel();

	private InputField subject = new InputField("Subject",20);
	private InputField email = new InputField("Your email adress here",20);
	
	private String [] centerNames = {"City syd","City Lade"};
	private JComboBox<String> centerName = new JComboBox<String>(centerNames);
	
	
	private JTextArea supportMessage = new JTextArea("What seems to be the officer problem?");
	
	
	
	private JButton send = new JButton("Send");
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 242612470705756493L;

	public CustomerCSView(){
		contactCs.setLayout(new BoxLayout(contactCs,BoxLayout.Y_AXIS));
		contactCs.setPreferredSize(new Dimension(1200, 675));

		add(contactCs, BorderLayout.CENTER);
		
	// Info panel
		
		info.setLayout(new GridLayout(2,2, 2,5));
	
		info.setPreferredSize(new Dimension(0,50));
		info.add(subject);
		info.add(centerName);
		info.add(email);
		info.add(send);
		
	// Message Area
		
		message.setLayout(new BorderLayout());
		message.setPreferredSize(new Dimension(0,600));
		supportMessage.setEditable(true);
		message.add(supportMessage,BorderLayout.CENTER);
		
	
		
		contactCs.add(info);
		contactCs.add(message);
		
		ButtonListener clicked = new ButtonListener();
		send.addActionListener(clicked);
		
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			/*
			 * getCenter, getSubject. getuserID, get message^
			 * send to database
			 */
			System.out.println("Send button has been pressed");
			
		}
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[]args){
		SSSWindow cv = new SSSWindow();
		System.out.println("SSSWindow added");
		cv.addTab("Customer Service",new CustomerCSView());
		System.out.println("tab added to window");
		cv.showWindow();
		System.out.println("Visible");	
	}

}
