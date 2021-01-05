package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
	public Shift[] getOfficersShifts(Date beginDate, Date lastDate) {
		return dbac.getShifts(beginDate, lastDate, loggedOfficer.getHeadquarter());
	}

	public Shift[] getActiveOfficers() {
		Shift[] sh = dbac.getActiveShifts(loggedOfficer.getHeadquarter());
		List<Officer> activeOfficers = new LinkedList<>();
		for (Shift temp: sh
			 ) {
			activeOfficers.add(temp.getOfficer());
		}

		return activeOfficers.toArray(new Shift[activeOfficers.size()]);
	}

}