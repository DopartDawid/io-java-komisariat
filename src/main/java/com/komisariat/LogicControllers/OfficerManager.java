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
	 * @param kitID
	 * @param VIN
	 * @param regionID
	 */
	public void createShift(Integer kitID, String VIN, Integer regionID) {
		if(dbac.getActiveShift(loggedOfficer) != null){
			return; //TODO Exception or sth
		}

		Shift newShift = new Shift();
		Date timestamp = new Date();
		newShift.setStartDate(timestamp);
		newShift.setOfficer(loggedOfficer);
		for(Kit kit: getShiftKits()){
			if(kit.getId() == kitID){
				newShift.setKit(kit);
			}
		}
		for(Vehicle vehicle: getShiftVehicles()){
			if(vehicle.getVIN().equals(VIN)){
				newShift.setVehicle(vehicle);
			}
		}
		for(PatrolRegion region: getShiftRegions()){
			if(region.getId() == regionID){
				newShift.setPatrolRegion(region);
			}
		}

		if(newShift.getKit() == null || newShift.getVehicle() == null || newShift.getPatrolRegion() == null){
			return; //TODO Exception or sth
		}
		dbac.saveShift(newShift);
	}

	/**
	 * 
	 * @param title
	 * @param content
	 * @param endMileage
	 */
	public boolean finishActiveShift(String title, String content, int endMileage) {
		Shift updatedShift = dbac.getActiveShift(loggedOfficer);
		if( updatedShift == null){
			return false;
		}

		Vehicle updatedVehicle = updatedShift.getVehicle();
		Date timestamp = new Date();
		updatedShift.setEndDate(timestamp);
		Report report = createReport(title, content);
		report.setShift(updatedShift);
		updatedVehicle.setMileage(endMileage);
		dbac.updateVehicleInfo(updatedVehicle);
		updatedShift.setReport(report);
		return dbac.updateShiftInfo(updatedShift);
	}

	/**
	 * 
	 * @param title
	 * @param content
	 */
	public Report createReport(String title, String content) {
		Report report = new Report();
		report.setTitle(title);
		report.setContent(content);
		Date timestamp = new Date();
		report.setDate(timestamp);		// TODO need to be checked

		return report;
	}

}