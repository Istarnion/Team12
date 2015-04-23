package no.hist.aitel.team12.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.Trade;
import no.hist.aitel.team12.util.Text;

public class EstablishmentView extends JPanel {
	
	private JLabel eNameLabel;
	private JLabel eName;
	private JLabel eOpenLabel;
	private JLabel eOpen;
	private JLabel eTradeLabel;
	private JLabel eTrade;
	private JLabel eEmailLabel;
	private JLabel eEmail;
	private JLabel ePhoneLabel;
	private JLabel ePhone;
	private JLabel eAdrLabel;
	private JLabel eAdr;
	private JLabel eFlrLabel;
	private JLabel eFlr;
	private JLabel eDescriptLabel;
	private JLabel eDescript;
	
	private JButton contactCS;
	
	private static final long serialVersionUID = 8193973009955888884L;

	public EstablishmentView() {
		eNameLabel = new JLabel();
		eName = new JLabel();
		
		eOpenLabel = new JLabel();
		eOpen = new JLabel();
		
		eTradeLabel = new JLabel();
		eTrade = new JLabel();
		
		eEmailLabel = new JLabel();
		eEmail = new JLabel();
		
		ePhoneLabel = new JLabel();
		ePhone = new JLabel();
		
		eAdrLabel = new JLabel();
		eAdr = new JLabel();
		
		eFlrLabel = new JLabel();
		eFlr = new JLabel();
		
		eDescriptLabel = new JLabel(); 
		eDescript = new JLabel();
		
		contactCS = new JButton();
		
		add(eNameLabel);
		add(eName);
		add(eOpenLabel);
		add(eOpen);
		add(eTradeLabel);
		add(eTrade);
		add(eEmailLabel);
		add(eEmail);
		add(ePhoneLabel);
		add(ePhone);
		add(eAdrLabel);
		add(eAdr);
		add(eFlrLabel);
		add(eFlr);
		add(eDescriptLabel);
		add(eDescript);
		add(contactCS);
		
		
	}

	public void updateCard(Establishment estab) {
		eNameLabel.setText(Text.getString("name")+":");
		eName.setText(estab.getBusinessName());
		
		eOpenLabel.setText(Text.getString("openingHrs")+":");
		eOpen.setText(estab.getOpeningHours());
		
		eTradeLabel.setText(Text.getString("tradeType")+":");
		String trades = "";
		for(Trade t : estab.getSelectedTrades()){
			trades += t.toString() + ", ";
			
		}
		 if(!trades.isEmpty()){
			 trades = trades.substring(0,trades.length()-2);
		 }
		 eTrade.setText(trades);
		 
		 eEmailLabel.setText(Text.getString("email")+":");
		 eEmail.setText(estab.getEmail().getEmailAddress());
		 
		 ePhoneLabel.setText(Text.getString("tel")+":");
		 ePhone.setText(""+estab.getTelephone());
		 
		 eAdrLabel.setText(Text.getString("adr")+":");
		 eAdr.setText(estab.getAddress().toString());
		 
		 eFlrLabel.setText(Text.getString("floor")+":");
		 eFlr.setText(""+estab.getFloorNumber());
		 
		 eDescriptLabel.setText(Text.getString("textDescription")+":");
		 eDescript.setText(estab.getDescription());
		 
		 contactCS.setText(Text.getString("contactCS"));
	}
}
