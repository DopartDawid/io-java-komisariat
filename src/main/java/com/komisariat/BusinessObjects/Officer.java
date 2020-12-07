package com.komisariat.BusinessObjects;

import javax.persistence.PrimaryKeyJoinColumn;
@PrimaryKeyJoinColumn(name = "User_id")
public class Officer extends User {

	private int badgeNumber;
	private String firstName;
	private String lastName;
	private String rank;

	public int getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(int badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}


}