package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.app.ShoppingCentre;
import no.hist.aitel.team12.util.Text;

public class CentreView extends JPanel {
	
	private JPanel info;
	private JPanel cDescription;
	
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
	private JLabel cDescript;
	private JButton contactCS;
	
	private static final long serialVersionUID = -4615331862168657390L;

	public CentreView() {
		
		info = new JPanel();
		
		setLayout(new GridLayout(7,2));
		cNameLabel = new JLabel();
		cName = new JLabel();
		info.add(cNameLabel);
		info.add(cName);
		cAdrLabel = new JLabel();
		cAdr = new JLabel();
		info.add(cAdrLabel);
		info.add(cAdr);
		cOpenLabel = new JLabel();
		cOpen = new JLabel();
		info.add(cOpenLabel);
		info.add(cOpen);
		cParkLabel = new JLabel();
		cPark = new JLabel();
		info.add(cParkLabel);
		info.add(cPark);
		cAreaLabel = new JLabel();
		cArea = new JLabel();
		info.add(cAreaLabel);
		info.add(cArea);
		cEmailLabel = new JLabel();
		cEmail = new JLabel();
		info.add(cEmailLabel);
		info.add(cEmail);
		cPhoneLabel = new JLabel();
		cPhone = new JLabel();
		info.add(cPhoneLabel);
		info.add(cPhone);
		
		add(info,BorderLayout.NORTH);
		
		cDescription = new JPanel();
		cDescription.setLayout(new BorderLayout());
		cDescriptLabel = new JLabel();
		cDescript = new JLabel();
		cDescription.add(cDescriptLabel,BorderLayout.WEST);
		cDescription.add(cDescript,BorderLayout.CENTER);
		
		add(cDescription,BorderLayout.CENTER);
		
		contactCS = new JButton();
		add(contactCS, BorderLayout.SOUTH);
		

	}

	public void updateCard(ShoppingCentre centre) {
		cNameLabel.setText(Text.getString("name")+":");
		cName.setText(centre.getBusinessName());
		cAdrLabel.setText(Text.getString("adr")+":");
		cAdr.setText(centre.getAddress().toString());
		cOpenLabel.setText(Text.getString("openingHrs")+":");
		cOpen.setText(centre.getOpeningHours());
		cParkLabel.setText(Text.getString("park")+":");
		cPark.setText(""+ centre.getParkingSpaces());
		cAreaLabel.setText(Text.getString("area")+":");
		cArea.setText(""+centre.getArea());
		cEmailLabel.setText(Text.getString("email")+":");
		cEmail.setText(""+centre.getEmail().getEmailAddress());
		cPhoneLabel.setText(Text.getString("tel")+":");
		cPhone.setText(""+centre.getTelephone());
		cDescriptLabel.setText(Text.getString("textDescription")+":");
		cDescript.setText(centre.getDescription());
		contactCS.setText(Text.getString("contactCS"));
		

		
	}
}
