package com.komisariat.BusinessObjects;

import javax.persistence.*;

@Entity
@Table(name = "Streets")
public class Street {

	@Id
	private int id;

	@Column(name = "Street_Name")
	private String streetName;

	@Column(name = "First_Num")
	private int firstNumber;

	@Column(name = "Last_Num")
	private int lastNumber;

	@ManyToOne
	@JoinColumn(name = "Region_ID")
	private PatrolRegion patrolRegion;

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

	public PatrolRegion getPatrolRegion() { return patrolRegion; }
	public void setPatrolRegion(PatrolRegion patrolRegion) { this.patrolRegion = patrolRegion; }
}