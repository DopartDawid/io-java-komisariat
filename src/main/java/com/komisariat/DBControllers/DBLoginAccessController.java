package com.komisariat.DBControllers;

import com.komisariat.BusinessObjects.Officer;
import com.komisariat.BusinessObjects.User;
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

public class DBLoginAccessController implements IDBLoginAccessController {

	private static DBLoginAccessController instance = null;
	private final SessionFactory factory;

	private DBLoginAccessController() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.configure().applySetting("hibernate.connection.username", "log_user")
				.applySetting("hibernate.connection.password", "login")
				.build();

		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		factory = meta.getSessionFactoryBuilder().build();
	}

	public static DBLoginAccessController getInstance() {
		if(instance == null) {
			instance = new DBLoginAccessController();
		}

		return instance;
	}

	/**
	 * 
	 * @param login
	 * @param passHash
	 */
	public User getUserFromCredentials(String login, String passHash) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);
		Root<User> root = cr.from(User.class);

		Predicate[] predicates = new Predicate[2];
		predicates[0] = cb.equal(root.get("login"), login);
		predicates[1] = cb.equal(root.get("passHash"), passHash);

		cr.select(root).where(cb.and(predicates));

		List<User> results = session.createQuery(cr).getResultList();

		tx.commit();
		session.close();
		if(results.size() != 1)
			return null; //TODO - Change to exception ???

		return results.get(0);
	}

	public Officer getOfficerFromUser(User user) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Officer> cr = cb.createQuery(Officer.class);
        Root<Officer> root = cr.from(Officer.class);

        cr.select(root).where(cb.equal(root.get("User_ID"), user.getId()));

        List<Officer> results = session.createQuery(cr).getResultList();

        tx.commit();
        session.close();
        if(results.size() != 1)
            return null; //TODO - Change to exception ???

        return results.get(0);
    }
}