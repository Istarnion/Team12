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
 * BuildingCard.java Team 12, 27. apr. 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.awt.Dimension;
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
import no.hist.aitel.team12.app.UserType;
import no.hist.aitel.team12.util.Text;

public class BuildingCard extends JPanel {

	private static final long serialVersionUID = -3244200076799752084L;

	private JTextField nrOfFloors, area, buildingName;

	private JLabel nrOfFloorsLabel, areaLabel, buildingNameLabel;

	private JButton nrOfFloorsButton, areaButton, buildingNameButton;

	private ButtonListener buttonListener;
	
	private Building building;
	private UserType type;
	public BuildingCard(int userID, UserType type) {
		this.setLayout(new GridBagLayout());
		this.type = type;
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
		
		buildingNameButton.setPreferredSize(new Dimension(60, 23));
		nrOfFloorsButton.setPreferredSize(new Dimension(60, 23));
		areaButton.setPreferredSize(new Dimension(60, 23));
		
		nrOfFloorsButton.addActionListener(buttonListener);
		areaButton.addActionListener(buttonListener);
		buildingNameButton.addActionListener(buttonListener);
		
		// Grid bag layout
		GridBagConstraints constraints = new GridBagConstraints();
		
		// Setting some margins for a bit of space between each field/button/label
		constraints.insets.bottom 	= 5;
		constraints.insets.top 		= 5;
		
		// Building name grid bag setup
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
	
		// Number of floors grid bag setup
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
		
		// Area grid bag setup
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
		this.building = building;
		buildingName.setText(building.getBuildingName());
		nrOfFloors.setText(String.valueOf(building.getFloors()));
		area.setText(String.valueOf(building.getArea()));
		
		buildingName.setEditable(false);
		nrOfFloors.setEditable(false);
		area.setEditable(false);
		
		buildingNameButton.setText(Text.getString("edit"));
		nrOfFloorsButton.setText(Text.getString("edit"));
		areaButton.setText(Text.getString("edit"));
		if(type == UserType.SHOP_OWNER) {
			buildingNameButton.setEnabled(false);
			nrOfFloorsButton.setEnabled(false);
			areaButton.setEnabled(false);
		}
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton pressedButton = (JButton) event.getSource();
			
			if(pressedButton.equals(nrOfFloorsButton)) {
				if(nrOfFloorsButton.getText().equals(Text.getString("edit"))) {
					nrOfFloors.setEditable(true);
					nrOfFloorsButton.setText(Text.getString("save"));
				}
				else {
					if(!building.setNrOfFloors(nrOfFloors.getText())) {
						return;	
					}
					nrOfFloors.setEditable(false);
					nrOfFloorsButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(areaButton)) {
				if(areaButton.getText().equals(Text.getString("edit"))) {
					area.setEditable(true);
					areaButton.setText(Text.getString("save"));
				}
				else {
					if(!building.setArea(area.getText())) {
						return;
					}
					area.setEditable(false);
					areaButton.setText(Text.getString("edit"));
				}
			}
			else if(pressedButton.equals(buildingNameButton)) {
				if(buildingNameButton.getText().equals(Text.getString("edit"))) {
					buildingName.setEditable(true);
					buildingNameButton.setText(Text.getString("save"));
				}
				else {
					if(!building.setBuildingName(buildingName.getText())) {
						return;
					}
					buildingName.setEditable(false);
					buildingNameButton.setText(Text.getString("edit"));
				}
			}
		}
	}
}
