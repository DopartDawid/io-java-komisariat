package com.komisariat.MainControllers;

import com.komisariat.LogicControllers.*;

public class Facade {

	private App app;

	public Facade() {
		app = new App();
	}

	public void signIn() throws Exception {
		app.signIn();
	}

	public void signOut() {
		app.signOut();
	}

	public void startShift() {
		app.startShift();
	}

	public void finishShift() {
		app.finishShift();
	}

	public void viewReports() {
		app.viewReports();
	}

	public void viewOfficerInfos() {
		app.viewOfficerInfos();
	}

	public void viewTimeSheet() {
		app.viewTimeSheet();
	}

	public void viewActiveOfficers() {
		app.viewActiveOfficers();
	}

	public void addNewKit() {
		app.addNewKit();
	}

	public void removeKit() {
		app.removeKit();
	}

	public void editKit() {
		app.editKit();
	}

	public void addNewOfficer() {
		app.addNewOfficer();
	}

	public void editOfficer() {
		app.editOfficer();
	}

	public void removeOfficer() {
		app.removeOfficer();
	}

}