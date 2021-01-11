package com.komisariat.MainControllers;

import com.komisariat.BusinessObjects.*;
import com.komisariat.LogicControllers.AdminManager;
import com.komisariat.LogicControllers.CommissionerManager;
import com.komisariat.LogicControllers.LoginManager;
import com.komisariat.LogicControllers.OfficerManager;
import com.komisariat.UI.GraphicalUI;
import com.komisariat.UI.IUserInterface;
import com.komisariat.UI.TextUI;

import java.text.SimpleDateFormat;
import java.util.*;

public class App {

	private LoginManager loginManager;
	private AdminManager adminManager;
	private OfficerManager officerManager;
	private CommissionerManager commissionerManager;
	private IUserInterface ui;

	public App() {
		loginManager = new LoginManager();
		adminManager = null;
		officerManager = null;
		commissionerManager = null;
		ui = GraphicalUI.getInstance();
	}

	public void start() {
		ui.startUI();
	}

	public void signIn() throws Exception {
		List<String> credentials = new LinkedList<>(ui.getCredentials());

		if(credentials.size() != 2) {
			throw new Exception("Bad argument number: must be login and password!");
		}

		loginManager.signInNewUser(credentials.get(0), credentials.get(1));
		adminManager = loginManager.am;
		officerManager = loginManager.om;
		commissionerManager = loginManager.cm;

		if(adminManager != null) {
			ui.showAdminUI();
			return;
		}
		else if(officerManager != null) {
			ui.showOfficerUI();
			return;
		}
		else if(commissionerManager != null) {
			officerManager = commissionerManager;
			ui.showCommissionerUI();
			return;
		}

		ui.showErrorMessage("Niepoprawny login lub hasło!");
	}

	public void signOut() {
		loginManager.am = null;
		loginManager.om= null;
		loginManager.cm = null;
	}

	public void startShift() {
		Map<String, String> shiftInfo = ui.getNewShiftInfo();
		OfficerManager om = null;
		if(officerManager != null) {
			om = officerManager;
		}
		else
			om = commissionerManager;

		om.createShift(Integer.parseInt(shiftInfo.get("kitID")),
				shiftInfo.get("vehicleID"),
				Integer.parseInt(shiftInfo.get("regionID")));
	}

	public void finishShift() {
		OfficerManager om = null;
		if(officerManager != null) {
			om = officerManager;
		}
		else
			return;
		Map<String, String> shiftInfo = ui.getEndShiftInfo();
		if(shiftInfo == null)
			return;
		boolean isFinished = om.finishActiveShift(shiftInfo.get("title"),
								shiftInfo.get("content"),
								Integer.parseInt(shiftInfo.get("endMileage")));
	}

	public void viewReports() {
		ui.showReports();
	}

	public void viewOfficerInfos() {
		ui.showOfficersInfo();
	}

	public void viewTimeSheet() {
		ui.showTimesheet();
	}

	public void viewActiveOfficers() {
		ui.showActiveOfficers();
	}

	public void addNewKit() {
		Map<String, String> kitInfo = ui.getKitInfo();
		Collection<Map<String, String>> toolsInfo = ui.getToolsInfo();
		adminManager.addKit(kitInfo.get("name"), kitInfo.get("category"), Integer.parseInt(kitInfo.get("hqID")), toolsInfo);
	}

	public void removeKit() {
		Integer chosenKitID = ui.getRemoveKitID();
		Collection<Kit> kits = adminManager.getKits();

		for (Kit kit: kits
			 ) {
			if (kit.getId() == chosenKitID) {
				adminManager.removeKit(kit);
				break;
			}
		}
	}

	public void editKit() {
		Map<String, String> editedKitInfo = ui.getEditedKitInfo();
		if(editedKitInfo == null)
			return; //ANULOWANIE WYBORU
		Collection<Map<String, String>> tools = ui.getEditedToolsInfo(Integer.parseInt(editedKitInfo.get("id")));

		adminManager.editKit(Integer.parseInt(editedKitInfo.get("id")),
				editedKitInfo.get("name"),
				editedKitInfo.get("category"),
				Integer.parseInt(editedKitInfo.get("hqID")),
				tools);
	}

	public void addNewOfficer() {
		Map<String, String> officerInfo = ui.getNewOfficerInfo();
		adminManager.addOfficer(Integer.parseInt(officerInfo.get("badgeNumber")),
				officerInfo.get("firstName"), officerInfo.get("lastName"),
				Integer.parseInt(officerInfo.get("hqID")),
				officerInfo.get("rank"));
	}

	public void editOfficer() {
		Map<String, String> editedOfficerInfo = ui.getEditedOfficerInfo();
		if(editedOfficerInfo == null)
			return;

		adminManager.editOfficer(Integer.parseInt(editedOfficerInfo.get("id")),
				Integer.parseInt(editedOfficerInfo.get("badgeNumber")),
				editedOfficerInfo.get("firstName"),
				editedOfficerInfo.get("lastName"),
				Integer.parseInt(editedOfficerInfo.get("hqID")),
				editedOfficerInfo.get("rank"));
	}

	public void removeOfficer() {
		Integer chosenOfficerID = ui.getRemoveOfficerID();
		adminManager.removeOfficer(chosenOfficerID);
	}


	//UTIL FUNCTIONS

	public Collection<Map<String, String>> getHeadquarters() {
		Collection<Headquarter> hqs = adminManager.getHeadquarters();
		Collection<Map<String, String>> hqsInfo = new ArrayList<>();

		for (Headquarter hq: hqs
			 ) {
			Map<String, String> temp = new HashMap<>();
			temp.put("id", Integer.toString(hq.getId()));
			temp.put("address", hq.getStreet() + " " + hq.getNumber());
			hqsInfo.add(temp);
		}
		return hqsInfo;
	}

	public Collection<Map<String, String>> getKits() {
		Collection<Kit> kits = null;

		if(adminManager != null)
			kits = adminManager.getKits();
		else if(officerManager != null)
			kits = officerManager.getShiftKits();
		else if(commissionerManager != null)
			kits = commissionerManager.getShiftKits();

		Collection<Map<String, String>> kitsInfo = new ArrayList<>();

		for (Kit kit: kits
		) {
			Map<String, String> temp = new HashMap<>();
			temp.put("id", Integer.toString(kit.getId()));
			temp.put("name", kit.getName());
			temp.put("category", kit.getCategory());
			temp.put("hqID", Integer.toString(kit.getHeadquarter().getId()));
			kitsInfo.add(temp);
		}
		return kitsInfo;
	}

	public Collection<Map<String, String>> getKitTools(int kitID) {
		Collection<Kit> kits = null;

		if(adminManager != null)
			kits = adminManager.getKits();
		else if(officerManager != null)
			kits = officerManager.getShiftKits();
		else if(commissionerManager != null)
			kits = commissionerManager.getShiftKits();

		Collection<Map<String, String>> toolsInfo = new ArrayList<>();

		for (Kit kit: kits
		) {
			if(kit.getId() == kitID) {
				for (Tool tool: kit.getTools()
					 ) {
					Map<String, String> temp = new HashMap<>();
					temp.put("model", tool.getModel());
					temp.put("manufacturer", tool.getManufacturer());
					temp.put("category", tool.getCategory());
					toolsInfo.add(temp);
				}
				return toolsInfo;
			}
		}
		return null; //TODO - EXCEPTION A NIE NULL
	}

	public Collection<String> getRanks() {
		Collection<String> ranks = new LinkedList<>();

		for (Rank rank: adminManager.getRanks()
			 ) {
			ranks.add(rank.getRankTitle());
		}
		return ranks;
	}

	public Collection<Map<String, String>> getOfficers(){
		Collection<Officer> officers = null;

		if(adminManager != null)
			officers = adminManager.getOfficers();
		else if(commissionerManager != null)
			officers = commissionerManager.getOfficersInfo();

		Collection<Map<String, String>> officersInfo = new LinkedList<>();

		for (Officer officer: officers
			 ) {
			Map<String, String> temp = new HashMap<>();
			temp.put("id", Integer.toString(officer.getId()));
			temp.put("badgeNumber", Integer.toString(officer.getBadgeNumber()));
			temp.put("firstName", officer.getFirstName());
			temp.put("lastName", officer.getLastName());
			temp.put("hqID", Integer.toString(officer.getHeadquarter().getId()));
			temp.put("hqAddress", officer.getHeadquarter().getStreet() + " " + officer.getHeadquarter().getNumber());
			temp.put("rank", officer.getRank().getRankTitle());
			officersInfo.add(temp);
		}

		return officersInfo;
	}

	public Collection<Map<String, String>> getActiveOfficers(){
		Collection<Officer> officers = commissionerManager.getActiveOfficers();
		Collection<Map<String, String>> officersInfo = new LinkedList<>();
		Collection<Shift> shifts = commissionerManager.getOfficersShifts(null, null);
		for (Officer officer: officers
		) {
			Map<String, String> temp = new HashMap<>();
			temp.put("id", Integer.toString(officer.getId()));
			temp.put("badgeNumber", Integer.toString(officer.getBadgeNumber()));
			temp.put("firstName", officer.getFirstName());
			temp.put("lastName", officer.getLastName());
			temp.put("hqID", Integer.toString(officer.getHeadquarter().getId()));
			temp.put("rank", officer.getRank().getRankTitle());
			for (Shift shift: shifts
				 ) {
				if(shift.getOfficer().getId() == officer.getId() && shift.getEndDate() == null) {
					String streets = "";
					for (Street street: shift.getPatrolRegion().getStreets()
						 ) {
						streets += ("[" + street.getStreetName() + " (" + street.getFirstNumber() + "-" + street.getLastNumber() + ")]");
					}
					temp.put("regionInfo", streets);
				}
			}
			officersInfo.add(temp);
		}

		return officersInfo;
	}

	public Collection<Map<String, String>> getReports(Date begDate, Date endDate) {
		Collection<Report> reports = commissionerManager.getReports(begDate, endDate);

		Collection<Map<String, String>> reportsInfo = new LinkedList<>();

		for (Report report: reports
			 ) {
			Map<String, String> temp = new HashMap<>();
			temp.put("title", report.getTitle());
			temp.put("content", report.getContent());
			temp.put("date", report.getDate().toString());
			temp.put("officerName", report.getShift().getOfficer().getFirstName()+ " " +report.getShift().getOfficer().getLastName());
			reportsInfo.add(temp);
		}

		return reportsInfo;
	}

	public Collection<Map<String, String>> getShiftsInfo(Date begDate, Date endDate) {
		Collection<Shift> shifts = commissionerManager.getOfficersShifts(begDate, endDate);

		Collection<Map<String, String>> shiftsInfo = new LinkedList<>();

		for (Shift shift: shifts
		) {
			Map<String, String> temp = new HashMap<>();
			temp.put("begDate", shift.getStartDate().toString());
			temp.put("endDate", shift.getEndDate() == null ? "W TRAKCIE" : shift.getEndDate().toString());
			String streets = "";
			for (Street street: shift.getPatrolRegion().getStreets()
			) {
				streets += ("[" + street.getStreetName() + " (" + street.getFirstNumber() + "-" + street.getLastNumber() + ")]");
			}
			temp.put("regionInfo", streets);
			temp.put("kitInfo", shift.getKit().getName());
			temp.put("vehicleInfo", shift.getVehicle().getManufacturer() + " " + shift.getVehicle().getModel());
			temp.put("officerInfo", shift.getOfficer().getFirstName()+ " " +shift.getOfficer().getLastName());
			shiftsInfo.add(temp);
		}

		return shiftsInfo;
	}

	public Collection<Map<String, String>> getVehicles() {
		Collection<Vehicle> vehicles = null;

		if(officerManager != null)
			vehicles = officerManager.getShiftVehicles();
		else
			vehicles = commissionerManager.getShiftVehicles();

		Collection<Map<String, String>> vehiclesInfo = new ArrayList<>();

		for (Vehicle vehicle: vehicles
		) {
			Map<String, String> temp = new HashMap<>();
			temp.put("vin", vehicle.getVIN());
			temp.put("manufacturer", vehicle.getManufacturer());
			temp.put("model", vehicle.getModel());
			vehiclesInfo.add(temp);
		}
		return vehiclesInfo;
	}

	public Collection<Map<String, String>> getRegions() {
		Collection<PatrolRegion> patrolRegions = null;

		if(officerManager != null)
			patrolRegions = officerManager.getShiftRegions();
		else
			patrolRegions = commissionerManager.getShiftRegions();

		Collection<Map<String, String>> regionsInfo = new ArrayList<>();

		for (PatrolRegion patrolRegion: patrolRegions
		) {
			Map<String, String> temp = new HashMap<>();
			temp.put("id", Integer.toString(patrolRegion.getId()));
			temp.put("danger", Integer.toString(patrolRegion.getDangerLevel()));
			String streets = "";
			for (Street street: patrolRegion.getStreets()
			) {
				streets += ("[" + street.getStreetName() + " (" + street.getFirstNumber() + "-" + street.getLastNumber() + ")]");
			}
			temp.put("streets", streets);
			regionsInfo.add(temp);
		}
		return regionsInfo;
	}
}