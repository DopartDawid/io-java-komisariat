package com.komisariat.BusinessObjects;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Kits")
public class Kit {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String name;
	private String category;

	@ManyToOne
	@JoinColumn(name = "Headquarter_ID")
	private Headquarter headquarter;

	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(mappedBy = "kit", fetch = FetchType.EAGER)
	private Collection<Tool> tools;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Headquarter getHeadquarter() { return headquarter; }

	public void setHeadquarter(Headquarter headquarter) { this.headquarter = headquarter; }

	public Collection<Tool> getTools() { return tools; }

	public void setTools(Collection<Tool> tools) { this.tools = tools; }
}