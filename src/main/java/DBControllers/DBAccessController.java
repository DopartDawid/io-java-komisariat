package DBControllers;

import BusinessObjects.*;

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

	public string[] getRanks() {
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
	public bool saveShift(Shift shift) {
		// TODO - implement DBAccessController.saveShift
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 */
	public bool saveKit(Kit kit) {
		// TODO - implement DBAccessController.saveKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public bool saveOfficer(Officer officer) {
		// TODO - implement DBAccessController.saveOfficer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shift
	 */
	public bool updateShiftInfo(Shift shift) {
		// TODO - implement DBAccessController.updateShiftInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldOfficer
	 * @param newOfficer
	 */
	public bool updateOfficer(Officer oldOfficer, Officer newOfficer) {
		// TODO - implement DBAccessController.updateOfficer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldKit
	 * @param newKit
	 */
	public bool updateKit(Kit oldKit, Kit newKit) {
		// TODO - implement DBAccessController.updateKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 */
	public bool removeKit(Kit kit) {
		// TODO - implement DBAccessController.removeKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public bool removeOfficer(Officer officer) {
		// TODO - implement DBAccessController.removeOfficer
		throw new UnsupportedOperationException();
	}

}