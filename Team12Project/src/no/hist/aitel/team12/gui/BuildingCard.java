package no.hist.aitel.team12.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.util.Text;

public class BuildingCard extends JPanel {

	private static final long serialVersionUID = -3244200076799752084L;

	private JTextField nrOfFloors, area;

	private JLabel nrOfFloorsLabel, areaLabel;

	private JButton nrOfFloorsButton, areaButton;

	private ButtonListener buttonListener;

	public BuildingCard(int userID) {
		this.setLayout(new GridLayout(2, 3));
		
		buttonListener 		= new ButtonListener();
		
		nrOfFloorsLabel 	= new JLabel(Text.getString("nrOfFloors"));
		areaLabel 			= new JLabel(Text.getString("area"));
		
		nrOfFloors 			= new JTextField("");
		area 				= new JTextField("");
		
		nrOfFloorsButton 	= new JButton(Text.getString("edit"));
		areaButton 			= new JButton(Text.getString("edit"));
		
		nrOfFloors.setEditable(false);
		area.setEditable(false);
		
		nrOfFloors.setBorder(null);
		area.setBorder(null);
		
		nrOfFloorsButton.addActionListener(buttonListener);
		areaButton.addActionListener(buttonListener);
		
		this.add(nrOfFloorsLabel);
		this.add(nrOfFloors);
		this.add(nrOfFloorsButton);
		
		this.add(areaLabel);
		this.add(area);
		this.add(areaButton);

	}

	public void updateCard(Building building) {
		nrOfFloors.setText(String.valueOf(building.getFloors()));
		area.setText(String.valueOf(building.getArea()));
		
		nrOfFloors.setEditable(false);
		area.setEditable(false);
		
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
		}
		
	}
}
