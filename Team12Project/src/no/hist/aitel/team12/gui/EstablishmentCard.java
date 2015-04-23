package no.hist.aitel.team12.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.hist.aitel.team12.app.Establishment;
import no.hist.aitel.team12.app.Trade;
import no.hist.aitel.team12.util.Text;

public class EstablishmentCard extends BusinessCard {

	private static final long serialVersionUID = 9040445246335124938L;
	private JButton tradesButtonRight, tradesButtonLeft, tradeButton, floorButton;
	private JPanel tradesPanel;
	private ButtonListener buttonListener;
	private ListListener listListener;
	private JScrollPane tradesLeftScroll, tradesRightScroll;
	private Establishment establishment;
	private JList<Trade> tradesLeftList, tradesRightList;
	private ArrayList<Trade> allTrades;
	private JTextField floor;
	private JLabel floorLabel;

	public EstablishmentCard(int userID) {

		allTrades = Trade.getAllTrades();

		buttonListener 		= new ButtonListener();
		listListener 		= new ListListener();

		tradesPanel			= new JPanel(new GridBagLayout());		
		tradesPanel.setPreferredSize(new Dimension(1,200));	
		tradeButton			= new JButton(Text.getString("trades"));	
		tradesButtonLeft	= new JButton("\u21E6");		
		tradesButtonRight	= new JButton("\u21E8");
		tradesButtonLeft.setPreferredSize(new Dimension(60, 23));
		tradesButtonRight.setPreferredSize(new Dimension(60, 23));
		tradesLeftList			= new JList<Trade>();
		tradesRightList			= new JList<Trade>();
		tradesLeftScroll	 	= new JScrollPane(tradesLeftList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tradesRightScroll 		= new JScrollPane(tradesRightList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tradesLeftScroll.setPreferredSize(new Dimension(225, tradesLeftScroll.getPreferredSize().height));
		tradesRightScroll.setPreferredSize(new Dimension(225, tradesRightScroll.getPreferredSize().height));

		textDescriptionScroll	= new JScrollPane(textDescription, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textDescriptionScroll.setPreferredSize(new Dimension(0, 200));	

		floorLabel = new JLabel(Text.getString("floor") + ": ", SwingConstants.RIGHT);
		floor = new JTextField("");
		floorButton = new JButton(Text.getString("edit"));
		floorButton.setPreferredSize(new Dimension(72, 23));

		tradesRightList.addListSelectionListener(listListener);
		tradesLeftList.addListSelectionListener(listListener);

		businessButton.addActionListener(buttonListener);
		addressButton.addActionListener(buttonListener);
		emailButton.addActionListener(buttonListener);
		telephoneButton.addActionListener(buttonListener);
		openingHrsButton.addActionListener(buttonListener);
		textDescrButton.addActionListener(buttonListener);
		tradeButton.addActionListener(buttonListener);
		zipButton.addActionListener(buttonListener);
		floorButton.addActionListener(buttonListener);
		tradesButtonLeft.addActionListener(buttonListener);
		tradesButtonRight.addActionListener(buttonListener);


		// Grid bag layout
		GridBagConstraints constraints = new GridBagConstraints();

		// Setting some margins for a bit of space between each field/button/label
		constraints.insets.bottom = 5;
		constraints.insets.top = 5;


		// floor grid bag
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(floorLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(floor, constraints);

		constraints.gridx = 4;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		this.add(floorButton, constraints);

		// description grid bag setup
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		this.add(textDescrLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(textDescriptionScroll, constraints);

		constraints.gridx = 4;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		this.add(textDescrButton, constraints);

		// trades grid bag setup 
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.01;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(tradesPanel, constraints);

		// tradesPanel internal grid bag setup
		constraints = new GridBagConstraints();

		int margin = 10;
		Insets noInsets		= new Insets(0, 0, 0, 0);
		Insets leftInsets	= new Insets(0, 0, 0, margin);
		Insets rightInsets	= new Insets(0, margin, 0, 0);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.weightx = 0.20;
		constraints.weighty = 1;
		constraints.insets = leftInsets;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		constraints.fill = GridBagConstraints.VERTICAL;
		tradesPanel.add(tradesLeftScroll, constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.20;
		constraints.weighty = 0.001;
		constraints.insets = noInsets;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.NONE;
		tradesPanel.add(tradesButtonLeft, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.20;
		constraints.weighty = 0.001;
		constraints.anchor = GridBagConstraints.NORTH;
		tradesPanel.add(tradesButtonRight, constraints);

		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.weightx = 0.20;
		constraints.weighty = 1;
		constraints.insets = rightInsets;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.VERTICAL;
		tradesPanel.add(tradesRightScroll, constraints);
	}

	public void updateCard(Establishment establishment) {
		this.establishment = establishment;

		DefaultListModel<Trade> selectedListModel = new DefaultListModel<Trade>();

		tradesLeftList.setModel(selectedListModel);

		DefaultListModel<Trade> availableListModel = new DefaultListModel<Trade>();
		boolean foundMatch = false;
		for(Trade aT : allTrades) {
			foundMatch = false;
			for(Trade sT : establishment.getSelectedTrades()) {

				if(sT.equals(aT)) {
					foundMatch = true;
					selectedListModel.addElement(sT);
					break;
				}
			}
			if(!foundMatch) {
				availableListModel.addElement(aT);
			}

		}
		tradesRightList.setModel(availableListModel);


		businessName.setText(establishment.getBusinessName());
		address.setText(establishment.getAddress().getAdress());
		email.setText(establishment.getEmail().getEmailAddress());
		telephone.setText(String.valueOf(establishment.getTelephone()));
		String openingHrs = establishment.getOpeningHours();
		openingTextField1.setText(openingHrs.substring(0, 2));
		openingTextField2.setText(openingHrs.substring(2, 4));
		openingTextField3.setText(openingHrs.substring(4, 6));
		openingTextField4.setText(openingHrs.substring(6, 8));
		textDescription.setText(establishment.getDescription());
		zip.setText(String.valueOf(establishment.getAddress().getZipcode()));
		floor.setText(String.valueOf(establishment.getFloorNumber()));

		businessName.setEditable(false);
		address.setEditable(false);
		email.setEditable(false);
		telephone.setEditable(false);
		openingTextField1.setEditable(false);
		openingTextField2.setEditable(false);
		openingTextField3.setEditable(false);
		openingTextField4.setEditable(false);
		textDescription.setEditable(false);
		zip.setEditable(false);
		floor.setEditable(false);

		businessButton.setText(Text.getString("edit"));
		addressButton.setText(Text.getString("edit"));
		emailButton.setText(Text.getString("edit"));
		telephoneButton.setText(Text.getString("edit"));
		openingHrsButton.setText(Text.getString("edit"));
		textDescrButton.setText(Text.getString("edit"));
		zipButton.setText(Text.getString("edit"));
		floorButton.setText(Text.getString("edit"));
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton pressedButton = (JButton) event.getSource();
			if(pressedButton.equals(businessButton)) {

				if(businessButton.getText().equals(Text.getString("edit"))) {
					businessName.setEditable(true);
					businessButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setBusinessName(businessName.getText())) {
						return;
					}
					businessName.setEditable(false);
					businessButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(addressButton)) {
				if(addressButton.getText().equals(Text.getString("edit"))) {
					address.setEditable(true);
					addressButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setAddress(address.getText())) {
						return;
					}
					address.setEditable(false);
					addressButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(emailButton)) {
				if(emailButton.getText().equals(Text.getString("edit"))) {
					email.setEditable(true);
					emailButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setEmail(email.getText())) {
						return;
					}
					email.setEditable(false);
					emailButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(telephoneButton)) {
				if(telephoneButton.getText().equals(Text.getString("edit"))) {
					telephone.setEditable(true);
					telephoneButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setTelephone(telephone.getText())) {
						JOptionPane.showMessageDialog(null, Text.getString("invalidTel"));
						return;
					}
					telephone.setEditable(false);
					telephoneButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(openingHrsButton)) {
				if(openingHrsButton.getText().equals(Text.getString("edit"))) {
					openingTextField1.setEditable(true);
					openingTextField2.setEditable(true);
					openingTextField3.setEditable(true);
					openingTextField4.setEditable(true);
					openingHrsButton.setText(Text.getString("save"));
				}
				else {
					String newValue = 
							openingTextField1.getText() + 
							openingTextField2.getText() + 
							openingTextField3.getText() + 
							openingTextField4.getText();
					if(newValue.length() != 8) {
						JOptionPane.showMessageDialog(null, Text.getString("invalidHrs"));
						return;
					}
					if(!establishment.setOpeningHours(newValue)) {
						return;
					}
					openingTextField1.setEditable(false);
					openingTextField2.setEditable(false);
					openingTextField3.setEditable(false);
					openingTextField4.setEditable(false);
					openingHrsButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(textDescrButton)) {
				if(textDescrButton.getText().equals(Text.getString("edit"))) {
					textDescription.setEditable(true);
					textDescrButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setDescription(textDescription.getText())) {
						return;
					}
					textDescription.setEditable(false);
					textDescrButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(zipButton)) {
				if(zipButton.getText().equals(Text.getString("edit"))) {
					zip.setEditable(true);
					zipButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setZipcode(zip.getText())) {
						return;
					}
					zip.setEditable(false);
					zipButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(floorButton)) {
				if(floorButton.getText().equals(Text.getString("edit"))) {
					floor.setEditable(true);
					floorButton.setText(Text.getString("save"));
				}
				else {
					if(!establishment.setFloorNumber(floor.getText())) {
						return;
					}
					floor.setEditable(false);
					floorButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(tradesButtonRight)) {
				if(!tradesLeftList.isSelectionEmpty()) {
					if(establishment.removeTrade(tradesLeftList.getSelectedValue().getTradeId())) {
						Trade tradeToMove = tradesLeftList.getSelectedValue();
						DefaultListModel listModel = (DefaultListModel) tradesLeftList.getModel();
						listModel.remove(tradesLeftList.getSelectedIndex());
						tradesLeftList.setModel(listModel);

						listModel = (DefaultListModel) tradesRightList.getModel();
						listModel.addElement(tradeToMove);
						tradesRightList.setModel(listModel);
					}
					else {
						JOptionPane.showMessageDialog(null, "Database not responding");
					}
				}
			}
			else if(pressedButton.equals(tradesButtonLeft)) {
				if(!tradesRightList.isSelectionEmpty()) {
					if(establishment.addTrade(tradesRightList.getSelectedValue().getTradeId())) {
						Trade tradeToMove = tradesRightList.getSelectedValue();
						DefaultListModel listModel = (DefaultListModel) tradesRightList.getModel();
						listModel.remove(tradesRightList.getSelectedIndex());
						tradesRightList.setModel(listModel);

						listModel = (DefaultListModel) tradesLeftList.getModel();
						listModel.addElement(tradeToMove);
						tradesLeftList.setModel(listModel);
					}
					else {
						JOptionPane.showMessageDialog(null, "Database not responding");
					}
				}
			}
		}
	}

	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			if(!event.getValueIsAdjusting()) {
				if(event.getSource().equals(tradesLeftList)) {
					if(tradesLeftList.getSelectedIndex()>=0) {
						tradesRightList.clearSelection();
					}
				}
				else {
					if(tradesRightList.getSelectedIndex()>=0) {
						tradesLeftList.clearSelection();
					}
				}
			}
		}
	}
}


