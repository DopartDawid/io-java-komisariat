package com.komisariat.MainControllers;

import com.komisariat.LogicControllers.AdminManager;
import com.komisariat.LogicControllers.CommissionerManager;
import com.komisariat.LogicControllers.LoginManager;
import com.komisariat.LogicControllers.OfficerManager;
import com.komisariat.UI.IUserInterface;
import com.komisariat.UI.TextUI;

import java.util.LinkedList;
import java.util.List;

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
		// TODO - implement Facade.addNewKit
		throw new UnsupportedOperationException();
	}

	public void removeKit() {
		// TODO - implement Facade.removeKit
		throw new UnsupportedOperationException();
	}

	public void editKit() {
		// TODO - implement Facade.editKit
		throw new UnsupportedOperationException();
	}

	public void addNewOfficer() {
		// TODO - implement Facade.addNewOfficer
		throw new UnsupportedOperationException();
	}

	public void editOfficer() {
		// TODO - implement Facade.editOfficer
		throw new UnsupportedOperationException();
	}

	public void removeOfficer() {
		// TODO - implement Facade.removeOfficer
		throw new UnsupportedOperationException();
	}

}