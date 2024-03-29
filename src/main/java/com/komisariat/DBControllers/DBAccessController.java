package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DBAccessController implements IDBAccessController {

	private static DBAccessController instance;
	private final SessionFactory factory;
	private final AccessLevel accessLevel;

	private DBAccessController(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;

		String login = "";
		String password = "";

		if(accessLevel == null) { //TODO - DO USUNIECIA (TYLKO DO TESTOW)
			login = "rroot";
			password = "blazej123";
		}
		else {
			switch(accessLevel) {
				case Admin: login = "admin"; password = "admin"; break;
				case Officer: login = "officer"; password = "funkcjonariusz"; break;
				case Commissioner: login = "commissioner"; password = "komendant"; break;

				default: //TODO - THROW EXCEPTION
			}
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
	 * @return
	 */
	public Collection<Kit> getAvailableKits(Headquarter hq) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String select = "FROM Kit k WHERE k.category != :removed AND k.headquarter.id = :hq AND NOT EXISTS (FROM Shift s WHERE s.officer.headquarter.id = :hq AND s.endDate IS NULL AND s.kit.id = k.id)";
		Query query = session.createQuery(select).setParameter("hq", hq.getId()).setParameter("removed", "USUNIETY");

		List<Kit> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	public Collection<Kit> getAllKits() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String select = "FROM Kit k WHERE k.category != :removed";
		Query query = session.createQuery(select).setParameter("removed", "USUNIETY");

		List<Kit> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	/**
	 *
	 * @param hq
	 * @return
	 */
	public Collection<Vehicle> getAvailableVehicles(Headquarter hq) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String select = "FROM Vehicle v WHERE v.headquarter.id = :hq AND NOT EXISTS (FROM Shift s WHERE s.officer.headquarter.id = :hq AND s.endDate IS NULL AND s.vehicle.id = v.id)";
		Query query = session.createQuery(select).setParameter("hq", hq.getId());

		List<Vehicle> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	/**
	 *
	 * @param hq
	 * @return
	 */
	public Collection<PatrolRegion> getAvailableRegions(Headquarter hq) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String select = "FROM PatrolRegion p WHERE p.headquarter.id = :hq AND (SELECT count(*) FROM Shift s WHERE s.officer.headquarter.id = :hq AND s.endDate IS NULL AND s.patrolRegion.id = p.id) < p.capacity";
		Query query = session.createQuery(select).setParameter("hq", hq.getId());

		List<PatrolRegion> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	public Collection<Shift> getShifts(Date startDate, Date endDate, Headquarter hq) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String select = "FROM Shift s WHERE s.officer.headquarter.id = :hq AND s.startDate BETWEEN :startD AND :endD";
		Query query = session.createQuery(select).setParameter("hq", hq.getId()).setParameter("startD", startDate).setParameter(("endD"), endDate);
		if(startDate == null && endDate == null) {
			select = "FROM Shift s WHERE s.officer.headquarter.id = :hq";
			query = session.createQuery(select).setParameter("hq", hq.getId());
		}
		else if(startDate != null && endDate == null) {
			query = session.createQuery(select).setParameter("hq", hq.getId()).setParameter("startD", new Date()).setParameter(("endD"), new Date());
		}
		else if(startDate == null && endDate != null) {
			try {
				query = session.createQuery(select).setParameter("hq", hq.getId()).setParameter("startD", new SimpleDateFormat("DD/MM/YYYY").parse("01/01/1900")).setParameter(("endD"), endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		List<Shift> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	/**
	 * 
	 * @param officer
	 */
	public Shift getActiveShift(Officer officer) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String select = "FROM Shift s WHERE s.officer.headquarter.id = :hq AND s.endDate IS NULL AND s.officer.badgeNumber = :badge";
		Query query = session.createQuery(select).setParameter("hq", officer.getHeadquarter().getId()).setParameter("badge", officer.getBadgeNumber());

		List<Shift> results = query.getResultList();

		tx.commit();
		session.close();

		return results.isEmpty() ? null : results.get(0);
	}

	/**
	 *
	 * @param hq
	 * @return
	 */
	public Collection<Shift> getActiveShifts(Headquarter hq) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String select = "FROM Shift s WHERE s.officer.headquarter.id = :hq AND s.endDate IS NULL";
		Query query = session.createQuery(select).setParameter("hq", hq.getId());

		List<Shift> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	/**
	 *  @param startDate
	 * @param endDate
	 * @param hq
	 * @return
	 */
	public Collection<Report> getReports(Date startDate, Date endDate, Headquarter hq) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String select = "FROM Report r WHERE (SELECT s.officer.headquarter.id FROM Shift s WHERE s.report.id = r.id) = :hq AND r.date BETWEEN :startD AND :endD";
		Query query = session.createQuery(select).setParameter("hq", hq.getId()).setParameter("startD", startDate).setParameter(("endD"), endDate);
		if(startDate == null && endDate == null) {
			select = "FROM Report r WHERE (SELECT s.officer.headquarter.id FROM Shift s WHERE s.report.id = r.id) = :hq";
			query = session.createQuery(select).setParameter("hq", hq.getId());
		}
		else if(startDate != null && endDate == null) {
			query = session.createQuery(select).setParameter("hq", hq.getId()).setParameter("startD", new Date()).setParameter(("endD"), new Date());
		}
		else if(startDate == null && endDate != null) {
			try {
				query = session.createQuery(select).setParameter("hq", hq.getId()).setParameter("startD", new SimpleDateFormat("DD/MM/YYYY").parse("01/01/1900")).setParameter(("endD"), endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<Report> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	/**
	 *
	 * @param hq
	 * @return
	 */
	public Collection<Officer> getOfficers(Headquarter hq) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String select = "FROM Officer o WHERE o.headquarter.id = :hq AND o.accessLevel != :removed";
		Query query = session.createQuery(select).setParameter("hq", hq.getId()).setParameter("removed", AccessLevel.Removed);

		List<Officer> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	public Collection<Officer> getAllOfficers() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String select = "FROM Officer o WHERE o.accessLevel != :removed";
		Query query = session.createQuery(select).setParameter("removed", AccessLevel.Removed);

		List<Officer> results = query.getResultList();

		tx.commit();
		session.close();

		return results;
	}

	public Collection<Rank> getRanks() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Rank> cr = cb.createQuery(Rank.class);
		Root<Rank> root = cr.from(Rank.class);

		cr.select(root);

		List<Rank> results = session.createQuery(cr).getResultList();

		tx.commit();
		session.close();

		return results;
	}

	public Collection<Headquarter> getHeadquarters() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Headquarter> cr = cb.createQuery(Headquarter.class);
		Root<Headquarter> root = cr.from(Headquarter.class);

		cr.select(root);

		List<Headquarter> results = session.createQuery(cr).getResultList();

		tx.commit();
		session.close();

		return results;
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

	/**
	 *
	 * @param tool
	 */
	public boolean saveTool(Tool tool) {
		save(tool);
		return true;
	}

	/**
	 *
	 * @param report
	 */
	public Integer saveReport(Report report) {
		return save(report);
	}

	private Integer save(Object object) {
		Session session = factory.openSession();
		Transaction tx = null;
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
	 * @param vehicle
	 */
	public boolean updateVehicleInfo(Vehicle vehicle) {
		update(vehicle);
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
		Transaction tx = null;
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