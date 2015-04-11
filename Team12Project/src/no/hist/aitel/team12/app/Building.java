package no.hist.aitel.team12.app;

import java.util.ArrayList;

public class Building {

	private int building_id;
	
	private String building_name;
	
	private int floors;
	
	private ArrayList<Establishment> establishments;

	public Building(int building_id, String building_name, int floors) {
		super();
		this.building_id = building_id;
		this.building_name = building_name;
		this.floors = floors;
	}

	public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}

	public int getBuilding_id() {
		return building_id;
	}

	public int getFloors() {
		return floors;
	}
	
	
	
	
}
