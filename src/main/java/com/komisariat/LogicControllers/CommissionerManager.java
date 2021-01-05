package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;

import java.util.Date;

public class CommissionerManager extends OfficerManager {

	/**
	 * @param user
	 */
	public CommissionerManager(User user) {
		super(user);
	}

	/**
	 * 
	 * @param beginDate
	 * @param lastDate
	 */
	public Report[] getReports(Date beginDate, Date lastDate) {
		return dbac.getReports(beginDate, lastDate, loggedOfficer.getHeadquarter());
	}

	public Officer[] getOfficersInfo() {
		return dbac.getOfficers(loggedOfficer.getHeadquarter());
	}

	/**
	 * 
	 * @param beginDate
	 * @param lastDate
	 */
	public Shift[] getOfficersShifts(int beginDate, int lastDate) {
		return dbac.get
	}

	public Shift[] getActiveOfficers() {
		// TODO - implement CommissionerManager.getActiveOfficers
		throw new UnsupportedOperationException();
	}

}