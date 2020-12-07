package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBAccessController implements IDBAccessController {

	private static DBAccessController instance;
	private final SessionFactory factory;
	private final AccessLevel accessLevel;

	private DBAccessController(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;

		String login = "";
		String password = "";

		switch(accessLevel) {
			case Admin: login = "admin"; password = "admin"; break;
			case Officer: login = "officer"; password = "officer";
			case Commissioner: login = "komendant"; password = "commissioner";

			default: //TODO - THROW EXCEPTION
		}
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.configure().applySetting("hibernate.connection.username", login)
				.applySetting("hibernate.connection.password", password)
				.build();

		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		factory = meta.getSessionFactoryBuilder().build();
	}

	public static DBAccessController getInstance(AccessLevel accessLevel) {
		if(instance == null || instance.accessLevel != accessLevel)
			instance = new DBAccessController(accessLevel);

		return instance;
	}

	/**
	 * 
	 * @param hq
	 */
	public Kit[] getAvailableKits(Headquarter hq) {
		// TODO - implement DBAccessController.getAvailableKits
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param hq
	 */
	public Vehicle[] getAvailableVehicles(Headquarter hq) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param hq
	 */
	public PatrolRegion[] getAvailableRegions(Headquarter hq) {
		// TODO - implement DBAccessController.getAvailableRegions
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public Shift getActiveShift(Officer officer) {
		// TODO - implement DBAccessController.getActiveShift
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param hq
	 */
	public Report[] getReports(int startDate, int endDate, Headquarter hq) {
		// TODO - implement DBAccessController.getReports
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param hq
	 */
	public Officer[] getOfficers(Headquarter hq) {
		// TODO - implement DBAccessController.getOfficers
		throw new UnsupportedOperationException();
	}

	public String[] getRanks() {
		// TODO - implement DBAccessController.getRanks
		throw new UnsupportedOperationException();
	}

	public Headquarter[] getHeaquarters() {
		// TODO - implement DBAccessController.getHeaquarters
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shift
	 */
	public boolean saveShift(Shift shift) {
		// TODO - implement DBAccessController.saveShift
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 */
	public boolean saveKit(Kit kit) {
		// TODO - implement DBAccessController.saveKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public boolean saveOfficer(Officer officer) {
		// TODO - implement DBAccessController.saveOfficer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param shift
	 */
	public boolean updateShiftInfo(Shift shift) {
		// TODO - implement DBAccessController.updateShiftInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldOfficer
	 * @param newOfficer
	 */
	public boolean updateOfficer(Officer oldOfficer, Officer newOfficer) {
		// TODO - implement DBAccessController.updateOfficer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldKit
	 * @param newKit
	 */
	public boolean updateKit(Kit oldKit, Kit newKit) {
		// TODO - implement DBAccessController.updateKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param kit
	 */
	public boolean removeKit(Kit kit) {
		// TODO - implement DBAccessController.removeKit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param officer
	 */
	public boolean removeOfficer(Officer officer) {
		// TODO - implement DBAccessController.removeOfficer
		throw new UnsupportedOperationException();
	}

}