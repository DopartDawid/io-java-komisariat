package com.komisariat.BusinessObjects;

import javax.persistence.*;

@Entity
@Table(name = "Tools")
public class Tool {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String model;
	private String manufacturer;
	private String category;

	@ManyToOne
	@JoinColumn(name = "Kit_ID")
	private Kit kit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Kit getKit() { return kit; }

	public void setKit(Kit kit) { this.kit = kit; }
}