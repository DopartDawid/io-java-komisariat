package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.*;

import java.util.Date;

public interface IDBAccessController {

	static DBAccessController getInstance(AccessLevel accessLevel) {
		return null;
	}

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

	Shift[] getShifts(Date startDate, Date endDate, Headquarter hq);

	/**
	 * 
	 * @param officer
	 */
	Shift getActiveShift(Officer officer);

	/**
	 *
	 * @param hq
	 */
	Shift[] getActiveShifts(Headquarter hq);

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param hq
	 */
	Report[] getReports(Date startDate, Date endDate, Headquarter hq);

	/**
	 * 
	 * @param hq
	 */
	Officer[] getOfficers(Headquarter hq);

	String[] getRanks();

	Headquarter[] getHeadquarters();

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
	 * @param tool
	 */
	boolean saveTool(Tool tool);

	/**
	 * 
	 * @param shift
	 */
	boolean updateShiftInfo(Shift shift);

	/**
	 * 
	 * @param kit
	 */
	boolean updateKit(Kit kit);

	/**
	 * 
	 * @param officer
	 */
	boolean updateOfficer(Officer officer);

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