package com.komisariat.BusinessObjects;

public class Street {

	private int id;
	private String streetName;
	private int firstNumber;
	private int lastNumber;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public int getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(int lastNumber) {
		this.lastNumber = lastNumber;
	}

}