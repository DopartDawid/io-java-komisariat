package com.komisariat.BusinessObjects;

public class PatrolRegion {

	private int regionID;
	private int capacity;
	private int dangerLevel;
	private Street[] streets;

	public int getRegionID() {
		return regionID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public Street[] getStreets() {
		return streets;
	}

	public void setStreets(Street[] streets) {
		this.streets = streets;
	}
}