package com.komisariat.BusinessObjects;

import javax.persistence.*;

@Entity
@Table(name="Vehicles")
public class Vehicle {

	@Id
	private String VIN;

	private String manufacturer;
	private String model;

	@Column(name = "Production_Year")
	private int productionYear;
	private int mileage;

	@ManyToOne
	@JoinColumn(name = "Headquarter_ID")
	private Headquarter headquarter;

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String VIN) {
		this.VIN = VIN;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
}