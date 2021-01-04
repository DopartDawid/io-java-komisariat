package com.komisariat.BusinessObjects;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Patrol_Regions")
public class PatrolRegion {

	@Id
	private int id;
	private int capacity;

	@Column(name = "Danger_level")
	private int dangerLevel;

	@OneToMany(mappedBy = "patrolRegion")
	private Collection<Street> streets;

	@ManyToOne
	@JoinColumn(name = "Headquarter_ID")
	private Headquarter headquarter;

	public int getId() {
		return id;
	}
	public void setId(int regionID) {
		this.id = regionID;
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

	public Collection<Street> getStreets() { return streets; }
	public void setStreets(Collection<Street> streets) { this.streets = streets; }

	public Headquarter getHeadquarter() { return headquarter; }
	public void setHeadquarter(Headquarter headquarter) { this.headquarter = headquarter; }
}