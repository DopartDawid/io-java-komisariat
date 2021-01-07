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
	public void createShift(Kit kit, Vehicle vehicle, PatrolRegion region) {
		Shift newShift = new Shift();
		Date timestamp = new Date();
		newShift.setStartDate(timestamp);
		newShift.setOfficer(loggedOfficer);
		newShift.setKit(kit);
		newShift.setVehicle(vehicle);
		newShift.setPatrolRegion(region);

		dbac.saveShift(newShift);
	}

	/**
	 * 
	 * @param report
	 * @param endMileage
	 */
	public boolean finishActiveShift(Report report, int endMileage) {
		Collection<Shift> sh = dbac.getActiveShifts(loggedOfficer.getHeadquarter());
		Shift updatedShift = null;
		Vehicle updatedVehicle = null;
		boolean changesMade = false;

		for (Shift temp: sh){
			if(loggedOfficer == temp.getOfficer()) {
				updatedShift = temp;
				updatedVehicle = updatedShift.getVehicle();
				changesMade = true;
			}
		}

		if(changesMade){
		Date timestamp = new Date();
		updatedShift.setEndDate(timestamp);
		updatedShift.setReport(report);
		updatedVehicle.setMileage(endMileage);
		dbac.updateShiftInfo(updatedShift);
		dbac.updateVehicleInfo(updatedVehicle);
		}

		return changesMade;
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