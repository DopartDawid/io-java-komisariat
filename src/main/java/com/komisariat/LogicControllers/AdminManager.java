package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.DBControllers.DBAccessController;
import com.komisariat.DBControllers.IDBAccessController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class AdminManager {
	private IDBAccessController accessController = DBAccessController.getInstance(AccessLevel.Admin);

	public Collection<Kit> getKits() {
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
		//TODO - PASS HASH
		Officer newOfficer = createOfficer(badgeNumber, firstName, lastName, hqID, rank);
		newOfficer.setPassHash(hashPass(Integer.toString(badgeNumber)));
		newOfficer.setAccessLevel(AccessLevel.Officer);
		newOfficer.setLogin(firstName + "_" + lastName);
		accessController.saveOfficer(newOfficer);
	}

	private Officer createOfficer(int badgeNumber, String firstName, String lastName, Integer hqID, String rank) {
		Officer newOfficer = new Officer();
		newOfficer.setBadgeNumber(badgeNumber);
		newOfficer.setFirstName(firstName);
		newOfficer.setLastName(lastName);

		for (Headquarter hq: this.getHeadquarters() //TODO - WALIDACJA CZY NA PEWNO ZOSTAL DODANY HQ
		) {
			if(hq.getId() == hqID) {
				newOfficer.setHeadquarter(hq);
				break;
			}
		}

		for (Rank rankObj: this.getRanks() //TODO - WALIDACJA CZY NA PEWNO ZOSTAL DODANY HQ
		) {
			if(rankObj.getRankTitle().equals(rank)) {
				newOfficer.setRank(rankObj);
				break;
			}
		}

		return newOfficer;
	}

	public Collection<Rank> getRanks() { return accessController.getRanks(); }

	public Collection<Officer> getOfficers() { return accessController.getAllOfficers(); }

	public void removeOfficer(Integer officerID) {
		for (Officer officer: getOfficers()
			 ) {
			if(officer.getId() == officerID) {
				officer.setAccessLevel(AccessLevel.Removed);
				accessController.updateOfficer(officer);
			}
		}
	}


	public void editOfficer(int oldID, int badgeNumber, String firstName, String lastName, Integer hqID, String rank) {
		Officer editedOfficer = createOfficer(badgeNumber, firstName, lastName, hqID, rank);
		for (Officer officer: getOfficers()
			 ) {
			if(officer.getId() == oldID) {
				editedOfficer.setLogin(officer.getLogin());
				editedOfficer.setPassHash(officer.getPassHash());
				editedOfficer.setAccessLevel(officer.getAccessLevel());
			}
		}
		editedOfficer.setId(oldID);
		accessController.updateOfficer(editedOfficer);
	}

	public Collection<Headquarter> getHeadquarters() {
		return accessController.getHeadquarters();
	}

	private String hashPass(String password){
		String passHash;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger representation = new BigInteger(1, messageDigest);
			passHash = representation.toString(16);
			while(passHash.length() < 32){
				passHash = "0"+ passHash;
			}
		}catch (NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
		return passHash;
	}
}