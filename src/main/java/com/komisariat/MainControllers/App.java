package com.komisariat.MainControllers;

import com.komisariat.BusinessObjects.Headquarter;
import com.komisariat.BusinessObjects.Kit;
import com.komisariat.BusinessObjects.Tool;
import com.komisariat.LogicControllers.AdminManager;
import com.komisariat.LogicControllers.CommissionerManager;
import com.komisariat.LogicControllers.LoginManager;
import com.komisariat.LogicControllers.OfficerManager;
import com.komisariat.UI.IUserInterface;
import com.komisariat.UI.TextUI;

import java.util.*;

public class App {

	private LoginManager loginManager;
	private AdminManager adminManager;
	private OfficerManager officerManager;
	private CommissionerManager commissionerManager;
	private IUserInterface ui;

	public App() {
		loginManager = new LoginManager();
		adminManager = null;
		officerManager = null;
		commissionerManager = null;
		ui = new TextUI(this);
	}

	public void start() {
		ui.startUI();
	}

	public void signIn() throws Exception {
		List<String> credentials = new LinkedList<>(ui.getCredentials());

		if(credentials.size() != 2) {
			throw new Exception("Bad argument number: must be login and password!");
		}

		loginManager.signInNewUser(credentials.get(0), credentials.get(1));
		adminManager = loginManager.am;
		officerManager = loginManager.om;
		commissionerManager = loginManager.cm;

		if(adminManager != null) {
			ui.showAdminUI();
			return;
		}
		else if(officerManager != null) {
			ui.showOfficerUI();
			return;
		}
		else if(commissionerManager != null) {
			ui.showCommissionerUI();
			return;
		}

		ui.showErrorMessage("Niepoprawny login lub hasło!");
	}

	public void signOut() {
		// TODO - implement Facade.signOut
		throw new UnsupportedOperationException();
	}

	public void startShift() {
		// TODO - implement Facade.startShift
		throw new UnsupportedOperationException();
	}

	public void finishShift() {
		// TODO - implement Facade.finishShift
		throw new UnsupportedOperationException();
	}

	public void viewReports() {
		// TODO - implement Facade.viewReports
		throw new UnsupportedOperationException();
	}

	public void viewOfficerInfos() {
		// TODO - implement Facade.viewOfficerInfos
		throw new UnsupportedOperationException();
	}

	public void viewTimeSheet() {
		// TODO - implement Facade.viewTimeSheet
		throw new UnsupportedOperationException();
	}

	public void viewActiveOfficers() {
		// TODO - implement Facade.viewActiveOfficers
		throw new UnsupportedOperationException();
	}

	public void addNewKit() {
		Set<String> keys = new HashSet<>();
		keys.add("name");
		keys.add("category");
		keys.add("hqID");
		Map<String, String> kitInfo = ui.getKitInfo(keys);
		keys = new HashSet<>();
		keys.add("name");
		keys.add("manufacturer");
		keys.add("category");
		Collection<Map<String, String>> toolsInfo = ui.getToolsInfo(keys);
		adminManager.addKit(kitInfo.get("name"), kitInfo.get("category"), Integer.parseInt(kitInfo.get("hqID")), toolsInfo);
	}

	public void removeKit() {
		Integer chosenKitID = ui.getRemoveKitID();
		Kit[] kits = adminManager.getKits();

		for (Kit kit: kits
			 ) {
			if (kit.getId() == chosenKitID) {
				adminManager.removeKit(kit);
				break;
			}
		}
	}

	public void editKit() {
		Map<String, String> editedKitInfo = ui.getEditedKitInfo();

		if(editedKitInfo == null)
			return; //ANULOWANIE WYBORU

		//TODO - dokonczyc
	}

	public void addNewOfficer() {
		Set<String> keys = new HashSet<>();
		keys.add("badge number");
		keys.add("first name");
		keys.add("last name");
		keys.add("hqID");
		keys.add("rank");

		Map<String, String> officerInfo = ui.getNewOfficerInfo(keys);
		adminManager.addOfficer(Integer.parseInt(officerInfo.get("badge number")), officerInfo.get("first name"), officerInfo.get("last name"), Integer.parseInt(officerInfo.get("hqID")), officerInfo.get("rank"));
	}

	public void editOfficer() {
		// TODO - implement Facade.editOfficer
		throw new UnsupportedOperationException();
	}

	public void removeOfficer() {
		// TODO - implement Facade.removeOfficer
		throw new UnsupportedOperationException();
	}


	//UTIL FUNCTIONS

	public Collection<Map<String, String>> getHeadquarters() {
		Headquarter[] hqs = adminManager.getHeadquarters();
		Collection<Map<String, String>> hqsInfo = new ArrayList<>();

		for (Headquarter hq: hqs
			 ) {
			Map<String, String> temp = new HashMap<>();
			temp.put("id", Integer.toString(hq.getId()));
			temp.put("address", hq.getStreet() + " " + hq.getNumber());
			hqsInfo.add(temp);
		}
		return hqsInfo;
	}

	public Collection<Map<String, String>> getKits() {
		Kit[] kits = null;

		if(adminManager != null)
			kits = adminManager.getKits();
		else if(officerManager != null)
			kits = officerManager.getShiftKits();
		else if(commissionerManager != null)
			kits = commissionerManager.getShiftKits();

		Collection<Map<String, String>> kitsInfo = new ArrayList<>();

		for (Kit kit: kits
		) {
			Map<String, String> temp = new HashMap<>();
			temp.put("id", Integer.toString(kit.getId()));
			temp.put("name", kit.getName());
			temp.put("category", kit.getCategory());
			kitsInfo.add(temp);
		}
		return kitsInfo;
	}
}