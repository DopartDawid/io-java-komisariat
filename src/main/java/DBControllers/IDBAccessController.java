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

	string[] getRanks();

	Headquarter[] getHeaquarters();

	/**
	 * 
	 * @param shift
	 */
	bool saveShift(Shift shift);

	/**
	 * 
	 * @param kit
	 */
	bool saveKit(Kit kit);

	/**
	 * 
	 * @param officer
	 */
	bool saveOfficer(Officer officer);

	/**
	 * 
	 * @param shift
	 */
	bool updateShiftInfo(Shift shift);

	/**
	 * 
	 * @param oldKit
	 * @param newKit
	 */
	bool updateKit(Kit oldKit, Kit newKit);

	/**
	 * 
	 * @param oldOfficer
	 * @param newOfficer
	 */
	bool updateOfficer(Officer oldOfficer, Officer newOfficer);

	/**
	 * 
	 * @param kit
	 */
	bool removeKit(Kit kit);

	/**
	 * 
	 * @param officer
	 */
	bool removeOfficer(Officer officer);

}