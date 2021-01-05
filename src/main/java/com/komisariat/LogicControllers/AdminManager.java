package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.DBControllers.DBAccessController;
import com.komisariat.DBControllers.IDBAccessController;

import java.util.Collection;

public class AdminManager {
	private IDBAccessController accessController = DBAccessController.getInstance(AccessLevel.Admin);

	public Kit[] getKits() {
		// TODO - implement AdminManager.getKits
		throw new UnsupportedOperationException();
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
	public Tool addTool(String model, String manufacturer, String category) {
		// TODO - implement AdminManager.addTool
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 */
	public void removeKit(Kit kit) {
		// TODO - implement AdminManager.removeKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldKit
	 * @param newKit
	 */
	public Kit editKit(Kit oldKit, Kit newKit) {
		// TODO - implement AdminManager.editKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param hq
	 * @param rank
	 */
	public void addOfficer(String firstName, String lastName, Headquarter hq, String rank) {
		// TODO - implement AdminManager.addOfficer
		throw new UnsupportedOperationException();
	}

	public String[] getRanks() {
		// TODO - implement AdminManager.getRanks
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
		// TODO - implement AdminManager.removeOfficer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldOfficer
	 * @param newOfficer
	 */
	public Officer editOfficer(Officer oldOfficer, Officer newOfficer) {
		// TODO - implement AdminManager.editOfficer
		throw new UnsupportedOperationException();
	}

	public Headquarter[] getHeadquarters() {
		// TODO - implement AdminManager.getHeadquarters
		throw new UnsupportedOperationException();
	}

}