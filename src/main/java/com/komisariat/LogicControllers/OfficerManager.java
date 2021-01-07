package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.DBControllers.DBAccessController;
import com.komisariat.DBControllers.IDBAccessController;

import java.util.Collection;
import java.util.Date;

public class OfficerManager {

	protected IDBAccessController dbac;
	protected Officer loggedOfficer;

	/**
	 * 
	 * @param user
	 */
	public OfficerManager(User user) {
		dbac = DBAccessController.getInstance(user.getAccessLevel());
		loggedOfficer = (Officer) user;
	}

	protected Headquarter getOfficerHQ() {
		Collection<Headquarter> hqs = dbac.getHeadquarters();
		for (Headquarter hq: hqs) {
			if(hq.getId() == loggedOfficer.getHeadquarter().getId()){return hq;}
		}
		return null;
	}

	public Collection<Kit> getShiftKits() {
		return dbac.getAvailableKits(getOfficerHQ());
	}

	public Collection<Vehicle> getShiftVehicles() {
		return dbac.getAvailableVehicles(getOfficerHQ());
	}

	public Collection<PatrolRegion> getShiftRegions() {
		return dbac.getAvailableRegions(getOfficerHQ());
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
	public void createReport(String title, String content) {
		Report report = new Report();
		report.setTitle(title);
		report.setContent(content);
		Date timestamp = new Date();
		report.setDate(timestamp);		// TODO need to be checked

		dbac.saveReport(report);
	}

}