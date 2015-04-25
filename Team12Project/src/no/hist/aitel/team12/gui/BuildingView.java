package no.hist.aitel.team12.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	private JPanel placement;

	private static final long serialVersionUID = 30928182953933521L;

	public BuildingView() {
		placement = new JPanel();
		placement.setLayout(new GridLayout(1,2));
		
		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		bNameLabel = new JLabel();
		bNameLabel.setBorder(new EmptyBorder(5, 20, 0 ,0 ));
		bFloorLabel = new JLabel();
		bFloorLabel.setBorder(new EmptyBorder(5, 20, 0 ,0 ));
		bAreaLabel = new JLabel();
		bAreaLabel.setBorder(new EmptyBorder(5, 20, 0 ,0 ));
		labelPanel.add(bNameLabel);
		labelPanel.add(bFloorLabel);
		labelPanel.add(bAreaLabel);
		
		buildingPanel = new JPanel();
		buildingPanel.setLayout(new BoxLayout(buildingPanel,BoxLayout.Y_AXIS));
		bName = new JLabel();
		bName.setBorder(new EmptyBorder(5, 20, 0 ,0 ));
		bFloor = new JLabel();
		bFloor.setBorder(new EmptyBorder(5, 20, 0 ,0 ));
		bArea = new JLabel();
		bArea.setBorder(new EmptyBorder(5, 20, 0 ,0 ));
		buildingPanel.add(bName);
		buildingPanel.add(bFloor);
		buildingPanel.add(bArea);
		
		placement.add(labelPanel);
		placement.add(buildingPanel);
		
		setLayout(new BorderLayout());
		add(placement, BorderLayout.WEST);
		
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
