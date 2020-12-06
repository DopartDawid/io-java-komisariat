package DBControllers;

import BusinessObjects.*;

public interface IDBAccessController {

	DBAccessController getInstance();

	/**
	 * 
	 * @param hq
	 */
	Kit[] getAvailableKits(Headquarter hq);

	/**
	 * 
	 * @param hq
	 */
	Vehicle[] getAvailableVehicles(Headquarter hq);

	/**
	 * 
	 * @param hq
	 */
	PatrolRegion[] getAvailableRegions(Headquarter hq);

	/**
	 * 
	 * @param officer
	 */
	Shift getActiveShift(Officer officer);

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param hq
	 */
	Report[] getReports(int startDate, int endDate, Headquarter hq);

	/**
	 * 
	 * @param hq
	 */
	Officer[] getOfficers(Headquarter hq);

	String[] getRanks();

	Headquarter[] getHeaquarters();

	/**
	 * 
	 * @param shift
	 */
	boolean saveShift(Shift shift);

	/**
	 * 
	 * @param kit
	 */
	boolean saveKit(Kit kit);

	/**
	 * 
	 * @param officer
	 */
	boolean saveOfficer(Officer officer);

	/**
	 * 
	 * @param shift
	 */
	boolean updateShiftInfo(Shift shift);

	/**
	 * 
	 * @param oldKit
	 * @param newKit
	 */
	boolean updateKit(Kit oldKit, Kit newKit);

	/**
	 * 
	 * @param oldOfficer
	 * @param newOfficer
	 */
	boolean updateOfficer(Officer oldOfficer, Officer newOfficer);

	/**
	 * 
	 * @param kit
	 */
	boolean removeKit(Kit kit);

	/**
	 * 
	 * @param officer
	 */
	boolean removeOfficer(Officer officer);

}