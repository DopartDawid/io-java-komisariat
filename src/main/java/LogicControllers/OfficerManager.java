package LogicControllers;

import BusinessObjects.*;

public class OfficerManager {

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

	public Region[] getShiftRegions() {
		// TODO - implement OfficerManager.getShiftRegions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 * @param vehicle
	 * @param region
	 */
	public Shift createShift(Kit kit, Vehicle vehicle, Region region) {
		// TODO - implement OfficerManager.createShift
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param report
	 * @param endMileage
	 */
	public bool finishActiveShift(Report report, int endMileage) {
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