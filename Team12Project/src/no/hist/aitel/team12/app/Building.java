package no.hist.aitel.team12.app;

import java.util.Arrays;


public class Building {

	private int buildingId;
	
	private String buildingName;
	
	private int floors;
	
	private Establishment[] establishments;

	private int numEstablishments;
	
	public Building(int buildingId, String buildingName, int floors) {
		super();
		this.buildingId = buildingId;
		this.buildingName = buildingName;
		this.floors = floors;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getBuildingId() {
		return buildingId;
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
		return buildingName;
	}
}
