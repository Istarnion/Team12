package no.hist.aitel.team12.gui;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.hist.aitel.team12.app.Building;
import no.hist.aitel.team12.util.Text;

public class BuildingView extends JPanel {
	
	private JLabel bNameLabel;
	private JLabel bName;
	private JLabel bFloorLabel;
	private JLabel bFloor;
	private JLabel bAreaLabel;
	private JLabel bArea;
	
	private JPanel labelPanel;
	private JPanel buildingPanel;

	private static final long serialVersionUID = 30928182953933521L;

	public BuildingView() {
		setLayout(new GridLayout(1,2));
		
		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		bNameLabel = new JLabel();
		bFloorLabel = new JLabel();
		bAreaLabel = new JLabel();
		labelPanel.add(bNameLabel);
		labelPanel.add(bFloorLabel);
		labelPanel.add(bAreaLabel);
		
		buildingPanel = new JPanel();
		buildingPanel.setLayout(new BoxLayout(buildingPanel,BoxLayout.Y_AXIS));
		bName = new JLabel();
		bFloor = new JLabel();
		bArea = new JLabel();
		buildingPanel.add(bName);
		buildingPanel.add(bFloor);
		buildingPanel.add(bArea);
		
		add(labelPanel);
		add(buildingPanel);
	
		
	}

	public void updateCard(Building building) {
		
		bNameLabel.setText(Text.getString("name")+":");
		bName.setText(building.getBuildingName());
		
		bFloorLabel.setText(Text.getString("floorPlur")+":");
		bFloor.setText(""+building.getFloors());
		
		bAreaLabel.setText(Text.getString("area")+":");
		bArea.setText(""+building.getArea());
		
		
		
	}
}
