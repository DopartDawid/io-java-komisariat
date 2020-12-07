package com.komisariat.BusinessObjects;

public class Kit {

	private int id;
	private String name;
	private String category;
	private Tool[] tools;

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

	public Tool[] getTools() {
		return tools;
	}

	public void setTools(Tool[] tools) {
		this.tools = tools;
	}
}