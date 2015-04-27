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
 * EstablishmentView.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

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
	private JTextArea eDescript;
	private JScrollPane scrollDescript;
	private Box topBox;
	private Box eNameBox;
	private Box eAdrBox;
	private Box eOpenBox;
	private Box eFloorBox;
	private Box eTradeBox;
	private Box eEmailBox;
	private Box ePhoneBox;
	private Box eDescriptBox;

	private static final long serialVersionUID = 8193973009955888884L;

	public EstablishmentView() {

		eNameBox = new Box(BoxLayout.X_AXIS); 
		eNameLabel = new JLabel(Text.getString("name")+":");
		eNameLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		eNameLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		eName = new JLabel();
		eNameBox.add(eNameLabel);
		eNameBox.add(Box.createGlue());
		eNameBox.add(eName);
		
		eAdrBox = new Box(BoxLayout.X_AXIS);
		eAdrLabel = new JLabel(Text.getString("adr")+":");
		eAdrLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		eAdrLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		eAdr = new JLabel();
		eAdrBox.add(eAdrLabel);
		eAdrBox.add(Box.createGlue());
		eAdrBox.add(eAdr);
		
		eOpenBox = new Box(BoxLayout.X_AXIS);
		eOpenLabel = new JLabel(Text.getString("openingHrs")+":");
		eOpenLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		eOpenLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		eOpen = new JLabel();
		eOpenBox.add(eOpenLabel);
		eOpenBox.add(Box.createGlue());
		eOpenBox.add(eOpen);
		
		eFloorBox = new Box(BoxLayout.X_AXIS);
		eFlrLabel = new JLabel(Text.getString("floor")+":");
		eFlrLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		eFlrLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		eFlr = new JLabel();
		eFloorBox.add(eFlrLabel);
		eFloorBox.add(Box.createGlue());
		eFloorBox.add(eFlr);
		
		eTradeBox = new Box(BoxLayout.X_AXIS);
		eTradeLabel = new JLabel(Text.getString("tradeType")+":");
		eTradeLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		eTradeLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		eTrade = new JLabel();
		eTradeBox.add(eTradeLabel);
		eTradeBox.add(Box.createGlue());
		eTradeBox.add(eTrade);
		
		eEmailBox = new Box(BoxLayout.X_AXIS);
		eEmailLabel = new JLabel(Text.getString("email")+":");
		eEmailLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		eEmailLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		eEmail = new JLabel();
		eEmailBox.add(eEmailLabel);
		eEmailBox.add(Box.createGlue());
		eEmailBox.add(eEmail);
		
		ePhoneBox = new Box(BoxLayout.X_AXIS);
		ePhoneLabel = new JLabel(Text.getString("tel")+":");
		ePhoneLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		ePhoneLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		ePhone = new JLabel();
		ePhoneBox.add(ePhoneLabel);
		ePhoneBox.add(Box.createGlue());
		ePhoneBox.add(ePhone);
		
		eDescriptBox = new Box(BoxLayout.X_AXIS);
		eDescriptLabel = new JLabel(Text.getString("textDescription")+":");
		eDescriptLabel.setBorder(new EmptyBorder(0, 20, 0 ,0 ));
		eDescriptLabel.setPreferredSize(new Dimension(200,(int)getPreferredSize().getHeight()));
		eDescript = new JTextArea(10,65);
		eDescript.setLineWrap(true);
		eDescript.setWrapStyleWord(true);
		eDescript.setEditable(false);
		eDescript.setBorder(new EmptyBorder(5, 5, 5 ,5));
		scrollDescript = new JScrollPane(eDescript);
		scrollDescript.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		eDescriptBox.add(eDescriptLabel);
		eDescriptBox.add(Box.createGlue());
		eDescriptBox.add(scrollDescript);
		
		topBox = new Box(BoxLayout.Y_AXIS);
		topBox.add(eNameBox);
		topBox.add(eAdrBox);
		topBox.add(eOpenBox);
		topBox.add(eFloorBox);
		topBox.add(eTradeBox);
		topBox.add(eEmailBox);
		topBox.add(ePhoneBox);
		topBox.add(eDescriptBox);
		
		setLayout(new BorderLayout());
		add(topBox,BorderLayout.WEST);
	}

	public void updateCard(Establishment estab) {
		
		eName.setText(estab.getBusinessName());
		if(estab.getOpeningHours() != null){
		
			String openingHours =estab.getOpeningHours();
			eOpen.setText("("+openingHours.substring(0,2)+"-"+openingHours.substring(2,4)+"("+openingHours.substring(4,6)+"-"+openingHours.substring(6,8)+"))");
		}
		
		if(estab.getSelectedTrades() != null) {
			String trades = "";
			for(Trade t : estab.getSelectedTrades()){
				trades += t.toString() + ", ";
			}
			if(!trades.isEmpty()){
				trades = trades.substring(0,trades.length()-2);
			}
			eTrade.setText(trades);
		}
		else {
			eTrade.setText("");
		}
		
		eEmail.setText(estab.getEmail().getEmailAddress());
		ePhone.setText(""+estab.getTelephone());
		eAdr.setText(estab.getAddress().toShortString());
		eFlr.setText(""+estab.getFloorNumber());
		eDescript.setText(estab.getDescription());
		scrollDescript.getVerticalScrollBar().setValue(0);
	}
}
