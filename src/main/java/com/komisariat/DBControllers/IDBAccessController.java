package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.*;

import java.util.Collection;
import java.util.Date;

public interface IDBAccessController {

	static DBAccessController getInstance(AccessLevel accessLevel) {
		return null;
	}

	/**
	 *
	 * @param hq
	 * @return
	 */
	Collection<Kit> getAvailableKits(Headquarter hq);


	Collection<Kit> getAllKits();
	/**
	 *
	 * @param hq
	 * @return
	 */
	Collection<Vehicle> getAvailableVehicles(Headquarter hq);

	/**
	 *
	 * @param hq
	 * @return
	 */
	Collection<PatrolRegion> getAvailableRegions(Headquarter hq);

	Collection<Shift> getShifts(Date startDate, Date endDate, Headquarter hq);

	/**
	 * 
	 * @param officer
	 */
	Shift getActiveShift(Officer officer);

	/**
	 *
	 * @param hq
	 * @return
	 */
	Collection<Shift> getActiveShifts(Headquarter hq);

	/**
	 *  @param startDate
	 * @param endDate
	 * @param hq
	 * @return
	 */
	Collection<Report> getReports(Date startDate, Date endDate, Headquarter hq);

	/**
	 *
	 * @param hq
	 * @return
	 */
	Collection<Officer> getOfficers(Headquarter hq);

	Collection<Officer> getAllOfficers();

	Collection<Rank> getRanks();

	Collection<Headquarter> getHeadquarters();

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
	Integer saveReport(Report report);
	/**
	 * 
	 * @param shift
	 */
	boolean updateShiftInfo(Shift shift);

	/**
	 *
	 * @param vehicle
	 */
	boolean updateVehicleInfo(Vehicle vehicle);

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