package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.util.Text;

public class CentreView extends JPanel {
	
	private Box	mainBox;
	private Box cAdrBox;
	private Box cNameBox;
	private Box cOpenBox;
	private Box cParkBox;
	private Box cAreaBox;
	private Box cEmailBox;
	private Box cPhoneBox;
	private Box cDescriptBox;
	private Box contactCSBox;
	
	private JLabel cAdrLabel;
	private JLabel cAdr;
	private JLabel cNameLabel;
	private JLabel cName;
	private JLabel cOpenLabel;
	private JLabel cOpen;
	private JLabel cPark;
	private JLabel cParkLabel;
	private JLabel cAreaLabel;
	private JLabel cArea;
	private JLabel cEmailLabel;
	private JLabel cEmail;
	private JLabel cPhoneLabel;
	private JLabel cPhone;
	private JLabel cDescriptLabel;
	private JLabel contactCSLabel;
	
	private JTextArea cDescript;
	
	private JScrollPane scroll;
	
	private JButton contactCS;
	
	private static final long serialVersionUID = -4615331862168657390L;

	public CentreView() {
		
		
		cNameBox = new Box(BoxLayout.X_AXIS);
		cNameLabel = new JLabel(Text.getString("name")+":");
		cNameLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cName = new JLabel();
		cNameBox.add(cNameLabel);
		cNameBox.add(Box.createGlue());
		cNameBox.add(cName);
		
		
		cAdrBox = new Box(BoxLayout.X_AXIS);
		cAdrLabel = new JLabel((Text.getString("adr")+":"));
		cAdrLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cAdr = new JLabel();
		cAdrBox.add(cAdrLabel);
		cAdrBox.add(Box.createGlue());
		cAdrBox.add(cAdr);
		
		cOpenBox = new Box(BoxLayout.X_AXIS);
		cOpenLabel = new JLabel(Text.getString("openingHrs")+":");
		cOpenLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cOpen = new JLabel();
		cOpenBox.add(cOpenLabel);
		cOpenBox.add(Box.createGlue());
		cOpenBox.add(cOpen);
		
		cParkBox = new Box(BoxLayout.X_AXIS);
		cParkLabel = new JLabel(Text.getString("park")+":");
		cParkLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cPark = new JLabel();
		cParkBox.add(cParkLabel);
		cParkBox.add(Box.createGlue());
		cParkBox.add(cPark);
		
		cAreaBox = new Box(BoxLayout.X_AXIS);
		cAreaLabel = new JLabel(Text.getString("area")+":");
		cAreaLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cArea = new JLabel();
		cAreaBox.add(cAreaLabel);
		cAreaBox.add(Box.createGlue());
		cAreaBox.add(cArea);
		
		cEmailBox = new Box(BoxLayout.X_AXIS);
		cEmailLabel = new JLabel(Text.getString("email")+":");
		cEmailLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cEmail = new JLabel();
		cEmailBox.add(cEmailLabel);
		cEmailBox.add(Box.createGlue());
		cEmailBox.add(cEmail);
		
		cPhoneBox = new Box(BoxLayout.X_AXIS);
		cPhoneLabel = new JLabel(Text.getString("tel")+":");
		cPhoneLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cPhone = new JLabel();
		cPhoneBox.add(cPhoneLabel);
		cPhoneBox.add(Box.createGlue());
		cPhoneBox.add(cPhone);
		
		cDescriptBox = new Box(BoxLayout.X_AXIS);
		cDescriptLabel = new JLabel(Text.getString("textDescription")+":");
		cDescriptLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		cDescript = new JTextArea(20,65);
		cDescript.setLineWrap(true);
		cDescript.setWrapStyleWord(true);
		cDescript.setEditable(false);
		scroll = new JScrollPane(cDescript);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		cDescriptBox.add(cDescriptLabel);
		cDescriptBox.add(Box.createGlue());
		cDescriptBox.add(scroll);
		
		
		contactCSBox = new Box(BoxLayout.X_AXIS);
		contactCSLabel = new JLabel();
		contactCSLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		contactCS = new JButton(Text.getString("contactCS"));
		contactCSBox.add(contactCSLabel);
		contactCSBox.add(Box.createGlue());
		contactCSBox.add(contactCS);
		
		mainBox = new Box(BoxLayout.Y_AXIS);
		mainBox.add(cNameBox);
		mainBox.add(cAdrBox);
		mainBox.add(cOpenBox);
		mainBox.add(cParkBox);
		mainBox.add(cAreaBox);
		mainBox.add(cEmailBox);
		mainBox.add(cPhoneBox);
		mainBox.add(cDescriptBox);
		mainBox.add(contactCSBox);
		
		setLayout(new BorderLayout());
		add(mainBox, BorderLayout.WEST);
		
		Buttonlistener pushed = new Buttonlistener();
		contactCS.addActionListener(pushed);
		
		
		

	}
	
	private class Buttonlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public void updateCard(ShoppingCentre centre) {
		cName.setText(centre.getBusinessName());
		cAdr.setText(centre.getAddress().toString());
		cOpen.setText(centre.getOpeningHours());
		cPark.setText(""+ centre.getParkingSpaces());
		cArea.setText(""+centre.getArea());
		cEmail.setText(""+centre.getEmail().getEmailAddress());
		cPhone.setText(""+centre.getTelephone());
		cDescript.setText(centre.getDescription());
		
		

		
	}
}
