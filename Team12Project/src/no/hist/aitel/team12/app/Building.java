package no.hist.aitel.team12.app;

import java.util.Arrays;


public class Building {

	private int building_id;
	
	private String building_name;
	
	private int floors;
	
	private Establishment[] establishments;

	private int numEstablishments;
	
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
	
	public void setEstablishments(Establishment[] establishments) {
		this.establishments = establishments;
		numEstablishments = establishments.length;
	}
	
	public Establishment[] getEstablishments() {
		return establishments;
	}
	
	public void addEstablishment(Establishment e) {
		if(establishments == null) establishments = new Establishment[2];
		if(numEstablishments == establishments.length) {
			establishments = Arrays.copyOf(establishments, establishments.length*2);
			establishments[numEstablishments++] = e;
		}
		else {
			establishments[numEstablishments++] = e;
		}
	}
	
	@Override
	public String toString() {
		return building_name;
	}
}
