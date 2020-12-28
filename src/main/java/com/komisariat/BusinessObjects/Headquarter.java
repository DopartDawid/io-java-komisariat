package com.komisariat.BusinessObjects;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Police_hqs")
public class Headquarter {

	@Id
	private int id;

	private String street;
	private int number;

	@OneToOne
	@JoinColumn(name = "Commissioner_ID", referencedColumnName = "Badge_Number")
	private Officer officer;

	@OneToMany(mappedBy = "headquarter")
	private Collection<Vehicle> vehicle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String streetName) {
		this.street = streetName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

    public Collection<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(Collection<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

	public Officer getOfficer() { return officer; }

	public void setOfficer(Officer officer) { this.officer = officer; }
}