package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.DBControllers.DBAccessController;
import com.komisariat.DBControllers.IDBAccessController;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class AdminManager {
	private IDBAccessController accessController = DBAccessController.getInstance(AccessLevel.Admin);

	public Kit[] getKits() {
		return accessController.getAllKits();
	}

	/**
	 * 
	 * @param name
	 * @param cat
	 * @param hqID
	 * @param toolsInfo
	 */
	public void addKit(String name, String cat, Integer hqID, Collection<Map<String, String>> toolsInfo) {
		Kit newKit = createKit(name, cat, hqID, toolsInfo);
		accessController.saveKit(newKit);
	}

	private Kit createKit(String name, String cat, Integer hqID, Collection<Map<String, String>> toolsInfo) {
		Kit newKit = new Kit();
		newKit.setName(name);
		newKit.setCategory(cat);

		for (Headquarter hq: this.getHeadquarters() //TODO - WALIDACJA CZY NA PEWNO ZOSTAL DODANY HQ
			 ) {
			if(hq.getId() == hqID) {
				newKit.setHeadquarter(hq);
				break;
			}
		}

		Collection<Tool> tools = new LinkedList<>();
		for (Map<String, String> toolInfo: toolsInfo
		) {
			tools.add(addTool(toolInfo.get("model"), toolInfo.get("manufacturer"), toolInfo.get("category"), newKit));
		}
		newKit.setTools(tools);
		return newKit;
	}

	/**
	 * 
	 * @param model
	 * @param manufacturer
	 * @param category
	 */
	private Tool addTool(String model, String manufacturer, String category, Kit kit) {
		Tool newTool = new Tool();
		newTool.setModel(model);
		newTool.setManufacturer(manufacturer);
		newTool.setCategory(category);
		newTool.setKit(kit);

		return newTool;
	}

	/**
	 * 
	 * @param kit
	 */
	public void removeKit(Kit kit) {
		kit.setCategory("USUNIETY");
		accessController.updateKit(kit);
	}


	public void editKit(Integer oldID, String name, String cat, Integer hqID, Collection<Map<String, String>> toolsInfo) {
		Kit editedKit = createKit(name, cat, hqID, toolsInfo);
		editedKit.setId(oldID);
		accessController.updateKit(editedKit);
	}

	/**
	 * @param badgeNumber
	 * @param firstName
	 * @param lastName
	 * @param hqID
	 * @param rank
	 */
	public void addOfficer(int badgeNumber, String firstName, String lastName, Integer hqID, String rank) {
		Officer newOfficer = new Officer();
		newOfficer.setBadgeNumber(badgeNumber);
		newOfficer.setFirstName(firstName);
		newOfficer.setLastName(lastName);
		newOfficer.setHeadquarter(this.getHeadquarters()[hqID]);
		newOfficer.setRank(rank);
		newOfficer.setAccessLevel(AccessLevel.Officer);
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