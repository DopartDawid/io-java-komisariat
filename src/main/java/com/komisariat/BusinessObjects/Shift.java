package com.komisariat.BusinessObjects;

import java.util.Date;

public class Shift {

	private int id;
	private Date startDate;
	private Date endDate;
	private PatrolRegion patrolRegion;
	private Report report;
	private Kit kit;
	private Vehicle vehicle;
	private Officer officer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PatrolRegion getPatrolRegion() {
		return patrolRegion;
	}

	public void setPatrolRegion(PatrolRegion patrolRegion) {
		this.patrolRegion = patrolRegion;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}
}