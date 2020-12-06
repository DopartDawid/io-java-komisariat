package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.*;

public class DBAccessController implements IDBAccessController {

	private DBAccessController instance;

	private DBAccessController() {
		// TODO - implement DBAccessController.DBAccessController
		throw new UnsupportedOperationException();
	}

	public DBAccessController getInstance() {
		return this.instance;
	}

	/**
	 * 
	 * @param hq
	 */
	public Kit[] getAvailableKits(Headquarter hq) {
		// TODO - implement DBAccessController.getAvailableKits
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param hq
	 */
	public Vehicle[] getAvailableVehicles(Headquarter hq) {
		// TODO - implement DBAccessController.getAvailableVehicles
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param hq
	 */
	public PatrolRegion[] getAvailableRegions(Headquarter hq) {
		// TODO - implement DBAccessController.getAvailableRegions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public Shift getActiveShift(Officer officer) {
		// TODO - implement DBAccessController.getActiveShift
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param hq
	 */
	public Report[] getReports(int startDate, int endDate, Headquarter hq) {
		// TODO - implement DBAccessController.getReports
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param hq
	 */
	public Officer[] getOfficers(Headquarter hq) {
		// TODO - implement DBAccessController.getOfficers
		throw new UnsupportedOperationException();
	}

	public String[] getRanks() {
		// TODO - implement DBAccessController.getRanks
		throw new UnsupportedOperationException();
	}

	public Headquarter[] getHeaquarters() {
		// TODO - implement DBAccessController.getHeaquarters
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shift
	 */
	public boolean saveShift(Shift shift) {
		// TODO - implement DBAccessController.saveShift
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 */
	public boolean saveKit(Kit kit) {
		// TODO - implement DBAccessController.saveKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public boolean saveOfficer(Officer officer) {
		// TODO - implement DBAccessController.saveOfficer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shift
	 */
	public boolean updateShiftInfo(Shift shift) {
		// TODO - implement DBAccessController.updateShiftInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldOfficer
	 * @param newOfficer
	 */
	public boolean updateOfficer(Officer oldOfficer, Officer newOfficer) {
		// TODO - implement DBAccessController.updateOfficer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldKit
	 * @param newKit
	 */
	public boolean updateKit(Kit oldKit, Kit newKit) {
		// TODO - implement DBAccessController.updateKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 */
	public boolean removeKit(Kit kit) {
		// TODO - implement DBAccessController.removeKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public boolean removeOfficer(Officer officer) {
		// TODO - implement DBAccessController.removeOfficer
		throw new UnsupportedOperationException();
	}

}