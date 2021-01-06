package com.komisariat.BusinessObjects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Police_officers")
@PrimaryKeyJoinColumn(name = "User_id")
public class Officer extends User implements Serializable {

	@Column(name = "Badge_Number")
	private int badgeNumber;

	@Column(name = "First_Name")
	private String firstName;

	@Column(name = "Second_Name")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "Rank_title")
	private Rank rank;

	@ManyToOne
	@JoinColumn(name = "Headquarter_ID")
	private Headquarter headquarter;

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

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Headquarter getHeadquarter() { return headquarter; }

	public void setHeadquarter(Headquarter headquarter) { this.headquarter = headquarter; }
}