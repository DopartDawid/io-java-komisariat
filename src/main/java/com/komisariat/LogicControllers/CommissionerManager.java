package com.komisariat.LogicControllers;

import com.komisariat.BusinessObjects.*;

import java.util.Collection;
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
	public Collection<Report> getReports(Date beginDate, Date lastDate) {
		return dbac.getReports(beginDate, lastDate, loggedOfficer.getHeadquarter());
	}

	public Collection<Officer> getOfficersInfo() {
		return dbac.getOfficers(loggedOfficer.getHeadquarter());
	}

	/**
	 * 
	 * @param beginDate
	 * @param lastDate
	 */
	public Collection<Shift> getOfficersShifts(Date beginDate, Date lastDate) {
		return dbac.getShifts(beginDate, lastDate, loggedOfficer.getHeadquarter());
	}

	public Collection<Officer> getActiveOfficers() {
		Collection<Shift> sh = dbac.getActiveShifts(loggedOfficer.getHeadquarter());
		List<Officer> activeOfficers = new LinkedList<>();
		for (Shift temp: sh
			 ) {
			activeOfficers.add(temp.getOfficer());
		}

		return activeOfficers;
	}

}