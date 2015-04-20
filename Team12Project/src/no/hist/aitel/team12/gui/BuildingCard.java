package no.hist.aitel.team12.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.util.Text;

public class BuildingCard extends JPanel {

	private static final long serialVersionUID = -3244200076799752084L;

	private JTextField nrOfFloors, area, buildingName;

	private JLabel nrOfFloorsLabel, areaLabel, buildingNameLabel;

	private JButton nrOfFloorsButton, areaButton, buildingNameButton;

	private ButtonListener buttonListener;

	public BuildingCard(int userID) {
		this.setLayout(new GridBagLayout());
		
		buttonListener 		= new ButtonListener();
		
		buildingNameLabel	= new JLabel(Text.getString("name") + ": ", SwingConstants.RIGHT);
		nrOfFloorsLabel 	= new JLabel(Text.getString("nrOfFloors") + ": ", SwingConstants.RIGHT);
		areaLabel 			= new JLabel(Text.getString("area") + ": ", SwingConstants.RIGHT);
		
		buildingName		= new JTextField("");
		nrOfFloors 			= new JTextField("");
		area 				= new JTextField("");
		
		buildingNameButton	= new JButton(Text.getString("edit"));
		nrOfFloorsButton 	= new JButton(Text.getString("edit"));
		areaButton 			= new JButton(Text.getString("edit"));
		
		nrOfFloorsButton.addActionListener(buttonListener);
		areaButton.addActionListener(buttonListener);
		buildingNameButton.addActionListener(buttonListener);
		

		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets.bottom 	= 5;
		constraints.insets.top 		= 5;
		
		constraints.gridx 			= 0;
		constraints.gridy 			= 0;
		constraints.gridwidth 		= 1;
		constraints.gridheight 		= 1;
		constraints.weightx 		= 0.10;
		constraints.weighty 		= 0.0;
		constraints.fill 			= GridBagConstraints.HORIZONTAL;
		
		this.add(buildingNameLabel, constraints);
		
		
		constraints.gridx 			= 1;
		constraints.gridy 			= 0;
		constraints.gridwidth 		= 3;
		constraints.gridheight 		= 1;
		constraints.weightx 		= 0.8;
		constraints.weighty 		= 0.0;
		constraints.fill 			= GridBagConstraints.HORIZONTAL;
		
		this.add(buildingName, constraints);
		
		
		constraints.gridx 			= 4;
		constraints.gridy 			= 0;
		constraints.gridwidth 		= 1;
		constraints.gridheight 		= 1;
		constraints.weightx 		= 0.10;
		constraints.weighty 		= 0.0;
		constraints.fill 			= GridBagConstraints.NONE;
		
		this.add(buildingNameButton, constraints);
		
		
		
		constraints.gridx 			= 0;
		constraints.gridy 			= 1;
		constraints.gridwidth 		= 1;
		constraints.gridheight 		= 1;
		constraints.weightx 		= 0.10;
		constraints.weighty 		= 0.0;
		constraints.fill 			= GridBagConstraints.HORIZONTAL;
		this.add(nrOfFloorsLabel, constraints);
		
		constraints.gridx 			= 1;
		constraints.gridy 			= 1;
		constraints.gridwidth 		= 3;
		constraints.gridheight 		= 1;
		constraints.weightx 		= 0.8;
		constraints.weighty 		= 0.0;
		constraints.fill 			= GridBagConstraints.HORIZONTAL;
		this.add(nrOfFloors, constraints);
		
		constraints.gridx 			= 4;
		constraints.gridy 			= 1;
		constraints.gridwidth 		= 1;
		constraints.gridheight 		= 1;
		constraints.weightx 		= 0.10;
		constraints.weighty 		= 0.0;
		constraints.fill 			= GridBagConstraints.NONE;
		this.add(nrOfFloorsButton, constraints);
		
		
		
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;

		this.add(areaLabel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.8;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(area, constraints);
		
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.10;
		constraints.weighty = 0.001;
		constraints.fill = GridBagConstraints.NONE;

		this.add(areaButton, constraints);
		
		
		
		
		
	}

	public void updateCard(Building building) {
		buildingName.setText(building.getBuildingName());
		nrOfFloors.setText(String.valueOf(building.getFloors()));
		area.setText(String.valueOf(building.getArea()));
		
		buildingName.setEditable(false);
		nrOfFloors.setEditable(false);
		area.setEditable(false);
		
		buildingNameButton.setText(Text.getString("edit"));
		nrOfFloorsButton.setText(Text.getString("edit"));
		areaButton.setText(Text.getString("edit"));
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton pressedButton = (JButton) event.getSource();
			
			if(pressedButton.equals(nrOfFloorsButton)) {
				if(nrOfFloorsButton.getText() == Text.getString("edit")) {
					nrOfFloors.setEditable(true);
					nrOfFloorsButton.setText(Text.getString("save"));
				}
				else {
					nrOfFloors.setEditable(false);
					nrOfFloorsButton.setText(Text.getString("edit"));
					
				}
			}
			else if(pressedButton.equals(areaButton)) {
				if(areaButton.getText() == Text.getString("edit")) {
					area.setEditable(true);
					areaButton.setText(Text.getString("save"));
				}
				else {
					area.setEditable(false);
					areaButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(buildingNameButton)) {
				if(buildingNameButton.getText() == Text.getString("edit")) {
					buildingName.setEditable(true);
					buildingNameButton.setText(Text.getString("save"));
				}
				else {
					buildingName.setEnabled(false);
					buildingNameButton.setText(Text.getString("edit"));
				}
			}
		}
		
	}
}