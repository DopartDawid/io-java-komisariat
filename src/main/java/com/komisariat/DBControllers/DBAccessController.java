package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.*;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

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

	public Headquarter[] getHeadquarters() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Headquarter> cr = cb.createQuery(Headquarter.class);
		Root<Headquarter> root = cr.from(Headquarter.class);

		cr.select(root);

		List<Headquarter> results = session.createQuery(cr).getResultList();

		tx.commit();
		session.close();

		return (Headquarter[])results.toArray();
	}

	/**
	 * 
	 * @param shift
	 */
	public boolean saveShift(Shift shift) {
		save(shift);
		return true;
	}

	/**
	 * 
	 * @param kit
	 */
	public boolean saveKit(Kit kit) {
		save(kit);
		return true;
	}

	/**
	 * 
	 * @param officer
	 */
	public boolean saveOfficer(Officer officer) {
		save(officer);
		return true;
	}

	private Integer save(Object object) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Integer ID = null;
		try {
			tx = session.beginTransaction();
			ID = (Integer) session.save(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return ID;
	}

	/**
	 * 
	 * @param shift
	 */
	public boolean updateShiftInfo(Shift shift) {
		update(shift);
		return true;
	}

	/**
	 * 
	 * @param officer
	 */
	public boolean updateOfficer(Officer officer) {
		update(officer);
		return true;
	}

	/**
	 * 
	 * @param kit
	 */
	public boolean updateKit(Kit kit) {
		update(kit);
		return true;
	}

	private void update(Object object) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			tx = session.beginTransaction();
			session.update(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * @param kit
	 */
	public boolean removeKit(Kit kit) {
		delete(kit);
		return true;
	}

	/**
	 * 
	 * @param officer
	 */
	public boolean removeOfficer(Officer officer) {
		delete(officer);
		return true;
	}

	private void delete(Object object) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Integer officerID = null;
		try {
			tx = session.beginTransaction();
			session.delete(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}