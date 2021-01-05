package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.DBControllers.DBAccessController;
import com.komisariat.DBControllers.IDBAccessController;

import java.util.Collection;

public class AdminManager {
	private IDBAccessController accessController = DBAccessController.getInstance(AccessLevel.Admin);

	public Kit[] getKits() {
		return accessController.getAllKits();
	}

	/**
	 * 
	 * @param name
	 * @param cat
	 * @param hq
	 * @param tools
	 */
	public void addKit(String name, String cat, Headquarter hq, Collection<Tool> tools) {
		Kit newKit = new Kit();
		newKit.setName(name);
		newKit.setCategory(cat);
		newKit.setHeadquarter(hq);
		newKit.setTools(tools);

		accessController.saveKit(newKit);
	}

	/**
	 * 
	 * @param model
	 * @param manufacturer
	 * @param category
	 */
	public void addTool(String model, String manufacturer, String category) {
		Tool newTool = new Tool();
		newTool.setModel(model);
		newTool.setManufacturer(manufacturer);
		newTool.setCategory(category);

		accessController.saveTool(newTool);
	}

	/**
	 * 
	 * @param kit
	 */
	public void removeKit(Kit kit) {
		kit.setCategory("USUNIETY");
		accessController.updateKit(kit);
	}

	/**
	 * 
	 * @param oldKit
	 * @param newKit
	 */
	public void editKit(Kit oldKit, Kit newKit) {
		newKit.setId(oldKit.getId());
		accessController.updateKit(newKit);
	}

	/**
	 * @param badgeNumber
	 * @param firstName
	 * @param lastName
	 * @param hq
	 * @param rank
	 */
	public void addOfficer(int badgeNumber, String firstName, String lastName, Headquarter hq, String rank) {
		Officer newOfficer = new Officer();
		newOfficer.setBadgeNumber(badgeNumber);
		newOfficer.setFirstName(firstName);
		newOfficer.setLastName(lastName);
		newOfficer.setHeadquarter(hq);
		newOfficer.setRank(rank);
		newOfficer.setAccessLevel(AccessLevel.Officer);
		newOfficer.setLogin("");
		newOfficer.setPassHash("");
		accessController.saveOfficer(newOfficer);
	}

	public String[] getRanks() {
		// TODO - implement AdminManager.getRanks ????
		throw new UnsupportedOperationException();
	}

	public Officer[] getOfficers() {
		// TODO - implement AdminManager.getOfficers
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public void removeOfficer(Officer officer) {
		officer.setAccessLevel(AccessLevel.Removed);
		accessController.updateOfficer(officer);
	}

	/**
	 * 
	 * @param oldOfficer
	 * @param newOfficer
	 */
	public void editOfficer(Officer oldOfficer, Officer newOfficer) {
		newOfficer.setId(oldOfficer.getId());
		accessController.saveOfficer(newOfficer);
	}

	public Headquarter[] getHeadquarters() {
		return accessController.getHeadquarters();
	}

}