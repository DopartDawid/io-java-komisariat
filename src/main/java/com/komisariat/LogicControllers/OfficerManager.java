package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.DBControllers.IDBAccessController;

public class OfficerManager {

	protected IDBAccessController dbac;
	protected Officer loggedOfficer;

	/**
	 * 
	 * @param user
	 */
	public OfficerManager(User user) {
		// TODO - implement OfficerManager.OfficerManager
		throw new UnsupportedOperationException();
	}

	protected Headquarter getOfficerHQ() {
		// TODO - implement OfficerManager.getOfficerHQ
		throw new UnsupportedOperationException();
	}

	public Kit[] getShiftKits() {
		// TODO - implement OfficerManager.getShiftKits
		throw new UnsupportedOperationException();
	}

	public Vehicle[] getShiftVehicles() {
		// TODO - implement OfficerManager.getShiftVehicles
		throw new UnsupportedOperationException();
	}

	public PatrolRegion[] getShiftRegions() {
		// TODO - implement OfficerManager.getShiftRegions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 * @param vehicle
	 * @param region
	 */
	public Shift createShift(Kit kit, Vehicle vehicle, PatrolRegion region) {
		// TODO - implement OfficerManager.createShift
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param report
	 * @param endMileage
	 */
	public boolean finishActiveShift(Report report, int endMileage) {
		// TODO - implement OfficerManager.finishActiveShift
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param title
	 * @param content
	 */
	public Report createReport(String title, String content) {
		// TODO - implement OfficerManager.createReport
		throw new UnsupportedOperationException();
	}

}