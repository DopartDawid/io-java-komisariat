package com.komisariat.BusinessObjects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;


public class Main {
    private static SessionFactory factory;

    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        List users = session.createQuery("FROM User").list();
        for(Iterator iterator = users.iterator(); iterator.hasNext();) {
            User user = (User) iterator.next();
            System.out.println("Login: " + user.getLogin());
            System.out.println("PassHash: " + user.getPassHash());
            System.out.println("Access level: " + user.getAccessLevel()+"\n");
        }
        t.commit();
    }
}
