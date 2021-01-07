package com.komisariat.BusinessObjects;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Shifts")
public class Shift {

	@Id
	private int id;

	@Column(name = "Shift_Start_Date")
	private Date startDate;

	@Column(name = "Shift_End_Date")
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "Region_ID")
	private PatrolRegion patrolRegion;

	@OneToOne
	@JoinColumn(name = "Report_ID")
	private Report report;

	@ManyToOne
	@JoinColumn(name = "Kit_ID")
	private Kit kit;

	@ManyToOne
	@JoinColumn(name = "Vehicle_ID")
	private Vehicle vehicle;

	@ManyToOne
	@JoinColumn(name = "Officer_ID", referencedColumnName = "Badge_Number")
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